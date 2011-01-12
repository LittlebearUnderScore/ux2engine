/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * This class is a base class for a collision volume.
 * @author David
 */
public abstract class CollisionVolume
		implements Volume {
	protected IGameObject owner = null;
	protected Vector2D position = new Vector2D();

	public Vector2D getLocation() {
		if (owner != null) {
			return owner.getPosition();
		}
		return position;
	}

	public boolean hasOwner() {
		return owner != null;
	}

	public IGameObject getOwner() {
		return owner;
	}
}
