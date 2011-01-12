/*
 * IDG2Engine Version 12.0.0
 *
 * IDG2Engine is property of IDGames.NET copyright 2008 - 2010.
 * Redistribution of this software in either binary or source form
 * without written permission from IDGames.NET is strictly forbidden.
 *
 * IDG2Engine is a game engine framework targeted for 2D games, utilizing the Java (TM) platform.
 * IDG2Engine provides a high-level interface to lower level drawing, input, and logical functions.
 * IDG2Engine is based around the concept of a scene-graph based game engine.
 * If you need the lower level functions, IDG2Engine exposes those too.
 *
 * Java is Copyright (c) Sun Microsystems.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.IDGEngineException;
import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.math.Size2D;
import com.uxsoft.engine.ux2.scene.IScene;
import com.uxsoft.engine.ux2.util.Sys;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferStrategy;

/**
 * The Core of any Game implemented in IDG2Engine.
 * @author David
 */
public class GameCore
		extends Canvas
		implements
		IGameContext,
		BufferedDrawSurface {
	BufferStrategy drawBuffer;
	volatile IScene scene;
	final Object SceneLock = new Object();
	long lastUpdate = 0;
	boolean vSyncEnabled = true;
	RenderWorker renderer;
	LogicWorker updater;
	RenderSpace renderSpace;
	boolean active = false;

	/**
	 * This method creates the input listeners for this GameCore.
	 * You need not call this method directly, as
	 * {@link IGameContext#initialize(java.awt.Dimension, int, java.awt.BufferCapabilities) }
	 * calls this method.
	 */
	public void createInputListeners() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (scene != null) scene.onKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (scene != null) scene.onKeyReleased(e);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (scene != null) scene.onMousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (scene != null) scene.onMouseReleased(e);
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (scene != null) scene.onMouseDragged(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (scene != null) scene.onMouseMoved(e);
			}
		});
		addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (scene != null) scene.onMouseWheelMoved(e);
			}
		});
	}

	public void createBuffers(int numBuffers, BufferCapabilities caps) {
		if (caps != null) {
			try {
				createBufferStrategy(numBuffers, caps);
			} catch (AWTException error) {
				createBufferStrategy(numBuffers); // Just fallback to default
			}
		} else {
			createBufferStrategy(numBuffers);
		}
		drawBuffer = getBufferStrategy();
	}

	public void startUpRenderer() {
		if (renderer != null) {
			throw new IllegalStateException();
		}
		renderer = new RenderWorker(this);
		renderer.start();
	}

	public void startUpLogics() {
		if (updater != null) {
			throw new IllegalStateException();
		}
		updater = new LogicWorker(this);
		updater.start();
	}

	public BufferStrategy getDrawBuffer() {
		return this.drawBuffer;
	}

	public IScene getScene() {
		return scene;
	}

	public boolean isVSyncEnabled() {
		return vSyncEnabled;
	}

	public void setVSyncEnabled(boolean enabled) {
		this.vSyncEnabled = enabled;
	}

	public boolean gameActive() {
		return active;
	}

	/**
	 * This always throws a {@link NoSuchMethodError}.
	 */
	public boolean initialize(Size2D screensize, int numBuffers, BufferCapabilities caps) {
		throw new NoSuchMethodError();
	}
	
	/**
	 * This method should be overridden if the game is to show alternative content while the display is null.
	 * By default, this just fills in the screen with a black rectangle.
	 * @param g Graphics Context
	 */
	protected void noDisplayRender(final RenderSpace rs) {
		rs.setColor(Color.BLACK);
		rs.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Default Render Operation.
	 */
	protected void defaultRender() {
		if (this.drawBuffer == null) {
			throw new IDGEngineException();
		}
		try {
			final Graphics2D g = (Graphics2D) drawBuffer.getDrawGraphics();
			this.renderSpace.setGraphicsContext(g);
			synchronized (SceneLock) {
				if (scene != null) {
					scene.doRender(renderSpace);
				} else {
					noDisplayRender(renderSpace);
				}
			}
			this.renderSpace.dispose(); // Dispose Graphics Context
			if (!drawBuffer.contentsLost()) {
				drawBuffer.show();
			}
			if (vSyncEnabled) {
				Toolkit.getDefaultToolkit().sync();
			}
		} catch (ClassCastException err) {
			throw new IDGEngineException(err);
		}
	}

	protected void createRenderSpace() {
		if (this.renderSpace == null) {
			this.renderSpace = new RenderSpace();
		}
	}

	public void onRender() {
		defaultRender();
	}

	public void setScene(IScene scene) {
		synchronized (SceneLock) {
			this.scene = scene;
		}
	}

	public void onUpdate() {
		if (lastUpdate == 0) {
			lastUpdate = Sys.systemTime();
			return;
		}
		if (scene != null) {
			scene.doUpdate(Sys.systemTime() - lastUpdate);
		}
		lastUpdate = Sys.systemTime();
	}

	public Graphics2D getDrawGraphics() {
		return (Graphics2D) drawBuffer.getDrawGraphics();
	}

	@Override
	public Graphics getBufferGraphics() {
		return drawBuffer.getDrawGraphics();
	}
}
