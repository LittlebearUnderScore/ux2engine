/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.core.IGameObject;

/**
 *
 * @author David
 */
public class RenderTree {
	protected class Node {
		private IGameObject obj;
		private Node left, right;
	}

	private Node root;

	public RenderTree() {
		root = new Node();
	}

	public void insert(IGameObject obj) {
		if (obj.getDepth() <= 0) {
			// Insert left
		} else {
			// Insert right
		}
	}
}
