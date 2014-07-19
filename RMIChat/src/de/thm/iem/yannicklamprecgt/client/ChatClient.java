/**
 * ChatClient.java
 * 
 * Created on , 09:47:49 by @author Yannick Lamprecht
 *
 * RMIChat Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecgt.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.thm.iem.lamprecht.yannick.api.MessageResponder;
import de.thm.iem.lamprecht.yannick.api.ServerResponder;

/**
 * @author yannicklamprecht
 *
 */
public class ChatClient extends UnicastRemoteObject implements MessageResponder {

	/**
	*
	**/
	private static final long serialVersionUID = 1L;
	private String name;
	private String displayName;
	private ServerResponder server;
	private String serverIP;

	/**
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */

	protected ChatClient(String name, String serverIP) throws RemoteException,
			MalformedURLException, NotBoundException {
		super();
		this.name = name;
		this.serverIP = serverIP;
		this.displayName=name;

		server = (ServerResponder) Naming.lookup("rmi://" + this.serverIP
				+ "/ChatServer");

		server.register(this);

		allowInput();

		server.unregister(this);

		exit();

	}

	public void allowInput() {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String message;
		try {
			message = bf.readLine();

			while (!message.equalsIgnoreCase("quit") && server.isOnline(this)) {

				server.sendMessage(this, message);
				message = bf.readLine();
			}
		} catch (IOException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecgt.client.MessageResponder#sendMessageToClient
	 * (java.lang.String)
	 */
	@Override
	public void sendMessageToClient(String message) throws RemoteException {
		System.out.println(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecgt.client.MessageResponder#getID()
	 */
	@Override
	public String getName() throws RemoteException {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecgt.client.MessageResponder#renameIfExist(java
	 * .lang.String)
	 */
	@Override
	public void renameIfExist(String name) throws RemoteException {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.MessageResponder#exit()
	 */
	@Override
	public void exit() throws RemoteException {
		if(!server.isOnline(this)){
			System.exit(0);
		}else{
			server.getServerLogger().error("Chatclient can't be closed. It has to be unregistered first!");
		}
		
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.MessageResponder#getDisplayName()
	 */
	@Override
	public String getDisplayName() throws RemoteException {
		return this.displayName;
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.MessageResponder#setDisplayName()
	 */
	@Override
	public void setDisplayName(String displayName) throws RemoteException {
		this.displayName= displayName;
	}

	
	
	public static void main(String[] args) {

		if (args.length == 2) {
			String serverIP = args[0];
			String userName = args[1];
			try {
				new ChatClient(userName, serverIP);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (args.length == 1) {
			String userName = args[0];
			String serverIP = "127.0.0.1";
			try {
				new ChatClient(userName, serverIP);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out
					.println("java -jar RMIChat.jar [serverip] <username> or java -jar RMIChat.jar <username>");
		}

	}
	
	
	
	
}
