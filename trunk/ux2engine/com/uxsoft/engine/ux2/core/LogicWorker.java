/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.scene.IScene;
import com.uxsoft.engine.ux2.util.ThreadUtil;
import com.uxsoft.engine.ux2.util.Timer;

/**
 *
 * @author David
 */
final class LogicWorker
		extends Thread {
	private IGameContext context;
	private Timer timer;

	public LogicWorker(IGameContext context) {
		super("IDGEngine-LogicWorker");
		this.context = context;
		this.timer = new Timer();
		setDaemon(true);
	}

	@Override
	public void run() {
		while (context.gameActive()) {
			timer.start();
			context.onUpdate();
			IScene scene = context.getScene();
            if (scene != null && (timer.getTicks() < 1000/scene.getUpdateFrequency())) {
                // Sleep the remaining time
                ThreadUtil.sleep(Math.max(1, (1000/scene.getUpdateFrequency()) - timer.getTicks()));
            }
		}
	}
}
