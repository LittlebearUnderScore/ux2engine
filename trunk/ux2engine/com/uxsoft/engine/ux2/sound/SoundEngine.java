/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.uxsoft.engine.ux2.sound.ext.AbstractSound;
import com.uxsoft.engine.ux2.sound.ext.AbstractSoundDevice;
import com.uxsoft.engine.ux2.util.DebugUtil;

/**
 *
 * @author David
 */
public class SoundEngine {
	private AbstractSoundDevice device;
	private Map<Object, SoundCodecProvider> codecs = new HashMap<Object, SoundCodecProvider>();
	private Deque<AbstractSound> queuedSounds = new LinkedList();
	private final Object poolSync = new Object();
	private final Object queueSync = new Object();
	private boolean shutDown = false;
	private List<Player> playerPool;
	private int poolsize;
	private AtomicInteger playingSounds = new AtomicInteger(0);
	private int maxPlayingSoundsPerPool = 32;
	private float masterVolume = 1.0f;

	public SoundEngine(AbstractSoundDevice device) {
		if (device == null) {
			throw new IDGSoundException("Sound Device is null");
		}
		this.device = device;
		device.setEngine(this);
	}

	public void startup(int poolSize) {
		if (poolSize < 1 || poolSize > 1000) {
			throw new IllegalArgumentException("Invalid pool size: " + poolSize);
		}
		this.poolsize = poolSize;
		playerPool = new ArrayList(poolSize);
		for (int i = 0; i < poolSize; i++) {
			Player player = new Player();
			playerPool.add(player);
			player.start();
		}
	}

	public Sound playSound(InputStream stream, Object soundType) {
		SoundCodecProvider codecProvider = codecs.get(soundType);
		if (codecProvider == null) {
			throw new IDGSoundException("No codec associated with file type");
		}
		AbstractSound ret = device.open(soundType, stream);
		SoundCodec codec = codecProvider.createCodecFor(ret);
		ret.playWithCodec(codec);
		playSound(ret);
		return ret;
	}

	private void playSound(AbstractSound info) {
		if (!device.soundAvailable()) {
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

	private AbstractSound pollQueuedSound() {
		synchronized (queueSync) {
			return queuedSounds.poll();
		}
	}

	public void registerCodec(SoundCodecProvider codec, Object soundType) {
		codecs.put(soundType, codec);
	}

	public SoundCodecProvider fetchProvider(Object type) {
		return codecs.get(type);
	}

	final class Player
			extends Thread {
		List<AbstractSound> playList = new LinkedList();
		final Object soundSync = new Object();
		boolean inListLoop = false;

		Player() {
			super("IDGEngine-ASPThread");
		}

		public void stopAllSounds(boolean instant) {
			synchronized (soundSync) {
				int i = 0;
				Iterator<AbstractSound> pInfo = playList.iterator();
				while (pInfo.hasNext()) {
					AbstractSound info = pInfo.next();
					if (info.isOpen()) {
						if (!instant) {
							info.blockingDrain();
						}
						info.close();
						//try {
						//	info.input.close();
						//} catch (IOException e) {}
					}
					pInfo.remove();
					i++;
				}
				playingSounds.addAndGet(-i);
			}
		}

		@Override
		public void run() {
			try {
				byte[] buf = new byte[2<<15];
				while (!shutDown) {
					synchronized (soundSync) {
						Iterator<AbstractSound> pInfo = playList.iterator();
						while (pInfo.hasNext()) {
							inListLoop = true;
							AbstractSound info = pInfo.next();
							long oldpos = info.getFramePosition();
							long mspos = info.getMicrosecondsElapsed();
							try {
								if (!info.isPaused()) {
									int avail = info.availableToRender();
									int avail2 = info.getInput().available();
									int read = info.getCodec().decodeBytes(info.getInput(), buf, Math.min(avail2,Math.min(avail, buf.length)));
									if (read > 0) {
										//info.renderDecodedBytes(buf, 0, read);
										List<SoundListener> listeners = info.getListenersList();
										if (listeners != null) {
											for (int i = 0; i < listeners.size(); i++) {
												SoundListener listener = listeners.get(i);
												listener.update(buf, 0, read);
												listener.positionUpdate(oldpos, info.getFramePosition(), mspos);
											}
										}
									} else if (info.reachedEndOfSound()) {
										info.blockingDrain();
										info.close();
										info.getInput().close();
										pInfo.remove();
										playingSounds.decrementAndGet();
										List<SoundListener> listeners = info.getListenersList();
										if (listeners != null) {
											for (int i = 0; i < listeners.size(); i++) {
												SoundListener listener = listeners.get(i);
												listener.stopped();
											}
										}
									} else {
										List<SoundListener> listeners = info.getListenersList();
										if (listeners != null) {
											for (int i = 0; i < listeners.size(); i++) {
												SoundListener listener = listeners.get(i);
												listener.update(buf, 0, read);
												listener.positionUpdate(oldpos, info.getFramePosition(), mspos);
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
							AbstractSound nextSound = pollQueuedSound();
							if (nextSound != null) {
								synchronized (soundSync) {
									playList.add(nextSound);
									List<SoundListener> listeners = nextSound.getListenersList();
									if (listeners != null) {
										for (int i = 0; i < listeners.size(); i++) {
											SoundListener listener = listeners.get(i);
											listener.started();
										}
									}
									playingSounds.incrementAndGet();
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
}
