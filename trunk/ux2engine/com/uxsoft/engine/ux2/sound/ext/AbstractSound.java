/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound.ext;

import java.io.InputStream;
import java.util.ArrayList;
import com.uxsoft.engine.ux2.sound.Sound;
import com.uxsoft.engine.ux2.sound.SoundCodec;
import com.uxsoft.engine.ux2.sound.SoundListener;

/**
 *
 * @author David
 */
public abstract class AbstractSound
		implements Sound {
	protected ArrayList<SoundListener> listeners = new ArrayList();
	protected SoundCodec codec = null;
	protected InputStream stream = null;
	protected boolean looping = false;

	public ArrayList<SoundListener> getListenersList() {
		return listeners;
	}

	public abstract int renderDecodedBytes(byte[] input, int offset, int length);

	public abstract void initializeLine(Object arg);

	public abstract boolean isOpen();

	public abstract boolean blockingDrain();

	public abstract boolean close();

	public abstract long getFramePosition();

	public abstract long getMicrosecondsElapsed();

	public abstract int availableToRender();

	public InputStream getInput() {
		return stream;
	}

	public abstract boolean reachedEndOfSound();

	public SoundCodec getCodec() {
		return codec;
	}

	public abstract void playWithCodec(SoundCodec codec);

	public void addSoundListener(SoundListener listener) {
		listeners.add(listener);
	}

	public boolean removeSoundListener(SoundListener listener) {
		return listeners.remove(listener);
	}

	public SoundListener[] getListeners() {
		return listeners.toArray(new SoundListener[listeners.size()]);
	}

	public boolean isLooping() {
		return looping;
	}
}
