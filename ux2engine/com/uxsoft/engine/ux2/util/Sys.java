/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

/**
 * System utilities.
 * @author David
 */
public class Sys {
	private static final int MILLION = 1000000;
	private static Ticker ticker;

	static {
		setTicker(new NanoTicker());
	}

	public static synchronized void setTicker(Ticker ticker) {
		Sys.ticker = ticker;
	}

	/**
	 * Returns the System time in Milliseconds.
	 * @return System time in milliseconds
	 */
	public static final long systemTime() {
		return ticker.milliTime();
	}

	/**
	 * A ticker can calculate time.
	 */
	public static interface Ticker {
		long milliTime();

		long nanoTime();
	}

	/**
	 * Nanosecond precision ticker.
	 */
	public static final class NanoTicker
			implements Ticker {

		public long nanoTime() {
			return System.nanoTime();
		}

		public long milliTime() {
			return System.nanoTime()/MILLION;
		}

	}
}
