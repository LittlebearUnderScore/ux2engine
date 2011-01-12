/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * Base class for a Sprite.
 * @author David
 */
public abstract class BaseSprite
		implements Sprite {
	protected int w = -1, h = -1;
	protected SpriteSource source;
	protected final Vector2D origin = new Vector2D();
	protected int delay;

	public int getDelay() {
		return this.delay;
	}

	public Vector2D getOrigin() {
		return this.origin;
	}

	public int getOriginX() {
		return (int) this.origin.x;
	}

	public int getOriginY() {
		return (int) this.origin.y;
	}
}
