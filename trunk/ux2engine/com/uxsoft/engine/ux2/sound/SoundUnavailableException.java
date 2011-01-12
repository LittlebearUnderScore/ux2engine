/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

/**
 * Thrown when sound is unavailable on the host machine.
 * @author David
 */
public class SoundUnavailableException
		extends IDGSoundException {
	public SoundUnavailableException(Throwable cause) {
		super(cause);
	}

	public SoundUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoundUnavailableException(String message) {
		super(message);
	}

	public SoundUnavailableException() {
	}
}
