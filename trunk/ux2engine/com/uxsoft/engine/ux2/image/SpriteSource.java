/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * 
 * @author David
 */
public interface SpriteSource {
	/**
	 * Returns the input stream where the system can read from.
	 * @return
	 */
	public InputStream getSource();

	/**
	 * Returns a buffered input stream where the system can read from.
	 * @return
	 */
	public BufferedInputStream getBufferedSource();
}
