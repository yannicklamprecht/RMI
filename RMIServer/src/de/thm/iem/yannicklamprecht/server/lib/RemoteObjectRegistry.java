/**
 * RemoteObjectRegistry.java
 * 
 * Created on , 10:16:24 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 27.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author yannicklamprecht
 *
 */
public class RemoteObjectRegistry<T extends Remote, V extends Remote> {

	@SuppressWarnings("unchecked")
	public boolean registerRemoteObjects(T robject, Class<V> rinterface) {

		try {
			UnicastRemoteObject.unexportObject(robject, true);
			V perm = (V) UnicastRemoteObject.exportObject(robject, 0);
			RemoteServer.setLog(System.out);
			LocateRegistry.getRegistry().rebind(
					rinterface.getClass().getName(), perm);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		return true;

	}

}
