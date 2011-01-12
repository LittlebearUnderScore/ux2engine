/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.math;

import java.awt.Point;

/**
 * Generic class for a line segment.
 * @author David
 */
public class LineSegment {
	public final Point start = new Point(), end = new Point();

	public LineSegment() {
		this(0, 0, 0, 0);
	}

	public LineSegment(Point p1, Point p2) {
		this(p1.x, p1.y, p2.x, p2.y);
	}

	public LineSegment(int x1, int y1, int x2, int y2) {
		start.x = x1;
		start.y = y1;
		end.x = x2;
		end.y = y2;
	}

	public Point getEnd() {
		return end;
	}

	public Point getStart() {
		return start;
	}

	/**
	 * Returns whether this line intersects the line denoted by <tt>other</tt>
	 * @param other other line
	 * @return true if intersects; false otherwise.
	 */
	public boolean intersects(LineSegment other) {
		return getIntersection(other) != null;
	}

	/**
	 * Gets the intersection point for this line intersecting with another line.
	 * <p>
	 * Note that this is a convienience method, and does the same as:
	 * <pre>
	 * getIntersection(other.start.x, other.start.y, other.end.x, other.end.y)
	 * </pre>
	 * </p>
	 * @param other another line
	 * @return non <tt>null</tt> if intersects, or else <tt>null</tt>
	 */
	public Vector2D getIntersection(LineSegment other) {
		return getIntersection(other.start.x, other.start.y, other.end.x, other.end.y);
	}

	/**
	 * Gets the intersection point for this line intersecting with another line.
	 * @return non <tt>null</tt> if intersects, or else null
	 */
	public Vector2D getIntersection(int x1, int y1, int x2, int y2) {
		return MathTool.lineIntersect(start.x, start.y, end.x, end.y, x1, y1, x2, y2);
	}
}
