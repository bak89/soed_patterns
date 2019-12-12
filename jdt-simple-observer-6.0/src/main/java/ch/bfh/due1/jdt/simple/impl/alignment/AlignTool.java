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

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.DefaultTool;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;


/**
 * The align tool allows the user to align selected shapes. ...
 * 
 * @author Eric Dubuis
 */
public class AlignTool extends DefaultTool {

	/**
	 * Field that refers to the user dialog. Field must be accessed via getter
	 * method getDialog().
	 */
	private AlignToolUserSelectionDialog dialog;

	/**
	 * The list of selected shapes.
	 */
	private List<Shape> selectedShapes;

	/**
	 * Creates an instance of the align tool. Creates a user dialog but keeps it
	 * invisible.
	 * 
	 * @param e
	 *            the associated editor
	 */
	public AlignTool(Editor e) {
		super(e, false);
	}

	/**
	 * Gets the selection from the editor and, provided that there are 2 or more
	 * selected shapes, offers the dialog to the user. Otherwise, calls
	 * toolDone() on the editor. The actual alignment is performed upon pressing
	 * a button on the dialog.
	 * 
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#activate()
	 */
	@Override
	public void activate() {
		super.activate();

		// get selected shapes
		this.selectedShapes = getEditor().getSelection();

		if (selectedShapes != null && selectedShapes.size() > 1) {
			// Open a dialog and get user's choice. User's choice results
			// in calling the setAlignXxx() method.
			getDialog().setVisible(true, this);
		} else {
			// If there is only one or less shape selected the do nothing.
			// Tell editor that we're done.
			getEditor().toolDone();
		}
	}

	/**
	 * Calls deactivate() on the superclass and makes the dialog invisible.
	 * 
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#deactivate()
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		getDialog().setVisible(false);
	}

	/**
	 * Callback for GUI dialogs to indicate that we are done with the tool.
	 * Calls toolDone() on the editor.
	 */
	public void toolDone() {
		getEditor().toolDone();
	}

	/**
	 * Registers the command performed by the concrete strategy.
	 * 
	 * @param c
	 *            a command
	 */
	public void registerCommandPerformed(Command c) {
		CommandHandler ch = getEditor().getCommandHandler();
		if (ch != null) {
			ch.addCommand(c);
		}
	}

	/**
	 * Calls performAlignment() with an instance of AlignLeftStrategy.
	 */
	public void setAlignLeft() {
		performAlignment(new AlignLeftStrategy());
	}

	/**
	 * Calls performAlignment() with an instance of
	 * AlignJustifyVerticalStrategy.
	 */
	public void setAlignJustifyVertical() {
		performAlignment(new AlignJustifyVerticalStrategy());
	}

	/**
	 * Calls performAlignment() with an instance of AlignRightStrategy.
	 */
	public void setAlignRight() {
		performAlignment(new AlignRightStrategy());
	}

	/**
	 * Calls performAlignment() with an instance of AlignTopStrategy.
	 */
	public void setAlignTop() {
		performAlignment(new AlignTopStrategy());
	}

	/**
	 * Calls performAlignment() with an instance of
	 * AlignJustifyHorizontalStrategy.
	 */
	public void setAlignJustifyHorizontal() {
		performAlignment(new AlignJustifyHorizontalStrategy());
	}

	/**
	 * Calls performAlignment() with an instance of AlignBottomStrategy.
	 */
	public void setAlignBottom() {
		performAlignment(new AlignBottomStrategy());
	}

	/**
	 * Performs the alignment by using the given align strategy.
	 * 
	 * @param strategy
	 *            an align strategy
	 */
	private void performAlignment(AlignStrategy strategy) {
		strategy.align(this, this.selectedShapes);
	}

	/**
	 * Helper method that returns the dialog. Creates the dialog if it does not
	 * yet exist by calling createDialog() template method.
	 * 
	 * @return the dialog
	 */
	private final synchronized AlignToolUserSelectionDialog getDialog() {
		if (this.dialog == null) {
			this.dialog = createDialog();
		}
		return this.dialog;
	}

	/**
	 * Instantiates a dialog. A subclass may overwrite this method to create an
	 * alternate dialog, e.g., one that does not hinder the testing.
	 * 
	 * @return a dialog
	 */
	protected AlignToolUserSelectionDialog createDialog() {
		return new GetUserSelectionDialog();
	}
}
