/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.ext;

import com.uxsoft.engine.ux2.math.MathTool;

/**
 * Represents Directions.
 * @author David
 */
public enum Direction {
	/**
	 * This is the no-direction state. There is no vertical or horiziontal motion.
	 */
	NONE(0, 0, MathTool.toRadians(0)),
	/**
	 * This is the north state. The Y component points upwards.
	 */
	NORTH(0, -1, MathTool.toRadians(270)),
	/**
	 * This is the south state. The Y component points downwards.
	 */
	SOUTH(0, 1, MathTool.toRadians(90)),
	/**
	 * This is the east state. The X component points to the right.
	 */
	EAST(1, 0, MathTool.toRadians(0)),
	/**
	 * This is the west state. The X component points to the left.
	 */
	WEST(-1, 0, MathTool.toRadians(180)),
	/**
	 * This is the north-east state. The X component points to the right, and the Y component points upwards.
	 */
	NORTHEAST(1, -1, MathTool.toRadians(315)),
	/**
	 * This is the south-east state. The X component points to the right, and the Y component points downwards.
	 */
	SOUTHEAST(1, 1, MathTool.toRadians(45)),
	/**
	 * This is the north-west state. The X component points to the left, and the Y component points upwards.
	 */
	NORTHWEST(-1, -1, MathTool.toRadians(225)),
	/**
	 * This is the south-west state. The X component points to the left, and the Y component points downwards.
	 */
	SOUTHWEST(-1, 1, MathTool.toRadians(135));

	private final int dx, dy;
	private final double dir;

	Direction(int dx, int dy, double dir) {
		this.dx = dx; this.dy = dy; this.dir = dir;
	}

	public int getRelativeX() {
		return dx;
	}

	public int getRelativeY() {
		return dy;
	}

	public double directionRadians() {
		return dir;
	}

	public double directionDegrees() {
		return MathTool.toDegrees(dir);
	}
}
