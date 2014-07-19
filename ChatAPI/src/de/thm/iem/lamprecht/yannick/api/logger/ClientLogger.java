/**
 * ClientLogger.java
 * 
 * Created on , 14:46:35 by @author Yannick Lamprecht
 *
 * ChatAPI Copyright (C) 28.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.lamprecht.yannick.api.logger;

import java.rmi.RemoteException;

import de.thm.iem.lamprecht.yannick.api.MessageResponder;

/**
 * @author yannicklamprecht
 *
 */
public class ClientLogger extends Logger {

	private MessageResponder sender;

	public ClientLogger(MessageResponder sender) {
		this.sender = sender;
	}

	@Override
	public void error(String message) throws RemoteException {
		sender.sendMessageToClient(getErrorFormat(message));
	}

	@Override
	public void inform(String message) throws RemoteException {
		sender.sendMessageToClient(getInformFormat(message));
	}

	@Override
	public void log(String message) throws RemoteException {
		sender.sendMessageToClient(getLogFormat(message));
	}

	@Override
	public void warn(String message) throws RemoteException {
		sender.sendMessageToClient(getWarnFormat(message));
	}

	@Override
	public void command(String message) throws RemoteException {
		sender.sendMessageToClient(getCommandFormat(message));
	}
	
	@Override
	public String getChatFormat(String message) throws RemoteException{
		return "[".concat(sender.getName()).concat("] ").concat(message);
	}
	
	@Override
	public void chat(String message) throws RemoteException{
		sender.sendMessageToClient(message);
	}
}
