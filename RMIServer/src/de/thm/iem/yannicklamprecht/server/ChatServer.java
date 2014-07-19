/**
 * ChatServer.java
 * 
 * Created on , 09:28:20 by @author Yannick Lamprecht
 *
 * RMIChat Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

import de.thm.iem.lamprecht.yannick.api.MessageResponder;
import de.thm.iem.lamprecht.yannick.api.ServerResponder;
import de.thm.iem.lamprecht.yannick.api.logger.ILogger;
import de.thm.iem.lamprecht.yannick.api.logger.Logger;
import de.thm.iem.yannicklamprecht.server.commands.AddPermission;
import de.thm.iem.yannicklamprecht.server.commands.Help;
import de.thm.iem.yannicklamprecht.server.commands.Kick;
import de.thm.iem.yannicklamprecht.server.commands.List;
import de.thm.iem.yannicklamprecht.server.commands.Stop;
import de.thm.iem.yannicklamprecht.server.lib.Lib;

/**
 * @author yannicklamprecht
 *
 */
public class ChatServer extends UnicastRemoteObject implements ServerResponder {

	/**
	*
	**/
	private static final long serialVersionUID = 1L;

	private static ServerResponder server;

	private Hashtable<String, PermissionMessageResponder> nameReceiver = new Hashtable<>();

	private String serverName;

	private ILogger logger = new Logger();
	
	public static void main(String[] args) {
		try {
			server = new ChatServer("ChatServer");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("ChatServer Startet");

	}

	public static ServerResponder getInstance() {
		return server;
	}

	/**
	 * @throws RemoteException
	 */
	protected ChatServer(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;

		try {
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			Naming.bind("ChatServer", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

		CommandHandler.instance().registerCommand(new List(this.nameReceiver));
		CommandHandler.instance().registerCommand(new Kick());
		CommandHandler.instance().registerCommand(new Stop());
		CommandHandler.instance().registerCommand(new Help());
		CommandHandler.instance().registerCommand(new AddPermission());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecht.server.ServerResponder#register(de.thm.iem
	 * .yannicklamprecgt.client.MessageResponder)
	 */
	@Override
	public void register(MessageResponder receiver) throws RemoteException {

		int i = 0;
		while (containsName(receiver.getName())) {
			receiver.renameIfExist(receiver.getName() + i);
			i++;
		}

		this.nameReceiver.put(receiver.getName(),
				new PermissionMessageResponder(this, receiver));

		receiver.sendMessageToClient("Connected \nWelcome to THM-IRC-Network - "
				+ this.serverName);
		sendMessage(receiver, "Connected");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecht.server.ServerResponder#sendMessage(java.lang
	 * .String)
	 */
	@Override
	public void sendMessage(MessageResponder sender, String message)
			throws RemoteException {

		if (!server.isOnline(sender)) {
			sender.sendMessageToClient("You was kicked or disconnected anyway. Type <quit> to exit the programm");
			return;
		}

		if (!message.startsWith("/")) {

			logger.log(sender.getName() + ": " + message);

			for (String receiver : nameReceiver.keySet()) {
				nameReceiver
						.get(receiver)
						.getMessageResponder()
						.sendMessageToClient(
								"[" + sender.getName() + "] " + message);
			}
		} else {

			System.out.println("[Command] " + sender.getName()
					+ " issued command: " + message);
			String[] m = message.split(" ");

			if (m.length > 0) {
				String command = m[0].replace('/', ' ').trim();
				if (CommandHandler.instance().isCommandRegistered(command)) {

					CommandHandler
							.instance()
							.getCommand(command)
							.onCommand(
									this.nameReceiver.get(sender.getName()),
									command,
									new de.thm.iem.yannicklamprecht.server.lib.Arrays(
											m).subArray(1, m.length).toArray());
				} else {
					sender.sendMessageToClient("Command not found");
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecht.server.ServerResponder#unregister(de.thm.
	 * iem.yannicklamprecgt.client.MessageResponder)
	 */
	@Override
	public void unregister(MessageResponder receiver) throws RemoteException {
		if (nameReceiver.containsKey(receiver.getName())) {
			sendMessage(receiver, "Disconnected");
			nameReceiver.remove(receiver.getName());
		}
	}

	public boolean containsName(String name) {
		return nameReceiver.containsKey(name);
	}

	public boolean containsValue(MessageResponder receiver) {
		return nameReceiver.containsValue(receiver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ServerResponder#quit()
	 */
	@Override
	public void quit() throws RemoteException {
		String message = "Server CLosed";
		for (PermissionMessageResponder connectedUser : nameReceiver.values()) {
			connectedUser.disconnect(message);
		}
		System.exit(0);
	}

	public String getUserListAsNames() {
		return Lib.toString(nameReceiver.keySet());
	}

	public Hashtable<String, MessageResponder> getUsers()
			throws RemoteException {

		Hashtable<String, MessageResponder> tempRes = new Hashtable<>();

		for (String name : this.nameReceiver.keySet()) {
			tempRes.put(name, this.nameReceiver.get(name).getMessageResponder());
		}
		return tempRes;
	}

	public Hashtable<String, PermissionMessageResponder> getPermissionUser() {
		return this.nameReceiver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ServerResponder#getUser()
	 */
	@Override
	public Collection<MessageResponder> getUser() throws RemoteException {

		Collection<MessageResponder> resp = new HashSet<MessageResponder>();

		for (PermissionMessageResponder perm : nameReceiver.values()) {
			resp.add(perm.getMessageResponder());
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.ServerResponder#isOnline(de.thm.iem.
	 * lamprecht.yannick.api.MessageResponder)
	 */
	@Override
	public boolean isOnline(MessageResponder receiver) throws RemoteException {
		return isOnline(receiver.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.ServerResponder#isOnline(java.lang.String
	 * )
	 */
	@Override
	public boolean isOnline(String receiver) throws RemoteException {
		return nameReceiver.containsKey(receiver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ServerResponder#getServerLogger()
	 */
	@Override
	public ILogger getServerLogger() throws RemoteException {
		return logger;
	}

}
