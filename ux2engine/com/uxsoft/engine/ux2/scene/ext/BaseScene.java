/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.input.InputHandler;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import com.uxsoft.engine.ux2.scene.IScene;
import com.uxsoft.engine.ux2.scene.SceneRenderer;

/**
 * {@link BaseScene} is just a class implementing most of IScene's functions,
 * and providing interfaces for creating a scene.
 * It does nothing by default.
 * @author David
 */
public abstract class BaseScene
		implements IScene {
	protected SceneRenderer renderer;
	protected InputHandler inputHandler;

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public void doRender(RenderSpace rs) {
		doDefaultRender(rs);
	}

	/**
	 * Default render operation, just calls <code>doRender</code> on the graphics context with the current renderer.
	 * @param g <code>RenderSpace</code> context
	 */
	public void doDefaultRender(RenderSpace rs) {
		if (renderer != null) {
			renderer.doRender(rs);
		}
	}

	public void onKeyReleased(KeyEvent evt) {
		if (inputHandler != null) {
			inputHandler.onKeyReleased(evt);
		}
	}

	public void onKeyPressed(KeyEvent evt) {
		if (inputHandler != null) {
			inputHandler.onKeyPressed(evt);
		}
	}

	public void onMousePressed(MouseEvent evt) {
		if (inputHandler != null) {
			inputHandler.onMousePressed(evt);
		}
	}

	public void onMouseReleased(MouseEvent evt) {
		if (inputHandler != null) {
			inputHandler.onMouseReleased(evt);
		}
	}

	public void onMouseMoved(MouseEvent evt) {
		if (inputHandler != null) {
			inputHandler.onMouseMoved(evt);
		}
	}

	public void onMouseDragged(MouseEvent evt) {
		if (inputHandler != null) {
			inputHandler.onMouseDragged(evt);
		}
	}

	public void onMouseWheelMoved(MouseWheelEvent evt) {
		if (inputHandler != null) {
			inputHandler.onMouseWheelMoved(evt);
		}
	}

	public SceneRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(SceneRenderer renderer) {
		this.renderer = renderer;
	}
}
