/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import com.uxsoft.engine.ux2.IDGEngineException;

/**
 *
 * @author David
 */
public class IDGSoundException
		extends IDGEngineException {
	public IDGSoundException() {
	}

	public IDGSoundException(String message) {
		super(message);
	}

	public IDGSoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDGSoundException(Throwable cause) {
		super(cause);
	}
}
