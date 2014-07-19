/**
 * Lib.java
 * 
 * Created on , 23:15:48 by @author Yannick Lamprecht
 *
 * RMIServer Copyright (C) 25.06.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package de.thm.iem.yannicklamprecht.server.lib;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author yannicklamprecht
 *
 */
public class Lib {

	public static String toString(Set<String> strings) {

		StringBuffer buffer = new StringBuffer();

		strings.parallelStream().forEachOrdered(s -> {
			buffer.append(s).append(",");
			if (buffer.length() % 10 == 0) {
				buffer.append("\n");
			}
		});

		for (String s : strings) {

			buffer.append(s);
			buffer.append(",");
			if (buffer.length() % 10 == 0) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	public static List<String> toString(String... strings) {
		return Arrays.asList(strings);
	}
}
