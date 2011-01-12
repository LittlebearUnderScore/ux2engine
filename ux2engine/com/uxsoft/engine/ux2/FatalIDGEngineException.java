/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2;

/**
 * Thrown when a serious Engine exception occurs.
 * @author David
 */
public class FatalIDGEngineException
		extends Exception {
	public FatalIDGEngineException(Throwable cause) {
		super(cause);
	}

	public FatalIDGEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	public FatalIDGEngineException(String message) {
		super(message);
	}

	public FatalIDGEngineException() {
	}
}
