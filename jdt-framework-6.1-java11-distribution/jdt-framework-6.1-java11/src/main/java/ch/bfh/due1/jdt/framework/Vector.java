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
 * This class represents a distance (vector) by the components c_x and c_y.
 * 
 * @author Eric Dubuis
 */
public class Vector {
	private double xComponent;

	private double yComponent;

	/**
	 * Constructs a vector.
	 * 
	 * @param deltaX
	 *            the size of the x-component
	 * @param deltaY
	 *            the size of the y-component
	 */
	public Vector(int deltaX, int deltaY) {
		super();
		this.xComponent = deltaX;
		this.yComponent = deltaY;
	}

	/**
	 * Constructs a place vector. A place vector is a vector whose tail is the
	 * origin, and whose tip the given coordinate c.
	 * 
	 * @param c
	 *            the tip of the vector
	 */
	public Vector(Coord c) {
		super();
		this.xComponent = c.getX0();
		this.yComponent = c.getY0();
	}

	/**
	 * Constructs a vector.
	 * 
	 * @param deltaX
	 *            the size of the x-component
	 * @param deltaY
	 *            the size of the y-component
	 */
	private Vector(double deltaX, double deltaY) {
		super();
		this.xComponent = deltaX;
		this.yComponent = deltaY;
	}

	/**
	 * Returns the (rounded) x-component.
	 * 
	 * @return the x-component
	 */
	public int getXComponent() {
		return (int) xComponent;
	}

	/**
	 * Computes the scalar product with another vector. If this = (x1, x2) and v
	 * = (v1, v2) then the scalar product is defined as follows: s = (x1 * v1) +
	 * (x2 * v2).
	 * 
	 * @param v
	 *            the second operand of this operation
	 * @return the scalar product of this vector with the given vector
	 */
	public double scalarProduct(Vector v) {
		return (this.xComponent * v.xComponent)
				+ (this.yComponent * v.yComponent);
	}

	/**
	 * Computes the magnitude of this vector. If this = (x1, x2) then |this| =
	 * sqrt(x1**2 + x2**).
	 * 
	 * @return the absolute value of this vector
	 */
	public double magnitude() {
		return Math.sqrt(Math.pow(this.xComponent, 2)
				+ Math.pow(this.yComponent, 2));
	}

	/**
	 * Returns the norm vector of this vector. The norm vector of this vector is
	 * a vector which is vertical to this vector. If this = (a, b) then
	 * norm(this) = (-b, a).
	 * 
	 * @return the norm vector of this vector
	 */
	public Vector getNormalVector() {
		return new Vector(-this.yComponent, this.xComponent);
	}

	/**
	 * Returns a vector which is the addition of this vector and the given
	 * vector b.
	 * 
	 * @param b
	 *            the given vector
	 * @return the addition of this vector and the given vector
	 */
	public Vector plus(Vector b) {
		return new Vector(this.xComponent + b.xComponent, this.yComponent
				+ b.yComponent);
	}

	/**
	 * Returns a vector which is the subtraction of this vector and the given
	 * vector b.
	 * 
	 * @param b
	 *            the given vector
	 * @return the subtraction of this vector and the given vector
	 */
	public Vector minus(Vector b) {
		return new Vector(this.xComponent - b.xComponent, this.yComponent
				- b.yComponent);
	}

	/**
	 * Returns a vector which has the negative values of each of the components
	 * of this vector.
	 * 
	 * @return a new vector
	 */
	public Vector neg() {
		return new Vector(-this.xComponent, -this.yComponent);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + (int) this.xComponent + "," + (int) this.yComponent + ")";
	}

	/**
	 * Returns the (rounded) y-component.
	 * 
	 * @return the y-component
	 */
	public int getYComponent() {
		return (int) yComponent;
	}

	/**
	 * Given this = (a1, a2) and given vector b = (b1, b2) then calculates the
	 * determinant D = a1*b2 - b1*a2
	 * 
	 * @param b
	 *            the given vector
	 * @return the determinant D = a1*b2 - b1*a2
	 */
	public double getDeterminantBy(Vector b) {
		return this.xComponent * b.yComponent - b.xComponent * this.yComponent;
	}

	/**
	 * Returns the hash value of this vector.
	 * @return the hash value of this vector
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) this.xComponent;
		result = PRIME * result + (int) this.yComponent;
		return result;
	}

	/**
	 * Determines the equality of this vector with the given vector.
	 * @return true if this vector equals the given vector, false otherwise
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
		final Vector other = (Vector) obj;
		if (Double.doubleToLongBits(this.xComponent) != Double
				.doubleToLongBits(other.xComponent))
			return false;
		if (Double.doubleToLongBits(this.yComponent) != Double
				.doubleToLongBits(other.yComponent))
			return false;
		return true;
	}
}
