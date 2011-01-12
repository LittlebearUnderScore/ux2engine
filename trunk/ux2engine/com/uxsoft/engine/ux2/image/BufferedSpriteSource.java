/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 *
 * @author David
 */
public class BufferedSpriteSource
		implements SpriteSource {
	private BufferedInputStream stream;

	public BufferedSpriteSource(InputStream stream) {
		if (!(stream instanceof BufferedInputStream)) {
			BufferedInputStream bufferedStream = new BufferedInputStream(stream);
			bufferedStream.mark(Integer.MAX_VALUE);
			this.stream = bufferedStream;
		} else {
			this.stream = (BufferedInputStream) stream;
			this.stream.mark(Integer.MAX_VALUE);
		}
	}

	public InputStream getSource() {
		return stream;
	}

	public BufferedInputStream getBufferedSource() {
		return stream;
	}
}
