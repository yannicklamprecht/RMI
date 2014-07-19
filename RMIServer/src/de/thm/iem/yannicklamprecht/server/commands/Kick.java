/**
 * Kick.java
 * 
 * Created on , 18:38:51 by @author Yannick Lamprecht
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
import de.thm.iem.yannicklamprecht.server.lib.Arrays;

/**
 * @author yannicklamprecht
 *
 */
public class Kick implements ICommand {

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

			if (!sender.hasPermission("command.kick")) {
				sender.getMessageResponder()
						.sendMessageToClient(
								"You don't have the permission to perform this command");
				return false;
			}

			if (args.length == 1) {

				if (sender.getServer().getPermissionUser().containsKey(args[0])) {

					sender.getServer().getPermissionUser().get(args[0])
							.disconnect();
				}

			} else if (args.length >= 2) {
				if (sender.getServer().getPermissionUser().containsKey(args[0])) {
					sender.getServer().getPermissionUser().get(args[0])
							.disconnect(new Arrays(args).subArray(1, args.length).toArray());
				}

			} else {
				sender.getMessageResponder().sendMessageToClient(getUsage());
			}

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
		return "kick";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.ICommand#getUsage()
	 */
	@Override
	public String getUsage() {
		return "/kick <username> [messsage]";
	}

}
