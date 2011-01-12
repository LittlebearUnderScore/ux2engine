/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound.ext;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.uxsoft.engine.ux2.sound.IDGSoundException;
import com.uxsoft.engine.ux2.sound.SoundCodec;
import com.uxsoft.engine.ux2.sound.SoundCodecProvider;
import com.uxsoft.engine.ux2.sound.SoundDeviceType;
import com.uxsoft.engine.ux2.sound.SoundUnavailableException;
import com.uxsoft.engine.ux2.sound.SoundUtil;

/**
 *
 * @author David
 */
public class JavaSoundDevice
		extends AbstractSoundDevice {
	private Mixer mixer;

	public JavaSoundDevice(Mixer mixer) {
		this.mixer = mixer;
		if (mixer == null) {
			soundAvailable = false;
		} else {
			try {
				mixer.open();
				soundAvailable = true;
			} catch (LineUnavailableException e) {
				soundAvailable = false;
			}
		}
	}

	public JavaSoundDevice() {
		this(SoundUtil.getDefaultMixer(SoundUtil.PLAYBACK_MIXER));
	}

	public Object getType() {
		return SoundDeviceType.TYPE_JAVASOUND;
	}

	public AbstractSound open(Object argument, InputStream stream) {
		if (!soundAvailable) {
			throw new SoundUnavailableException();
		}
		/*if (argument instanceof AudioInputStream) {
			return open((AudioInputStream) argument);
		} else if (argument instanceof InputStream) {
			try {
				InputStream is = (InputStream) argument;
				if (!is.markSupported()) {
					is = new BufferedInputStream(is);
				}
				AudioInputStream stream = AudioSystem.getAudioInputStream(is);
				return open(stream);
			} catch (UnsupportedAudioFileException ex) {
				throw new IDGSoundException(ex);
			} catch (IOException ex) {
				throw new IDGSoundException(ex);
			}
		} else if (argument instanceof AudioFormat) {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, (AudioFormat) argument, AudioSystem.NOT_SPECIFIED);

            if (!AudioSystem.isLineSupported(info)) {
                throw new IDGSoundException("Line " + info + " not supported.");
            }

			return open(info);
		}

		return null;*/
		SoundCodecProvider provider = engine.fetchProvider(argument);
		if (provider == null) {
			throw new IDGSoundException("No codec registered to handle stream type: " + argument);
		}
		JavaSound sound = new JavaSound();
		sound.stream = stream;
		return sound;
	}

//	private Sound open(DataLine.Info info) {
//		SourceDataLine line = null;
//		try {
//			try {
//				line = (SourceDataLine) mixer.getLine(info);
//			} catch (Throwable t) {
//				line = (SourceDataLine) AudioSystem.getLine(info);
//			}
//		} catch (LineUnavailableException ex) {
//			throw new SoundUnavailableException(ex);
//		}
//
//		return new JavaSound(line);
//	}

//	private Sound open(AudioInputStream arg) {
//		SourceDataLine line = null;
//		DataLine.Info info = new DataLine.Info(SourceDataLine.class, (arg).getFormat());
//		try {
//			try {
//				line = (SourceDataLine) mixer.getLine(info);
//			} catch (Throwable t) {
//				line = (SourceDataLine) AudioSystem.getLine(info);
//			}
//			if (line == null) {
//				throw new SoundUnavailableException();
//			}
//			line.open(arg.getFormat());
//		} catch (LineUnavailableException ex) {
//			throw new SoundUnavailableException(ex);
//		}
//
//		return new JavaSound(line, arg, arg.getFrameLength());
//	}

	public boolean close() {
		try {
			mixer.close();
			return true;
		} catch (SecurityException blah) {
			return false;
		}
	}

	public class JavaSound
			extends AbstractSound {
		SourceDataLine line;
		BooleanControl mute;
		FloatControl gain, balance, pan;
		float volume;
		long length;
		boolean paused, playing;

		public int renderDecodedBytes(byte[] input, int offset, int length) {
			return line.write(input, offset, length);
		}

		public boolean close() {
			try {
				line.close();
			} catch (SecurityException whatTheFuck) {
				return false;
			}

			return true;
		}

		public boolean setVolume(int volume) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public int getVolume() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public boolean setMuted(boolean mute) {
			if (this.mute == null) {
				return false;
			}
			this.mute.setValue(mute);
			return true;
		}

		public boolean isMuted() {
			return mute != null && mute.getValue();
		}

		public boolean setPan(int pan) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public int getPan() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public boolean blockingDrain() {
			line.drain();
			return true;
		}

		public boolean isOpen() {
			return line.isOpen();
		}

		public long getFramePosition() {
			return line.getFramePosition();
		}

		public long getMicrosecondsElapsed() {
			return line.getMicrosecondPosition();
		}

		public int availableToRender() {
			return line.available();
		}

		public boolean reachedEndOfSound() {
			return 
					!line.isOpen() ||
					(line.getLongFramePosition() >= length && length > 0);
		}

		public void playWithCodec(SoundCodec codec) {
			line.start();
			this.codec = codec;
		}

		private void initializeControls() {
			if (line.isControlSupported(BooleanControl.Type.MUTE)) {
				mute = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
			}
			if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				gain = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
			}
			if (line.isControlSupported(FloatControl.Type.BALANCE)) {
				balance = (FloatControl) line.getControl(FloatControl.Type.BALANCE);
			}
			if (line.isControlSupported(FloatControl.Type.PAN)) {
				pan = (FloatControl) line.getControl(FloatControl.Type.PAN);
			}
		}

		public void initializeLine(Object arg) {
			if (arg instanceof AudioFormat) {
				AudioFormat format = (AudioFormat) arg;
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);

				if (!AudioSystem.isLineSupported(info)) {
					throw new IDGSoundException("Line " + info + " not supported.");
				}
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(format);
				} catch (Exception e) {
					throw new IDGSoundException(e);
				}
			} else if (arg == null) {
				AudioInputStream as = null;
				if (stream instanceof AudioInputStream) {
					as = (AudioInputStream) stream;
				} else {
					if (!stream.markSupported()) {
						stream = new BufferedInputStream(stream);
					}
					try {
						as = AudioSystem.getAudioInputStream(stream);
					} catch (UnsupportedAudioFileException ex) {
						throw new IDGSoundException("Unable to find decoder for sound", ex);
					} catch (IOException ex) {
						throw new IDGSoundException("I/O Exception while initializing sound line", ex);
					}
				}
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, as.getFormat());
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(as.getFormat());
				} catch (Exception e) {
					throw new IDGSoundException(e);
				}
				length = as.getFrameLength();
			}

			if (line != null) {
				initializeControls();
				return;
			}

			throw new IDGSoundException("Reached end of InitializeLine, this means that a line could not be allocated.");
		}

		public boolean isPlaying() {
			return playing && !paused;
		}

		public boolean isPaused() {
			return paused;
		}

		public synchronized void pause() throws IDGSoundException {
			if (paused) {
				throw new IDGSoundException("Sound is already paused.");
			}
			paused = true;
			playing = false;
		}

		public synchronized void stop() throws IDGSoundException {
			if (!playing) {
				throw new IDGSoundException("Sound is already stopped.");
			}
			playing = false;
		}

		public synchronized void resume() throws IDGSoundException {
			if (playing) {
				throw new IDGSoundException("Sound is already playing.");
			}
			playing = true;
			paused = false;
		}
	}

}
