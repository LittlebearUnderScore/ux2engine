/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.math;

/**
 * A generic Rectangle class.
 * @author David
 */
public class Rectangle {
	public int x, y, width, height;
	private double rotation;

	public Rectangle() {
		this(0, 0);
	}

	public Rectangle(int w, int h) {
		this(0, 0, w, h);
	}

	public Rectangle(int x, int y, int w, int h) {
		this(x, y, w, h, 0);
	}

	public Rectangle(int x, int y, int width, int height, double rotation) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
