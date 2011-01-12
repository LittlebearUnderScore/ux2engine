/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.graphics.RenderSpace;
import java.awt.Color;
import java.awt.Dimension;
import com.uxsoft.engine.ux2.scene.SceneRenderer;

/**
 * The <tt>BlankRenderer</tt> is a renderer that renders a blank rectangle of any color to the screen.
 * @author David
 */
public class BlankRenderer
		implements SceneRenderer {
	private Color background;
	private Dimension size;

	/**
	 * Creates an instance of <tt>BlankRenderer</tt> with a background color of white.
	 * @param size
	 */
	public BlankRenderer(Dimension size) {
		this(size, Color.WHITE);
	}

	/**
	 * Creates an instance of <tt>BlankRenderer</tt> with a custom background color.
	 * @param size
	 * @param background
	 */
	public BlankRenderer(Dimension size, Color background) {
		this.background = background;
		this.size = size;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public void doRender(RenderSpace rs) {
		rs.setColor(background);
		rs.fillRect(0, 0, size.width, size.height);
	}
}
