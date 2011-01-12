/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.collision;

/**
 *
 * @author David
 */
public class BoxVolume
		extends CollisionVolume {

	public boolean collidesWith(Volume other) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Object getType() {
		return VolumeType.TYPE_BOX;
	}

}
