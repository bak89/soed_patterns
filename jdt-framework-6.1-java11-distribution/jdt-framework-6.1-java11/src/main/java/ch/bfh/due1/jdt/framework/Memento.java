/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework;

import java.io.Serializable;

/**
 * Marker interface for a memento. The concrete methods are only known by the
 * originator.
 */
public interface Memento extends Serializable {
	// Intentionally left empty.
}
