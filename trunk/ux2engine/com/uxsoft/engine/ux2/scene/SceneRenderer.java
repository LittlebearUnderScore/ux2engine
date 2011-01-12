/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene;

import com.uxsoft.engine.ux2.graphics.RenderSpace;

/**
 * The {@link SceneRenderer} interface provides abstract methods for drawing scenes.
 * @author David
 */
public interface SceneRenderer {
	/**
	 * Renders this scene.
	 * @param rs A render space context to use.
	 */
	void doRender(RenderSpace rs);
}
