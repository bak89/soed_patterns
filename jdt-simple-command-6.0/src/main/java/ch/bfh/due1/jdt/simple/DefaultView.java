/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedEvent;
import ch.bfh.due1.jdt.framework.SheetChangedListener;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.View;

/**
 * The default view. Paints its content, i.e., a sheet with shapes etc.
 * Handles mouse and key events and delegates them to the respective clients.
 * 
 * @author Eric Dubuis
 */
public class DefaultView extends JComponent implements View {

	/** The serial version UID. */
	private static final long serialVersionUID = -6553319343893593591L;

	/** The logger for this class, named "jdt.framework.DefaultView". */
	private Logger log = Logger.getLogger(DefaultView.class);

	/**
	 * The list of selected shapes.
	 */
	private List<Shape> selection = new LinkedList<Shape>();

	/**
	 * The list of the handles of the selected shapes.
	 */
	private List<ShapeHandle> handles = new LinkedList<ShapeHandle>();

	/**
	 * The listener being registered with the associated sheet(s) of this view.
	 */
	private SheetChangedListener mySheetChangedListener;

	/**
	 * The current tool.
	 */
	private Tool currentTool;

	/**
	 * The associated editor.
	 */
	private Editor editor;

	/**
	 * The sheet.
	 */
	private Sheet sheet;

	/**
	 * Mouse listener adapter.
	 * 
	 * @author Eric Dubuis
	 */
	private class ViewMouseListener implements MouseListener {
		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			log.debug("Mouse clicked: " + mouseInfo(e));
		}

		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			log.debug("Mouse entered: " + mouseInfo(e));
		}

		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			log.debug("Mouse exited: " + mouseInfo(e));
		}

		/**
		 * Logs the event and calls the mouseDown() method on the current tool.
		 * 
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			log.debug("Mouse pressed: " + mouseInfo(e));
			getTool().mouseDown(new Coord(e.getX(), e.getY()),
					getKeyModifier(e));
		}

		/**
		 * Logs the event and calls the mouseUp() and mouseOver() methods on the
		 * current tool.
		 * 
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			log.debug("Mouse released: " + mouseInfo(e));
			getTool().mouseUp(new Coord(e.getX(), e.getY()), getKeyModifier(e));
			getTool().mouseOver(new Coord(e.getX(), e.getY()),
					getKeyModifier(e));
		}
	}

	/**
	 * Mouse motion listener adapter.
	 * 
	 * @author Eric Dubuis
	 */
	private class ViewMouseMotionListener implements MouseMotionListener {
		/**
		 * Logs the event and calls mouseDrag() method on the current tool.
		 * 
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			log.debug("Mouse dragged: " + mouseInfo(e));
			getTool().mouseDrag(new Coord(e.getX(), e.getY()),
					getKeyModifier(e));
		}

		/**
		 * Logs the event and calls mouseOver() method on the current tool.
		 * 
		 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseMoved(MouseEvent e) {
			log.debug("Mouse moved: " + mouseInfo(e));
			getTool().mouseOver(new Coord(e.getX(), e.getY()),
					getKeyModifier(e));
		}
	}

	/**
	 * Key listener adapter. Needs to be used iff key modifiers are taken into
	 * account.
	 * 
	 * @author Eric Dubuis
	 */
	private class ViewKeyListener implements KeyListener {
		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			log.debug("Key pressed: " + e.getKeyCode());
		}

		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			log.debug("Key released: " + e.getKeyCode());
		}

		/**
		 * Logs the event.
		 * 
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			log.debug("Key typed: " + e.getKeyCode());
		}
	}

	/**
	 * No-arg constructor for subclasses. Subclasses <em> must</em> set the
	 * editor.
	 */
	protected DefaultView() {
		this(null);
	}

	/**
	 * Constructs a default view.
	 * 
	 * @param editor
	 *            The associated editor. If null then client must configure
	 *            immediately the view.
	 */
	public DefaultView(Editor editor) {
		super();

		this.setMinimumSize(new Dimension(800, 500));
		this.setPreferredSize(new Dimension(800, 500));
		this.setMaximumSize(new Dimension(800, 500));

		this.addMouseListener(new ViewMouseListener());
		this.addMouseMotionListener(new ViewMouseMotionListener());

		this.addKeyListener(new ViewKeyListener());

		this.editor = editor;

		this.mySheetChangedListener = new SheetChangedListener() {

			@Override
			public void sheetChanged(SheetChangedEvent e) {
				log.debug("Sheet changed: " + e.getSheet());
				repaint();
			}
		};
	}

	/**
	 * Adds the given shape to the associated sheet.
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#addShape(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public void addShape(Shape s) {
		getSheet().addShape(s);
	}

	/**
	 * Removes the given shape from the associated sheet.
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#removeShape(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public boolean removeShape(Shape s) {
		return getSheet().removeShape(s);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#getShapes()
	 */
	@Override
	public List<Shape> getShapes() {
		return getSheet().getShapes();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#getShapesByStackingOrder(java.util.Collection)
	 */
	@Override
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
		return getSheet().getShapesByStackingOrder(shapes);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addToSelection(Shape s) {
		if (!this.selection.contains(s)) {
			this.selection.add(s);
			s.setSelected(true);
			List<ShapeHandle> hList = s.getShapeHandles();
			if (hList != null) {
				this.handles.addAll(hList);
			}
		}
		repaint();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void removeFromSelection(Shape s) {
		this.selection.remove(s);
		s.setSelected(false);
		for (Iterator<ShapeHandle> it = this.handles.iterator(); it.hasNext();) {
			if (it.next().getOwner() == s) {
				it.remove();
			}
		}
		repaint();
	}

	/**
	 * Returns the list of selected shapes.
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#getSelection()
	 */
	@Override
	public List<Shape> getSelection() {
		return this.selection;
	}

	/**
	 * Returns the handles of selected shapes.
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#getSelectionHandles()
	 */
	@Override
	public List<ShapeHandle> getSelectionHandles() {
		return this.handles;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void clearSelection() {
		for (Shape s : this.selection) {
			s.setSelected(false);
		}
		this.selection.clear();
		this.handles.clear();
		setCursor(Cursor.getDefaultCursor());
		repaint();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Editor getEditor() {
		return this.editor;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Tool getTool() {
		return this.currentTool;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void setTool(Tool tool) {
		if (tool == null)
			throw new IllegalArgumentException("Tool must not be null.");

		if (this.currentTool != tool) {
			if (this.currentTool != null)
				this.currentTool.deactivate();
			this.currentTool = tool;
			this.currentTool.activate();
			log.debug("Tool set: " + this.currentTool);
			repaint();
		} else {
			// (Re-) activate tool if given tool is the same
			// this.currentTool == tool
			this.currentTool.activate();
		}
		this.editor.checkEditorState();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void setEditor(Editor editor) {
		if (editor == null) {
			throw new IllegalArgumentException("Editor must not be null.");
		}
		this.editor = editor;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void setSheet(Sheet s) {
		if (s != null) {
			s.addSheetChangedListener(this.mySheetChangedListener);
			this.sheet = s;

			log.debug("Sheet set: " + this.sheet);
			repaint();
		}
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#createMemento()
	 */
	@Override
	public Memento createMemento() {
		throw new UnsupportedOperationException("Method not yet implemented");
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#setMemento(ch.bfh.due1.jdt.framework.Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		throw new UnsupportedOperationException("Method not yet implemented");
	}

	/**
	 * When called, draws the only associated sheet of this DefaultView object.
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		// This view is being painted. Call the content's draw method.
		this.sheet.draw(g);
	
		// Is this the way to provide the space of a sheet??
		g.clearRect(800, 0, this.getWidth(), this.getHeight());
		g.clearRect(0, 500, this.getWidth(), this.getHeight());
	}

	/**
	 * Returns the class name of this view implementation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}

	/**
	 * Helper that returns the associated sheet. Used internally only.
	 */
	private Sheet getSheet() {
		return this.sheet;
	}

	/**
	 * Helper method for printing mouse event information.
	 * 
	 * @param e
	 *            the mouse event
	 * @return a corresponding string
	 */
	private String mouseInfo(MouseEvent e) {
		StringBuffer sb = new StringBuffer();
		sb.append("x=" + e.getX() + ",");
		sb.append("y=" + e.getY() + ",");
		sb.append("AltDown=" + e.isAltDown() + ",");
		sb.append("AltGraphDown=" + e.isAltGraphDown() + ",");
		sb.append("ControlDown=" + e.isControlDown() + ",");
		sb.append("ShiftDown=" + e.isShiftDown() + ",");
		sb.append("MetaDown=" + e.isMetaDown() + ".");
		return sb.toString();
	}

	/**
	 * Maps Java/AWT key modifier contained in mouse events into an
	 * application-level key modifier. The mapping is not total.
	 * 
	 * @param e
	 *            the mouse event
	 * @return the application-level key modifier
	 */
	private KeyModifier getKeyModifier(MouseEvent e) {
		// Note: It is assumed that there is only one key modifier active at a
		// time! (This is, of course, a limitation.)
		if (e.isControlDown()) {
			return KeyModifier.CONTROL_DOWN;
		} else if (e.isShiftDown()) {
			return KeyModifier.SHIFT_DOWN;
		} else if (e.isAltDown()) {
			return KeyModifier.ALT_DOWN;
		} else if (e.isMetaDown()) {
			return KeyModifier.META_DOWN;
		} else if (e.isAltGraphDown()) {
			return KeyModifier.ALT_GRAPH_DOWN;
		} else {
			return KeyModifier.NONE;
		}
	}
}
