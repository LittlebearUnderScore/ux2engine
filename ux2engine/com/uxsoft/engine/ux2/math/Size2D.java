/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.math;

import java.awt.Dimension;

/**
 * Class to represent 2D sizes.
 * @author David
 */
public class Size2D {
	public int width, height;

	public Size2D(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Size2D() {
		this(0, 0);
	}

	public Dimension dimension() {
		return new Dimension(width, height);
	}
}
