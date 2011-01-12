/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.scene.ObjectIterator;
import com.uxsoft.engine.ux2.scene.ObjectList;

/**
 * Implements a Doubly Linked List used for fast rendering, with depth sorting.
 * <br />
 * The object with the largest depth is at the front, the object with the smallest depth is at the back.
 * @author David
 */
public class LinkedObjectList
		implements ObjectList {
	private SortedNode head, tail, marked;
	private int size;

	/**
	 * This function adds a value to the list, using the <tt>compareTo</tt> function to compare values.
	 * @param value
	 */
	public void add(IGameObject value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (head == null && tail == null) {
			head = tail = new SortedNode(null, null, value);
		} else {
			SortedNode current = head;
			boolean inserted = false;
			while (current != null) {
				if (value.getDepth()-current.value.getDepth() < 0) {
					// less than this value. Insert.
					insertBefore(current, value);
					inserted = true;
					break;
				}
				current = current.next;
			}
			if (!inserted) { // Not inserted, so this is one of the largest values
				insertLast(value);
			}
		}
		size++;
	}

	public void insertFromFront(IGameObject value) {
		add(value);
	}

	public void insertFromBehind(IGameObject value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (head == null && tail == null) {
			head = tail = new SortedNode(null, null, value);
		} else {
			SortedNode current = tail;
			boolean inserted = false;
			while (current != null) {
				if (value.getDepth()-current.value.getDepth() > 0) {
					// less than this value. Insert.
					insertAfter(current, value);
					inserted = true;
					break;
				}
				current = current.prev;
			}
			if (!inserted) { // Not inserted, so this is one of the largest values
				insertLast(value);
			}
		}
		size++;
	}

	/**
	 * This function removes a value if it is found in the list and returns it.
	 * If it is not found, it will return <tt>null</tt>
	 * @param value
	 * @return
	 */
	public IGameObject remove(IGameObject value) {
		if (value == null) {
			throw new NullPointerException();
		}
		SortedNode current = head;
		while (current != null) {
			if (current.value.equals(value)) {
				IGameObject ret = current.value;
				unlink(current);
				size--;
				return ret;
			}
			current = current.next;
		}

		return null;
	}

	/**
	 * This function returns the size of the list.
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether this list is empty
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		if (o == null) { // SortedList cannot contain nulls
			return false;
		}
		SortedNode current = head;
		while (current != null) {
			if (current.value.equals(o)) {
				return true;
			}
			current = current.next;
		}

		return false;
	}

	public IGameObject[] toArray() {
		IGameObject[] array = new IGameObject[size];
		SortedNode current = head;
		for (int i = 0; current != null; i++) {
			array[i] = current.value;
			current = current.next;
		}

		return array;
	}

	public  IGameObject[] toArray(IGameObject[] a) {
		if (a.length >= size) {
			SortedNode current = head;
			for (int i = 0; current != null; i++) {
				a[i] = current.value;
				current = current.next;
			}
			return a;
		} else {
			return (toArray());
		}
	}

	public void clear() {
		size = 0;
		head = tail = null;
	}

	public IGameObject get(int index) {
		if (index < size/2) {
			SortedNode current = head;
			for (int i = 0; current != null; i++) {
				if (i == index) {
					return current.value;
				}
				current = current.next;
			}
		} else {
			SortedNode current = tail;
			for (int i = size-1; current != null; i--) {
				if (i == index) {
					return current.value;
				}
				current = current.prev;
			}
		}

		return null;
	}

	public IGameObject set(int index, IGameObject element) {
		throw new UnsupportedOperationException();
	}

	public void add(int index, IGameObject element) {
		throw new UnsupportedOperationException();
	}

	public IGameObject remove(int index) {
		if (index < size/2) {
			SortedNode current = head;
			for (int i = 0; current != null; i++) {
				if (i == index) {
					IGameObject ret =current.value;
					unlink(current);
					return ret;
				}
				current = current.next;
			}
		} else {
			SortedNode current = tail;
			for (int i = size-1; current != null; i--) {
				if (i == index) {
					IGameObject ret = current.value;
					unlink(current);
					return ret;
				}
				current = current.prev;
			}
		}

		return null;
	}

	public int indexOf(Object o) {
		SortedNode current = head;
		for (int i = 0; current != null; i++) {
			if (current.value.equals(o)) {
				return i;
			}
			current = current.next;
		}

		return -1;
	}

	public int lastIndexOf(Object o) {
		SortedNode current = tail;
		for (int i = size-1; current != null; i--) {
			if (current.value.equals(o)) {
				return i;
			}
			current = current.prev;
		}

		return -1;
	}

	public void chopOffFromTail(int index) { // XXX: Todo
		if (index < size/2) {
			SortedNode current = head;
			for (int i = 0; current != null; i++) {
				if (i == index) {
					IGameObject ret = current.value;
					head = current.next;
					unlink(current);
				}
				current = current.next;
			}
		} else {
			SortedNode current = tail;
			for (int i = size-1; current != null; i--) {
				if (i == index) {
					IGameObject ret = current.value;
					unlink(current);
				}
				current = current.prev;
			}
		}
	}

	public SortedIterator sortedIterator(int index) {
		if (index < (size()>>1)) {
			SortedNode current = head;
			for (int i = 0; current != null; i++) {
				if (i == index) {
					return new SortedIterator(current, i);
				}
				current = current.next;
			}
		} else {
			SortedNode current = tail;
			for (int i = size()-1; current != null; i--) {
				if (i == index) {
					return new SortedIterator(current, i);
				}
				current = current.prev;
			}
		}

		return null;
	}

	public ObjectIterator iterator() {
		return new SortedIterator(head, 0);
	}

	public class SortedIterator
			implements ObjectIterator {
		private SortedNode current, lastReturned;
		private int index;

		SortedIterator(SortedNode current, int index) {
			this.current = current;
			this.index = index;
		}

		public boolean hasNext() {
			return current != null;
		}

		public IGameObject next() {
			IGameObject val = current.value;
			lastReturned = current;
			current = current.next;
			index++;
			return val;
		}

		public void remove() {
			unlink(current);
		}

		public boolean hasPrevious() {
			return current != null;
		}

		public IGameObject previous() {
			IGameObject val = current.value;
			lastReturned = current;
			current = current.prev;
			index--;
			return val;
		}

		public int nextIndex() {
			return index+1;
		}

		public int previousIndex() {
			return index-1;
		}

		public void set(IGameObject e) {
			lastReturned.value = e;
		}

		public void add(IGameObject e) {
			insertBefore(current, e);
		}

		public void chopBackwards() {

		}
	}

	/**
	 * This function inserts a value before a specified node.
	 * @param node
	 * @param value
	 */
	private void insertBefore(SortedNode node, IGameObject value) {
		SortedNode newNode = new SortedNode(node.prev, node, value);
		if (node == head) {
			head = newNode;
		}
	}

	private void insertAfter(SortedNode node, IGameObject value) {
		SortedNode newNode = new SortedNode(node, node.next, value);
		if (node == tail) {
			tail = newNode;
		}
	}

	/**
	 * This function replaces the tail with a node containing the provided value.
	 * @param value
	 */
	private void insertLast(IGameObject value) {
		SortedNode newNode = new SortedNode(tail, null, value);
		tail = newNode;
	}

	/**
	 * This function unlinks a node. It also frees the object that the node references.
	 * @param node
	 */
	private void unlink(SortedNode node) {
		if (node == head) { // if we are the head, unlink head
			if (node.next != null) {
				node.next.prev = null;
				head = node.next;
			} else { // head is only link, unlink all
				head = tail = null;
			}
		} else if (node == tail) { // if we are the tail, unlink head
			if (node.prev != null) {
				node.prev.next = null;
				tail = node.prev;
			} else { // tail is only link, unlink all
				head = tail = null;
			}
		} else { // if we are nothing, unlink both
			SortedNode next = node.next; // these are then linked together
			SortedNode prev = node.prev;
			next.prev = prev;
			prev.next = next;
		}
		node.unlink(); // remove references
	}

	private class SortedNode {
		SortedNode prev, next;
		IGameObject value;

		public SortedNode(SortedNode prev, SortedNode next, IGameObject value) {
			this.prev = prev;
			this.next = next;
			this.value = value;
			if (prev != null) {
				prev.next = this;
			}
			if (next != null) {
				next.prev = this;
			}
		}

		void unlink() {
			prev = next = null;
			value = null;
		}
	}
}
