/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene;

import com.uxsoft.engine.ux2.core.IGameObject;

/**
 * An iterator for rendering game objects.
 * @author David
 */
public interface ObjectIterator {
	/**
	 * Returns the next <tt>IGameObject</tt> that this <tt>ObjectIterator</tt> holds, returns <tt>null</tt>
	 * if there are no more objects.
	 * @return Next game object, or <tt>null</tt>
	 */
	public IGameObject next();

	/**
	 * Returns whether there is another object to render after the current one.
	 * @return
	 */
	public boolean hasNext();
}
