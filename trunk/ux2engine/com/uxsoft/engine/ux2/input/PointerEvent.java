/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author David
 */
public class PointerEvent {
	public static final int
			MAX_PRESSURE = 1023
	;

	private int clickCount = 1;
	private int x, y;
	private Button button;
	private InputSource inputSource;
	private int pressure = MAX_PRESSURE;
	private long when;

	public static enum Button {
		NONE,
		MOUSE_BUTTON1,
		MOUSE_BUTTON2,
		MOUSE_BUTTON3,
		TABLET_PEN,
		TABLET_ERASER
	}

	public static enum InputSource {
		MOUSE,
		TABLET
	}

	public static PointerEvent newFromTablet(int tx, int ty, int button, int pressure) {
		PointerEvent evt = new PointerEvent();
		evt.x = tx;
		evt.y = ty;
		evt.button = button == 2 ? Button.TABLET_ERASER : Button.TABLET_PEN;
		evt.pressure = pressure;
		evt.inputSource = InputSource.TABLET;
		return evt;
	}

	public static PointerEvent newFromMouse(MouseEvent e) {
		return newFromMouse(e.getX(), e.getY(), e.getButton(), e.getClickCount());
	}

	public static PointerEvent newFromMouse(int mx, int my, int button, int clickCount) {
		PointerEvent evt = new PointerEvent();
		evt.x = mx;
		evt.y = my;
		evt.clickCount = clickCount;
		evt.inputSource = InputSource.MOUSE;
		switch (button) {
			case MouseEvent.BUTTON1:
				evt.button = Button.MOUSE_BUTTON1;
				break;
			case MouseEvent.BUTTON2:
				evt.button = Button.MOUSE_BUTTON2;
				break;
			case MouseEvent.BUTTON3:
				evt.button = Button.MOUSE_BUTTON3;
				break;
			case 0:
				evt.button = Button.NONE;
				break;
			default:
				throw new IllegalArgumentException("Invalid button: " + button);
		}

		return evt;
	}

	public boolean isFromTablet() {
		return inputSource == InputSource.TABLET;
	}

	public boolean isFromMouse() {
		return inputSource == InputSource.MOUSE;
	}

	public boolean isLeftButton() {
		return button == Button.TABLET_PEN || button == Button.MOUSE_BUTTON1;
	}

	public boolean isRightButton() {
		return button == Button.TABLET_ERASER || button == Button.MOUSE_BUTTON3;
	}

	public Button getButton() {
		return button;
	}

	public int getPressure() {
		return pressure;
	}

	public InputSource getSource() {
		return inputSource;
	}

	public int getClickCount() {
		return clickCount;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getPoint() {
		return new Point(x, y);
	}
}
