/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * This interface for a factory for the creation of the states of a simple stop
 * watch. Allows the support of different (consistent) state implementations.
 * <p>
 * Limitation: The set of states must be fixed.
 */
public interface StateFactory {

	/**
	 * Creates the zero (initial) state.
	 * 
	 * @param sw
	 *            the stop watch as the context
	 * @return a Zero state
	 * @throws Exception
	 *             if the instance for this state cannot be created
	 */
	public State createIdleState(StopWatch sw) throws Exception;

	/**
	 * Creates the intermediate state where the stop watch displays the
	 * intermediate time.
	 * 
	 * @param sw
	 *            the stop watch as the context
	 * @return a Intermediate state
	 * @throws Exception
	 *             if the instance for this state cannot be created
	 */
	public State createIntermediateState(StopWatch sw) throws Exception;

	/**
	 * Creates the running state.
	 * 
	 * @param sw
	 *            the stop watch as the context
	 * @return a Running state
	 * @throws Exception
	 *             if the instance for this state cannot be created
	 */
	public State createRunningState(StopWatch sw) throws Exception;

	/**
	 * Creates the stopped state.
	 * 
	 * @param sw
	 *            the stop watch as the context
	 * @return a Stopped state
	 * @throws Exception
	 *             if the instance for this state cannot be created
	 */
	public State createStoppedState(StopWatch sw) throws Exception;
}
