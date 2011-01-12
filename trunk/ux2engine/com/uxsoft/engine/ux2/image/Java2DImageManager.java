/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

/**
 * A Java2D extended image manager. Returns Java2D type images.
 * @author David
 */
public interface Java2DImageManager
		extends ImageManager {

	BufferedSprite fetchBufferedSprite(Object key);

	VolatileSprite fetchVolatileSprite(Object key);
}
