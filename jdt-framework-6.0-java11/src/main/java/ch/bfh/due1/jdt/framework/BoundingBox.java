/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Rectangle;


/**
 * The helper class defines a rectangular area in the plane similar to the AWT
 * rectangle class. However, areas of this class are NOT considered to be
 * "empty" if the width or the height is negative. In addition, instances of
 * this class are immutable.
 * 
 * @author Eric Dubuis
 */
public final class BoundingBox {
	private final Coord origin;

	private final int width;

	private final int height;

	/**
	 * Creates a bounding box.
	 * 
	 * @param x0
	 *            the X0 of the origin
	 * @param y0
	 *            the Y0 of the origin
	 * @param width
	 *            the width of the bounding box
	 * @param height
	 *            the height of the bounding box
	 */
	public BoundingBox(int x0, int y0, int width, int height) {
		this(new Coord(x0, y0), width, height);
	}

	/**
	 * Creates a bounding box.
	 * 
	 * @param origin
	 *            the origin
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public BoundingBox(Coord origin, int width, int height) {
		super();
		this.origin = origin;
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a bounding box.
	 * 
	 * @param r
	 *            an AWT rectangle
	 */
	public BoundingBox(Rectangle r) {
		this(new Coord(r.x, r.y), r.width, r.height);
	}

	/**
	 * Returns the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the origin.
	 * 
	 * @return the origin
	 */
	public Coord getOrigin() {
		return origin;
	}

	/**
	 * Returns X0 of the origin.
	 * 
	 * @return X0 of the origin
	 */
	public int getX0() {
		return this.origin.getX0();
	}

	/**
	 * Returns Y0 of the origin.
	 * 
	 * @return Y0 of the origin
	 */
	public int getY0() {
		return this.origin.getY0();
	}

	/**
	 * Returns the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Tests whether a coordinate point is contained within this bounding box.
	 * 
	 * @param c
	 *            the coordinate point
	 * @return true if the coordinate point is contained within this bounding
	 *         box, false otherwise
	 */
	public boolean contains(Coord c) {
		return getAWTRectangle().contains(c.getAWTPoint());
	}

	/**
	 * Constructs a new union form this bounding box and the given bounding box.
	 * 
	 * @param b
	 *            the given bounding box
	 * @return a new bounding box
	 */
	public BoundingBox union(BoundingBox b) {
		Rectangle r = getAWTRectangle();
		r.add(b.getAWTRectangle());
		return new BoundingBox(r);
	}

	/**
	 * Given this bounding box, returns the corresponding AWT rectangle object
	 * with r.width >= 0 and r.height >= 0. That is, the AWT rectangle expresses
	 * the same area in the plane but uses always 0 or positive values for width
	 * and height.
	 * 
	 * @return an AWT rectangle object
	 */
	public Rectangle getAWTRectangle() {
		Rectangle r = null;
		if (width >= 0) {
			if (height >= 0) {
				r = new Rectangle(this.origin.getX0(), this.origin
						.getY0(), width, height);
			} else {
				// width >= 0 && height < 0
				r = new Rectangle(this.origin.getX0(), this.origin
						.getY0()
						+ height, width, -height);
			}
		} else {
			if (height >= 0) {
				r = new Rectangle(this.origin.getX0() + width, this.origin
						.getY0(), -width, height);
			} else {
				// width < 0 && height < 0
				r = new Rectangle(this.origin.getX0() + width, this.origin
						.getY0()
						+ height, -width, -height);
			}
		}
		return r;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + height;
		result = PRIME * result + ((origin == null) ? 0 : origin.hashCode());
		result = PRIME * result + width;
		return result;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BoundingBox other = (BoundingBox) obj;
		if (height != other.height)
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of a bounding box.
	 * 
	 * @return a string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BB[" + origin.getX0() + "," + origin.getY0() + ","
				+ this.width + "," + this.height + "]";
	}
}
