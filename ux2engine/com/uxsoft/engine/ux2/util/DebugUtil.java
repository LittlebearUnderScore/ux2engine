/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

/**
 *
 * @author David
 */
public class DebugUtil {
	private static boolean enabled = true;

	private DebugUtil() {}

	public static void enable() {
		enabled = true;
	}

	public static void disable() {
		enabled = false;
	}

	public static void toggle() {
		enabled = !enabled;
	}

	private static void appendExceptionToBuffer(StringBuilder log, Throwable ex) {
		StackTraceElement[] trace = ex.getStackTrace();
		log.append("\n");
		log.append(ex.getClass().getName());
		log.append(": ");
		log.append(ex.getMessage());
		for (StackTraceElement element : trace) {
			log.append("\n\tat ");
			log.append(element.getClassName());
			log.append(".");
			log.append(element.getMethodName());
			log.append("(");
			log.append(element.getFileName());
			log.append(":");
			log.append(element.getLineNumber());
			log.append(")");
			if (element.isNativeMethod()) {
				log.append(" (Native Method)");
			}
		}
	}

	public static void logTrace(Throwable t) {
		logTrace(null, t);
	}

	public static void logTrace(String desc, Throwable t) {
		if (!enabled) {
			return;
		}
		if (desc != null) {
			System.err.println(desc);
		}
		StringBuilder buf = new StringBuilder();
		appendExceptionToBuffer(buf, t);
		System.err.println(buf.toString());
	}
}
