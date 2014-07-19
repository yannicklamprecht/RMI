/**
 * AddPermission.java
 * 
 * Created on , 22:34:52 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 27.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.commands;

import java.rmi.RemoteException;

import de.thm.iem.yannicklamprecht.server.ChatServer;
import de.thm.iem.yannicklamprecht.server.PermissionMessageResponder;
import de.thm.iem.yannicklamprecht.server.interfaces.ICommand;

/**
 * @author yannicklamprecht
 *
 */
public class AddPermission implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.yannicklamprecht.server.interfaces.ICommand#onCommand(de.thm
	 * .iem.yannicklamprecht.server.PermissionMessageResponder,
	 * java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(PermissionMessageResponder sender, String command,
			String[] args) throws RemoteException {

		if (args.length == 2) {
			if (ChatServer.getInstance().isOnline(args[0])) {

				PermissionMessageResponder res = sender.getServer().getPermissionUser().get(
						args[0]);

				if (res instanceof PermissionMessageResponder) {
					res.addPermission(args[1]);

					sender.getMessageResponder().sendMessageToClient(
							res.hasPermission(args[1]) + "");
				}

			} else {
				sender.getMessageResponder().sendMessageToClient(
						sender.getServer()
								.getServerLogger()
								.getCommandFormat(
										"Player " + args[0] + " isn't online"));
			}

		} else {
			sender.getMessageResponder().sendMessageToClient(this.getUsage());
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.interfaces.ICommand#getName()
	 */
	@Override
	public String getName() {
		return "manuaddp";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.yannicklamprecht.server.interfaces.ICommand#getUsage()
	 */
	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "/manuaddp <user> <permission>";
	}

}
