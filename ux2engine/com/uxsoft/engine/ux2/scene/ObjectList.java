/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene;

import com.uxsoft.engine.ux2.core.IGameObject;

/**
 * A <tt>ObjectList</tt> is an interface to abstract rendering list methods, such as methods that
 * sort objects by depth, by visibility, by y-values, etc.
 * @author David
 */
public interface ObjectList {
	/**
	 * Adds this object to this list.
	 * <br />
	 * The list may sort the object, depending on implementation;
	 * It is not guaranteed to insert the object at the end of the list.
	 * @param o Object to add
	 */
	void add(IGameObject o);

	/**
	 * Removes an object from the list.
	 * @param o Object to remove
	 * @return The removed object if it exists; else <tt>null</tt>
	 */
	IGameObject remove(IGameObject o);

	/**
	 * Returns the number of objects in this list.
	 * @return The number of objects
	 */
	int size();

	/**
	 * Returns a {@link ObjectIterator} that can be used to iterate through all the {@link IGameObject}s in this
	 * list.
	 * @return An iterator
	 */
	ObjectIterator iterator();
}
