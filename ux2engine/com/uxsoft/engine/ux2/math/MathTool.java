/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.math;

import com.uxsoft.engine.ux2.core.IGameObject;

/**
 * Provides generic mathematic utilities.
 * @author David
 */
public class MathTool {
	public static final double PI = 3.1415926535897932384626433832795;
	public static final double
			RadianMultiplier = PI/180,
			DegreeMultiplier = 180/PI;
    /**
	 * The value 2PI as a double.
	 */
    public static final double TwoPI = 2.0f * PI;

    /**
	 * The value PI/2 as a double.
	 */
    public static final double HalfPI = 0.5f * PI;

    /**
	 * The value 1/PI as a double.
	 */
    public static final double InversePI = 1.0f/PI;

	public static double log(double value, double base) {
		return Math.log(value)/Math.log(base);
	}

	/**
	 * Gets the distance in double precison between two points.
	 * @param p1 A <code>Vector2D</code> describing the first point.
	 * @param p2 A <code>Vector2D</code> describing the second point.
	 * @return The distance in double precison between the two points.
	 */
	public static double pointDistance(Vector2D p1, Vector2D p2) {
		double xdiff = p1.x - p2.x, ydiff = p1.y - p2.y;
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	/**
	 * Gets the Distance in double precison between two objects.
	 * @param obj1 First object
	 * @param obj2 Second object
	 * @return The distance between the two points.
	 */
	public static double pointDistance(IGameObject obj1, IGameObject obj2) {
		return pointDistance(obj1.getPosition(), obj2.getPosition());
	}

	/**
	 * Gets the collision point, or <code>null</code> between two lines.
	 * @param x1 X of first line, start point.
	 * @param y1 Y of first line, start point.
	 * @param x2 X of first line, end point.
	 * @param y2 Y of first line, end point.
	 * @param x3 X of second line, start point.
	 * @param y3 Y of second line, start point.
	 * @param x4 X of second line, end point.
	 * @param y4 Y of second line, end point.
	 * @return A {@link Vector2D} describing the intersection point, or <code>null</code>
	 * if these two lines do not intersect.
	 */
	public static Vector2D lineIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		if (denom == 0.0) { // Lines are parallel.
			return null;
		}
		double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/denom;
		double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/denom;
        if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
            // Get the intersection point.
            return new Vector2D((int) (x1 + ua*(x2 - x1)), (int) (y1 + ua*(y2 - y1)));
        }

		return null;
	}

	/**
	 * Converts an angle expressed in degrees to an angle expressed in radians.
	 * @param degrees
	 * @return
	 */
	public static double toRadians(double degrees) {
		return degrees * RadianMultiplier;
	}

	/**
	 * Converts an angle expressed in radians to an angle expressed in degrees.
	 * @param radians
	 * @return
	 */
	public static double toDegrees(double radians) {
		return radians * DegreeMultiplier;
	}

	/**
	 * Reduces the angle provided. (Puts it into -PI/2 to PI/2 space.)
	 * @param radians Angle in radians
	 * @return Reduced angle
	 */
	public static double reduceAngle(double radians) {
		radians %= TwoPI;
        if (abs(radians) > PI) {
            radians = radians - TwoPI;
        }
        if (abs(radians) > HalfPI) {
            radians = PI - radians;
        }
		return radians;
	}

	/**
	 * Returns the absolute value.
	 * @param value
	 * @return
	 */
	public static double abs(double value) {
		return (value <= 0.0D) ? 0.0D - value : value;
	}

	/**
	 * Returns the absolute value.
	 * @param value
	 * @return
	 */
	public static int abs(int value) {
		return value < 0 ? -value : value;
	}

	public static boolean inRect(int x, int y, int x1, int y1, int width, int height) {
		return (x >= x1 && x <= x1+width && y >= y1 && y <= y1+height);
	}

	public static boolean isPowerOfTwo(int x) {
		return x != 0 && (x&(x-1)) != 0;
	}

	public static boolean doesIntersect(double l1x1, double l1y1, double l1x2, double l1y2, double l2x1, double l2y1, double l2x2,
			double l2y2) {
		double denom = ((l2y2 - l2y1) * (l1x2 - l1x1)) - ((l2x2 - l2x1) * (l1y2 - l1y1));

		if (denom == 0.0f) {
			return false;
		}

		double ua = (((l2x2 - l2x1) * (l1y1 - l2y1)) - ((l2y2 - l2y1) * (l1x1 - l2x1))) / denom;
		double ub = (((l1x2 - l1x1) * (l1y1 - l2y1)) - ((l1y2 - l1y1) * (l1x1 - l2x1))) / denom;

		return ((ua >= 0.0d) && (ua <= 1.0d) && (ub >= 0.0d) && (ub <= 1.0d));
	}
}
