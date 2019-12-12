/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.command.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleBox;
import ch.bfh.sed.commandpattern.command.MacroCommand;
import ch.bfh.sed.commandpattern.command.PasteCommand;


/**
 * Test class for testing the 'paste' command.
 */
public class PasteCommandTest {

	private Editor e = new EditorAdaptor() {
		private List<Shape> shapes = new ArrayList<Shape>();
		private List<Shape> selection = new ArrayList<Shape>();

		@Override
		public void addShape(Shape s) {
			this.shapes.add(s);
		}

		@Override
		public void addToSelection(Shape s) {
			this.selection.add(s);
		}

		@Override
		public List<Shape> getShapes() {
			return this.shapes;
		}

		@Override
		public List<Shape> getSelection() {
			return this.selection;
		}

		@Override
		public boolean removeShape(Shape s) {
			return this.shapes.remove(s);
		}

		@Override
		public void removeFromSelection(Shape s) {
			this.selection.remove(s);
		}
	};

	@Test
	public void testPasteCommand() {
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(40, 40, 10, 10);
		Command mc = new MacroCommand();
		Command c1 = new PasteCommand(e, s1);
		Command c2 = new PasteCommand(e, s2);
		mc.addCommand(c1);
		mc.addCommand(c2);
		mc.execute();
		assertEquals(2, this.e.getShapes().size());
		assertEquals(2, this.e.getSelection().size());
		mc.undo();
		assertEquals(0, this.e.getShapes().size());
		assertEquals(0, this.e.getSelection().size());
	}
}
