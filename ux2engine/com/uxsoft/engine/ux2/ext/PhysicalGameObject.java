/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.ext;

import com.uxsoft.engine.ux2.core.GameObject;
import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.physics.CartesianVector2D;
import com.uxsoft.engine.ux2.physics.PolarVector2D;
import com.uxsoft.engine.ux2.physics.Vector2D;

/**
 *
 * @author David
 */
public abstract class PhysicalGameObject
		extends GameObject
		implements IPhysicalGameObject {
	public static final double
			VelocityEpsilon = 0.01;
	protected double gravity; // Gravitational value
	protected double friction; // Friction, how fast the decay rate of speed and gravity is.
	protected Vector2D velocity;
	protected double hspeed, vspeed;
	protected double xprev, yprev;
	protected IGameObject target;

	public PhysicalGameObject() {
		velocity = new CartesianVector2D(0.0D, 0.0D);
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public double getDirection() {
		return velocity.direction();
	}
	
	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void addMotion(Direction direction, double speed) {
		addMotionRadians(direction.directionRadians(), speed);
	}

	public void addMotion(double direction, double speed) {
		this.velocity.add(PolarVector2D.vectorFromDegrees(speed, direction));
	}

	public void addMotionRadians(double direction, double speed) {
		this.velocity.add(new PolarVector2D(speed, direction));
	}

	public double getHSpeed() {
		return hspeed;
	}

	public double getSpeed() {
		return getVelocity().magnitude();
	}

	public double getVSpeed() {
		return vspeed;
	}

	public double getXPrevious() {
		return xprev;
	}

	public double getYPrevious() {
		return yprev;
	}

	public void jumpToPosition(double x, double y) {
		this.position.setX(x);
		this.position.setY(y);
	}

	public void jumpToPosition(double x, double y, boolean relative) {
		this.position.setX(x + (relative ? this.position.getX() : 0));
		this.position.setY(y + (relative ? this.position.getY() : 0));
	}

	public void moveTowards(IGameObject target) {
		this.target = target;
	}

	public void reverseHorizontal() {
		this.hspeed *= -1.0D;
	}

	public void reverseVertical() {
		this.vspeed *= -1.0D;
	}

	public void setDirection(double direction) {
		this.getVelocity().setDirection(direction);
	}

	public void setHSpeed(double hspeed) {
		this.hspeed = hspeed;
	}

	public void setSpeed(double speed) {
		this.velocity.setMagnitude(speed);
	}

	public void setVSpeed(double vspeed) {
		this.vspeed = vspeed;
	}

	@Override
	public void doUpdate(long elapsedMillis) {
		this.physicalUpdate();
	}

	/**
	 * Updates the Physical state of this object.
	 * This should be called in the subclass's implementation of {@link GameObject#doUpdate(long) }.
	 */
	protected void physicalUpdate() {
		xprev = position.x;
		yprev = position.y;
		if (gravity != 0) {
			position.y += gravity;
		}
		if (friction > 0.0 && velocity.magnitude() > 0) {
			gravity -= gravity * friction;
		}
		if (velocity.magnitude() != 0.00D) {
			if (this.velocity.magnitude() <= VelocityEpsilon) {
				this.velocity.setMagnitude(0.0);
				this.velocity.setDirection(0.0);
			} else {
				position.x += velocity.xComponent();
				position.y += velocity.yComponent();
			}
		}
		position.x += hspeed;
		position.y += vspeed;
		if (friction > 0.0) {
			this.velocity.subtractFraction(this.friction);
			if (velocity.magnitude() != 0.00D) {
				if (this.velocity.magnitude() <= VelocityEpsilon) {
					this.velocity.setMagnitude(0.0);
					this.velocity.setDirection(0.0);
				}
			}
		}
	}
}
