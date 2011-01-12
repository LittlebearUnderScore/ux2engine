/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.physics;

/**
 * Vector2D represented in cartesian style coordinates, x and y.
 * @author David
 */
public class CartesianVector2D
		implements Vector2D {
	private double x, y;

	public CartesianVector2D() {
		x = y = 0;
	}

	public CartesianVector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double xComponent() {
		return x;
	}

	public double yComponent() {
		return y;
	}

	public double magnitude() {
		return Math.sqrt(x*x+y*y);
	}

	public double direction() {
		return Math.atan2(y, x);
	}

	public double dotProduct(Vector2D v) {
		return x*v.xComponent()+y*v.yComponent();
	}

	public void normalize() {
		double m = magnitude();
		x /= m;
		y /= m;
	}

	public void add(Vector2D v) {
		x += v.xComponent();
		y += v.yComponent();
	}

	public void subtract(Vector2D v) {
		x -= v.xComponent();
		y -= v.yComponent();
	}

	public void setXComponent(double newX) {
		x = newX;
	}

	public void setYComponent(double newY) {
		y = newY;
	}

	public void setDirection(double direction) {
		double magnitude = magnitude();
		x = Math.cos(direction) * magnitude;
		y = Math.sin(direction) * magnitude;
	}

	public void setMagnitude(double magnitude) {
		double dir = direction();
		x = Math.cos(dir) * magnitude;
		y = Math.sin(dir) * magnitude;
	}

	public void subtractFraction(double f) {
		setMagnitude((magnitude())*f);
	}

}
