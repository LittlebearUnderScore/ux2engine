/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.input.ext;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;
import com.uxsoft.engine.ux2.input.InputHandler;

/**
 * A chained input handler chains together a collection of input handlers. A single call to an event will invoke the handler in each chained handler.
 * @author David
 */
public class ChainedInputHandler
		implements InputHandler {
	private LinkedList<InputHandler> handlers = new LinkedList();
	private final Object lock = new Object();

	public void chainHandler(InputHandler handler) {
		synchronized (lock) {
			handlers.add(handler);
		}
	}

	public void unchainHandler(InputHandler handler) {
		synchronized (lock) {
			handlers.remove(handler);
		}
	}

	public void onKeyPressed(KeyEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onKeyPressed(evt);
			}
		}
	}

	public void onKeyReleased(KeyEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onKeyReleased(evt);
			}
		}
	}

	public void onMousePressed(MouseEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onMousePressed(evt);
			}
		}
	}

	public void onMouseReleased(MouseEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onMouseReleased(evt);
			}
		}
	}

	public void onMouseWheelMoved(MouseWheelEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onMouseWheelMoved(evt);
			}
		}
	}

	public void onMouseMoved(MouseEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onMouseMoved(evt);
			}
		}
	}

	public void onMouseDragged(MouseEvent evt) {
		synchronized (lock) {
			for (InputHandler handler : handlers) {
				handler.onMouseDragged(evt);
			}
		}
	}
}
