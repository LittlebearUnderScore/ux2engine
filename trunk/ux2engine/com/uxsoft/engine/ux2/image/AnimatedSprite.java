/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.image;

import java.util.ArrayList;
import java.util.Collection;
import com.uxsoft.engine.ux2.util.Sys;

/**
 * Represents an animated sprite.
 * @author David
 */
public class AnimatedSprite {
	private ArrayList<Sprite> animation;
	private int frame = 0;
	private long lastUpdate = 0;

	public AnimatedSprite() {
		animation = new ArrayList();
	}

	/**
	 * Creates a new <tt>AnimatedSprite</tt>. If the parameter <tt>spriteList</tt> is an instance of
	 * {@link java.util.ArrayList}, the animation is not copied; it is set to directly point to the
	 * reference of the supplied list. This is useful when you don't want to instantiate thousands of
	 * arrays for the same animation.
	 * @param spriteList A collection of sprites
	 */
	public AnimatedSprite(Collection<Sprite> spriteList) {
		if (spriteList instanceof ArrayList) {
			animation = (ArrayList) (spriteList);
		} else {
			animation = new ArrayList();
			animation.addAll(spriteList);
		}
	}

	/**
	 * Disposes this animated sprite. Releases the animation sequence.
	 */
	public void dispose() {
		animation.clear();
		animation = null;
	}

	/**
	 * Adds a frame to this animation.
	 * @param frame Sprite to add as a new frame
	 */
	public void addFrame(BufferedSprite frame) {
		animation.add(frame);
	}

	/**
	 * Returns the amount of frames this animation has.
	 * @return Number of frames
	 */
	public int frames() {
		return animation.size();
	}

	public void clear() {
		animation.clear();
	}

	/**
	 * Updates the animating state of this animation.
	 * If the last frame is reached, the frame pointer is moved to zero
	 * (Restarts the animation)
	 */
	public void update() {
		if (lastUpdate == 0) {
			lastUpdate = System.currentTimeMillis();
			return;
		}
		Sprite current = animation.get(frame);
		long time = System.currentTimeMillis();
		if (Sys.systemTime() - time >= current.getDelay()) {
			if (frame + 1 >= animation.size()) {
				frame = 0;
			} else {
				frame++;
			}
		}
	}

	/**
	 * Returns the current Sprite this animation should display.
	 * @return The current sprite this animation is showing
	 */
	public Sprite current() {
		return animation.get(frame);
	}

	/**
	 * Sets the delay for each sprite to the specified value.
	 * @param delay
	 */
	public void setAllDelay(int delay) {
		for (int i = 0; i < animation.size(); i++) {
			animation.get(i).setDelay(delay);
		}
	}
}
