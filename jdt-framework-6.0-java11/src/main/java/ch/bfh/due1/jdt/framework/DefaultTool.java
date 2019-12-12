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
 * Provides default implementations for a tool's methods. Can be used as base
 * class of concrete subclasses. The default implementations of the methods do
 * nothing except logging.
 * 
 * @author Eric Dubuis
 */
public class DefaultTool implements Tool {

	/** The associated editor of this tool. */
	private Editor editor;

	/**
	 * If true (by default) clears the editor's selected shapes when calling
	 * activate().
	 */
	private boolean needToClearSelections = true;

	/**
	 * Constructs a tool that does nothing.
	 * 
	 * @param e
	 *            the associated editor
	 */
	protected DefaultTool(Editor e) {
		this.editor = e;
	}

	/**
	 * Constructs a tool that does nothing.
	 * 
	 * @param e
	 *            the associated editor
	 * @param needToClearSelections
	 *            sets the need to clear selection flag
	 */
	protected DefaultTool(Editor e, boolean needToClearSelections) {
		this.editor = e;
		this.needToClearSelections = needToClearSelections;
	}

	/**
	 * Returns the current logger. To be called by subclasses when needed.
	 * 
	 * @return The current logger.
	 */
	final protected Logger getLogger() {
		return getEditor().getLogger(this.getClass());
	}

	/**
	 * Returns the associated editor.
	 * 
	 * @return The associated editor.
	 */
	final public Editor getEditor() {
		return this.editor;
	}

	/**
	 * Clears the collection of selected shapes of the associated view. Should
	 * be used by mouse trackers only.
	 */
	final protected void doClearSelection() {
		getEditor().clearSelection();
		getLogger().debug("Selection cleared");
	}

	/**
	 * If condition needToClearSelection is true clears the selections
	 * of the editor else does nothing.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#activate()
	 */
	@Override
	public void activate() {
		getLogger().debug("Method activate() called.");
		if (this.needToClearSelections) {
			doClearSelection();
		}
	}

	/**
	 * This method is a default implementation that does nothing.
	 * Subclasses may override this method.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#deactivate()
	 */
	@Override
	public void deactivate() {
		getLogger().debug("Method deactivate() called.");
	}

	/**
	 * This method is a default implementation that does nothing.
	 * Subclasses may override this method.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#mouseDown(Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseDown(Coord c, KeyModifier k) {
		getLogger().debug("Method mouseDown() called.");
	}

	/**
	 * This method is a default implementation that does nothing.
	 * Subclasses may override this method.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#mouseDrag(Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseDrag(Coord c, KeyModifier k) {
		getLogger().debug("Method mouseDrag() called.");
	}

	/**
	 * This method is a default implementation that does nothing.
	 * Subclasses may override this method.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#mouseUp(Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseUp(Coord c, KeyModifier k) {
		getLogger().debug("Method mouseUp() called.");
	}

	/**
	 * This method is a default implementation that does nothing.
	 * Subclasses may override this method.
	 *
	 * @see ch.bfh.due1.jdt.framework.Tool#mouseOver(Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseOver(Coord c, KeyModifier k) {
		getLogger().debug("Method mouseOver() called.");
	}

	/**
	 * Returns the class name of this default tool implementation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
