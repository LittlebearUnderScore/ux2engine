/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.math.Size2D;
import com.uxsoft.engine.ux2.scene.IScene;
import java.awt.BufferCapabilities;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * Base of Game Applets.
 * @author David
 */
public abstract class GameApplet
		extends 
		JApplet
		implements
		IGameContext,
		BufferedDrawSurface {
	private GameCore core;

	public boolean gameActive() {
		return core.gameActive();
	}

	public IScene getScene() {
		return core.getScene();
	}

	public boolean initialize(Size2D screensize, int numBuffers, BufferCapabilities caps) {
		core = new GameCore();
		final Dimension appSize = screensize.dimension();
		core.setSize(appSize);
		JPanel content = (JPanel) getContentPane();
		content.setPreferredSize(appSize);
		content.add(core);
		core.createBuffers(numBuffers, caps);
		core.createInputListeners();
		core.requestFocus();
		core.active = true;

		return true;
	}

	public boolean isVSyncEnabled() {
		return core.isVSyncEnabled();
	}

	public void onRender() {
		core.onRender();
	}

	public void onUpdate() {
		core.onUpdate();
	}

	public void setVSyncEnabled(boolean enabled) {
		core.setVSyncEnabled(enabled);
	}

	public BufferStrategy getDrawBuffer() {
		return core.getDrawBuffer();
	}
}
