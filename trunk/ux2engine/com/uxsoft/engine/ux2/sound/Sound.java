/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

/**
 *
 * @author David
 */
public interface Sound {
	boolean setVolume(int volume);

	int getVolume();

	boolean setMuted(boolean mute);

	boolean isMuted();

	boolean setPan(int pan);

	int getPan();

	void addSoundListener(SoundListener listener);

	boolean removeSoundListener(SoundListener listener);

	SoundListener [] getListeners();

	boolean isLooping();

	boolean isPlaying();

	boolean isPaused();

	void pause()
			throws IDGSoundException;

	void stop()
			throws IDGSoundException;

	void resume()
			throws IDGSoundException;
}
