/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import java.awt.Graphics2D;
import com.uxsoft.engine.ux2.math.Size2D;
import com.uxsoft.engine.ux2.scene.IScene;
import java.awt.BufferCapabilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GameWindow implements IGameWindow and creates the base of a BufferStrategy graphical system.
 * <p>
 * To create a basic game:
 * <pre>
 * GameWindow window = new GameWindow();
 * window.initialize(size, buffers, caps);
 * window.startUpEngine();
 * window.setVisible(true);
 * </pre>
 * </p>
 * Of course, to actually make the game playable (IE: have a scene) you must implement your own Scene class,
 * most likely by extending {@link idgengine.scene.AbstractScene} or {@link idgengine.scene.BaseScene} or
 * implementing {@link idgengine.scene.IScene}.
 * The class {@link idgengine.scene.AbstractScene} implements basic methods for you to set up a scene very quickly.
 * <p>
 * For a game to handle input, either the appropriate Input handler must be overriden in
 * {@link idgengine.scene.IScene}, or adding a {@link idgengine.input.InputHandler} to a
 * {@link idgengine.scene.BaseScene}.
 * </p>
 * @author David
 */
public abstract class GameWindow
		extends
		JFrame
		implements
		BufferedDrawSurface,
		IGameContext,
		IGameWindow {
	protected GameCore core = null;

	/**
	 * Initializes this window for display, {@link JFrame#setVisible(boolean) } should be invoked
	 * to show the window, and any other details such as <tt>setResizable</tt>, should be invoked
	 * before calling <tt>setVisible</tt>. The initialization phase also takes care of window insets,
	 * and such, and it also packs the JFrame ready for use.
	 * @param screensize Screen size to use
	 * @param numBuffers Number of buffers to use
	 * @param caps Buffering capabilities to use [optional]
	 */
	public boolean initialize(Size2D screensize, int numBuffers, BufferCapabilities caps) {
		if (core != null) {
			throw new IllegalStateException();
		}
		Dimension sizecopy = screensize.dimension();
		setSize(sizecopy);
		setPreferredSize(sizecopy);
		JPanel content = (JPanel) this.getContentPane();
		content.setPreferredSize(sizecopy);
		content.setSize(sizecopy);
		core = new GameCore();
		core.setPreferredSize(sizecopy);
		core.setSize(sizecopy);
		content.add(core);
		pack();
		Insets insets = getInsets();
		Dimension nScreensize = new Dimension(sizecopy.width + insets.left + insets.right,
				sizecopy.height + insets.top + insets.bottom);
		setPreferredSize(nScreensize);
		core.createBuffers(numBuffers, caps);
		this.setFocusTraversalKeysEnabled(false);
		this.setIgnoreRepaint(true);
		core.requestFocus();
		core.createInputListeners();
		core.createRenderSpace();
		registerWindowListener();
		core.active = true;
		// Clean up
		sizecopy = null;
		content = null;
		insets = null;
		nScreensize = null;

		return true;
	}

	private void registerWindowListener() {
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				onWindowOpened(e);
			}

			public void windowClosing(WindowEvent e) {
				onWindowClosing(e);
			}

			public void windowClosed(WindowEvent e) {
				onWindowClosed(e);
			}

			public void windowIconified(WindowEvent e) {
				onWindowMinimized(e);
			}

			public void windowDeiconified(WindowEvent e) {
				onWindowMaximized(e);
			}

			public void windowActivated(WindowEvent e) {
				onWindowActivated(e);
			}

			public void windowDeactivated(WindowEvent e) {
				onWindowDeactivated(e);
			}
		});
	}
	
	public void onWindowActivated(WindowEvent evt) {
		
	}
	
	public void onWindowDeactivated(WindowEvent evt) {
		
	}

	public void onWindowOpened(WindowEvent evt) {

	}

	public void onWindowClosed(WindowEvent evt) {
		
	}

	/**
	 * This method by default invokes {@link System#exit(int) }.
	 * If you want to add a custom close event handler, override this method.
	 * @param evt Window event descriptor
	 */
	public void onWindowClosing(WindowEvent evt) {
		System.exit(0);
	}

	public void onWindowMaximized(WindowEvent evt) {

	}

	public void onWindowMinimized(WindowEvent evt) {

	}

	public void onUpdate() {
		core.onUpdate();
	}

	public IScene getScene() {
		return core.getScene();
	}

	public void onRender() {
		core.onRender();
	}

	public void setScene(IScene scene) {
		core.setScene(scene);
	}

	/**
	 * This returns whether the game is still active.
	 * @return true if active; false otherwise.
	 */
	public boolean gameActive() {
		return core.gameActive();
	}

	/**
	 * Terminates both the Rendering and Logic/Updating modules of this game.
	 */
	public void terminateGame() {
		core.active = false;
	}

	public BufferStrategy getDrawBuffer() {
		return core.getDrawBuffer();
	}

	public void startUpRenderer() {
		core.startUpRenderer();
	}

	public void startUpLogics() {
		core.startUpLogics();
	}

	public void startUpEngine() {
		startUpRenderer();
		startUpLogics();
	}

	public boolean isVSyncEnabled() {
		return core.isVSyncEnabled();
	}

	public void setVSyncEnabled(boolean enabled) {
		core.setVSyncEnabled(enabled);
	}

	public Graphics2D getDrawGraphics() {
		return core.getDrawGraphics();
	}

	public Graphics getBufferGraphics() {
		return core.getBufferGraphics();
	}
}
