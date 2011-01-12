/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core.ext;

import com.uxsoft.engine.ux2.FatalIDGEngineException;
import com.uxsoft.engine.ux2.core.GameExecutionManager;
import com.uxsoft.engine.ux2.core.IGameContext;

/**
 *
 * @author David
 */
public class ThreadedExecutionManager
		implements GameExecutionManager {
	private Thread renderThread, logicThread;
	
	public void startUpRenderer() throws FatalIDGEngineException, IllegalStateException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void suspendRenderer() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void resumeRenderer() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void terminateRenderer() throws FatalIDGEngineException, IllegalStateException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void startUpLogics() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void suspendLogics() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void resumeLogics() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void terminateLogics() throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void bindToContext(IGameContext context) throws FatalIDGEngineException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private final class RenderThread
			extends Thread {
		
	}
}
