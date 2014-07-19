/**
 * Arrays.java
 * 
 * Created on , 23:25:51 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 27.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.lib;

/**
 * @author yannicklamprecht
 *
 */
public class Arrays {
	private String[] array;

	public Arrays(String... array) {
		this.array = array;
	}

	public Arrays subArray(int first, int last) {

		String[] rt = new String[last - first];
		for (int i = first; i < last; i++) {
			rt[i - first] = array[i];
		}
		return new Arrays(rt);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (String s : array) {
			sb.append(s);
			sb.append("");
		}
		return sb.toString();
	}

	public String[] toArray() {
		return this.array;
	}
}
