/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import java.io.InputStream;

/**
 * Describes a sound codec.
 * @author David
 */
public interface SoundCodec {
	int decodeBytes(InputStream input, byte[] buffer, int maxLength);
}
