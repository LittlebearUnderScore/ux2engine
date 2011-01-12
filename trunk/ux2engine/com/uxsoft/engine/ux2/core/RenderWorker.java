/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.scene.IScene;
import com.uxsoft.engine.ux2.util.ThreadUtil;

/**
 * Render worker thread. Package-private to prevent misuse.
 * @author David
 */
final class RenderWorker
		extends Thread {
	private IGameContext context;

	public RenderWorker(IGameContext context) {
		super("IDGEngine-RenderWorker");
		setDaemon(true);
		this.context = context;
	}

	@Override
	public void run() {
		while (context.gameActive()) {
			IScene scene = context.getScene();
			context.onRender();
			if (scene != null) {
				ThreadUtil.sleep(scene.getRenderDelay());
			} else {
				ThreadUtil.sleep(20);
			}
		}
	}
}
