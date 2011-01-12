/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.core;

import com.uxsoft.engine.ux2.FatalIDGEngineException;

/**
 *
 * @author David
 */
public interface GameExecutionManager {
	/**
	 * Starts up the Renderer. If a Renderer is already started, this method throws an {@link IllegalStateException}.
	 */
	void startUpRenderer()
			throws FatalIDGEngineException, IllegalStateException;

	/**
	 * Suspends the Renderer.
	 * @throws SeriousIDGEngineException
	 */
	void suspendRenderer()
			throws FatalIDGEngineException;

	/**
	 * Resumes the Renderer.
	 * @throws SeriousIDGEngineException
	 */
	void resumeRenderer()
			throws FatalIDGEngineException;

	/**
	 * Terminates the Renderer. After the Renderer is terminated, it must be started again via {@link startUpRenderer()}
	 * If a Renderer isn't already started, this method throws an {@link IllegalStateException}
	 * @throws SeriousIDGEngineException
	 */
	void terminateRenderer()
			throws FatalIDGEngineException, IllegalStateException;

	void startUpLogics()
			throws FatalIDGEngineException;

	void suspendLogics()
			throws FatalIDGEngineException;

	void resumeLogics()
			throws FatalIDGEngineException;

	void terminateLogics()
			throws FatalIDGEngineException;

	void bindToContext(IGameContext context)
			throws FatalIDGEngineException;
}
