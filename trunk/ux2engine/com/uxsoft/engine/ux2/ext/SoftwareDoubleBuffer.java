/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.ext;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import com.uxsoft.engine.ux2.core.BufferedDrawSurface;

/**
 * Implements a multi buffer in software.
 * @author David
 */
public class SoftwareDoubleBuffer
		implements BufferedDrawSurface {
	private int numBuffers;
	private int currentBuffer;
	private int graphicsBuffer;
	private BufferedImage[] buffers;

	public SoftwareDoubleBuffer(int numBuffers) {
		this.numBuffers = numBuffers;
		this.buffers = new BufferedImage[numBuffers];
		this.currentBuffer = this.graphicsBuffer = 0;
	}

	/**
	 * Swaps the current buffer to the next buffer, and uses the current buffer
	 * as the screen renderer.
	 */
	public void switchBuffer() {
		graphicsBuffer = currentBuffer;
		currentBuffer++;
		if (currentBuffer >= numBuffers - 1) {
			currentBuffer = 0;
		}
	}

	public BufferStrategy getDrawBuffer() {
		throw new UnsupportedOperationException();
	}

	public Graphics getBufferGraphics() {
		return buffers[graphicsBuffer].getGraphics();
	}

	public Graphics2D getDrawGraphics() {
		return buffers[graphicsBuffer].createGraphics();
	}
}
