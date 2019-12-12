/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework;

import java.util.List;

/**
 * An abstraction of a clip board for shapes.
 */
public interface Clipboard {

	/**
	 * Adds the given list of shapes to the clip board
	 * 
	 * @param shapes
	 *            a list of shapes
	 */
	public void put(List<Shape> shapes);

	/**
	 * Returns the content of the clip board.
	 * 
	 * @return a list of shapes
	 */
	public List<Shape> get();

	/**
	 * Removes the given list of shapes to the clip board
	 * 
	 * @param shapes
	 *            a list of shapes
	 */
	public void remove(List<Shape> shapes);

	/**
	 * Clears the content of the clip board.
	 */
	public void clear();
}
