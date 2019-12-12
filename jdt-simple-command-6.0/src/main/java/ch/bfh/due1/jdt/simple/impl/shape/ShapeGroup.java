/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.shape;

import java.awt.Cursor;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.AbstractHandle;
import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedEvent;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;

/**
 * A shape group groups together a list of shapes that can be manipulated as a
 * whole.
 *
 * @author Eric Dubuis
 */
public class ShapeGroup extends AbstractShape {
	/** The logger. */
	private Logger log = Logger.getLogger(ShapeGroup.class);

	private class NWHandle extends AbstractHandle {
		/**
		 * Creates a handle at the north-west position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public NWHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.NW_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(c, r.getX0() - c.getX0()
					+ r.getWidth(), r.getY0() - c.getY0() + r.getHeight());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class NHandle extends ch.bfh.due1.jdt.framework.AbstractHandle {
		/**
		 * Creates a handle at the north position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public NHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.N_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox original = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(
					original.getX0(),
					c.getY0(),
					original.getWidth(),
					original.getY0() - c.getY0() + original.getHeight());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class NEHandle extends AbstractHandle {
		/**
		 * Creates a handle at the north-east position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public NEHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.NE_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox original = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(
					original.getX0(),
					c.getY0(),
					c.getX0() - original.getX0(),
					original.getY0() - c.getY0() + original.getHeight());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class EHandle extends AbstractHandle {
		/**
		 * Creates a handle at the east position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public EHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.E_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(r.getX0(), r.getY0(),
					c.getX0() - r.getX0(), r.getHeight());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class SEHandle extends AbstractHandle {

		/**
		 * Creates a handle at the south-east position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public SEHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.SE_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(r.getX0(), r.getY0(),
					c.getX0() - r.getX0(), c.getY0() - r.getY0());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class SHandle extends AbstractHandle {
		/**
		 * Creates a handle at the south position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public SHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.S_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(r.getX0(), r.getY0(),
					r.getWidth(), c.getY0() - r.getY0());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class SWHandle extends AbstractHandle {
		/**
		 * Creates a handle at the south-west position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public SWHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.SW_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(c.getX0(), r.getY0(),
					r.getX0() - c.getX0() + r.getWidth(), c.getY0()
					- r.getY0());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	private class WHandle extends AbstractHandle {
		/**
		 * Creates a handle at the west position of a shape.
		 *
		 * @param owner
		 *            The shape that owns this handle.
		 * @param location
		 *            There this handle is located (the center).
		 */
		public WHandle(Shape owner, Coord location) {
			super(owner, location);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Cursor getCursor() {
			Cursor c = new Cursor(Cursor.W_RESIZE_CURSOR);
			return c;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void dragInteraction(Coord c, KeyModifier k) {
			// FIXME: other handles than this one jump back to their original position
			BoundingBox r = getOriginalBoundingBox();
			BoundingBox modified = new BoundingBox(c.getX0(), r.getY0(),
					r.getX0() - c.getX0() + r.getWidth(), r.getHeight());
			getOwner().setBoundingBox(modified);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void stopInteraction(Coord c, KeyModifier k) {
			dragInteraction(c, k);
		}
	}

	/**
	 * The memento class for groups of shapes. It stores the current
	 * location and size of the group as well as the mementos of
	 * its children.
	 *
	 * @author Eric Dubuis
	 */
	// Remark: Visibility is due to testing purposes.
	public static class ShapeGroupMemento implements Memento {
		/** Serial version UID. */
		private static final long serialVersionUID = 2588180526072659235L;
		private BoundingBox bb;
		private List<Memento> childMementos;
		private ShapeGroupMemento(BoundingBox bb, List<Memento> mementos) {
			this.bb = bb;
			this.childMementos = mementos;
		}
		public BoundingBox getBoundingBox() {
			return this.bb;
		}
		public List<Memento> getChildMementos() {
			return Collections.unmodifiableList(this.childMementos);
		}
	}

	/**
	 * The handles of the group.
	 */
	private List<ShapeHandle> handles;

	/**
	 * The listeners being registered at members of the group.
	 */
	private ShapeChangedListener myShapeListener;

	/**
	 * Container for holding a child shape and its original
	 * size (needed for scaling, even if current shape width
	 * or height becomes zero).
	 *
	 * @author Eric Dubuis
	 */
	private static class ChildShape {
		private Shape shape;
		private BoundingBox origSize;
		private ChildShape(Shape shape, BoundingBox origSize) {
			this.shape = shape;
			this.origSize = origSize;
		}
	}

	/**
	 * List of child shapes, containing tuples of child shape
	 * and the original size of the child shape.
	 */
	private List<ChildShape> children = new ArrayList<>();

	/**
	 * Creates a group of shapes.
	 *
	 * @param currentShapes
	 *            The initial list of shapes that form the group. Caller must
	 *            not change the list after the call.
	 */
	public ShapeGroup(List<Shape> currentShapes) {
		for (Shape s : currentShapes) {
			// Add shape to list of children. Record orginal
			// size of each shape.
			this.children.add(new ChildShape(s, s.getBoundingBox()));
		}
		this.myShapeListener = new ShapeChangedListener() {
			@Override
			public void shapeChanged(ShapeChangedEvent e) {
				log.debug("Shape changed: " + e.getShape());
				// So far, for each shape that sends us an event we notify our
				// sheet listeners.
				ShapeGroup.this.notifyShapeChangedListeners();
			}
		};
		for (Shape s : currentShapes) {
			s.addShapeChangedListener(this.myShapeListener);
		}
		this.handles = new ArrayList<ShapeHandle>();
		// Set handler; handles are adjusted via adjustHandles() helper.
		this.handles.add(new NWHandle(this, new Coord(0, 0)));
		this.handles.add(new NHandle(this, new Coord(0, 0)));
		this.handles.add(new NEHandle(this, new Coord(0, 0)));
		this.handles.add(new EHandle(this, new Coord(0, 0)));
		this.handles.add(new SEHandle(this, new Coord(0, 0)));
		this.handles.add(new SHandle(this, new Coord(0, 0)));
		this.handles.add(new SWHandle(this, new Coord(0, 0)));
		this.handles.add(new WHandle(this, new Coord(0, 0)));
		adjustHandles();
	}

	/**
	 * Private constructor used for building a clone of this
	 * group. The special thing is that the given clone child
	 * shapes contain the original size of the child shapes.
	 *
	 * @param childClones a list of child shape clones including
	 * 			the child's bounding box
	 * @param x dummy parameter, not used
	 */
	private ShapeGroup(List<ChildShape> childClones, boolean x) {
		this.children = childClones;
		this.myShapeListener = new ShapeChangedListener() {
			@Override
			public void shapeChanged(ShapeChangedEvent e) {
				log.debug("Shape changed: " + e.getShape());
				// So far, for each shape that sends us an event we notify our
				// sheet listeners.
				ShapeGroup.this.notifyShapeChangedListeners();
			}
		};
		for (ChildShape cs : childClones) {
			cs.shape.addShapeChangedListener(this.myShapeListener);
		}
		this.handles = new ArrayList<ShapeHandle>();
		// Set handler; handles are adjusted via adjustHandles() helper.
		this.handles.add(new NWHandle(this, new Coord(0, 0)));
		this.handles.add(new NHandle(this, new Coord(0, 0)));
		this.handles.add(new NEHandle(this, new Coord(0, 0)));
		this.handles.add(new EHandle(this, new Coord(0, 0)));
		this.handles.add(new SEHandle(this, new Coord(0, 0)));
		this.handles.add(new SHandle(this, new Coord(0, 0)));
		this.handles.add(new SWHandle(this, new Coord(0, 0)));
		this.handles.add(new WHandle(this, new Coord(0, 0)));
		adjustHandles();
	}

	/**
	 * Returns true if coordinate is on one of the shapes of this group.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#contains(Coord)
	 */
	@Override
	public boolean contains(Coord c) {
		for (ChildShape cs : this.children) {
			if (cs.shape.contains(c)) {
				// Point (x, y) is on a shape of this group; break and return
				// true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Draws each child of the group.
	 *
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#doDrawShape(java.awt.Graphics)
	 */
	@Override
	protected void doDrawShape(Graphics g) {
		for (ChildShape cs : this.children) {
			cs.shape.draw(g);
		}
	}

	/**
	 * Moves the group by moving each child of it.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#move(Vector)
	 */
	@Override
	public void move(Vector delta) {
		for (ChildShape cs : this.children) {
			cs.shape.move(delta);
		}
		adjustHandles();
		notifyShapeChangedListeners();
	}

	/**
	 * Returns the smallest bounding box enclosing all children of the group.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#getBoundingBox()
	 */
	@Override
	public BoundingBox getBoundingBox() {
		BoundingBox res = null;
		for (ChildShape cs : this.children) {
			BoundingBox r = cs.shape.getBoundingBox();
			if (res == null) {
				res = r;
			} else {
				res = res.union(r);
			}
		}
		return res;
	}

	/**
	 * Resizes (or repositions) the group by setting a new bounding box.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#setBoundingBox(ch.bfh.due1.jdt.framework.BoundingBox)
	 */
	@Override
	public void setBoundingBox(BoundingBox r) {
		// Perform calculations relative to the size of the original bounding
		// box.
		BoundingBox orig = getOriginalBoundingBox();

		if (getBoundingBox().equals(r)) {
			// There is nothing to change, return.
			return;
		}
		// If the width of the original group is then it remains 0 all the time.
		// Only the height can be changed.
		if (orig.getWidth() == 0) {
			// handle height only
			// FIXME: not completely correct...
			double factorY = ((double) r.getHeight())
					/ ((double) orig.getHeight());
			int deltaX = r.getX0() - orig.getX0();
			int deltaY = r.getY0() - orig.getY0();
			for (ChildShape cs : this.children) {
				BoundingBox rs = cs.origSize;
				BoundingBox newRs = new BoundingBox(
						getScaledX0(orig, rs, deltaX, 1),
						getScaledY0(orig, rs, deltaY, factorY),
						0,
						getScaledHeight(rs, factorY));
				cs.shape.setBoundingBox(newRs);
			}
			adjustHandles();
			notifyShapeChangedListeners();
			return;
		}
		// If the height of the original group is then it remains 0 all the time.
		// Only the width can be changed.
		if (orig.getHeight() == 0) {
			// handle width only
			// FIXME: not completely correct...
			double factorX = ((double) r.getWidth())
					/ ((double) orig.getWidth());
			int deltaX = r.getX0() - orig.getX0();
			int deltaY = r.getY0() - orig.getY0();
			for (ChildShape cs : this.children) {
				BoundingBox rs = cs.origSize;
				BoundingBox newRs = new BoundingBox(
						getScaledX0(orig, rs, deltaX, factorX),
						getScaledY0(orig, rs, deltaY, 1),
						getScaledWidth(rs, factorX),
						0);
				cs.shape.setBoundingBox(newRs);
			}
			adjustHandles();
			notifyShapeChangedListeners();
			return;
		}
		// handle both, width and height...
		double factorX = ((double) r.getWidth()) / ((double) orig.getWidth());
		double factorY = ((double) r.getHeight()) / ((double) orig.getHeight());
		int deltaX = r.getX0() - orig.getX0();
		int deltaY = r.getY0() - orig.getY0();
		for (ChildShape cs : this.children) {
			BoundingBox rs = cs.origSize;
			BoundingBox newRs = new BoundingBox(
					getScaledX0(orig, rs, deltaX, factorX),
					getScaledY0(orig, rs, deltaY, factorY),
					getScaledWidth(rs, factorX),
					getScaledHeight(rs, factorY));
			cs.shape.setBoundingBox(newRs);
		}

		adjustHandles();
		notifyShapeChangedListeners();
	}

	/**
	 * Adds a shape to this group.
	 *
	 * @param s
	 *            The shape.
	 */
	@Override
	public void add(Shape s) {
		ChildShape sc = new ChildShape(s, s.getBoundingBox());
		this.children.add(sc);
		s.addShapeChangedListener(this.myShapeListener);
		adjustHandles();
		notifyShapeChangedListeners();
	}

	/**
	 * Removes the given shape from the group. Notifies the listeners of this
	 * group if and only if the given shape was a member of this group.
	 *
	 * @param s
	 *            A shape.
	 * @return True if the given shape was a member of this group, false
	 *         otherwise.
	 */
	@Override
	public boolean remove(Shape s) {
		boolean listChanged = false;
		for (Iterator<ChildShape> it = this.children.iterator(); it.hasNext();) {
			ChildShape current = it.next();
			// We need object identity here.
			if (current.shape == s) {
				it.remove();
				s.removeShapeChangedListener(this.myShapeListener);
				listChanged = true;
			}
		}
		if (listChanged) {
			// The shape was in the list.
			adjustHandles();
			notifyShapeChangedListeners();
		}
		return listChanged;
	}

	/**
	 * Returns the list of child shapes. Clients must use add(Shape) or
	 * remove(Shape) to modify the list.
	 *
	 * @return The list of child shapes of this group.
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#getShapes()
	 */
	@Override
	public List<Shape> getShapes() {
		List<Shape> shapes = new ArrayList<>();
		for (ChildShape c : this.children) {
			shapes.add(c.shape);
		}
		return Collections.unmodifiableList(shapes);
	}

	/**
	 *
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#getShapeHandles()
	 */
	@Override
	public List<ShapeHandle> getShapeHandles() {
		return Collections.unmodifiableList(this.handles);
	}

	/**
	 * Yes, a ShapeGroup instance is a container for other shapes.
	 *
	 * @return Always true
	 *
	 * @see ch.bfh.due1.jdt.framework.AbstractShape#isContainer()
	 */
	@Override
	public boolean isContainer() {
		return true;
	}

	/**
	 * Returns a clone of this group by cloning all its
	 * children. The clone contains also a clone of the
	 * map with the original shapes of each group member.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#cloneMe()
	 */
	@Override
	public Shape cloneMe() {
		List<ChildShape> childClones = new ArrayList<>();
		for (ChildShape cs : this.children) {
			ChildShape clone =
					new ChildShape(cs.shape.cloneMe(), cs.origSize);
			childClones.add(clone);
		}
		return new ShapeGroup(childClones, true);
	}

	/**
	 * Returns the memento of the shape group. The memento
	 * contains the current bounding box of the group as
	 * well as the mementos of the group's children.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#createMemento()
	 */
	@Override
	public Memento createMemento() {
		List<Memento> childMementos =
				new ArrayList<>();
		for (ChildShape cs : this.children) {
			childMementos.add(cs.shape.createMemento());
		}
		return new ShapeGroupMemento(getBoundingBox(), childMementos);
	}

	/**
	 * Resets the shape according to the memento given. It does so by taking the
	 * child mementos contained in the given memento to reset the group's children,
	 * and by setting the current bounding box by the bounding box contained in the
	 * memento.
	 * <p>
	 * FIXME Add mechanism that avoids shape listener storms.
	 *
	 * @see ch.bfh.due1.jdt.framework.Shape#setMemento(Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		ShapeGroupMemento sm = (ShapeGroupMemento) m;
		for (int i = 0; i < this.children.size(); i++) {
			this.children.get(i).shape.setMemento(sm.getChildMementos().get(i));
		}
		setBoundingBox(sm.getBoundingBox());
	}

	/**
	 * Returns a string representation of this group.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("ShapeGroup[");
		for (ChildShape cs : this.children) {
			buf.append(cs.shape.toString());
		}
		buf.append("]");
		return buf.toString();
	}

	/**
	 * Adjusts handles according to the current size of the
	 * group.
	 */
	private final void adjustHandles() {
		BoundingBox r = getBoundingBox();
		this.handles.get(0).setPosition(new Coord(r.getX0(), r.getY0()));
		this.handles.get(1).setPosition(
				new Coord(r.getX0() + r.getWidth() / 2, r.getY0()));
		this.handles.get(2).setPosition(
				new Coord(r.getX0() + r.getWidth(), r.getY0()));
		this.handles.get(3).setPosition(
				new Coord(r.getX0() + r.getWidth(), r.getY0()
						+ r.getHeight() / 2));
		this.handles.get(4).setPosition(
				new Coord(r.getX0() + r.getWidth(), r.getY0() + r.getHeight()));
		this.handles.get(5).setPosition(
				new Coord(r.getX0() + r.getWidth() / 2, r.getY0()
						+ r.getHeight()));
		this.handles.get(6).setPosition(
				new Coord(r.getX0(), r.getY0() + r.getHeight()));
		this.handles.get(7).setPosition(
				new Coord(r.getX0(), r.getY0() + r.getHeight() / 2));
	}

	/**
	 * Scales X0 of a given bounding box relative to the original one,
	 * a delta, and a scaling factor.
	 */
	private static int getScaledX0(BoundingBox orig, BoundingBox rs,
			int deltaX, double factorX) {
		int res = orig.getX0() + deltaX
				+ (int) Math.round(Math.abs(rs.getX0() - orig.getX0()) * factorX);
		return res;
	}

	/**
	 * Scales Y0 of a given bounding box relative to the original one,
	 * a delta, and a scaling factor.
	 */
	private static int getScaledY0(BoundingBox orig, BoundingBox rs,
			int deltaY, double factorY) {
		int res = orig.getY0() + deltaY
				+ (int) Math.round((rs.getY0() - orig.getY0()) * factorY);
		return res;
	}

	/**
	 * Scales Width of a given bounding box relative to the original one,
	 * a delta, and a scaling factor.
	 */
	private static int getScaledWidth(BoundingBox rs, double factorX) {
		int res = (int) Math.round(rs.getWidth() * factorX);
		return res;
	}

	/**
	 * Scales Height of a given bounding box relative to the original one,
	 * a delta, and a scaling factor.
	 */
	private static int getScaledHeight(BoundingBox rs, double factorY) {
		int res = (int) Math.round(rs.getHeight() * factorY);
		return res;
	}

	/**
	 * Computes and returns the original size of the
	 * smallest bounding box encompassing all shapes
	 * of this group.
	 *
	 * @return the bounding box of the group
	 */
	private BoundingBox getOriginalBoundingBox() {
		BoundingBox res = null;
		for (ChildShape c : this.children) {
			if (res == null) {
				res = c.origSize;
			} else {
				res = res.union(c.origSize);
			}
		}
		return res;
	}
}
