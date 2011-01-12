/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.ext;

import com.uxsoft.engine.ux2.core.IGameObject;

/**
 *
 * @author David
 */
public interface IPhysicalGameObject
		extends IGameObject {
	/**
	 * Gets the horizontal speed of this object. (independent of speed)
	 * @return
	 */
	double getHSpeed();

	/**
	 * Gets the vertical speed of this object. (independent of speed)
	 * @return
	 */
	double getVSpeed();

	/**
	 * Gets the speed of this object. (independent of hspeed and vspeed)
	 * @return
	 */
	double getSpeed();

	/**
	 * Gets the direction of this object.
	 * @return
	 */
	double getDirection();

	/**
	 * Gets the friction of this object.
	 * @return
	 */
	double getFriction();

	/**
	 * Gets the previous x value of this object.
	 * @return
	 */
	double getXPrevious();

	/**
	 * Gets the previous y value of this object.
	 * @return
	 */
	double getYPrevious();

	/**
	 * Reverses this object horizontally, that is, invert the hspeed.
	 */
	void reverseHorizontal();

	/**
	 * Reverses this object vertically, that is, invert the vspeed.
	 */
	void reverseVertical();

	/**
	 * Moves towards another object.
	 * @param target
	 */
	void moveTowards(IGameObject target);

	/**
	 * Moves this object to a new point. (Absolute positioned)
	 * @param x
	 * @param y
	 */
	void jumpToPosition(double x, double y);

	/**
	 * Moves this object to a new point.
	 * @param x
	 * @param y
	 * @param relative
	 */
	void jumpToPosition(double x, double y, boolean relative);

	void addMotion(double direction, double speed);

	void addMotion(Direction direction, double speed);

	void addMotionRadians(double direction, double speed);

	void setHSpeed(double hspeed);

	void setVSpeed(double vspeed);

	void setSpeed(double speed);

	void setDirection(double direction);

	void setFriction(double friction);
}
