/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2009
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;
import ch.bfh.due1.jdt.framework.util.TestEditor;
import ch.bfh.due1.jdt.simple.ToolFactoriesBuilder;
import ch.bfh.due1.jdt.simple.selection.SelectionTool;
import ch.bfh.sed.observerpattern.SimpleBox;
import ch.bfh.sed.observerpattern.SimpleLine;

/**
 * Tests for testing the selection tool.
 */
public class SelectionToolTest {

	@Test
	public void testMouseDown1() throws Exception {
		Editor e = new TestEditor();
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(10, 10), KeyModifier.NONE);
		t.mouseDrag(new Coord(20, 20), KeyModifier.NONE);
		t.mouseUp(new Coord(20, 20), KeyModifier.NONE);
		assertEquals(0, e.getSelection().size());
	}

	@Test
	public void testMouseDown2() throws Exception {
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(11, 11, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s1);
		e.addShape(s2);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(0, 0), KeyModifier.NONE);
		t.mouseDrag(new Coord(30, 30), KeyModifier.NONE);
		t.mouseUp(new Coord(30, 30), KeyModifier.NONE);
		assertEquals(2, e.getSelection().size());
		assertEquals(16, e.getSelectionHandles().size());
	}

	@Test
	public void testMouseDown3() throws Exception {
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(11, 11, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s1);
		e.addShape(s2);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		t.mouseUp(new Coord(15, 15), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
		assertEquals(8, e.getSelectionHandles().size());
	}

	@Test
	public void testMoveShape() throws Exception {
		Editor e = new TestEditor();
		Shape s = new SimpleBox(10, 10, 10, 10);
		e.addShape(s);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		// Selection tool
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		t.mouseDrag(new Coord(20, 20), KeyModifier.NONE);
		t.mouseUp(new Coord(20, 20), KeyModifier.NONE);
		assertEquals(new BoundingBox(15, 15, 10, 10), s.getBoundingBox());
	}

	@Test
	public void testMoveUndoShape() throws Exception {
		Editor e = new TestEditor();
		Shape s = new SimpleBox(10, 10, 10, 10);
		e.addShape(s);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		// Selection tool
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		t.mouseDrag(new Coord(20, 20), KeyModifier.NONE);
		t.mouseUp(new Coord(20, 20), KeyModifier.NONE);
		assertEquals(new BoundingBox(15, 15, 10, 10), s.getBoundingBox());
		e.getCommandHandler().undoLast();
		assertEquals(new BoundingBox(10, 10, 10, 10), s.getBoundingBox());
	}

	@Test
	public void testMoveUndoShapes() throws Exception {
		// Creating two shapes
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(20, 20, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s1);
		e.addShape(s2);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		// Selection tool
		Tool t = tf.getTool(e);
		// Dragging selection area over both shapes
		t.mouseDown(new Coord(0, 0), KeyModifier.NONE);
		t.mouseDrag(new Coord(40, 40), KeyModifier.NONE);
		t.mouseUp(new Coord(40, 40), KeyModifier.NONE);
		// Pressing mouse over one shape, then moving it
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		t.mouseDrag(new Coord(20, 20), KeyModifier.NONE);
		t.mouseUp(new Coord(20, 20), KeyModifier.NONE);
		assertEquals(new BoundingBox(15, 15, 10, 10), s1.getBoundingBox());
		assertEquals(new BoundingBox(25, 25, 10, 10), s2.getBoundingBox());
		e.getCommandHandler().undoLast();
		assertEquals(new BoundingBox(10, 10, 10, 10), s1.getBoundingBox());
		assertEquals(new BoundingBox(20, 20, 10, 10), s2.getBoundingBox());
	}

	@Test
	public void testDragOnHandle1() throws Exception {
		Editor e = new TestEditor();
		Shape s = new SimpleBox(10, 10, 10, 10);
		e.addShape(s);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		t.mouseUp(new Coord(15, 15), KeyModifier.NONE);
		assertEquals(8, e.getSelectionHandles().size());
		t.mouseDown(new Coord(20, 20), KeyModifier.NONE);
		t.mouseDrag(new Coord(30, 30), KeyModifier.NONE);
		t.mouseUp(new Coord(30, 30), KeyModifier.NONE);
		assertEquals(new BoundingBox(10, 10, 20, 20), s.getBoundingBox());
	}

	@Test
	public void testMoveCursor() throws Exception {
		Editor e = new TestEditor();
		Shape s = new SimpleBox(10, 10, 10, 10);
		e.addShape(s);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.NONE);
		// PENDING Add infrastructure for testing the setting of cursors.
	}

	@Test
	public void testMultiSelection() throws Exception {
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(30, 30, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s1);
		e.addShape(s2);
		ToolFactoriesBuilder fac = new ToolFactoriesBuilder();
		ToolFactory tf = fac.getFactoryForDefaultTool();
		Tool t = tf.getTool(e);
		t.mouseDown(new Coord(15, 15), KeyModifier.CONTROL_DOWN);
		t.mouseUp(new Coord(15, 15), KeyModifier.CONTROL_DOWN);
		t.mouseDown(new Coord(35, 35), KeyModifier.CONTROL_DOWN);
		t.mouseUp(new Coord(35, 35), KeyModifier.CONTROL_DOWN);
		assertEquals(2, e.getSelection().size());
		assertEquals(16, e.getSelectionHandles().size());
		t.mouseDown(new Coord(15, 15), KeyModifier.CONTROL_DOWN);
		t.mouseUp(new Coord(15, 15), KeyModifier.CONTROL_DOWN);
		assertEquals(1, e.getSelection().size());
		assertEquals(8, e.getSelectionHandles().size());
	}

	@Test
	public void testSelectionArea1() {
		// slanting line
		Shape s = new SimpleLine(10, 10, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(25, 25), KeyModifier.NONE);
		t.mouseUp(new Coord(25, 25), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}

	@Test
	public void testSelectionArea2() {
		// vertical line
		Shape s = new SimpleLine(10, 10, 0, 10);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(15, 25), KeyModifier.NONE);
		t.mouseUp(new Coord(15, 25), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}

	@Test
	public void testSelectionArea3() {
		// horizontal line
		Shape s = new SimpleLine(10, 10, 10, 0);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(25, 15), KeyModifier.NONE);
		t.mouseUp(new Coord(25, 15), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}

	@Test
	public void testSelectionArea5() {
		// slanting line negated
		Shape s = new SimpleLine(20, 20, -10, -10);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(25, 25), KeyModifier.NONE);
		t.mouseUp(new Coord(25, 25), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}


	@Test
	public void testSelectionArea6() {
		// vertical line negated
		Shape s = new SimpleLine(10, 20, 0, -10);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(15, 25), KeyModifier.NONE);
		t.mouseUp(new Coord(15, 25), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}

	@Test
	public void testSelectionArea7() {
		// horizontal line negated
		Shape s = new SimpleLine(20, 10, -10, 0);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		t.mouseDown(new Coord(5, 5), KeyModifier.NONE);
		t.mouseDrag(new Coord(25, 15), KeyModifier.NONE);
		t.mouseUp(new Coord(25, 15), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}

	@Test
	public void testSelectionArea8() {
		// slanted line
		Shape s = new SimpleLine(10, 10, 10, 10);
		Editor e = new TestEditor();
		e.addShape(s);
		Tool t = new SelectionTool(e);
		// negated selection area
		t.mouseDown(new Coord(25, 25), KeyModifier.NONE);
		t.mouseDrag(new Coord(5, 5), KeyModifier.NONE);
		t.mouseUp(new Coord(5, 5), KeyModifier.NONE);
		assertEquals(1, e.getSelection().size());
	}
}
