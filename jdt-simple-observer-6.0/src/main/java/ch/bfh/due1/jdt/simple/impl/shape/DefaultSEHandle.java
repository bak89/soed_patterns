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

import ch.bfh.due1.jdt.framework.AbstractHandle;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;


/**
 * Default handle at the south-east position of a shape.
 * 
 * @author Eric Dubuis
 */
public class DefaultSEHandle extends AbstractHandle {
	/**
	 * Creates a handle at the south-east position of a shape.
	 * 
	 * @param owner
	 *            The shape that owns this handle.
	 * @param location
	 *            There this handle is located (the center).
	 */
	public DefaultSEHandle(Shape owner, Coord location) {
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
		BoundingBox r = getOwner().getBoundingBox();
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
