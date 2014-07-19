/**
* ICommandRegister.java
* 
* Created on , 18:41:51 by @author Yannick Lamprecht
*
* RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package de.thm.iem.yannicklamprecht.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author yannicklamprecht
 *
 */
public interface ICommandRegister extends Remote{
	public void registerCommand(ICommand cmd)throws RemoteException;
	public void unregisterCommand(ICommand cmd)throws RemoteException;
	public Set<String> getCommandList()throws RemoteException;
	public Hashtable<String, ICommand> getCommands()throws RemoteException;
}
