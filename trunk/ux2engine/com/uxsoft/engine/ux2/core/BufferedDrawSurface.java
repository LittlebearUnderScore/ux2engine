/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 * A <tt>BufferedDrawSurface</tt> is a drawing surface which is buffered, by using a class of type
 * {@link BufferStrategy} to maintain accelerated buffer drawing surfaces, by either using
 * page flipping or page blitting.
 * @author David
 */
public interface BufferedDrawSurface {
	/**
	 * Returns the {@link BufferStrategy} that this <tt>BufferedDrawSurface</tt> uses.
	 * @return
	 */
	BufferStrategy getDrawBuffer();

	/**
	 * Returns the Graphics context that is used to draw to this buffer.
	 * @return
	 */
	Graphics getBufferGraphics();

	/**
	 * Returns the Graphics2D context that is used to draw to this buffer.
	 * @return
	 */
	Graphics2D getDrawGraphics();
}
