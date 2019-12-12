/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

/**
 * System independent key modifier. Key modifiers expressed in mouse events are
 * mapped onto a value of this Enum. This allows to isolate consumers of mouse
 * events for concrete Swing components, except the first one.
 * 
 * @see java.awt.event.MouseEvent
 * 
 * @author Eric Dubuis
 */
public enum KeyModifier {
	/**
	 * No modifier is in effect.
	 */
	NONE,
	/**
	 * Alt-down key is pressed.
	 */
	ALT_DOWN,
	/**
	 * Alt-graph key is pressed.
	 */
	ALT_GRAPH_DOWN,
	/**
	 * Control-key is pressed.
	 */
	CONTROL_DOWN,
	/**
	 * Meta key is pressed.
	 */
	META_DOWN,
	/**
	 * Shift key is pressed.
	 */
	SHIFT_DOWN
}
