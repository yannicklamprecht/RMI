/**
 * List.java
 * 
 * Created on , 13:31:27 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.commands;

import java.rmi.RemoteException;
import java.util.Hashtable;

import de.thm.iem.yannicklamprecht.server.PermissionMessageResponder;
import de.thm.iem.yannicklamprecht.server.interfaces.ICommand;
import de.thm.iem.yannicklamprecht.server.lib.Lib;

/**
 * @author yannicklamprecht
 *
 */
public class List implements ICommand {

	private Hashtable<String, PermissionMessageResponder> users;

	public List(Hashtable<String, PermissionMessageResponder> users) {
		this.users = users;
	}

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
			
			
			sender.getMessageResponder().sendMessageToClient("Online(" + users.size() + "): "
					+ Lib.toString(users.keySet()));
		} catch (RemoteException e) {
			e.printStackTrace();
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

		return "list";
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
