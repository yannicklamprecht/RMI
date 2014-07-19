/**
 * Help.java
 * 
 * Created on , 21:50:16 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.commands;

import java.rmi.RemoteException;

import de.thm.iem.yannicklamprecht.server.CommandHandler;
import de.thm.iem.yannicklamprecht.server.PermissionMessageResponder;
import de.thm.iem.yannicklamprecht.server.interfaces.ICommand;

/**
 * @author yannicklamprecht
 *
 */
public class Help implements ICommand {

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
		
		if(!sender.hasPermission("command.help")){
			return false;
		}

		try {

			sender.getMessageResponder().sendMessageToClient("-----Help-----");

			for (String cmd : CommandHandler.instance().getCommandList()) {
				sender.getMessageResponder().sendMessageToClient(
						CommandHandler.instance().getCommands().get(cmd)
								.getUsage());
			}

		} catch (RemoteException e1) {
			e1.printStackTrace();
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
		return "help";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.ICommand#getUsage()
	 */
	@Override
	public String getUsage() {
		return "/" + getName();
	}

}
