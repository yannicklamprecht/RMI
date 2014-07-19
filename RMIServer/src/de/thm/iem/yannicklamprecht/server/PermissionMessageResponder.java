/**
 * PermissionMessageResponder.java
 * 
 * Created on , 22:08:01 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 27.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server;

import java.rmi.RemoteException;
import java.util.Vector;

import de.thm.iem.lamprecht.yannick.api.MessageResponder;
import de.thm.iem.yannicklamprecht.server.lib.Arrays;

/**
 * @author yannicklamprecht
 *
 */
public class PermissionMessageResponder {

	private MessageResponder user;
	private ChatServer server;
	private String displayName;
	

	private Vector<String> permissions;

	public PermissionMessageResponder(ChatServer server, MessageResponder user) {
		this.user = user;
		this.server = server;
		try {
			this.displayName = this.user.getName();
		} catch (RemoteException e) {
		}
		this.permissions = new Vector<>();
	}

	public void sendMessage(String message) throws RemoteException{
		this.user.sendMessageToClient(message);
	}
	
	public MessageResponder getMessageResponder() {
		return user;
	}

	public boolean addPermission(String permission) {

		if (!permissions.contains(permission)) {
			permissions.add(permission);
			return true;
		}

		return false;
	}

	public boolean removePermission(String permission) {
		if (permissions.contains(permission)) {
			permissions.remove(permission);
			return true;
		}

		return false;
	}

	public boolean hasPermission(String permission) {
		return permissions.contains(permission);
	}

	public ChatServer getServer() {
		return server;
	}

	public void disconnect(String... message) throws RemoteException {
		if (message == null) {
			String kickMessage = "Du wurdest von einem Moderator gekickt!";
			this.user.sendMessageToClient(getServer().getServerLogger()
					.getKickFormat(kickMessage));
			this.server.getServerLogger().kick(kickMessage);
			;
		} else {
			String message2 = new Arrays(message).toString();
			this.user.sendMessageToClient(getServer().getServerLogger()
					.getKickFormat(message2));
			this.server.getServerLogger().kick(message2);
		}

		this.server.unregister(this.getMessageResponder());
		this.user.exit();
	}

	public String getChatName() {
		return this.displayName;
	}
	public void setChatName(String chatName){
		this.displayName=chatName;
	}
}
