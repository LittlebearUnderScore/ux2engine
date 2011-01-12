/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import java.util.Iterator;
import java.util.LinkedList;
import com.uxsoft.engine.ux2.scene.SceneRenderer;

/**
 * An implementation of {@link SceneRenderer} that links one or more {@link SceneRender}s together into one unit.
 * @author David
 */
public class LinkedSceneRenderer
		implements SceneRenderer {
	private LinkedList<SceneRenderer> renderers = new LinkedList();

	/**
	 * Registers a {@link SceneRenderer} to this <tt>LinkedSceneRenderer</tt>
	 * @param renderer Renderer to register
	 */
	public void registerRenderer(SceneRenderer renderer) {
		renderers.addLast(renderer);
	}

	public void clearRenderers() {
		renderers.clear();
	}

	public void doRender(RenderSpace rs) {
		Iterator<SceneRenderer> renderItr = renderers.iterator();
		while (renderItr.hasNext()) {
			renderItr.next().doRender(rs);
		}
	}
}
