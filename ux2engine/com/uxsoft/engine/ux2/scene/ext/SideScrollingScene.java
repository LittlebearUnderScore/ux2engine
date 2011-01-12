/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.ext.ViewPort;
import com.uxsoft.engine.ux2.math.Size2D;

/**
 *
 * @author David
 */
public abstract class SideScrollingScene
		extends BaseScene {
	private ViewPort viewport;

	private void createViewPort(IGameObject obj, Size2D vpSize, Size2D gSize) {
		viewport = new ViewPort(obj, vpSize, gSize);
	}

	public void updateViewPort() {
		viewport.update();
	}

	public void setFollowingObject() {

	}
}
