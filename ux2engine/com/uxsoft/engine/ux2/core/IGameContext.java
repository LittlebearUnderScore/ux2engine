/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.math.Size2D;
import com.uxsoft.engine.ux2.scene.IScene;
import java.awt.BufferCapabilities;

/**
 * Base of the Game Context.
 * @author David
 */
public interface IGameContext
		extends BufferedDrawSurface {
	/**
	 * Invoked when Rendering should happen again
	 */
	void onRender();

	/**
	 * Invoked when the game should update its state
	 */
	void onUpdate();

	/**
	 * Returns the Scene of this context or <tt>null</tt> if there isn't one
	 */
	IScene getScene();

	/**
	 * Returns whether this context is active
	 * @return Active, or not
	 */
	boolean gameActive();

	/**
	 * Initializes the game.
	 * @param screensize Screen size
	 * @param numBuffers Number of buffers
	 * @param caps Buffer capabilities
	 * @return true if okay, false otherwise
	 */
	boolean initialize(Size2D screensize, int numBuffers, BufferCapabilities caps);

	/**
	 * Returns whether Vertical Synchronizing is currently enabled.
	 * @return true if vsync is enabled; false otherwise.
	 */
	public boolean isVSyncEnabled();

	/**
	 * Enables or disables vertical synchronizing on this game.
	 * @param enabled whether vsync should be enabled
	 */
	public void setVSyncEnabled(boolean enabled);
}
