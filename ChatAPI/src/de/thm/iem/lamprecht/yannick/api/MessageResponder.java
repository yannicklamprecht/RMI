/**
* MessageResponder.java
* 
* Created on , 09:27:04 by @author Yannick Lamprecht
*
* RMIChat Copyright (C) 25.06.2014  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package de.thm.iem.lamprecht.yannick.api;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author yannicklamprecht
 *
 */
public interface MessageResponder extends Remote, Serializable{
	public void sendMessageToClient(String message)throws RemoteException;
	public String getName()throws RemoteException;
	public void renameIfExist(String name)throws RemoteException;
	public void exit()throws RemoteException;
}
