/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.math.Rectangle;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 *
 * @author David
 */
public abstract class GameObject
		implements IGameObject {
	// Area (will change to a volume later)
	protected Rectangle area = new Rectangle();
	protected final Vector2D position = new Vector2D();
	// Depth
	protected int depth;
	// Various flags
	protected boolean visible   = true,
					  solid     = true,
					  activated = true;

	public Rectangle getArea() {
		return this.area;
	}

	public int getDepth() {
		return this.depth;
	}

	public Vector2D getPosition() {
		return this.position;
	}

	public void setArea(Rectangle area) {
		this.area.width = area.width;
		this.area.height = area.height;
		this.area.x = area.x;
		this.area.y = area.y;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setPosition(Vector2D pos) {
		this.position.x = pos.x;
		this.position.y = pos.y;
	}

	public boolean isSolid() {
		return this.solid;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * Implement this method to handle the rendering of this object.
	 * @param rs
	 */
	public void doRender(RenderSpace rs) {}

	/**
	 * Implement this method to handle the updating of this object.
	 * @param elapsedMillis
	 */
	public void doUpdate(long elapsedMillis) {}

	/**
	 * Disposes this object. Subclasses should override this method to do specific cleanup tasks.
	 */
	public void dispose() {}
}
