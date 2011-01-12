/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

/**
 * A Sound Listener can listen for events invoked on a sound player.
 * @author David
 */
public interface SoundListener {
	/**
	 * Invoked when the sound is started
	 */
	void started();

	/**
	 * Invoked when the sound is stopped
	 */
	void stopped();

	/**
	 * Invoked when the sound is updated.
	 * @param data Data read
	 * @param off Offset of <tt>data</tt> where the data was read
	 * @param len length of data read
	 */
	void update(byte[] data, int off, int len);

	/**
	 * Invoked when the position of the sound is updated.
	 * @param oldPosition Old position
	 * @param newPosition New position
	 * @param msTicks Milliseconds ticked
	 */
	void positionUpdate(long oldPosition, long newPosition, long msTicks);
}
