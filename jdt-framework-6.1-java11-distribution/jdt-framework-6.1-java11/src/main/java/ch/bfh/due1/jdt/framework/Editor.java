/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Cursor;
import java.util.List;


/**
 * The interface Editor defines the interface for communicating with an editor.
 * The editor manages registered views and registered tools. It is also
 * responsible to provide a command handler. In addition, tools creating shapes
 * register new shapes with the editor; it is the responsibility of the editor
 * to pass them over to the current view.
 *
 * @author Eric Dubuis
 */
public interface Editor {
	/**
	 * Creates a new view.
	 */
	public void createView();

	/**
	 * Returns the editor's current drawing view.
	 *
	 * @return the current drawing view displayed in this editor.
	 */
	public View getCurrentView();

	/**
	 * Registers a new view to the editor.
	 *
	 * @param view
	 *            a view
	 */
	public void registerView(View view);

	/**
	 * Returns the number of registered views.
	 *
	 * @return the total number of registered views
	 */
	public int getViewCount();

	/**
	 * Sets the current view to be the (ith-1) view. Notice that views are
	 * numbered starting from 0 up to n-1.
	 *
	 * @param ith
	 *            the index indicating which view will be the current one
	 */
	public void setCurrentView(int ith);

	/**
	 * Returns the ith view. Recall that views are numbered starting from 0 up
	 * to n-1.
	 *
	 * @param ith
	 *            the index indicating which view will be returned
	 * @return a view
	 */
	public View getIthView(int ith);

	/**
	 * Registers the default tool.
	 *
	 * @param t
	 *            a tool
	 */
	public void registerDefaultTool(Tool t);

	/**
	 * Registers a tool.
	 *
	 * @param t
	 *            a tool
	 */
	public void registerTool(Tool t);

	/**
	 * Adds given shape to the set of selected shapes.
	 *
	 * @param s
	 *            a shape
	 */
	public void addToSelection(Shape s);

	/**
	 * Removes a shape from the set of selected shapes.
	 *
	 * @param s
	 *            a shape
	 */
	public void removeFromSelection(Shape s);

	/**
	 * Clears the selection.
	 */
	public void clearSelection();

	/**
	 * Returns the selection.
	 *
	 * @return a list of shapes
	 */
	public List<Shape> getSelection();

	/**
	 * Returns an ordered list of all shapes of the current sheet.
	 *
	 * @return an ordered list of shapes of the current sheet, or null
	 */
	public List<Shape> getShapes();

	/**
	 * Returns the handles of selected shapes of the current view.
	 *
	 * @return a list of shape handles
	 */
	public List<ShapeHandle> getSelectionHandles();

	/**
	 * Sets the cursor of the current view.
	 *
	 * @param c
	 *            a cursor
	 */
	public void setCursor(Cursor c);

	/**
	 * Adds a shape to the current sheet.
	 *
	 * @param s
	 *            a shape
	 */
	public void addShape(Shape s);

	/**
	 * Removes a shape from the current sheet.
	 *
	 * @param s
	 *            a shape to be removed
	 * @return true if the given shape was removed
	 */
	public boolean removeShape(Shape s);

	/**
	 * Shows a status message in the editor's user interface.
	 *
	 * @param msg
	 *            The status message to be displayed.
	 */
	public void showStatus(String msg);

	/**
	 * Informs the editor that a tool has done its interaction. This method can
	 * be used to switch back to the default tool.
	 */
	public void toolDone();

	/**
	 * Informs the editor that it should check the state of menu entries and the
	 * like.
	 */
	public void checkEditorState();

	/**
	 * Returns the command handler of the editor.
	 *
	 * @return a command handler
	 */
	public CommandHandler getCommandHandler();

	/**
	 * Returns the specified logger.
	 *
	 * @param logFacility
	 *            The logger facility.
	 * @return The logger.
	 */
	public Logger getLogger(Class<?> logFacility);

	/**
	 * Returns the clip board of the editor.
	 *
	 * @return the only clip board of an editor
	 */
	public Clipboard getClipboard();
}
