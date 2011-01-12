/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.physics;

/**
 * Represents a Vector in 2D space. Physical vectors have two parts, a magnitude and a direction.
 * @author David
 */
public interface Vector2D {

	/**
	 * Returns the x component.
	 * @return
	 */
	public double xComponent();

	/**
	 * Returns the y component.
	 * @return
	 */
	public double yComponent();

	/**
	 * Returns the magnitude.
	 */
	public double magnitude();

	/**
	 * Returns the direction.
	 * @return
	 */
	public double direction();

	/**
	 * Returns the dot product of this vector and another vector.
	 * @param v Vector2D instance
	 * @return
	 */
	public double dotProduct(Vector2D v);

	/**
	 * Normalizes this vector. Normalization makes the magnitude of the vector one unit without changing direction.
	 */
	public void normalize();

	/**
	 * Adds a vector to this vector.
	 * @param v
	 */
	public void add(Vector2D v);

	/**
	 * Subtracts a vector from this vector.
	 * @param v
	 */
	public void subtract(Vector2D v);

	public void setXComponent(double newX);

	public void setYComponent(double newY);

	public void setDirection(double direction);

	public void setMagnitude(double magnitude);

	public void subtractFraction(double f);
}
