/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import com.uxsoft.engine.ux2.util.DebugUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

/**
 * An {@link AsyncSoundPlayer} is a sound player with a thread pool using an
 * asynchronous read/write non-blocking method to play sounds concurrently
 * using a few, limited number of threads.
 * @author David
 */
public class AsyncSoundPlayer {
	private Deque<SoundInfo> queuedSounds = new LinkedList();
	private final Object poolSync = new Object();
	private final Object queueSync = new Object();
	private boolean shutDown = false;
	private List<Player> playerPool;
	private int poolsize;
	private AudioFormat playBackFormat;
	private AtomicInteger playingSounds = new AtomicInteger(0);
	private int maxPlayingSoundsPerPool = 32;
	private Mixer mixer;
	private boolean soundAvailable = false;
	private float masterVolume = 1.0f;

	/**
	 * Creates a {@link AsyncSoundPlayer} with a pool size of one, and the default mixer.
	 */
	public AsyncSoundPlayer() {
		this(1, null);
	}

	public AsyncSoundPlayer(int poolsize, Mixer useMixer) {
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		if (infos == null || infos.length < 1) {
			throw new SoundUnavailableException();
		}
		if (poolsize <= 0) {
			throw new IllegalArgumentException();
		}
		if (useMixer != null) {
			this.mixer = useMixer;
		} else {
			this.mixer = SoundUtil.getDefaultMixer(SoundUtil.PLAYBACK_MIXER);
		}
		this.poolsize = poolsize;
		soundAvailable = true;
		//this.playBackFormat = playBackFormat;
	}

	public float getMasterVolume() {
		return masterVolume;
	}

	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	public void start() {
		if (!soundAvailable) {
			throw new SoundUnavailableException();
		}
		if (playerPool == null) {
			playerPool = new LinkedList();
			for (int i = 0; i < poolsize; i++) {
				Player p = new Player();
				p.start();
				playerPool.add(p);
			}
			try {
				mixer.open();
			} catch (LineUnavailableException ex) {
				throw new IDGSoundException(ex);
			}
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * Stops the Internal Thread Pool that this {@link AsyncSoundPlayer} runs on.
	 * This method blocks until all threads in the thread pool finish.
	 */
	public void stop() {
		stop(0);
	}

	/**
	 * Waits at most {@code maxwait} milliseconds for this {@link AsyncSoundPlayer} to die.
	 * @param maxwait Max waiting time in milliseconds
	 */
	public void stop(int maxwait) {
		if (playerPool != null) {
			shutDown = true;
			try {
				synchronized (poolSync) {
					Iterator<Player> pItr = playerPool.iterator();
					while (pItr.hasNext()) {
						Player p = pItr.next();
						p.join(maxwait);
					}
				}
			} catch (InterruptedException ex) {}
			playerPool = null;
			mixer.close();
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * Sets the Pool size of this <tt>AsyncSoundPlayer</tt>.
	 * <br />
	 * If this method is called while the sound player is active, it will throw a {@link IllegalArgumentException}.
	 * @param ps New pool size
	 * @throws IllegalStateException If the sound player is still active
	 */
	public void setPoolSize(int ps)
			throws IllegalStateException {
		if (playerPool != null) {
			throw new IllegalStateException();
		}
		poolsize = ps;
	}

	/**
	 * Gets the pool size that this sound player uses.
	 * @return Pool size
	 */
	public int getPoolsize() {
		return poolsize;
	}

    public AudioInputStream getAudioInputStream(InputStream is) {
        try {
            if (!is.markSupported()) {
                is = new BufferedInputStream(is);
            }

            // open the source stream
            AudioInputStream source = AudioSystem.getAudioInputStream(is);

            // convert to playback format
            return playBackFormat != null ? convertStream(source) : source;
        } catch (UnsupportedAudioFileException ex) {
            throw new IDGSoundException(ex);
        } catch (IOException ex) {
            throw new IDGSoundException(ex);
        } catch (IllegalArgumentException ex) {
            throw new IDGSoundException(ex);
        }
    }

	/**
	 * Stops all sounds playing from this sound player.
	 * @param instant Whether it should instantly stop playing, or wait until all sounds have finished playing.
	 */
	public void stopAllSounds(boolean instant) {
		Iterator<Player> pItr = playerPool.iterator();
		while (pItr.hasNext()) {
			Player p = pItr.next();
			p.stopAllSounds(instant);
		}
		playingSounds.set(0);
	}

	static int getMaxDefaultMixerLines(AudioFormat format) {
		try {
			Mixer mixer = AudioSystem.getMixer(null);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			return mixer.getMaxLines(info);
		} catch (Exception e) {
			DebugUtil.logTrace(null, e);
			return -1;
		}
	}

	private AudioInputStream convertStream(AudioInputStream stream) {
		if (playBackFormat == null) {
			return stream;
		}
		try {
			return AudioSystem.getAudioInputStream(playBackFormat, stream);
		} catch (IllegalArgumentException e) {
			DebugUtil.logTrace(e);
			return stream;
		}
	}

	/**
	 * Plays a sound instantly.
	 * @param input Input
	 * @return sound info context
	 */
	public SoundInfo playSound(InputStream input) {
		return playSound(input, true);
	}

	/**
	 * Creates a {@link SoundInfo} context for the given input, and optionally plays the sound on one of the sound
	 * player threads.
	 * <p>If <tt>instant</tt> is false, only the context is created. To play the sound you must invoke
	 * {@link #playSound(renoria.sound.AsyncSoundPlayer.SoundInfo) } to pass it to one of the sound player threads.
	 * @param input Input to read from
	 * @param instant Whether we should instantly play the sound
	 * @return Sound Context
	 */
	public SoundInfo playSound(InputStream input, boolean instant) {
		if (!soundAvailable) {
			throw new SoundUnavailableException();
		}
		AudioInputStream stream = getAudioInputStream(input);
		AudioFormat format = stream.getFormat();
		SoundInfo info = new SoundInfo();
		info.format = format;
		info.input = stream;
		info.length = stream.getFrameLength();
		info.player = this;
		SourceDataLine line = null;
		DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, format);
		try {
			try {
				line = (SourceDataLine) mixer.getLine(dinfo); // Try using this mixer first
			} catch (Throwable t) {
				line = (SourceDataLine) AudioSystem.getLine(dinfo); // Fallback to another mixer on the system
			}
			if (line == null) {
				throw new SoundUnavailableException();
			}
			line.open(format);
			if (line.isControlSupported(BooleanControl.Type.MUTE)) {
				info.muteControl = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
			}
			if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				info.gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
			}
			if (line.isControlSupported(FloatControl.Type.BALANCE)) {
				info.balanceControl = (FloatControl) line.getControl(FloatControl.Type.BALANCE);
			}
			if (line.isControlSupported(FloatControl.Type.PAN)) {
				info.panControl = (FloatControl) line.getControl(FloatControl.Type.PAN);
			}
		} catch (Exception e) {
			throw new IDGSoundException(e);
		}

		line.start();
		info.line = line;
		if (instant) {
			playSound(info);
		}

		return info;
	}

	public List<SoundInfo> allSoundsPlaying() {
		List<SoundInfo> ret = new ArrayList();
		synchronized (poolSync) {
			Iterator<Player> players = playerPool.iterator();
			while (players.hasNext()) {
				Player p = players.next();
				synchronized (p.soundSync) {
					ret.addAll(p.playList);
				}
			}
		}

		return ret;
	}

	public int numPlayingSounds() {
		return playingSounds.get();
	}

	public int getMaxPlayingSoundsPerPool() {
		return maxPlayingSoundsPerPool;
	}

	public void setMaxPlayingSoundsPerPool(int maxPlayingSoundsPerPool) {
		if (playerPool != null) {
			throw new IllegalStateException();
		}
		this.maxPlayingSoundsPerPool = maxPlayingSoundsPerPool;
	}

	public AudioFormat getPlayBackFormat() {
		return playBackFormat;
	}

	public void setPlayBackFormat(AudioFormat playBackFormat) {
		this.playBackFormat = playBackFormat;
	}

	public void playSound(SoundInfo info) {
		if (!soundAvailable) {
			throw new SoundUnavailableException();
		}
		synchronized (poolSync) {
			Iterator<Player> players = playerPool.iterator();
			Player min = null;
			int mini = Integer.MAX_VALUE;
			while (players.hasNext()) {
				Player mplay = players.next();
				int psize = mplay.playList.size();
				if (psize < mini && psize < maxPlayingSoundsPerPool && !mplay.inListLoop) {
					mini = mplay.playList.size();
					min = mplay;
				}
			}
			if (min != null) {
				synchronized (min.soundSync) {
					min.playList.add(info);
				}
				playingSounds.incrementAndGet();
			} else {
				synchronized (queueSync) {
					queuedSounds.addLast(info);
				}
			}
		}
	}

	private SoundInfo pollQueuedSound() {
		synchronized (queueSync) {
			return queuedSounds.poll();
		}
	}

	final class Player
			extends Thread {
		List<SoundInfo> playList = new LinkedList();
		final Object soundSync = new Object();
		boolean inListLoop = false;

		Player() {
			super("IDGEngine-ASPThread");
		}

		public void stopAllSounds(boolean instant) {
			synchronized (soundSync) {
				int i = 0;
				Iterator<SoundInfo> pInfo = playList.iterator();
				while (pInfo.hasNext()) {
					SoundInfo info = pInfo.next();
					if (info.line.isOpen()) {
						if (!instant) {
							info.line.drain();
						}
						info.line.close();
						try {
							info.input.close();
						} catch (IOException e) {}
					}
					pInfo.remove();
					i++;
				}
				AsyncSoundPlayer.this.playingSounds.addAndGet(-i);
			}
		}

		@Override
		public void run() {
			try {
				byte[] buf = new byte[1024];
				while (!shutDown) {
					synchronized (soundSync) {
						Iterator<SoundInfo> pInfo = playList.iterator();
						while (pInfo.hasNext()) {
							inListLoop = true;
							SoundInfo info = pInfo.next();
							long oldpos = info.line.getLongFramePosition();
							long mspos = info.line.getMicrosecondPosition();
							try {
								int avail = info.line.available();
								int read = info.input.read(buf, 0, Math.min(avail, buf.length));
								if (read > 0) {
									info.line.write(buf, 0, read);
									if (info.listeners != null) {
										synchronized (info.listenerLock) {
											Iterator<SoundListener> listeners = info.listeners.iterator();
											while (listeners.hasNext()) {
												SoundListener listener = listeners.next();
												listener.update(buf, 0, read);
												listener.positionUpdate(oldpos, info.line.getLongFramePosition(), mspos);
											}
										}
									}
								} else if (!info.line.isRunning() ||
										!info.line.isActive() ||
										!info.line.isOpen() ||
										info.line.getLongFramePosition() >= info.length) {
									info.line.drain();
									info.line.close();
									info.input.close();
									pInfo.remove();
									AsyncSoundPlayer.this.playingSounds.decrementAndGet();
									if (info.listeners != null) {
										synchronized (info.listenerLock) {
											Iterator<SoundListener> listeners = info.listeners.iterator();
											while (listeners.hasNext()) {
												SoundListener listener = listeners.next();
												listener.stopped();
											}
										}
									}
								} else {
									if (info.listeners != null) {
										synchronized (info.listenerLock) {
											Iterator<SoundListener> listeners = info.listeners.iterator();
											while (listeners.hasNext()) {
												SoundListener listener = listeners.next();
												listener.positionUpdate(oldpos, info.line.getLongFramePosition(), mspos);
											}
										}
									}
								}
							} catch (IOException e) {
								throw new IDGSoundException(e);
							}
						}
						inListLoop = false;
						if (playList.size() < maxPlayingSoundsPerPool) {
							SoundInfo nextSound = pollQueuedSound();
							if (nextSound != null) {
								synchronized (soundSync) {
									playList.add(nextSound);
									if (nextSound.listeners != null) {
										synchronized (nextSound.listenerLock) {
											Iterator<SoundListener> listeners = nextSound.listeners.iterator();
											while (listeners.hasNext()) {
												SoundListener listener = listeners.next();
												listener.started();
											}
										}
									}
									AsyncSoundPlayer.this.playingSounds.incrementAndGet();
								}
							}
						}
						try {
							Thread.sleep(1);
						} catch (Exception e) {}
					}
					try {
						Thread.sleep(1);
					} catch (Exception e) {}
				}
			} catch (Throwable t) {
				DebugUtil.logTrace(t);
			}
		}
	}

	public static final class SoundInfo {
		private AudioFormat format;
		private SourceDataLine line;
		private AudioInputStream input;
		private BooleanControl muteControl;
		private FloatControl gainControl;
		private FloatControl balanceControl;
		private FloatControl panControl;
		private long length;
		private float volume = 1.0f;
		private List<SoundListener> listeners = null;
		private final Object listenerLock = new Object();
		private AsyncSoundPlayer player;

		private SoundInfo() {
		}

		public boolean isMuteAvailable() {
			return muteControl != null;
		}

		public boolean isGainAvailable() {
			return gainControl != null;
		}

		public boolean isBalanceAvailable() {
			return balanceControl != null;
		}

		public boolean isPanAvailable() {
			return panControl != null;
		}

		public AudioFormat getFormat() {
			return format;
		}

		public FloatControl getBalanceControl() {
			return balanceControl;
		}

		public FloatControl getGainControl() {
			return gainControl;
		}

		public BooleanControl getMuteControl() {
			return muteControl;
		}

		public FloatControl getPanControl() {
			return panControl;
		}

		public void setVolume(float volume) {
			this.volume = volume * player.masterVolume;
			setVolumeIntern();
		}

		private void setVolumeIntern() {
			if (gainControl != null) {
				if (volume <= 0) {
					if (muteControl != null) {
						muteControl.setValue(true);
					} else {
						gainControl.setValue(gainControl.getMinimum());
					}
				} else {
					if (muteControl != null) {
						muteControl.setValue(false);
					}

					float max = gainControl.getMaximum();
					float min = gainControl.getMinimum();
					float vol = (float) (Math.log(volume) / Math.log(10.0) * 20.0);

					vol = Math.max(min, Math.min(max, vol));
					gainControl.setValue(vol);
				}
			}
		}

		public AudioInputStream getInput() {
			return input;
		}

		public long getLength() {
			return length;
		}

		public SourceDataLine getLine() {
			return line;
		}

		public float getVolume() {
			return volume;
		}

		public void addSoundListener(SoundListener listener) {
			if (listeners == null) {
				listeners = new LinkedList();
			}
			synchronized (listenerLock) {
				listeners.add(listener);
			}
		}

		public void removeSoundListener(SoundListener listener) {
			if (listeners == null) {
				throw new IllegalStateException();
			}
			synchronized (listenerLock) {
				listeners.remove(listener);
			}
		}

		public SoundListener[] getListeners() {
			return listeners.toArray(new SoundListener[listeners.size()]);
		}
	}
}
