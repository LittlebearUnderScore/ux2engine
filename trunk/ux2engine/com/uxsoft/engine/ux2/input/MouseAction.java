/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input;

import java.awt.event.MouseEvent;

/**
 * Interface to listen to mouse events.
 * @author David
 */
public interface MouseAction {
	/**
	 * Invoked when the mouse is either pressed, released or the mouse wheel is scrolled.
	 * @param evt Event information
	 */
	void onMouseAction(MouseEvent evt);
}
