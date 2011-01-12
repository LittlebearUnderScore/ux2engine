/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class ImageUtils {
	private static final MediaTracker tracker = new MediaTracker(new JPanel());
	private static final Object trackerLock = new Object();
	private static int trackerID = 0;

	public static final BufferedImage readImage(final InputStream stream)
			throws ImageException {
		return readImage(stream, true, true);
	}

	/**
	 * Reads an image from a stream, then creates a {@link Sprite} for it.
	 * @param stream
	 * @return
	 * @throws IDGImageException
	 */
	public static final BufferedSprite readSprite(final InputStream stream)
			throws ImageException {
		return new BufferedSprite(new BufferedSpriteSource(stream), true);
	}

	public static final BufferedImage readSprite(final SpriteSource source, boolean optimize, boolean sync)
			throws ImageException {
		return readImage(source.getSource(), optimize, sync);
	}

	/**
	 * Attempts to read in an Image and synchronizes it through a media tracker.
	 * @param stream Stream to read from
	 * @param optimize whether to optimize the returned image
	 * @return The read image
	 * @throws IDGImageException If there is an I/O error reading the image
	 */
	public static final BufferedImage readImage(final InputStream stream, boolean optimize, boolean sync)
			throws ImageException {

		try {
			final BufferedImage ret = ImageIO.read(stream);

			if (sync) {
				synchronized (trackerLock) {
					tracker.addImage(ret, trackerID);
					try {
						tracker.waitForID(trackerID);
						tracker.removeImage(ret, trackerID++);
					} catch (InterruptedException interruptedErr) {}
				}
			}

			return optimize ? optimizeImage(ret) : ret;
		} catch (IOException ioErr) {
			throw new ImageException(ioErr);
		}
	}

	/**
	 * Optimizes an Image for the default display.
	 * @param image
	 * @return
	 */
	public static final BufferedImage optimizeImage(final BufferedImage image) {
		return optimizeImage(image,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
	}

	/**
	 * Optimizes an Image for the provided GraphicsConfiguration.
	 * @param image
	 * @param gConf
	 * @return
	 */
	public static final BufferedImage optimizeImage(final BufferedImage image, final GraphicsConfiguration gConf) {
		if (image.getColorModel().equals(gConf.getColorModel())) {
			return image;
		}

		BufferedImage ret = gConf.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g = ret.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return ret;
	}
}
