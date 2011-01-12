/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * A Volatile Sprite is a Sprite based on a Volatile Image.<br/>
 * A Volatile Sprite can lose its surface data at the command of the operating system.
 * @author David
 */
public class VolatileSprite
		extends BaseSprite {
	private VolatileImage vImage;
	private BufferedImage snapshot = null;

	public VolatileSprite(SpriteSource source) {
		this.source = source;
	}

	private void validateSnapshot() {
		if (snapshot == null) {
			snapshot = vImage.getSnapshot();
		}
	}

	public void releaseSnapshot() {
		if (snapshot != null) {
			snapshot.flush();
			snapshot = null;
		}
	}

	public void updateVolatileBuffer() {
		if (snapshot == null) {
			throw new ImageException("No snapshot created");
		}
		Graphics2D g2d = vImage.createGraphics();
		g2d.drawImage(snapshot, 0, 0, null);
		g2d.dispose();
		g2d = null;
	}

	/**
	 * Gets a pixel in the RGB format from this volatile sprite.<br/>
	 * <b>WARNING: If you need to use this method, please consider using a Buffered Sprite instead as this method
	 * creates a Buffered Image transparently, overriding any performance benefits a Volatile sprite may offer.</b>
	 * @param x
	 * @param y
	 * @return
	 */
	public int getRGB(int x, int y) {
		validateSnapshot();
		return snapshot.getRGB(x, y);
	}

	/**
	 * Sets a RGB pixel on this volatile sprite.<br/>
	 * <b>WARNING: If you need to use this method, please consider using a Buffered Sprite instead as this method
	 * creates a Buffered Image transparently, overriding any performance benefits a Volatile sprite may offer.</b>
	 * @param x
	 * @param y
	 * @param rgb
	 */
	public void setRGB(int x, int y, int rgb) {
		validateSnapshot();
		snapshot.setRGB(x, y, rgb);
	}

	/**
	 * Returns the width of this volatile sprite.
	 * @return
	 */
	public int getWidth() {
		if (w == -1) {
			w = vImage.getWidth();
		}
		return w;
	}

	/**
	 * Returns the height of this volatile sprite.
	 * @return
	 */
	public int getHeight() {
		if (h == -1) {
			h = vImage.getHeight();
		}
		return h;
	}

	public ColorModel getColorModel() {
		validateSnapshot();
		return snapshot.getColorModel();
	}

	public boolean acquire() {
		return false;
	}

	public void release() {
		vImage.flush();
	}

	public boolean releasable() {
		return true;
	}

	public int getTransparency() {
		return vImage.getTransparency();
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setOriginX(int x) {
		this.origin.x = x;
	}

	public void setOriginY(int y) {
		this.origin.y = y;
	}

	public void setOrigin(Vector2D origin) {
		this.origin.x = origin.x;
		this.origin.y = origin.y;
	}

	public void draw(Graphics2D g, int x, int y, ImageObserver obs) {
		g.drawImage(vImage, x, y, obs);
	}

	public void draw(Graphics2D g, int x, int y, Color bgColor, ImageObserver observer) {
		g.drawImage(vImage, x, y, bgColor, observer);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h, ImageObserver observer) {
		g.drawImage(vImage, x, y, w, h, observer);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h, Color bgColor, ImageObserver observer) {
		g.drawImage(vImage, x, y, w, h, bgColor, observer);
	}

	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		g.drawImage(vImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver observer) {
		g.drawImage(vImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, observer);
	}

	public boolean draw(Graphics2D g, AffineTransform transform, ImageObserver observer) {
		return g.drawImage(vImage, transform, observer);
	}

	public Object getSpriteType() {
		return SpriteType.TYPE_VOLATILE;
	}
}
