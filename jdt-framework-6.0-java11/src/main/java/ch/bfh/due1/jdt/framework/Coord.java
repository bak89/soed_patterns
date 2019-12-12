/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Point;


/**
 * This class defines a point in the plane similar to the AWT class Point.
 * Instances are, however, immutable.
 * 
 * @author Eric Dubuis
 */
public final class Coord {
	private final int x0;

	private final int y0;

	/**
	 * Creates an instance.
	 * 
	 * @param x0
	 *            the x-coordinate of the point
	 * @param y0
	 *            the y-coordinate of the point
	 */
	public Coord(int x0, int y0) {
		super();
		this.x0 = x0;
		this.y0 = y0;
	}

	/**
	 * Returns the x-coordinate.
	 * 
	 * @return the x0
	 */
	public int getX0() {
		return x0;
	}

	/**
	 * Returns the y-coordinate.
	 * 
	 * @return the y0
	 */
	public int getY0() {
		return y0;
	}

	/**
	 * Returns the corresponding AWT Point object.
	 * 
	 * @return the corresponding AWT point
	 */
	public Point getAWTPoint() {
		return new Point(this.x0, this.y0);
	}

	/**
	 * Given this coordinate and a coordinate b, the displacement vector v is
	 * the place vector of this coordinate minus the place vector of the given
	 * coordinate b.
	 * 
	 * @param b the given coordinate
	 * @return the displacement vector
	 */
	public Vector getDisplacementVector(Coord b) {
		return new Vector(b).minus(new Vector(this));
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + x0;
		result = PRIME * result + y0;
		return result;
	}

	/**
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
		final Coord other = (Coord) obj;
		if (x0 != other.x0)
			return false;
		if (y0 != other.y0)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of a coordinate.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coord[" + this.x0 + "," + this.y0 + "]";
	}
}
