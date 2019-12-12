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
import java.util.Collection;
import java.util.List;


/**
 * This interface represents views being used for Sheet model objects.
 * 
 * <p>
 * A view must act as a listener for its only sheet. Thus, it must register a
 * {@link SheetChangedListener} with its associated sheet.
 * <p>
 * The sheet can be the receiver for commands.
 * 
 * @see ch.bfh.due1.jdt.framework.Sheet
 * @author Eric Dubuis
 */
public interface View {
	/**
	 * Associates a Sheet object to this view.
	 * 
	 * @param s
	 *            The sheet. Can be null.
	 */
	public void setSheet(Sheet s);

	/**
	 * Returns the associated editor.
	 * 
	 * @return The editor.
	 */
	public Editor getEditor();

	/**
	 * Returns the shapes of the associated sheet.
	 * 
	 * @return a list of shapes
	 */
	public List<Shape> getShapes();

	/**
	 * Associates an Editor object to this view.
	 * 
	 * @param editor
	 *            The editor. If null an IllegalArgumentException is thrown.
	 */
	public void setEditor(Editor editor);

	/**
	 * Returns the associated tool, if any.
	 * 
	 * @return The associated tool. Can be null.
	 */
	public Tool getTool();

	/**
	 * Associates a tool to this view.
	 * 
	 * @param tool
	 *            The tool. Can be null.
	 */
	public void setTool(Tool tool);

	/**
	 * Adds a shape to the sheet associated to this view.
	 * 
	 * @param s
	 *            a shape
	 */
	public void addShape(Shape s);

	/**
	 * Removes a shape from the sheet associated to this view.
	 * 
	 * @param s
	 *            a shape to be removed
	 * @return true if the given shape was removed
	 */
	public boolean removeShape(Shape s);

	/**
	 * Sets the selected shapes.
	 * 
	 * @param s
	 *            A (perhaps empty) list of shapes.
	 */
	public void addToSelection(Shape s);

	/**
	 * Removes a shape from the selected shapes.
	 * 
	 * @param s
	 *            A shape.
	 */
	public void removeFromSelection(Shape s);

	/**
	 * Clears the selection.
	 */
	public void clearSelection();

	/**
	 * Returns the selection.
	 * 
	 * @return The list of the selection.
	 */
	public List<Shape> getSelection();

	/**
	 * Returns a sublist of shapes, ordered along the stacking order. The
	 * sublist contains only the shapes given in the collection.
	 * 
	 * @param shapes
	 *            A collection of shapes.
	 * 
	 * @return A list of the shapes of the view's sheet, ordered along the
	 *         stacking order, or null.
	 */
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes);

	/**
	 * Returns the handles of selected shapes.
	 * 
	 * @return A list of handles.
	 */
	public List<ShapeHandle> getSelectionHandles();

	/**
	 * Sets the cursor of the View.
	 * 
	 * @param c
	 *            The current cursor.
	 */
	public void setCursor(Cursor c);

	/**
	 * Creates a memento and returns it to the caretaker.
	 * @return a memento
	 */
	public Memento createMemento();

	/**
	 * Sets the memento to restore its internal state.
	 */
	public void setMemento(Memento m);
}
