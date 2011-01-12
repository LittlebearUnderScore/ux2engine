/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.math.Rectangle;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * Base for a Game Object.
 * @author David
 */
public interface IGameObject {
	/**
	 * Returns the position
	 * @return position of this object
	 */
	Vector2D getPosition();

	/**
	 * Sets the position of this object
	 * @param pos New Position
	 */
	void setPosition(Vector2D pos);

	/**
	 * Gets the Area of the object
	 * @return An area
	 */
	Rectangle getArea();

	/**
	 * Sets the Area of the object
	 * @param area An area
	 */
	void setArea(Rectangle area);

	/**
	 * Gets the Depth of this object.
	 * @return Depth
	 */
	int getDepth();

	/**
	 * Sets the Depth of this object.
	 * @param depth New Depth
	 */
	void setDepth(int depth);

	/**
	 * Renders this object using the specified Render Space
	 * @param g Render Context
	 */
	void doRender(final RenderSpace rs);

	/**
	 * Updates this object.
	 * @param elapsedMillis Time elapsed since last update in Milliseconds
	 */
	void doUpdate(final long elapsedMillis);

	/**
	 * Returns whether this object is visible.
	 * @return
	 */
	boolean isVisible();

	/**
	 * Returns whether this object is solid.
	 * Soild objects can collide with other solid objects.
	 * @return
	 */
	boolean isSolid();

	/**
	 * Sets whether this object is visible.
	 * @param visible
	 */
	void setVisible(boolean visible);

	/**
	 * Sets whether this object is solid.
	 * @param solid
	 */
	void setSolid(boolean solid);

	/**
	 * Returns whether this object is activated. If the logic implementation chooses so,
	 * non-activated objects will not execute their events.
	 * The default logic implementation will not execute the events of a non-activated object.
	 * @return
	 */
	boolean isActivated();

	/**
	 * Sets the activated flag on this object.
	 * @param activated
	 */
	void setActivated(boolean activated);

	/**
	 * Disposes this object. 
	 * Once this method is called one should not assume that this object be used again for another purpose.
	 */
	void dispose();
}
