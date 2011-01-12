/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

/**
 * Timer utility, can count milliseconds past since start() was called.
 * @author David
 */
public class Timer {
    private long tick;

    public Timer() {
        tick = 0;
    }

	/**
	 * "Starts" the timer, sets the tickcount to the system time.
	 */
    public void start() {
        tick = Sys.systemTime();
    }

	/**
	 * Gets the number of ticks that have passed since {@link Timer#start() } was called.
	 * @return
	 */
    public long getTicks() {
        return Sys.systemTime() - tick;
    }

	/**
	 * Returns whether the amount of time indicated by <tt>span</tt> has elapsed since {@link Timer#start() }
	 * was last called.
	 * @param span timespan
	 * @return true if elapsed; false otherwise.
	 */
	public boolean timeElapsed(long span) {
		return getTicks() >= span;
	}
}
