/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;


/**
 * Implements a box-like shape.
 * 
 * @author Eric Dubuis
 */
public class SimpleBox extends AbstractShape {

	private BoundingBox r;

	private List<ShapeHandle> handles;

	/**
	 * Private memento that stores the current (drawing) state of this box.
	 * Instances of this class are value objects.
	 */
	public static class BoxMemento implements Memento {
		private static final long serialVersionUID = -5503462466151449721L;
		private BoundingBox bb;
		private Color fillColor;
		private Color penColor;
		private int penSize;

		private BoxMemento(BoundingBox bb, Color fillColor, Color penColor, int penSize) {
			this.bb = bb;
			this.fillColor = fillColor;
			this.penColor = penColor;
			this.penSize = penSize;
		}

		public BoundingBox getBoundingBox() {
			return this.bb;
		}

		public Color getFillColor() {
			return this.fillColor;
		}

		public Color getPenColor() {
			return this.penColor;
		}

		public int getPenSize() {
			return this.penSize;
		}
	}

	/**
	 * Creates an arbitrary rotated box with given location and size parameters.
	 * Notice the the box's handles are created, too. If the box changes its
	 * shape then the handles are updated correspondingly.
	 * 
	 * @param x0
	 *            The x-origin of the upper left corner.
	 * @param y0
	 *            The y-origin of the upper left corner.
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 */
	public SimpleBox(double x0, double y0, double width, double height) {
		super();
		this.handles = new ArrayList<ShapeHandle>();
		this.r = new BoundingBox((int) x0, (int) y0, (int) width, (int) height);
		this.handles.add(new DefaultNWHandle(this, new Coord(r.getX0(), r
				.getY0())));
		this.handles.add(new DefaultNHandle(this, new Coord(r.getX0()
				+ (int) (width / 2), r.getY0())));
		this.handles.add(new DefaultNEHandle(this, new Coord(r.getX0()
				+ (int) width, r.getY0())));
		this.handles.add(new DefaultEHandle(this, new Coord(r.getX0()
				+ (int) width, r.getY0() + (int) (height / 2))));
		this.handles.add(new DefaultSEHandle(this, new Coord(r.getX0()
				+ (int) width, r.getY0() + (int) height)));
		this.handles.add(new DefaultSHandle(this, new Coord(r.getX0()
				+ (int) (width / 2), r.getY0() + (int) height)));
		this.handles.add(new DefaultSWHandle(this, new Coord(r.getX0(), r
				.getY0() + (int) height)));
		this.handles.add(new DefaultWHandle(this, new Coord(r.getX0(), r
				.getY0() + (int) (height / 2))));
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
		g2.draw(this.r.getAWTRectangle());
		g2.setColor(this.fillColor);
		g2.fill(this.r.getAWTRectangle());
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
		return this.r.contains(c);
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
						new Coord(r.getX0() + (int) (r.getWidth() / 2), r
								.getY0()));
				this.handles.get(2).setPosition(
						new Coord(r.getX0() + r.getWidth(), r.getY0()));
				this.handles.get(3).setPosition(
						new Coord(r.getX0() + r.getWidth(), r.getY0()
								+ (int) (r.getHeight() / 2)));
				this.handles.get(4).setPosition(
						new Coord(r.getX0() + r.getWidth(), r.getY0()
								+ r.getHeight()));
				this.handles.get(5).setPosition(
						new Coord(r.getX0() + (int) (r.getWidth() / 2), r
								.getY0() + r.getHeight()));
				this.handles.get(6).setPosition(
						new Coord(r.getX0(), r.getY0() + r.getHeight()));
				this.handles.get(7).setPosition(
						new Coord(r.getX0(), r.getY0()
								+ (int) (r.getHeight() / 2)));
			}
			notifyShapeChangedListeners();
		}
	}

	/**
	 * Creates a memento storing the state of this box. It includes the
	 * bounding box, the fill and pen colors, and the pen size. The caretaker
	 * can restore the current state be calling method setMemento().
	 * 
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#createMemento()
	 */
	@Override
	public Memento createMemento() {
		Memento memento = new BoxMemento(this.r, getFillColor(),
				getPenColor(), getPenSize());
		return memento;
	}

	/**
	 * Restores a previous state of this box instance.
	 *
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#setMemento(ch.bfh.due1.jdt.framework.Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		BoxMemento lm = (BoxMemento) m;
		setFillColor(lm.getFillColor());
		setPenColor(lm.getPenColor());
		setPenSize(lm.getPenSize());
		setBoundingBox(lm.getBoundingBox());
	}

	/**
	 * Returns a clone of this box. Default properties of super class apply.
	 * This instance has no listeners.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Shape#cloneMe()
	 */
	@Override
	public Shape cloneMe() {
		Shape s = new SimpleBox(this.r.getX0(), this.r.getY0(),
				this.r.getWidth(), this.r.getHeight());
		return s;
	}

	/**
	 * Returns the name of this class including bounding box information.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleBox[" + r.getX0() + "," + r.getY0() + "," + r.getWidth()
				+ "," + r.getHeight() + "]";
	}
}
