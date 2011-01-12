/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2;

/**
 * General IDGEngine Exception
 * @author David
 */
public class IDGEngineException
		extends RuntimeException {

	public IDGEngineException(Throwable cause) {
		super(cause);
	}

	public IDGEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDGEngineException(String message) {
		super(message);
	}

	public IDGEngineException() {
	}

}
