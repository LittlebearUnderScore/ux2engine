/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import com.uxsoft.engine.ux2.IDGEngineException;

/**
 * This exception is thrown when there is an error in the Image package.
 * @author David
 */
public class ImageException
	extends IDGEngineException {

	public ImageException() {
	}

	public ImageException(String message) {
		super(message);
	}

	public ImageException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageException(Throwable cause) {
		super(cause);
	}
}
