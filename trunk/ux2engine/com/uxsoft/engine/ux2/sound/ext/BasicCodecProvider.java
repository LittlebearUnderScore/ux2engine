/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound.ext;

import java.io.IOException;
import java.io.InputStream;
import com.uxsoft.engine.ux2.sound.IDGSoundException;
import com.uxsoft.engine.ux2.sound.Sound;
import com.uxsoft.engine.ux2.sound.SoundCodec;
import com.uxsoft.engine.ux2.sound.SoundCodecProvider;
import com.uxsoft.engine.ux2.sound.SoundType;

/**
 *
 * @author David
 */
public class BasicCodecProvider
		implements SoundCodecProvider {
	public static final SoundCodec CODEC = new SoundCodec() {
		public int decodeBytes(InputStream input, byte[] buffer, int maxLength) {
			try {
				return input.read(buffer, 0, maxLength);
			} catch (IOException ex) {
				throw new IDGSoundException("NullCodec:Decode Exception", ex);
			}
		}
	};

	public SoundCodec createCodecFor(AbstractSound sound) {
		sound.initializeLine(null);
		return CODEC;
	}

	public Object getSoundType() {
		return SoundType.TYPE_ANYSUPPORTED;
	}

}
