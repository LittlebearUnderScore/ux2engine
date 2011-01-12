/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

import java.io.PrintStream;

/**
 *
 * @author David
 */
public class ThreadUtil {
	public static final void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
	}

	public static final void debugCurrentThread() {
		debugCurrentThread(System.err);
	}

	public static final void debugCurrentThread(PrintStream ps) {
		new Exception("Stack Trace").printStackTrace(ps);
	}
}
