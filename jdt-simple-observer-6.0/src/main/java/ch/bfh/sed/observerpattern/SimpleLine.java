/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.observerpattern;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.due1.jdt.framework.AbstractHandle;
import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;


/**
 * Implements a line shape.
 *
 * @author Eric Dubuis
 */
public class SimpleLine extends AbstractShape {
	/**
	 * The position of the line is represented by a bounding box. The origin of
	 * the line (p0) is the origin of the bounding box, and the width and
	 * height, respectively, denote the distance of the final point p1 from the
	 * origin.
	 */
	private BoundingBox r;

	/**
	 * Contains the handles. Notice that the handles' location is maintained by
	 * the line itself.
	 */
	private List<ShapeHandle> handles;

	/**
	 * Handle for the point at which the line starts.
	 *
	 * @author Eric Dubuis
	 */
	private class OriginPointHandle extends AbstractHandle {

		/**
		 * Constructs the handle. Uses a private interface of the Line.
		 *
		 * @param owner
		 *            The owner, i.e., this line.
		 * @param location
		 *            The location of the handle.
		 */
		protected OriginPointHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @see AbstractHandle#getCursor()
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			return c;
		}

		/**
		 * @see AbstractHandle#dragInteraction(Coord,
		 *      KeyModifier)
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			BoundingBox r = getOwner().getBoundingBox();
			BoundingBox modified = new BoundingBox(c.getX0(),
					c.getY0(),
					r.getWidth() - (c.getX0() - r.getX0()), r.getHeight()
					- (c.getY0() - r.getY0()));
			getOwner().setBoundingBox(modified);
		}
	}

	/**
	 * Handle for the point at which the line ends. Uses a private interface of
	 * the Line.
	 *
	 * @author Eric Dubuis
	 */
	private class FinalPointHandle extends AbstractHandle {

		/**
		 * Constructs the handle.
		 *
		 * @param owner
		 *            The owner, i.e., this line.
		 * @param location
		 *            The location of the handle.
		 */
		protected FinalPointHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @see AbstractHandle#getCursor()
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			return c;
		}

		/**
		 * @see AbstractHandle#dragInteraction(Coord,
		 *      KeyModifier)
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			BoundingBox r = getOwner().getBoundingBox();
			BoundingBox modified = new BoundingBox(r.getX0(), r.getY0(),
					c.getX0() - r.getX0(), c.getY0() - r.getY0());
			getOwner().setBoundingBox(modified);
		}
	}

	/**
	 * Private memento that stores the current (drawing) state of this line.
	 * Instances of this class are value objects.
	 */
	private static class LineMemento implements Memento {
		private static final long serialVersionUID = -7907435422009023760L;
		private BoundingBox bb;
		private Color fillColor;
		private Color penColor;
		private int penSize;

		LineMemento(BoundingBox bb, Color fillColor, Color penColor, int penSize) {
			this.bb = bb;
			this.fillColor = fillColor;
			this.penColor = penColor;
			this.penSize = penSize;
		}

		BoundingBox getBoundingBox() {
			return this.bb;
		}

		Color getFillColor() {
			return this.fillColor;
		}

		Color getPenColor() {
			return this.penColor;
		}

		int getPenSize() {
			return this.penSize;
		}
	}

	/**
	 * Creates a line with given location and size parameters. Notice the the
	 * box's handles are created, too. If the line changes its position then the
	 * handles are updated correspondingly.
	 *
	 * @param x0
	 *            The x-origin of the line's first point.
	 * @param y0
	 *            The y-origin of the line's first point.
	 * @param width
	 *            The width of the line.
	 * @param height
	 *            The height of the line.
	 */
	public SimpleLine(int x0, int y0, int width, int height) {
		super();
		this.r = new BoundingBox(x0, y0, width, height);
		this.handles = new ArrayList<ShapeHandle>();
		this.handles.add(new OriginPointHandle(this, new Coord(r.getX0(), r
				.getY0())));
		this.handles.add(new FinalPointHandle(this, new Coord(r.getX0()
				+ r.getWidth(), r.getY0() + r.getHeight())));
	}

	/**
	 * Draws the box into the given Graphics context.
	 *
	 * @param g
	 *            A Graphics context.
	 */
	@Override
	protected void doDrawShape(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color current = g2.getColor();
		Stroke stroke = new BasicStroke(this.penSize);
		g2.setStroke(stroke);
		g2.setColor(this.penColor);
		g2.drawLine(r.getX0(), r.getY0(), r.getX0() + r.getWidth(), r.getY0()
				+ r.getHeight());
		g2.setColor(current);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void move(Vector delta) {
		BoundingBox r = getBoundingBox();
		setBoundingBox(new BoundingBox(r.getX0() + delta.getXComponent(),
				r.getY0() + delta.getYComponent(), r.getWidth(), r.getHeight()));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean contains(Coord c) {
		boolean rval = false;
		rval = isCloseToLine(c, 2);
		return rval;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<ShapeHandle> getShapeHandles() {
		return this.handles;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public BoundingBox getBoundingBox() {
		return this.r;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void setBoundingBox(BoundingBox r) {
		if (!this.r.equals(r)) {
			this.r = r;
			if (this.handles != null) {
				this.handles.get(0)
				.setPosition(new Coord(r.getX0(), r.getY0()));
				this.handles.get(1).setPosition(
						new Coord(r.getX0() + r.getWidth(), r.getY0()
								+ r.getHeight()));
			}
			// TODO -- notify listeners

		}
	}

	/**
	 * Creates a memento storing the state of this line. It includes the
	 * bounding box, the fill and pen colors, and the pen size. The caretaker
	 * can restore the current state be calling method setMemento().
	 *
	 * @see AbstractShape#createMemento()
	 */
	@Override
	public Memento createMemento() {
		Memento memento = new LineMemento(this.r, getFillColor(),
				getPenColor(), getPenSize());
		return memento;
	}

	/**
	 * Restores a previous state of this line instance.
	 *
	 * @see AbstractShape#setMemento(Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		LineMemento lm = (LineMemento) m;
		setFillColor(lm.getFillColor());
		setPenColor(lm.getPenColor());
		setPenSize(lm.getPenSize());
		setBoundingBox(lm.getBoundingBox());
	}

	/**
	 * Returns a clone of this line. Default properties of super class apply.
	 * This instance has no listeners.
	 *
	 * @see Shape#cloneMe()
	 */
	@Override
	public Shape cloneMe() {
		Shape s = new SimpleLine(this.r.getX0(), this.r.getY0(),
				this.r.getWidth(), this.r.getHeight());
		return s;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleLine[" + r.getX0() + "," + r.getY0() + "," + r.getWidth()
		+ "," + r.getHeight() + "]";
	}

	/**
	 * Returns true if the smallest distance between c and the line is less than
	 * given epsilon (in pixels) provided that the norm vector crossing c
	 * intersects with the given line.
	 *
	 * @param c
	 *            the given point c
	 * @param epsilon
	 *            the allowed distance in pixels
	 * @return true if the distance is smaller than epsilon, false otherwise
	 */
	private boolean isCloseToLine(Coord c, int epsilon) {
		boolean rval;
		Coord a = this.r.getOrigin();
		Coord b = new Coord(a.getX0() + this.r.getWidth(), a.getY0()
				+ this.r.getHeight());
		Vector oa = new Vector(a); // place vector of a
		Vector ob = new Vector(b); // place vector of b
		Vector oc = new Vector(c); // place vector of c
		Vector ab = ob.minus(oa); // distance vector ab
		Vector ac = oc.minus(oa); // distance vector ac
		Vector ab_n = ab.getNormalVector(); // norm vector of ab
		double D = ab.getDeterminantBy(ab_n);
		double D_alpha = ac.getDeterminantBy(ab_n);
		double D_beta = ab.getDeterminantBy(ac);
		double alpha = D_alpha / D;
		double distance = 0.0;
		if (alpha < 0.0 || alpha > 1.0) {
			rval = false;
		} else {
			double beta = D_beta / D;
			distance = Math.abs(beta * ab_n.magnitude());
			rval = distance <= epsilon;
		}
		return rval;
	}
}
