/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;


/**
 * A handle being a small quadratic box. Full handle behavior must be provided
 * by sub-classing this class.
 * 
 * @author Eric Dubuis
 */
public abstract class AbstractHandle implements ShapeHandle {

	/** The size of the quadratic handle. */
	public static final int HANDLE_SIZE = 8;

	/** The size of the pen. */
	public static final int PEN_SIZE = 1;

	/** The color of the pen. */
	public static final Color PEN_COLOR = Color.BLACK;

	/** The fill color. */
	public static final Color FILL_COLOR = Color.WHITE;

	/** The location of this handle. */
	private Coord location;

	/** The owner of the handle. */
	private Shape owner;

	/**
	 * Initializes the abstract (common) part of a handle.
	 * @param owner the shape owning this handle
	 * @param location the current location of the handle
	 */
	protected AbstractHandle(Shape owner, Coord location) {
		this.owner = owner;
		this.location = location;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean contains(Coord c) {
		return new Rectangle(getX0(), getY0(), getWidth(), getHeight())
				.contains(c.getX0(), c.getY0());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color current = g2.getColor();
		Stroke stroke = new BasicStroke(PEN_SIZE);
		g2.setStroke(stroke);
		g2.setColor(PEN_COLOR);
		g2.draw(new Rectangle(getX0(), getY0(), getWidth(), getHeight()));
		g2.setColor(FILL_COLOR);
		g2.fill(new Rectangle(getX0(), getY0(), getWidth(), getHeight()));
		g2.setColor(current);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public BoundingBox getBounds() {
		return new BoundingBox(getX0(), getY0(), getWidth(), getHeight());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Shape getOwner() {
		return this.owner;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public abstract Cursor getCursor();

	/**
	 * Default (null) implementation
	 */
	@Override
	public void startInteraction(Coord c, KeyModifier k) {
	}

	/**
	 * Default (null) implementation
	 */
	@Override
	public void dragInteraction(Coord c, KeyModifier k) {
	}

	/**
	 * Default (null) implementation
	 */
	@Override
	public void stopInteraction(Coord c, KeyModifier k) {
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Coord getPosition() {
		return this.location;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void setPosition(Coord p) {
		this.location = p;
	}

	private int getX0() {
		return this.location.getX0() - HANDLE_SIZE / 2;
	}

	private int getY0() {
		return this.location.getY0() - HANDLE_SIZE / 2;
	}

	private int getWidth() {
		return HANDLE_SIZE;
	}

	private int getHeight() {
		return HANDLE_SIZE;
	}
}
