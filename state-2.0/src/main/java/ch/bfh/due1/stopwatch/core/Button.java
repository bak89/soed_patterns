/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * Abstraction of the stop watch's button.
 */
public interface Button {

	/**
	 * Sets the text on the button.
	 * 
	 * @param string
	 *            a text
	 */
	void setText(String string);
}
