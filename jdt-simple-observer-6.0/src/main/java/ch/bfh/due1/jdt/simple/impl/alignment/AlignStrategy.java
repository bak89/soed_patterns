/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.alignment;

import java.util.List;

import ch.bfh.due1.jdt.framework.Shape;


/**
 * Defines a common interface for different strategies to align selected shapes.
 * 
 * @author Eric Dubuis
 */
public interface AlignStrategy {

	/**
	 * Aligns the given list of shapes according to a concrete strategy. If the
	 * given tool is not null then the strategy can register a command that
	 * performed the alignment, thus, allowing a user to undo the alignment
	 * later.
	 * 
	 * @param tool
	 *            the align tool, can be null
	 * @param selectedShapes
	 *            a list of shapes; the first element of the list is the
	 *            reference element
	 */
	public void align(AlignTool tool, List<Shape> selectedShapes);
}
