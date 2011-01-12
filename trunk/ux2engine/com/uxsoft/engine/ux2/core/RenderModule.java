/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.scene.IScene;

/**
 *
 * @author David
 */
public abstract class RenderModule {
	public abstract void renderScene(final RenderSpace rs, final IScene scene);
}
