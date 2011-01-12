/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Interface to listen for input events.
 * @author David
 */
public interface InputHandler {
	void onKeyPressed(KeyEvent evt);

	void onKeyReleased(KeyEvent evt);

	void onMousePressed(MouseEvent evt);

	void onMouseReleased(MouseEvent evt);

	void onMouseWheelMoved(MouseWheelEvent evt);

	void onMouseMoved(MouseEvent evt);

	void onMouseDragged(MouseEvent evt);
}
