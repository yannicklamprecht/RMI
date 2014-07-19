/**
 * Stop.java
 * 
 * Created on , 20:28:08 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.commands;

import java.rmi.RemoteException;

import de.thm.iem.yannicklamprecht.server.PermissionMessageResponder;
import de.thm.iem.yannicklamprecht.server.interfaces.ICommand;

/**
 * @author yannicklamprecht
 *
 */
public class Stop implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecht.server.ICommand#onCommand(de.thm.iem.lamprecht
	 * .yannick.api.MessageResponder, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(PermissionMessageResponder sender, String command,
			String[] args) {

		try {
			sender.getServer().quit();
		} catch (RemoteException e) {
			return false;
		}

		
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.ICommand#getName()
	 */
	@Override
	public String getName() {
		return "stop";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.ICommand#getUsage()
	 */
	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "/" + getName();
	}

}
