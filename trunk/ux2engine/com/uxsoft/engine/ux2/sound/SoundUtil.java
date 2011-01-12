/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * Sound Utilities.
 * @author David
 */
public class SoundUtil {
	public static final int
			PLAYBACK_MIXER = 1,
			RECORD_MIXER = 2,
			ALL_MIXERS = 3;

	/**
	 * Lists all the <tt>Mixer</tt> instances supported by this <tt>AudioSystem</tt>
	 * that either playback, record or both.
	 * <br />
	 * type should be one of:
	 * <ul>
	 *	<li>PLAYBACK_MIXER</li>
	 *	<li>RECORD_MIXER</li>
	 *	<li>ALL_MIXERS</li>
	 * </ul>
	 * @param type Whether to return playback mixers, recording mixers or both.
	 * @return List of mixers
	 */
	public static List<Mixer> listMixers(int type) {
		List<Mixer> ret = new LinkedList();
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : infos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info lineInfo = null;
			switch (type) {
				case PLAYBACK_MIXER: {
					lineInfo = new SourceDataLine.Info(SourceDataLine.class, null);
					break;
				}
				case RECORD_MIXER: {
					lineInfo = new TargetDataLine.Info(TargetDataLine.class, null);
					break;
				}
			}
			if (lineInfo == null || mixer.isLineSupported(lineInfo)) {
				ret.add(mixer);
			}
		}

		return ret;
	}

	/**
	 * Returns a list of all mixers on the system.
	 * <br />
	 * This invocation is the same as calling <tt>SoundUtil.listMixers(SoundUtil.ALL_MIXERS);</tt>
	 * @return List of all mixers on the system.
	 */
	public static List<Mixer> listMixers() {
		return listMixers(ALL_MIXERS);
	}

	/**
	 * Gets the Default <tt>Mixer</tt> for the supplied type.
	 * @param type Type of mixer; One of: <tt>PLAYBACK_MIXER</tt>, <tt>RECORD_MIXER</tt> or <tt>ALL_MIXERS</tt>
	 * @return The mixer if one is available, or else <tt>null</tt>
	 */
	public static Mixer getDefaultMixer(int type) {
		List<Mixer> mixerList = listMixers(type);
		Line.Info lineInfo = null;
		switch (type) {
			case PLAYBACK_MIXER: {
				lineInfo = new SourceDataLine.Info(SourceDataLine.class, null);
				break;
			}
			case RECORD_MIXER: {
				lineInfo = new TargetDataLine.Info(TargetDataLine.class, null);
				break;
			}
		}
		for (Mixer mixer : mixerList) {
			if (lineInfo == null || mixer.isLineSupported(lineInfo)) {
				return mixer;
			}
		}

		return null;
	}
}
