/**
* ICommand.java
* 
* Created on , 12:38:56 by @author Yannick Lamprecht
*
* RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package de.thm.iem.yannicklamprecht.server.interfaces;

import java.rmi.RemoteException;

import de.thm.iem.yannicklamprecht.server.PermissionMessageResponder;


/**
 * @author yannicklamprecht
 *
 */
public interface ICommand {
	
	public boolean onCommand(PermissionMessageResponder sender, String command, String [] args)throws RemoteException;
	public String getName();
	public String getUsage();

}
