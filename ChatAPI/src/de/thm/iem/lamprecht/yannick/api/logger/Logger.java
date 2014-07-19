/**
 * Logger.java
 * 
 * Created on , 14:44:26 by @author Yannick Lamprecht
 *
 * ChatAPI Copyright (C) 28.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.lamprecht.yannick.api.logger;

import java.rmi.RemoteException;

/**
 * @author yannicklamprecht
 *
 */
public class Logger implements ILogger {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ILogger#error(java.lang.String)
	 */
	@Override
	public void error(String message) throws RemoteException {
		System.err.println(getErrorFormat(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ILogger#infom(java.lang.String)
	 */
	@Override
	public void inform(String message) throws RemoteException {
		System.out.println(getInformFormat(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ILogger#log(java.lang.String)
	 */
	@Override
	public void log(String message) throws RemoteException {
		System.out.println(getLogFormat(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.iem.lamprecht.yannick.api.ILogger#warn(java.lang.String)
	 */
	@Override
	public void warn(String message) throws RemoteException {
		System.err.println(getWarnFormat(message));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.logger.ILogger#getLogFormat(java.lang
	 * .String)
	 */
	@Override
	public String getLogFormat(String message) throws RemoteException {
		return "[LOG] " + message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.logger.ILogger#getWarnFormat(java.lang
	 * .String)
	 */
	@Override
	public String getWarnFormat(String message) throws RemoteException {
		return "[WARNING] " + message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.logger.ILogger#getInformFormat(java.
	 * lang.String)
	 */
	@Override
	public String getInformFormat(String message) throws RemoteException {
		return "[INFO] " + message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.thm.iem.lamprecht.yannick.api.logger.ILogger#getErrorFormat(java.lang
	 * .String)
	 */
	@Override
	public String getErrorFormat(String message) throws RemoteException {
		return "[ERROR] " + message;
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#getCommandFormat(java.lang.String)
	 */
	@Override
	public String getCommandFormat(String message) throws RemoteException {
		return "[COMMAND] "+message;
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#command(java.lang.String)
	 */
	@Override
	public void command(String message) throws RemoteException {
		System.out.println(getCommandFormat(message));
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#getKickFormat(java.lang.String)
	 */
	@Override
	public String getKickFormat(String message) throws RemoteException {
		// TODO Auto-generated method stub
		return "[KICK] "+message;
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#kick(java.lang.String)
	 */
	@Override
	public void kick(String message) throws RemoteException {
		System.out.println(getKickFormat(message));
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#getChatFormat(java.lang.String)
	 */
	@Override
	public String getChatFormat(String message) throws RemoteException {
		return "[Server]".concat(message);
	}

	/* (non-Javadoc)
	 * @see de.thm.iem.lamprecht.yannick.api.logger.ILogger#chat(java.lang.String)
	 */
	@Override
	public void chat(String message) throws RemoteException {
		System.out.println(getChatFormat(message));
	}

}
