/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

import com.uxsoft.engine.ux2.core.GameWindow;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * Utilities for changing the Display Mode and setting Full Screen windows.
 * @author David
 */
public class DisplayModeUtil {
	/**
	 * Last known display mode
	 */
	private static DisplayMode lastDisplayMode;

	/**
	 * Attempts to set a window as the full screen window on the default graphics device.
	 * @param window
	 * @return
	 */
	public static boolean setFullScreen(GameWindow window) {
		final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return setFullScreen(window, graphicsDevice);
	}

	/**
	 * Sets the specified window as the exclusive Full Screen window on the specified graphics device.
	 * @param window
	 * @param graphicsDevice
	 * @return
	 */
	public static boolean setFullScreen(GameWindow window, final GraphicsDevice graphicsDevice) {
		if (graphicsDevice.isFullScreenSupported()) {
			graphicsDevice.setFullScreenWindow(window);
			return true;
		}
		
		return false;
	}

	/**
	 * Attempts to restore the default graphics device.
	 * @return
	 */
	public static boolean restoreScreen() {
		final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (graphicsDevice.isFullScreenSupported()) {
			graphicsDevice.setFullScreenWindow(null);
			return true;
		}

		return false;
	}

	/**
	 * Attempts to change the current display mode to the new one specified.
	 * @param newDisplayMode New display mode
	 * @return true if successful, false otherwise
	 */
	public static boolean changeDisplayMode(final DisplayMode newDisplayMode) {
		final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		if (device.isDisplayChangeSupported()) {
			DisplayMode lDM = device.getDisplayMode();
			try {
				device.setDisplayMode(newDisplayMode);
			} catch (IllegalArgumentException err) {
				return false;
			}
			lastDisplayMode = lDM;
			return true;
		}

		return false;
	}

	/**
	 * Attempts to restore the display mode.
	 * A call to <tt>changeDisplayMode</tt> should be invoked before invoking this method.
	 * @return true if successful, false otherwise
	 */
	public static boolean restoreDisplayMode() {
		if (lastDisplayMode == null) {
			return false;
		}

		final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		if (device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(lastDisplayMode);
			} catch (IllegalArgumentException err) {
				return false;
			}
			return true;
		}

		return false;
	}

	/**
	 * Returns whether full screen mode is supported on the system.
	 * @return
	 */
	public static boolean fullScreenSupported() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isFullScreenSupported();
	}

	/**
	 * Returns whether changing the display mode is supported on the system.
	 * @return
	 */
	public static boolean displayChangeSupported() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isDisplayChangeSupported();
	}
}
