/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2011
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection.state;

import ch.bfh.due1.jdt.simple.selection.SelectionTool;
import ch.bfh.due1.jdt.simple.selection.SelectionToolState;
import ch.bfh.due1.jdt.simple.selection.StateFactory;


/**
 * A factory for creating state instances for the selection tool.
 */
public class StateFactoryImpl implements StateFactory {

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.StateFactory#createDragAreaState(ch.bfh.due1.jdt.simple.selection.SelectionTool)
	 */
	@Override
	public SelectionToolState createDragAreaState(SelectionTool context) {
		return new DragAreaState(context);
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.StateFactory#createDragHandleState(ch.bfh.due1.jdt.simple.selection.SelectionTool)
	 */
	@Override
	public SelectionToolState createDragHandleState(SelectionTool context) {
		return new DragHandleState(context);
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.StateFactory#createInitState(ch.bfh.due1.jdt.simple.selection.SelectionTool)
	 */
	@Override
	public SelectionToolState createInitState(SelectionTool context) {
		return new InitState(context);
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.StateFactory#createMovingState(ch.bfh.due1.jdt.simple.selection.SelectionTool)
	 */
	@Override
	public SelectionToolState createMovingState(SelectionTool context) {
		return new MovingState(context);
	}
}
