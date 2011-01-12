/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

import com.uxsoft.engine.ux2.math.Vector2D;

/**
 *
 * @author David
 */
public class PointVolume
		extends CollisionVolume {

	public boolean collidesWith(Volume other) {
		if (other.getType() == VolumeType.TYPE_POINT) {
			return collidesWithPoint((PointVolume) other);
		}

		return false;
	}

	public boolean collidesWithPoint(PointVolume other) {
		Vector2D pos = getLocation(), opos = other.getLocation();
		return pos.x==opos.x&&pos.y==opos.y;
	}

	public Object getType() {
		return VolumeType.TYPE_POINT;
	}
}
