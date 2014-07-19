/**
 * ILogger.java
 * 
 * Created on , 22:52:31 by @author Yannick Lamprecht
 *
 * ChatAPI Copyright (C) 27.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.lamprecht.yannick.api.logger;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author yannicklamprecht
 *
 */
public interface ILogger extends Remote {

	public String getLogFormat(String message) throws RemoteException;

	public String getWarnFormat(String message) throws RemoteException;

	public String getInformFormat(String message) throws RemoteException;

	public String getErrorFormat(String message) throws RemoteException;

	public String getCommandFormat(String message) throws RemoteException;

	public String getKickFormat(String message) throws RemoteException;

	public String getChatFormat(String message) throws RemoteException;

	public void log(String message) throws RemoteException;

	public void warn(String message) throws RemoteException;

	public void inform(String message) throws RemoteException;

	public void error(String message) throws RemoteException;

	public void command(String message) throws RemoteException;

	public void kick(String message) throws RemoteException;
	
	public void chat(String message) throws RemoteException;
}
