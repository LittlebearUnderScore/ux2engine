/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * This interface defines a collision volume.
 * @author David
 */
public interface Volume {
	/**
	 * Returns true if this volume collides with another volume.
	 * @param other
	 * @return
	 */
	boolean collidesWith(Volume other);

	/**
	 * Returns the origin-point of this volume.
	 * @return
	 */
	Vector2D getLocation();

	/**
	 * Returns the type of collision volume this is.
	 * @return
	 */
	Object getType();
}
