/**
 * ServerResponder.java
 * 
 * Created on , 09:25:01 by @author Yannick Lamprecht
 *
 * RMIChat Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.lamprecht.yannick.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;

import de.thm.iem.lamprecht.yannick.api.logger.ILogger;

/**
 * @author yannicklamprecht
 *
 */
public interface ServerResponder extends Remote {

	public void register(MessageResponder receiver) throws RemoteException;

	public void unregister(MessageResponder receiver) throws RemoteException;

	public void sendMessage(MessageResponder sender, String message)
			throws RemoteException;

	public void quit() throws RemoteException;

	public Hashtable<String, MessageResponder> getUsers()
			throws RemoteException;

	public boolean isOnline(MessageResponder receiver) throws RemoteException;

	public boolean isOnline(String receiver) throws RemoteException;

	public Collection<MessageResponder> getUser() throws RemoteException;

	public ILogger getServerLogger() throws RemoteException;

}
