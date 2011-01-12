/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import java.awt.event.WindowEvent;

/**
 * Base of the Game Window.
 * Implements all the methods only available in Windows.
 * @author David
 */
public interface IGameWindow
		extends IGameContext {
	/**
	 * Invoked when the window is closing
	 * @param evt
	 */
	void onWindowClosing(WindowEvent evt);

	/**
	 * Invoked when the window is closed
	 * @param evt
	 */
	void onWindowClosed(WindowEvent evt);

	/**
	 * Invoked when the window is maximized
	 * @param evt
	 */
	void onWindowMinimized(WindowEvent evt);

	/**
	 * Invoked when the window is minimized
	 * @param evt
	 */
	void onWindowMaximized(WindowEvent evt);
}
