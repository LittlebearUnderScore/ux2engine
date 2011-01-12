/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * This class implements radial volumes.
 * @author David
 */
public class RadialVolume
		extends CollisionVolume {
	private double radius;

	public boolean collidesWith(Volume other) {
		if (other.getClass() == RadialVolume.class) {
			return collidesWithRadial((RadialVolume) other);
		}
		return false;
	}

	public boolean collidesWithRadial(RadialVolume other) {
		double maxDist = this.radius + other.radius;
		Vector2D pos = getLocation(), opos = other.getLocation();
		double xdiff = pos.x - opos.x;
		double ydiff = pos.y - opos.y;
		return (xdiff * xdiff + ydiff * ydiff ) < (maxDist * maxDist);
	}

	public boolean collidesWithPoint(PointVolume other) {
		Vector2D pos = getLocation(), opos = other.getLocation();
		double xd = opos.x - pos.x, yd = opos.y - pos.y;
		double xf = xd*xd+yd*yd;
		return xf <= radius*radius;
	}

	public Object getType() {
		return VolumeType.TYPE_RADIAL;
	}
}
