/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.graphics;

import com.uxsoft.engine.ux2.image.Sprite;
import com.uxsoft.engine.ux2.math.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * Represents a rendering space.
 * @author David
 */
public class RenderSpace {
	private Graphics2D g;

	public RenderSpace() {
		this(null);
	}

	public RenderSpace(Graphics2D g) {
		this.g = g;
	}

	public void setGraphicsContext(Graphics2D g) {
		this.g = g;
	}

	public Graphics2D getGraphicsContext() {
		return this.g;
	}

	public void addRenderingHints(Map hints) {
		g.addRenderingHints(hints);
	}

	public void clip(Shape s) {
		g.clip(s);
	}

	public void draw(Shape s) {
		g.draw(s);
	}

	public void dispose() {
		g.dispose();
		g = null;
	}

	public void copyArea(int x, int y, int w, int h, int dx, int dy) {
		g.copyArea(x, y, w, h, dx, dy);
	}

	public void clearRect(int x, int y, int w, int h) {
		g.clearRect(x, y, w, h);
	}

	public void clearRect(Rectangle rect) {
		clearRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void clearRect(java.awt.Rectangle rect) {
		clearRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void clipRect(int x, int y, int w, int h) {
		g.clipRect(x, y, w, h);
	}

	public void clipRect(Rectangle rect) {
		clipRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void clipRect(java.awt.Rectangle rect) {
		clipRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
		g.drawArc(x, y, w, h, startAngle, arcAngle);
	}

	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		g.draw3DRect(x, y, width, height, raised);
	}

	public void draw3DRect(Rectangle rect, boolean raised) {
		draw3DRect(rect.x, rect.y, rect.width, rect.height, raised);
	}

	public void drawGlyphVector(GlyphVector g, float x, float y) {
		this.g.drawGlyphVector(g, x, y);
	}

	public void drawImage(BufferedImage image, BufferedImageOp op, double x, double y) {
		g.drawImage(image, op, (int) x, (int) y);
	}

	public boolean drawImage(Image image, AffineTransform xform) {
		return g.drawImage(image, xform, null);
	}

	public boolean drawImage(Image image, AffineTransform xform, ImageObserver observer) {
		return g.drawImage(image, xform, observer);
	}

	public void drawImage(BufferedImage image, BufferedImageOp op, int x, int y) {
		g.drawImage(image, op, x, y);
	}

	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		g.drawRenderableImage(img, xform);
	}

	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		g.drawRenderedImage(img, xform);
	}

	public void draw3DRect(java.awt.Rectangle rect, boolean raised) {
		draw3DRect(rect.x, rect.y, rect.width, rect.height, raised);
	}

	public void drawByteString(byte[] data, int offset, int len, int x, int y) {
		g.drawBytes(data, offset, len, x, y);
	}

	public void drawCharString(char[] data, int offset, int len, int x, int y) {
		g.drawChars(data, offset, len, x, y);
	}

	public void drawImage(Image image, double x, double y) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	public void drawImage(Image image, double x, double y, ImageObserver observer) {
		g.drawImage(image, (int) x, (int) y, observer);
	}

	public void drawImage(Image image, double x, double y, Color bgColor, ImageObserver observer) {
		g.drawImage(image, (int) x, (int) y, bgColor, observer);
	}

	public void drawImage(Image image, double x, double y, double w, double h) {
		g.drawImage(image, (int) x, (int) y, (int) w, (int) h, null);
	}

	public void drawImage(Image image, double x, double y, double w, double h, ImageObserver observer) {
		g.drawImage(image, (int) x, (int) y, (int) w, (int) h, observer);
	}

	public void drawImage(Image image, double x, double y, double w, double h, Color bgColor, ImageObserver observer) {
		g.drawImage(image, (int) x, (int) y, (int) w, (int) h, bgColor, observer);
	}

	public void drawImage(Image image, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2) {
		g.drawImage(image, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, null);
	}

	public void drawImage(Image image, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2, ImageObserver observer) {
		g.drawImage(image, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, observer);
	}

	public void drawImage(Image image, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2, Color bgColor, ImageObserver observer) {
		g.drawImage(image, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, bgColor, observer);
	}

	public void drawImage(Image image, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public void drawImage(Image image, int x, int y, ImageObserver observer) {
		g.drawImage(image, x, y, observer);
	}

	public void drawImage(Image image, int x, int y, Color bgColor, ImageObserver observer) {
		g.drawImage(image, x, y, bgColor, observer);
	}

	public void drawImage(Image image, int x, int y, int w, int h) {
		g.drawImage(image, x, y, w, h, null);
	}

	public void drawImage(Image image, int x, int y, int w, int h, ImageObserver observer) {
		g.drawImage(image, x, y, w, h, observer);
	}

	public void drawImage(Image image, int x, int y, int w, int h, Color bgColor, ImageObserver observer) {
		g.drawImage(image, x, y, w, h, bgColor, observer);
	}

	public void drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	public void drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver observer) {
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, observer);
	}

	public void drawSprite(Sprite sprite, int x, int y) {
		sprite.draw(g, x, y, null);
	}

	public void drawSprite(Sprite sprite, int x, int y, ImageObserver observer) {
		sprite.draw(g, x, y, observer);
	}

	public void drawSprite(Sprite sprite, int x, int y, Color bgColor, ImageObserver observer) {
		sprite.draw(g, x, y, bgColor, observer);
	}

	public void drawSprite(Sprite sprite, int x, int y, int w, int h) {
		sprite.draw(g, x, y, w, h, null);
	}

	public void drawSprite(Sprite sprite, int x, int y, int w, int h, ImageObserver observer) {
		sprite.draw(g, x, y, w, h, observer);
	}

	public void drawSprite(Sprite sprite, int x, int y, int w, int h, Color bgColor, ImageObserver observer) {
		sprite.draw(g, x, y, w, h, bgColor, observer);
	}

	public void drawSprite(Sprite sprite, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		sprite.draw(g, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	public void drawSprite(Sprite sprite, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		sprite.draw(g, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void drawSprite(Sprite sprite, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver observer) {
		sprite.draw(g, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, observer);
	}

	public boolean drawSprite(Sprite sprite, AffineTransform xform) {
		return sprite.draw(g, xform, null);
	}

	public boolean drawSprite(Sprite sprite, AffineTransform xform, ImageObserver observer) {
		return sprite.draw(g, xform, observer);
	}

	public void drawSprite(Sprite sprite, double x, double y) {
		sprite.draw(g, (int) x, (int) y, null);
	}

	public void drawSprite(Sprite sprite, double x, double y, ImageObserver observer) {
		sprite.draw(g, (int) x, (int) y, observer);
	}

	public void drawSprite(Sprite sprite, double x, double y, Color bgColor, ImageObserver observer) {
		sprite.draw(g, (int) x, (int) y, bgColor, observer);
	}

	public void drawSprite(Sprite sprite, double x, double y, double w, double h) {
		sprite.draw(g, (int) x, (int) y, (int) w, (int) h, null);
	}

	public void drawSprite(Sprite sprite, double x, double y, double w, double h, ImageObserver observer) {
		sprite.draw(g, (int) x, (int) y, (int) w, (int) h, observer);
	}

	public void drawSprite(Sprite sprite, double x, double y, double w, double h, Color bgColor, ImageObserver observer) {
		sprite.draw(g, (int) x, (int) y, (int) w, (int) h, bgColor, observer);
	}

	public void drawSprite(Sprite sprite, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2) {
		sprite.draw(g, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, null);
	}

	public void drawSprite(Sprite sprite, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2, ImageObserver observer) {
		sprite.draw(g, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, observer);
	}

	public void drawSprite(Sprite sprite, double dx1, double dy1, double dx2, double dy2, double sx1, double sy1, double sx2, double sy2, Color bgColor, ImageObserver observer) {
		sprite.draw(g, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, bgColor, observer);
	}

	public void drawShadowText(String text, int x, int y, int xShadowOffset, int yShadowOffset, Color textColor, Color shadowColor) {
		Color oldColor = null;
		if (textColor != null) {
			oldColor = getColor();
			setColor(textColor);
		}
		drawString(text, x, y);
		setColor(shadowColor);
		drawString(text, x + xShadowOffset, y + yShadowOffset);
		if (oldColor != null) {
			g.setColor(oldColor);
		}
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	public void drawPoint(int x, int y) {
		drawLine(x, y, x, y);
	}

	public void drawOval(int x, int y, int w, int h) {
		g.drawOval(x, y, w, h);
	}

	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	public void drawPolygon(Polygon p) {
		g.drawPolygon(p);
	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolyline(xPoints, yPoints, nPoints);
	}

	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	public void drawRect(Rectangle rect) {
		drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawRect(java.awt.Rectangle rect) {
		drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawRoundRect(Rectangle rect, int arcWidth, int arcHeight) {
		drawRoundRect(rect.x, rect.y, rect.width, rect.height, arcWidth, arcHeight);
	}

	public void drawRoundRect(java.awt.Rectangle rect, int arcWidth, int arcHeight) {
		drawRoundRect(rect.x, rect.y, rect.width, rect.height, arcWidth, arcHeight);
	}

	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		g.drawString(iterator, x, y);
	}

	public void drawString(String str, int x, int y) {
		g.drawString(str, x, y);
	}

	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		g.fill3DRect(x, y, width, height, raised);
	}

	public void fill3DRect(Rectangle rect, boolean raised) {
		fill3DRect(rect.x, rect.y, rect.width, rect.height, raised);
	}

	public void fill3DRect(java.awt.Rectangle rect, boolean raised) {
		fill3DRect(rect.x, rect.y, rect.width, rect.height, raised);
	}

	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	public void fillOval(int x, int y, int width, int height) {
		g.fillOval(x, y, width, height);
	}

	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.fillPolygon(xPoints, yPoints, nPoints);
	}

	public void fillPolygon(Polygon p) {
		g.fillPolygon(p);
	}

	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	public void fillRect(Rectangle rect) {
		fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void fillRect(java.awt.Rectangle rect) {
		fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void fillRoundRect(Rectangle rect, int arcWidth, int arcHeight) {
		fillRoundRect(rect.x, rect.y, rect.width, rect.height, arcWidth, arcHeight);
	}

	public void fillRoundRect(java.awt.Rectangle rect, int arcWidth, int arcHeight) {
		fillRoundRect(rect.x, rect.y, rect.width, rect.height, arcWidth, arcHeight);
	}

	@Override
	protected void finalize() {

	}

	public Color getBackground() {
		return g.getBackground();
	}

	public Composite getComposite() {
		return g.getComposite();
	}

	public GraphicsConfiguration getDeviceConfiguration() {
		return g.getDeviceConfiguration();
	}

	public FontRenderContext getFontRenderContext() {
		return g.getFontRenderContext();
	}

	public Paint getPaint() {
		return g.getPaint();
	}

	public Object getRenderingHint(RenderingHints.Key hintKey) {
		return g.getRenderingHint(hintKey);
	}

	public RenderingHints getRenderingHints() {
		return g.getRenderingHints();
	}

	public Stroke getStroke() {
		return g.getStroke();
	}

	public AffineTransform getTransform() {
		return g.getTransform();
	}

	public Shape getClip() {
		return g.getClip();
	}

	public java.awt.Rectangle getClipBounds() {
		return g.getClipBounds();
	}

	public java.awt.Rectangle getClipBounds(java.awt.Rectangle rect) {
		return g.getClipBounds(rect);
	}

	public java.awt.Rectangle getClipRect() {
		return getClipBounds();
	}

	public Color getColor() {
		return g.getColor();
	}

	public Font getFont() {
		return g.getFont();
	}

	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

	public FontMetrics getFontMetrics(Font f) {
		return g.getFontMetrics(f);
	}

	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return g.hit(null, s, onStroke);
	}

	public boolean hitClip(int x, int y, int w, int h) {
		return g.hitClip(x, y, w, h);
	}

	public boolean hitClip(Rectangle rect) {
		return hitClip(rect.x, rect.y, rect.width, rect.height);
	}

	public boolean hitClip(java.awt.Rectangle rect) {
		return hitClip(rect.x, rect.y, rect.width, rect.height);
	}

	public void rotate(double theta) {
		g.rotate(theta);
	}

	public void rotate(double theta, double x, double y) {
		g.rotate(theta, x, y);
	}

	public void scale(double sx, double sy) {
		g.scale(sx, sy);
	}

	public void setAlpha(int type) {
		setAlpha(1.0f, type);
	}

	public void setAlpha(float alpha) {
		setAlpha(alpha, AlphaComposite.SRC_OVER);
	}

	public void setAlpha(float alpha, int type) {
		if (alpha == 1.0f) {
			setComposite(AlphaComposite.getInstance(type));
		} else {
			setComposite(AlphaComposite.getInstance(type, alpha));
		}
	}

	public void setBackground(Color color) {
		g.setBackground(color);
	}

	public void setComposite(Composite composite) {
		g.setComposite(composite);
	}

	public void setClip(int x, int y, int w, int h) {
		g.setClip(x, y, w, h);
	}

	public void setClip(Rectangle rect) {
		setClip(rect.x, rect.y, rect.width, rect.height);
	}

	public void setClip(java.awt.Rectangle rect) {
		setClip(rect.x, rect.y, rect.width, rect.height);
	}

	public void setColor(Color c) {
		g.setColor(c);
	}

	public void setFont(Font f) {
		g.setFont(f);
	}

	public void setPaint(Paint paint) {
		g.setPaint(paint);
	}

	public void setPaintMode() {
		g.setPaintMode();
	}

	public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
		g.setRenderingHint(hintKey, hintValue);
	}

	public void setRenderingHints(Map<?, ?> hints) {
		g.setRenderingHints(hints);
	}

	public void setTransform(AffineTransform tx) {
		g.setTransform(tx);
	}

	public void shear(double shx, double shy) {
		g.shear(shx, shy);
	}

	public void transform(AffineTransform tx) {
		g.transform(tx);
	}

	public void translate(double tx, double ty) {
		g.translate(tx, ty);
	}

	public void translate(int x, int y) {
		g.translate(x, y);
	}

	public void setXORMode(Color xorColor) {
		g.setXORMode(xorColor);
	}

	@Override
	public String toString() {
		return "RenderSpace\n" + g.toString();
	}
}
