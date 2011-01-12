/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input.ext;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;
import com.uxsoft.engine.ux2.input.InputHandler;
import com.uxsoft.engine.ux2.input.KeyAction;
import com.uxsoft.engine.ux2.input.MouseAction;
import com.uxsoft.engine.ux2.input.MouseWheelAction;

/**
 * Supplies a class for mappable input. Keys and Mouse Buttons can be mapped to actions.
 * @author David
 */
public class MappableInputHandler
		implements InputHandler {
	private Map<Integer, KeyAction> keyPressedActions = new HashMap();
	private Map<Integer, KeyAction> keyReleasedActions = new HashMap();
	private MouseAction[] mousePressedActions = new MouseAction[3];
	private MouseAction[] mouseReleasedActions = new MouseAction[3];
	private MouseAction mouseMovedAction;
	private MouseAction mouseDraggedAction;
	private MouseWheelAction mouseWheelAction;
	private boolean[] keyStates = new boolean[1024];

	public MappableInputHandler() {
		for (int i = 0; i < keyStates.length; i++) {
			keyStates[i] = false;
		}
	}

	public void mapKeyPress(int keycode, KeyAction action) {
		mapKey(keycode, action, true);
	}

	public void mapKeyRelease(int keycode, KeyAction action) {
		mapKey(keycode, action, false);
	}

	public void mapKey(int keycode, KeyAction action, boolean pressed) {
		if (pressed) {
			keyPressedActions.put(Integer.valueOf(keycode), action);
		} else {
			keyReleasedActions.put(Integer.valueOf(keycode), action);
		}
	}

	public void mapMouseButtonPress(int mousebtn, MouseAction action) {
		mapMouseButton(mousebtn, action, true);
	}

	public void mapMouseButtonRelease(int mousebtn, MouseAction action) {
		mapMouseButton(mousebtn, action, false);
	}

	public void mapMouseButton(int mousebtn, MouseAction action, boolean pressed) {
		int index = mouseButtonIndex(mousebtn);
		if (pressed) {
			mousePressedActions[index] = action;
		} else {
			mouseReleasedActions[index] = action;
		}
	}
	
	public void mapMouseButtonMove(MouseAction action) {
		mouseMovedAction = action;
	}
	
	public void mapMouseButtonDrag(MouseAction action) {
		mouseDraggedAction = action;
	}

	public void mapMouseWheelAction(MouseWheelAction mouseWheelAction) {
		this.mouseWheelAction = mouseWheelAction;
	}

	public void onKeyPressed(KeyEvent evt) {
		KeyAction action = keyPressedActions.get(Integer.valueOf(evt.getKeyCode()));
		keyStates[evt.getKeyCode()] = true;
		if (action != null) {
			action.onKeyAction(evt);
		}
	}

	public void onKeyReleased(KeyEvent evt) {
		KeyAction action = keyReleasedActions.get(Integer.valueOf(evt.getKeyCode()));
		keyStates[evt.getKeyCode()] = false;
		if (action != null) {
			action.onKeyAction(evt);
		}
	}

	public boolean isKeyDown(int keycode) {
		if (keycode < 0 || keycode >= keyStates.length) {
			throw new IndexOutOfBoundsException();
		}
		return keyStates[keycode];
	}

	private int mouseButtonIndex(int i) {
		switch (i) {
			case MouseEvent.BUTTON1:
				return 0;
			case MouseEvent.BUTTON2:
				return 1;
			case MouseEvent.BUTTON3:
				return 2;
			default:
				throw new IllegalArgumentException(String.valueOf(i));
		}
	}

	public void onMousePressed(MouseEvent evt) {
		int index = mouseButtonIndex(evt.getButton());
		if (mousePressedActions[index] != null) {
			mousePressedActions[index].onMouseAction(evt);
		}
	}

	public void onMouseReleased(MouseEvent evt) {
		int index = mouseButtonIndex(evt.getButton());
		if (mouseReleasedActions[index] != null) {
			mouseReleasedActions[index].onMouseAction(evt);
		}
	}

	public void onMouseWheelMoved(MouseWheelEvent evt) {
		if (mouseWheelAction != null) {
			mouseWheelAction.onMouseWheelAction(evt);
		}
	}

	public void onMouseMoved(MouseEvent evt) {
		if (mouseMovedAction != null) {
			mouseMovedAction.onMouseAction(evt);
		}
	}

	public void onMouseDragged(MouseEvent evt) {
		if (mouseDraggedAction != null) {
			mouseDraggedAction.onMouseAction(evt);
		}
	}
}
