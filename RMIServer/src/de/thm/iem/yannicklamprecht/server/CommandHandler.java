/**
 * CommandHandler.java
 * 
 * Created on , 12:38:47 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

import de.thm.iem.yannicklamprecht.server.interfaces.ICommand;
import de.thm.iem.yannicklamprecht.server.interfaces.ICommandRegister;

/**
 * @author yannicklamprecht
 *
 */
public class CommandHandler implements ICommandRegister{
	private static CommandHandler handler;

	private Hashtable<String,ICommand> commands = new Hashtable<>();

	private CommandHandler() {

	}

	
	public void registerCommand(ICommand cmd){
		this.commands.put(cmd.getName(),cmd);
	}
	
	@Override
	public CommandHandler clone(){
		return this;
	}
	
 	public static CommandHandler instance() {
		if (handler == null)
			handler = new CommandHandler();

		return handler;
	}

	public boolean isCommandRegistered(String message) {
		return getCommand(message) != null;
	}

	public boolean isCommand(String... message) {
		if(message.length >0){
			return message[0].startsWith("/");
		}
		return false;
	}

	public ICommand getCommand(String name) {
		return commands.get(name);
	}

	public static String[] getParameter(String... message) {
		String[] messageparm = message;
		if (message.length > 0) {
			messageparm = (String[]) Arrays.asList(messageparm)
					.subList(1, messageparm.length).toArray();
		}
		return messageparm;
	}

	public static String getCommandName(String... message) {
		if (message.length == 0)
			return null;
		return message[0].replace('/', ' ').trim();
	}


	/* (non-Javadoc)
	 * @see de.thm.iem.yannicklamprecht.server.ICommandRegister#unregisterCommand(de.thm.iem.yannicklamprecht.server.ICommand)
	 */
	@Override
	public void unregisterCommand(ICommand cmd) throws RemoteException {
		if(commands.containsKey(cmd.getName())){
			commands.remove(cmd.getName());
		}
	}


	/* (non-Javadoc)
	 * @see de.thm.iem.yannicklamprecht.server.ICommandRegister#getCommandList()
	 */
	@Override
	public Set<String> getCommandList() throws RemoteException {
		// TODO Auto-generated method stub
		return this.commands.keySet();
	}


	/* (non-Javadoc)
	 * @see de.thm.iem.yannicklamprecht.server.ICommandRegister#getCommands()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Hashtable<String, ICommand> getCommands() throws RemoteException {
		return (Hashtable<String, ICommand>) this.commands.clone();
	}
	
	
}
