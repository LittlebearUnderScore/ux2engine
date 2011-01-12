/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

import com.uxsoft.engine.ux2.math.MathTool;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 *
 * @author David
 */
public class LineVolume
		extends CollisionVolume {
	private Vector2D endPoint;

	public boolean collidesWith(Volume other) {
		if (other.getType() == VolumeType.TYPE_LINE) {
			return collidesWithLine((LineVolume) other);
		}
		return false;
	}

	public boolean collidesWithLine(LineVolume o) {
		Vector2D pos = getLocation(), opos = o.getLocation();
		return MathTool.doesIntersect(pos.x, pos.y, endPoint.x, endPoint.y, opos.x, opos.y, o.endPoint.x, o.endPoint.y);
	}

	public Object getType() {
		return VolumeType.TYPE_LINE;
	}

}
