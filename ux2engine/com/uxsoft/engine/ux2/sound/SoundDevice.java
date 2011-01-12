/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import java.io.InputStream;
import com.uxsoft.engine.ux2.sound.ext.AbstractSound;

/**
 *
 * @author David
 */
public interface SoundDevice {
	/**
	 * Returns the type of sound device this is. For example, a JavaSound Sound device returns SoundDeviceType.TYPE_JAVASOUND.
	 * @return
	 */
	Object getType();

	/**
	 * Returns a sound created by opening the supplied stream with the supplied type. Fetches the required codec and begins decoding the stream.
	 * @param type
	 * @param stream
	 * @return
	 */
	AbstractSound open(Object type, InputStream stream);

	/**
	 * Closes this sound device and releases any native resources allocated by creating it.
	 * @return
	 */
	boolean close();

	/**
	 * Returns whether sound is playable from this sound device.
	 */
	boolean soundAvailable();
}
