/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * A sprite represents an image with an origin, and a delay.
 * The base of a buffered sprite is a buffered image.
 * @author David
 */
public class BufferedSprite
		extends BaseSprite {
	private BufferedImage image;
	private boolean releasable = true;

	public BufferedSprite(SpriteSource source) {
		this(source, true);
	}

	public BufferedSprite(final SpriteSource source, boolean load) {
		this.source = source;
		if (load) {
			acquire();
		}
	}

	public boolean acquire() {
		if (this.image == null) {
			if (this.source != null) {
				this.image = ImageUtils.readSprite(source, true, true);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Centers the Origin on this sprite
	 */
	public void centerOrigin() {
		origin.x = image.getWidth()/2;
		origin.y = image.getHeight()/2;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setOriginX(int nOriginX) {
		this.origin.x = nOriginX;
	}

	public void setOriginY(int nOriginY) {
		this.origin.y = nOriginY;
	}

	public void setOrigin(Vector2D origin) {
		this.origin.x = origin.x;
		this.origin.y = origin.y;
	}

	public BufferedSprite setOrigin(Point nOrigin) {
		return setOrigin(nOrigin.x, nOrigin.y);
	}

	public BufferedSprite setOrigin(int nOriginX, int nOriginY) {
		this.origin.x = nOriginX;
		this.origin.y = nOriginY;

		return this;
	}

	public int getRGB(int x, int y) {
		return image.getRGB(x, y);
	}

	public void setRGB(int x, int y, int rgb) {
		image.setRGB(x, y, rgb);
	}

	public int getWidth() {
		if (w == -1) {
			w = image.getWidth();
		}
		return w;
	}

	public int getHeight() {
		if (h == -1) {
			h = image.getHeight();
		}
		return h;
	}

	public ColorModel getColorModel() {
		return this.image.getColorModel();
	}

	public void release() {
		this.image.flush();
		this.image = null;
	}

	public WritableRaster getRaster() {
		return this.image.getRaster();
	}

	public WritableRaster getAlphaRaster() {
		return this.image.getAlphaRaster();
	}

	public WritableRaster getWritableTile(int x, int y) {
		return this.image.getWritableTile(x, y);
	}

	public Raster getData() {
		return this.image.getData();
	}

	public WritableRaster copyData(WritableRaster wr) {
		return this.image.copyData(wr);
	}

	public Raster getTile(int x, int y) {
		return this.image.getTile(x, y);
	}

	public Raster getData(Rectangle rect) {
		return this.image.getData(rect);
	}

	public void setData(Raster data) {
		this.image.setData(data);
	}

	public int getTransparency() {
		return this.image.getTransparency();
	}

	public boolean releasable() {
		return releasable;
	}

	public void draw(Graphics2D g, int x, int y, ImageObserver obs) {
		g.drawImage(image, x, y, obs);
	}

	public void draw(Graphics2D g, int x, int y, Color bgColor, ImageObserver observer) {
		g.drawImage(image, x, y, bgColor, observer);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h, ImageObserver observer) {
		g.drawImage(image, x, y, w, h, observer);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h, Color bgColor, ImageObserver observer) {
		g.drawImage(image, x, y, w, h, bgColor, observer);
	}

	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void draw(Graphics2D g, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver observer) {
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, observer);
	}

	public boolean draw(Graphics2D g, AffineTransform transform, ImageObserver observer) {
		return g.drawImage(image, transform, observer);
	}

	public Object getSpriteType() {
		return SpriteType.TYPE_BUFFERED;
	}
}
