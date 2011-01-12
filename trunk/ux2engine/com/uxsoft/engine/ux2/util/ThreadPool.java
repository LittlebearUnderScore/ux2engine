/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.util;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A thread pool is a pool (group) of threads that are used to execute tasks.
 * @author David
 */
public class ThreadPool extends ThreadGroup {
    private static final AtomicInteger threadPoolID = new AtomicInteger(0);
    private boolean isAlive;
    private LinkedList<Runnable> taskQueue;
    private int threadID;
	private int numThreads;

	/**
	 * Creates a new thread pool, and immediately starts it running.
	 * @param numThreads
	 */
    public ThreadPool(int numThreads) {
        super("IDGEngine-ThreadPool-" + (threadPoolID.getAndIncrement()));
		this.numThreads = numThreads;
        setDaemon(true);
        isAlive = true;
        taskQueue = new LinkedList();
    }

	/**
	 * Starts this thread poool.
	 * @throws IllegalStateException if this thread pool is already started.
	 */
	public void start() {
		if (isAlive) {
			throw new IllegalStateException();
		}
        for (int i = 0; i < numThreads; i++) {
            new PooledThread().start();
        }
	}

	/**
	 * Runs a task on this thread pool.
	 * @param task Task to run
	 */
    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }

        if (task != null) {
            taskQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask()
			throws InterruptedException {
        while (taskQueue.size() == 0) {
            if (!isAlive) {
                return null;
            }

            wait();
        }

        return taskQueue.removeFirst();
    }

	/**
	 * Closes this thread pool, and returns immediately. This method interrupts the thread group.
	 */
    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }

	/**
	 * Synchronously closes down the thread pool. This method waits until each thread is stopped before returning.
	 */
    public void shutDown() {
        // notify all waiting threads that this ThreadPool is no
        // longer alive
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        // wait for all threads to finish
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);

        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {}
        }
    }

    /**
     * A PooledThread is a Thread in a ThreadPool group, designed
     * to run tasks (Runnables).
     */
    protected class PooledThread extends Thread {
        public PooledThread() {
            super(ThreadPool.this, "PooledThread-" + (threadID++));
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                Runnable task = null;

                try {
                    task = getTask();
                } catch (InterruptedException ex) {}

                if (task == null) {
                    break;
                }

                try {
                    task.run();
                } catch (Throwable t) {
                    uncaughtException(this, t);
                }
            }
        }
    }
}
