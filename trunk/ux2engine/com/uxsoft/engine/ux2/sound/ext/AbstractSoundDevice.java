/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound.ext;

import com.uxsoft.engine.ux2.sound.SoundDevice;
import com.uxsoft.engine.ux2.sound.SoundEngine;

/**
 * A abstract sound device. All Sound devices must extend this class, instead of implementing SoundDevice.
 * @author David
 */
public abstract class AbstractSoundDevice
		implements SoundDevice {
	protected SoundEngine engine;
	protected boolean soundAvailable = false;

	public SoundEngine getEngine() {
		return engine;
	}

	public void setEngine(SoundEngine engine) {
		this.engine = engine;
	}

	public boolean soundAvailable() {
		return soundAvailable;
	}
}
