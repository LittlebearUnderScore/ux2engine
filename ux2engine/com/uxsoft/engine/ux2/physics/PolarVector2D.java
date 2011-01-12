/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.physics;

import com.uxsoft.engine.ux2.math.MathTool;

/**
 * A vector represented in polar style coordinates, magnitude and direction.
 * @author David
 */
public class PolarVector2D
		implements Vector2D {
	private double
			/**
			 * Magnitude of this vector.
			 */
			magnitude,
			/**
			 * Direction of this vector expressed in radians.
			 */
			direction;

	/**
	 * Constructs a new <tt>Vector2D</tt>.
	 * @param magnitude Magnitude of the vector.
	 * @param direction Direction of the vector, expressed in radians.
	 */
	public PolarVector2D(double magnitude, double direction) {
		this.magnitude = magnitude;
		this.direction = direction;
	}

	/**
	 * Adds the specified vector to this vector, returning the new vector.
	 * @param vec
	 * @return
	 */
	public PolarVector2D add(PolarVector2D vec) {
		double xc = Math.cos(this.direction) * this.magnitude;
		double yc = Math.sin(this.direction) * this.magnitude;
		double vxc = Math.cos(vec.direction) * vec.magnitude;
		double vyc = Math.sin(vec.direction) * vec.magnitude;
		double resx = xc + vxc;
		double resy = yc + vyc;
		return new PolarVector2D(Math.sqrt(resx * resx + resy * resy), Math.atan2(resy, resx));
	}

	public double getDirection() {
		return direction;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * Returns the x-component of this vector.
	 * @return
	 */
	public double xComponent() {
		return Math.cos(this.direction) * this.magnitude;
	}

	/**
	 * Returns the y-component of this vector.
	 * @return
	 */
	public double yComponent() {
		return Math.sin(this.direction) * this.magnitude;
	}

	public void subtractFraction(double mu) {
		this.magnitude -= this.magnitude * mu;
	}

	/**
	 * Constructs a <tt>Vector2D</tt> using degrees.
	 * @param magnitude
	 * @param direction
	 * @return
	 */
	public static PolarVector2D vectorFromDegrees(double magnitude, double direction) {
		return new PolarVector2D(magnitude, MathTool.toRadians(direction));
	}

	public double magnitude() {
		return magnitude;
	}

	public double direction() {
		return direction;
	}

	public double dotProduct(Vector2D v) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void normalize() {
		magnitude = 1.0d;
	}

	public void add(Vector2D v) {
		if (v instanceof PolarVector2D) {
			PolarVector2D pv = (PolarVector2D) v;
			double xc = Math.cos(this.direction) * this.magnitude;
			double yc = Math.sin(this.direction) * this.magnitude;
			double vxc = Math.cos(pv.direction) * pv.magnitude;
			double vyc = Math.sin(pv.direction) * pv.magnitude;
			double resx = xc + vxc;
			double resy = yc + vyc;
			magnitude = Math.sqrt(resx*resx+resy*resy);
			direction = Math.atan2(resy,resx);
		} else {
			double yV = yComponent() + v.yComponent();
			double xV = xComponent() + v.xComponent();
			magnitude = Math.sqrt(xV*xV+yV*yV);
			direction = Math.atan2(yV,xV);
		}
	}

	public void subtract(Vector2D v) {
		if (v instanceof PolarVector2D) {
			PolarVector2D pv = (PolarVector2D) v;
			double xc = Math.cos(this.direction) * this.magnitude;
			double yc = Math.sin(this.direction) * this.magnitude;
			double vxc = Math.cos(pv.direction) * pv.magnitude;
			double vyc = Math.sin(pv.direction) * pv.magnitude;
			double resx = xc - vxc;
			double resy = yc - vyc;
			magnitude = Math.sqrt(resx*resx+resy*resy);
			direction = Math.atan2(resy,resx);
		} else {
			double yV = yComponent() - v.yComponent();
			double xV = xComponent() - v.xComponent();
			magnitude = Math.sqrt(xV*xV+yV*yV);
			direction = Math.atan2(yV,xV);
		}
	}

	public void setXComponent(double newX) {
		double yC = yComponent();
		magnitude = Math.sqrt(newX*newX + yC * yC);
		direction = Math.atan2(yC, newX);
	}

	public void setYComponent(double newY) {
		double xC = xComponent();
		magnitude = Math.sqrt(xC*xC + newY*newY);
		direction = Math.atan2(newY,xC);
	}
}
