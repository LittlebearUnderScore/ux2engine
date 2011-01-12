/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input;

import java.awt.event.KeyEvent;

/**
 * Interface to listen for key events.
 * @author David
 */
public interface KeyAction {
	/**
	 * Invoked when a key is pressed or released.
	 * @param evt Event information
	 */
	void onKeyAction(KeyEvent evt);
}
