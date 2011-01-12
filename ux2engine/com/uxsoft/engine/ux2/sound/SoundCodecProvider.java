/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import com.uxsoft.engine.ux2.sound.ext.AbstractSound;

/**
 * A codec provider provides codecs (audio decoders) for sounds.
 * @author David
 */
public interface SoundCodecProvider {
	/**
	 * Returns a codec for the supplied sound.
	 * @param sound
	 * @return
	 */
	SoundCodec createCodecFor(AbstractSound sound);

	/**
	 * Returns the type of file this codec provider is able to decode.
	 * @return
	 */
	Object getSoundType();
}
