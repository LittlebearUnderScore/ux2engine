/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.math;

/**
 * A generic class to represent 2D vectors.
 * @author David
 */
public class Vector2D {
	public double x, y;

	public Vector2D() {
		this(0, 0);
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double distance(Vector2D other) {
		return MathTool.pointDistance(this, other);
	}

	public double distanceSq(Vector2D other) {
		double dist = distance(other);
		return dist * dist;
	}
}
