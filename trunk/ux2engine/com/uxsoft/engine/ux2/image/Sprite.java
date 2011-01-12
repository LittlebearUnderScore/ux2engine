/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import com.uxsoft.engine.ux2.math.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;

/**
 * A sprite is an image with an origin and delay. Sprites can be dynamically released and acquired from
 * system resources.
 * @author David
 */
public interface Sprite {
	/**
	 * Gets a RGB pixel from this sprite.
	 * @param x
	 * @param y
	 * @return
	 */
	int getRGB(int x, int y);

	/**
	 * Sets a RGB pixel on this sprite.
	 * @param x
	 * @param y
	 * @param rgb
	 */
	void setRGB(int x, int y, int rgb);

	/**
	 * Returns the width of this sprite.
	 * @return
	 */
	int getWidth();

	/**
	 * Returns the height of this sprite.
	 * @return
	 */
	int getHeight();

	/**
	 * Returns the color model this sprite uses.
	 * @return
	 */
	ColorModel getColorModel();

	/**
	 * Returns the x-origin of this sprite.
	 * @return
	 */
	int getOriginX();

	/**
	 * Returns the y-origin of this sprite.
	 * @return
	 */
	int getOriginY();

	/**
	 * Returns the origin of this sprite.
	 * @return
	 */
	Vector2D getOrigin();

	/**
	 * Attempts to acquire the resources used to reconstruct this sprite. If this sprite is already
	 * constructed, this method does nothing.
	 * @return true if acquire() was successful, false otherwise.
	 */
	boolean acquire();

	/**
	 * Releases the native system resources this sprite has allocated. If the resources are already
	 * released, this method does nothing.
	 */
	void release();

	/**
	 * Returns whether the memory allocated by this sprite is releasable.
	 * @return
	 */
	boolean releasable();

	/**
	 * Returns the frame delay imposed by this sprite.
	 * @return
	 */
	int getDelay();

	/**
	 * Returns the transparency property of the sprite.
	 * @return
	 */
	int getTransparency();

	/**
	 * Sets the delay for this sprite.
	 * @param delay
	 */
	void setDelay(int delay);

	/**
	 * Sets the origin's x coordinate.
	 * @param x
	 */
	void setOriginX(int x);

	/**
	 * Sets the origin's y coordinate.
	 * @param y
	 */
	void setOriginY(int y);

	/**
	 * Sets the origin.
	 * @param origin
	 */
	void setOrigin(Vector2D origin);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param x
	 * @param y
	 * @param obs
	 */
	public void draw(Graphics2D g, int x, int y, ImageObserver obs);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param x
	 * @param y
	 * @param bgColor
	 * @param observer
	 */
	public void draw(Graphics2D g, int x, int y, Color bgColor, ImageObserver observer);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param observer
	 */
	public void draw(Graphics2D g, int x, int y, int w, int h, ImageObserver observer);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param bgColor
	 * @param observer
	 */
	public void draw(Graphics2D g, int x, int y, int w, int h, Color bgColor, ImageObserver observer);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param sx1
	 * @param sy1
	 * @param sx2
	 * @param sy2
	 * @param observer
	 */
	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param sx1
	 * @param sy1
	 * @param sx2
	 * @param sy2
	 * @param bgColor
	 * @param observer
	 */
	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver observer);

	/**
	 * Draws this sprite to a Graphics 2D context.
	 * @param g
	 * @param transform
	 * @param observer
	 * @return
	 */
	public boolean draw(Graphics2D g, AffineTransform transform, ImageObserver observer);

	/**
	 * Returns the type of sprite this is.
	 * @return
	 */
	Object getSpriteType();
}
