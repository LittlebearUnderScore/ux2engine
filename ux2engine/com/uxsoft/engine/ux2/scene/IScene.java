/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Base Interface for Game Scenes (Renderer/Logic Handler/Input Handler)
 * @author David
 */
public interface IScene {
	/**
	 * Executes Rendering on this scene
	 * @param g Graphics Context
	 */
	void doRender(final RenderSpace rs);

	/**
	 * Executes an update
	 * @param elapsedMillis Milliseconds elapsed since last update
	 */
	void doUpdate(long elapsedMillis);

	/**
	 * Executed when a key is released
	 * @param evt Key Event Context
	 */
	void onKeyReleased(final KeyEvent evt);

	/**
	 * Executed when a key is pressed
	 * @param evt Key Event Context
	 */
	void onKeyPressed(final KeyEvent evt);

	/**
	 * Executed when the Mouse is Pressed
	 * @param evt Mouse Event Context
	 */
	void onMousePressed(final MouseEvent evt);

	/**
	 * Executed when the Mouse is Released
	 * @param evt Mouse Event Context
	 */
	void onMouseReleased(final MouseEvent evt);

	/**
	 * Executed when the Mouse is Moved
	 * @param evt Mouse Event Context
	 */
	void onMouseMoved(final MouseEvent evt);

	/**
	 * Executed when the Mouse is Dragged (IE: Button held down and moved)
	 * @param evt Mouse Event Context
	 */
	void onMouseDragged(final MouseEvent evt);

	/**
	 * Executed when the Mouse Wheel is Moved
	 * @param evt Mouse Wheel Event Context
	 */
	void onMouseWheelMoved(final MouseWheelEvent evt);

	/**
	 * Returns the render delay.
	 * @return Render delay
	 */
	int getRenderDelay();

	/**
	 * Returns the amount of updates that should be executed per second.
	 * @return Updates executed per second
	 */
	int getUpdateFrequency();

	/**
	 * Returns the render list.
	 * @return
	 */
	ObjectList getRenderList();
}
