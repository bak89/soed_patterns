/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.AbstractHandle;
import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;

/**
 * Class that tests abstract handle.
 * @author Eric Dubuis
 */
public class AbstractHandleTest {
	/**
	 * Dummy Graphics2D class. Experimental.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyGraphics extends Graphics2D {
		/** Test flag set when setStroke() is called. */
		private boolean isSetStroke = false;
		/** Test flag set when setColor() is called. */
		private boolean isSetColor = false;

		/**
		 * Returns the value of the isSetStroke test flag.
		 * @return value of isSetStroke
		 */
		public boolean isSetStroke() {
			return this.isSetStroke;
		}

		/**
		 * Returns the value of the isSetColor test flag.
		 * @return value of isSetColor
		 */
		public boolean isSetColor() {
			return this.isSetColor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#addRenderingHints(java.util.Map)
		 */
		@Override
		public void addRenderingHints(Map<?, ?> hints) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#clip(java.awt.Shape)
		 */
		@Override
		public void clip(java.awt.Shape s) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#draw(java.awt.Shape)
		 */
		@Override
		public void draw(java.awt.Shape s) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawGlyphVector(java.awt.font.GlyphVector,
		 *      float, float)
		 */
		@Override
		public void drawGlyphVector(GlyphVector g, float x, float y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawImage(java.awt.Image,
		 *      java.awt.geom.AffineTransform, java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, AffineTransform xform,
				ImageObserver obs) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawImage(java.awt.image.BufferedImage,
		 *      java.awt.image.BufferedImageOp, int, int)
		 */
		@Override
		public void drawImage(BufferedImage img, BufferedImageOp op, int x,
				int y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawRenderableImage(java.awt.image.renderable.RenderableImage,
		 *      java.awt.geom.AffineTransform)
		 */
		@Override
		public void drawRenderableImage(RenderableImage img,
				AffineTransform xform) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawRenderedImage(java.awt.image.RenderedImage,
		 *      java.awt.geom.AffineTransform)
		 */
		@Override
		public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawString(java.lang.String, int, int)
		 */
		@Override
		public void drawString(String str, int x, int y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawString(java.lang.String, float, float)
		 */
		@Override
		public void drawString(String s, float x, float y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator,
		 *      int, int)
		 */
		@Override
		public void drawString(AttributedCharacterIterator iterator, int x,
				int y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator,
		 *      float, float)
		 */
		@Override
		public void drawString(AttributedCharacterIterator iterator, float x,
				float y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#fill(java.awt.Shape)
		 */
		@Override
		public void fill(java.awt.Shape s) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getBackground()
		 */
		@Override
		public Color getBackground() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getComposite()
		 */
		@Override
		public Composite getComposite() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getDeviceConfiguration()
		 */
		@Override
		public GraphicsConfiguration getDeviceConfiguration() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getFontRenderContext()
		 */
		@Override
		public FontRenderContext getFontRenderContext() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getPaint()
		 */
		@Override
		public Paint getPaint() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getRenderingHint(java.awt.RenderingHints.Key)
		 */
		@Override
		public Object getRenderingHint(Key hintKey) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getRenderingHints()
		 */
		@Override
		public RenderingHints getRenderingHints() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getStroke()
		 */
		@Override
		public Stroke getStroke() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#getTransform()
		 */
		@Override
		public AffineTransform getTransform() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#hit(java.awt.Rectangle, java.awt.Shape,
		 *      boolean)
		 */
		@Override
		public boolean hit(Rectangle rect, java.awt.Shape s, boolean onStroke) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#rotate(double)
		 */
		@Override
		public void rotate(double theta) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#rotate(double, double, double)
		 */
		@Override
		public void rotate(double theta, double x, double y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#scale(double, double)
		 */
		@Override
		public void scale(double sx, double sy) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setBackground(java.awt.Color)
		 */
		@Override
		public void setBackground(Color color) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setComposite(java.awt.Composite)
		 */
		@Override
		public void setComposite(Composite comp) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setPaint(java.awt.Paint)
		 */
		@Override
		public void setPaint(Paint paint) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setRenderingHint(java.awt.RenderingHints.Key,
		 *      java.lang.Object)
		 */
		@Override
		public void setRenderingHint(Key hintKey, Object hintValue) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setRenderingHints(java.util.Map)
		 */
		@Override
		public void setRenderingHints(Map<?, ?> hints) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setStroke(java.awt.Stroke)
		 */
		@Override
		public void setStroke(Stroke s) {
			this.isSetStroke = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#setTransform(java.awt.geom.AffineTransform)
		 */
		@Override
		public void setTransform(AffineTransform Tx) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#shear(double, double)
		 */
		@Override
		public void shear(double shx, double shy) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#transform(java.awt.geom.AffineTransform)
		 */
		@Override
		public void transform(AffineTransform Tx) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#translate(int, int)
		 */
		@Override
		public void translate(int x, int y) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics2D#translate(double, double)
		 */
		@Override
		public void translate(double tx, double ty) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#clearRect(int, int, int, int)
		 */
		@Override
		public void clearRect(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#clipRect(int, int, int, int)
		 */
		@Override
		public void clipRect(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#copyArea(int, int, int, int, int, int)
		 */
		@Override
		public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#create()
		 */
		@Override
		public Graphics create() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#dispose()
		 */
		@Override
		public void dispose() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
		 */
		@Override
		public void drawArc(int x, int y, int width, int height,
				int startAngle, int arcAngle) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int,
		 *      java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int,
		 *      java.awt.Color, java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int x, int y, Color bgcolor,
				ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int,
		 *      java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int x, int y, int width,
				int height, ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int,
		 *      java.awt.Color, java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int x, int y, int width,
				int height, Color bgcolor, ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int,
		 *      int, int, int, int, java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
				int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int,
		 *      int, int, int, int, java.awt.Color,
		 *      java.awt.image.ImageObserver)
		 */
		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
				int sx1, int sy1, int sx2, int sy2, Color bgcolor,
				ImageObserver observer) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawLine(int, int, int, int)
		 */
		@Override
		public void drawLine(int x1, int y1, int x2, int y2) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawOval(int, int, int, int)
		 */
		@Override
		public void drawOval(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
		 */
		@Override
		public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
		 */
		@Override
		public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
		 */
		@Override
		public void drawRoundRect(int x, int y, int width, int height,
				int arcWidth, int arcHeight) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
		 */
		@Override
		public void fillArc(int x, int y, int width, int height,
				int startAngle, int arcAngle) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#fillOval(int, int, int, int)
		 */
		@Override
		public void fillOval(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#fillPolygon(int[], int[], int)
		 */
		@Override
		public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#fillRect(int, int, int, int)
		 */
		@Override
		public void fillRect(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
		 */
		@Override
		public void fillRoundRect(int x, int y, int width, int height,
				int arcWidth, int arcHeight) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#getClip()
		 */
		@Override
		public java.awt.Shape getClip() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#getClipBounds()
		 */
		@Override
		public Rectangle getClipBounds() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#getColor()
		 */
		@Override
		public Color getColor() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#getFont()
		 */
		@Override
		public Font getFont() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#getFontMetrics(java.awt.Font)
		 */
		@Override
		public FontMetrics getFontMetrics(Font f) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setClip(java.awt.Shape)
		 */
		@Override
		public void setClip(java.awt.Shape clip) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setClip(int, int, int, int)
		 */
		@Override
		public void setClip(int x, int y, int width, int height) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setColor(java.awt.Color)
		 */
		@Override
		public void setColor(Color c) {
			this.isSetColor = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setFont(java.awt.Font)
		 */
		@Override
		public void setFont(Font font) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setPaintMode()
		 */
		@Override
		public void setPaintMode() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Graphics#setXORMode(java.awt.Color)
		 */
		@Override
		public void setXORMode(Color c1) {
		}
	}

	/**
	 * Helper class for the tests.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyShape extends AbstractShape {

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Shape#contains(ch.bfh.due1.jdt.framework.Coord)
		 */
		@Override
		public boolean contains(Coord c) {
			return false;
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.AbstractShape#doDrawShape(java.awt.Graphics)
		 */
		@Override
		protected void doDrawShape(Graphics g) {
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Shape#getBoundingBox()
		 */
		@Override
		public BoundingBox getBoundingBox() {
			return null;
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Shape#move(ch.bfh.due1.jdt.framework.Vector)
		 */
		@Override
		public void move(Vector delta) {
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Shape#setBoundingBox(ch.bfh.due1.jdt.framework.BoundingBox)
		 */
		@Override
		public void setBoundingBox(BoundingBox r) {
		}

		/**
		 * Not implemented.
		 * @see ch.bfh.due1.jdt.framework.Shape#cloneMe()
		 */
		@Override
		public Shape cloneMe() {
			throw new UnsupportedOperationException("Method not implemented");
		}
		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#createMemento()
		 */
		@Override
		public Memento createMemento() {
			throw new UnsupportedOperationException("Method not implemented");
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#setMemento(Memento)
		 */
		@Override
		public void setMemento(Memento m) {
			throw new UnsupportedOperationException("Method not implemented");
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#getShapeHandles()
		 */
		@Override
		public List<ShapeHandle> getShapeHandles() {
			throw new UnsupportedOperationException("Method not implemented");
		}
	}

	/**
	 * Dummy handle to test.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyHandle extends AbstractHandle {
		/**
		 * Creates a dummy handle.
		 * 
		 * @param c
		 *            the location
		 */
		DummyHandle(Coord c) {
			super(new DummyShape(), c);
		}

		/**
		 * Creates a dummy handle.
		 * 
		 * @param s
		 *            a shape
		 * @param c
		 *            the location
		 */
		DummyHandle(Shape s, Coord c) {
			super(s, c);
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.AbstractHandle#getCursor()
		 */
		@Override
		public Cursor getCursor() {
			return null;
		}

	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#contains(ch.bfh.due1.jdt.framework.Coord)}.
	 */
	@Test
	public void testContains() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		assertTrue(h.contains(new Coord(10, 10)));
		assertTrue(h.contains(new Coord(10 - (AbstractHandle.HANDLE_SIZE / 2),
				10 - (AbstractHandle.HANDLE_SIZE / 2))));
		assertTrue(h.contains(new Coord(
				10 + (AbstractHandle.HANDLE_SIZE / 2) - 1,
				10 + (AbstractHandle.HANDLE_SIZE / 2) - 1)));
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractHandle#getBounds()}.
	 */
	@Test
	public void testGetBounds() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		BoundingBox actual = h.getBounds();
		BoundingBox expected = new BoundingBox(new Coord(
				10 - (AbstractHandle.HANDLE_SIZE / 2),
				10 - (AbstractHandle.HANDLE_SIZE / 2)),
				AbstractHandle.HANDLE_SIZE, AbstractHandle.HANDLE_SIZE);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractHandle#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		Shape s = new DummyShape();
		ShapeHandle h = new DummyHandle(s, new Coord(10, 10));
		assertTrue(h.getOwner() == s);
	}

	/**
	 * Tests if method
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#startInteraction(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}
	 * got default implementation.
	 */
	@Test
	public void testStartInteraction() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		h.startInteraction(new Coord(10, 10), null);
	}

	/**
	 * Tests if method
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#dragInteraction(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}
	 * got default implementation.
	 */
	@Test
	public void testDragInteraction() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		h.dragInteraction(new Coord(10, 10), null);
	}

	/**
	 * Tests if method
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#stopInteraction(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}
	 * got default implementation.
	 */
	@Test
	public void testStopInteraction() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		h.stopInteraction(new Coord(10, 10), null);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractHandle#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		Coord actual = h.getPosition();
		Coord expected = new Coord(10, 10);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#setPosition(ch.bfh.due1.jdt.framework.Coord)}.
	 */
	@Test
	public void testSetPosition() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		h.setPosition(new Coord(20, 20));
		Coord actual = h.getPosition();
		Coord expected = new Coord(20, 20);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractHandle#setPosition(ch.bfh.due1.jdt.framework.Coord)}.
	 * Experimental.
	 */
	@Test
	public void testDraw() {
		ShapeHandle h = new DummyHandle(new Coord(10, 10));
		DummyGraphics g = new DummyGraphics();
		h.draw(g);
		assertTrue(g.isSetColor() && g.isSetStroke());
	}

	/**
	 * Tests getPosition().
	 */
	@Test
	public void testPosition1() {
		ShapeHandle h = new DummyHandle(new Coord(10, 20));
		assertEquals(new Coord(10, 20), h.getPosition());
	}

	/**
	 * Tests setPosition() and getPosition().
	 */
	@Test
	public void testPosition2() {
		ShapeHandle h = new DummyHandle(new Coord(10, 20));
		h.setPosition(new Coord(40, 50));
		assertEquals(new Coord(40, 50), h.getPosition());
	}

	/**
	 * Tests getBounds() and BoundingBox.contains().
	 */
	@Test
	public void testContains1() {
		ShapeHandle h = new DummyHandle(new Coord(10, 20));
		assertTrue(h.getBounds().contains(new Coord(10, 20)));
	}

	/**
	 * Tests getBounds() and BoundingBox.contains().
	 */
	@Test
	public void testContains2() {
		ShapeHandle h = new DummyHandle(new Coord(10, 20));
		assertTrue(h.getBounds().contains(
				new Coord(10 - (AbstractHandle.HANDLE_SIZE - 1) / 2,
						20 - (AbstractHandle.HANDLE_SIZE - 1) / 2)));
		assertTrue(h.getBounds().contains(
				new Coord(10 - AbstractHandle.HANDLE_SIZE / 2,
						20 + (AbstractHandle.HANDLE_SIZE - 1) / 2)));
		assertTrue(h.getBounds().contains(
				new Coord(10 + (AbstractHandle.HANDLE_SIZE - 1) / 2,
						20 - (AbstractHandle.HANDLE_SIZE - 1) / 2)));
		assertTrue(h.getBounds().contains(
				new Coord(10 + (AbstractHandle.HANDLE_SIZE - 1) / 2,
						20 + (AbstractHandle.HANDLE_SIZE - 1) / 2)));
	}
}
