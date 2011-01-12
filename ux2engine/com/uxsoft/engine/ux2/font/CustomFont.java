/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.font;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;

/**
 *
 * @author David
 */
public class CustomFont extends Font {

	public CustomFont(Font font) {
		super(font);
	}

	public CustomFont(Map<? extends Attribute, ?> attributes) {
		super(attributes);
	}

	public CustomFont(String name, int style, int size) {
		super(name, style, size);
	}

	@Override
	public GlyphVector layoutGlyphVector(FontRenderContext frc, char[] text, int start, int limit, int flags) {
		return super.layoutGlyphVector(frc, text, start, limit, flags);
	}

}
