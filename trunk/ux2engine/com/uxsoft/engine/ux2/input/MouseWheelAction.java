/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input;

import java.awt.event.MouseWheelEvent;

/**
 * Interface to listen for Mouse wheel actions.
 * @author David
 */
public interface MouseWheelAction {
	/**
	 * Invoked when the Mouse wheel is moved in some way.
	 * @param evt Object describing the event
	 */
	void onMouseWheelAction(MouseWheelEvent evt);
}
