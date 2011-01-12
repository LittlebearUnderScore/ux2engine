/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.font;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import com.uxsoft.engine.ux2.graphics.RenderSpace;

/**
 *
 * @author David
 */
public class SpriteFont {
	private BufferedImage[] fontCharacters;

	public void drawFont(RenderSpace g, String text, int x, int y, ImageObserver observer) {
		char[] textData = text.toCharArray();
		int currentDrawX = x;
		for (int i = 0; i < textData.length; i++) {
			g.drawImage(fontCharacters[textData[i]], currentDrawX, y, observer);
			currentDrawX += fontCharacters[textData[i]].getWidth();
		}
	}
}
