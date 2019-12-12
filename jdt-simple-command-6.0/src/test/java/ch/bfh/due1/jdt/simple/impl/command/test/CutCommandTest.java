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

import ch.bfh.due1.jdt.framework.Clipboard;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleBox;
import ch.bfh.sed.commandpattern.command.CutCommand;
import ch.bfh.sed.commandpattern.command.MacroCommand;


/**
 * Test class for testing the 'cut' command.
 */
public class CutCommandTest {
	private Editor e = new EditorAdaptor() {
		private List<Shape> shapes = new ArrayList<Shape>();

		private Clipboard cp = new Clipboard() {
			private List<Shape> shapes = new ArrayList<Shape>();

			@Override
			public void put(List<Shape> shapes) {
				this.shapes.addAll(shapes);
			}

			@Override
			public List<Shape> get() {
				return this.shapes;
			}

			@Override
			public void remove(List<Shape> shapes) {
				this.shapes.removeAll(shapes);
			}

			@Override
			public void clear() {
				this.shapes.clear();
			}
		};

		@Override
		public Clipboard getClipboard() {
			return this.cp;
		}

		@Override
		public void addShape(Shape s) {
			this.shapes.add(s);
		}

		@Override
		public boolean removeShape(Shape s) {
			return this.shapes.remove(s);
		}

		@Override
		public List<Shape> getShapes() {
			return this.shapes;
		}

		@Override
		public void removeFromSelection(Shape s) {
			// Intentionally left empty
		}
	};

	@Test
	public void testCutCommand() {
		Shape s1 = new SimpleBox(10, 10, 10, 10);
		Shape s2 = new SimpleBox(40, 40, 10, 10);
		e.addShape(s1);
		e.addShape(s2);
		assertEquals(2, e.getShapes().size());
		Command mc = new MacroCommand();
		Command c1 = new CutCommand(e, s1);
		Command c2 = new CutCommand(e, s2);
		mc.addCommand(c1);
		mc.addCommand(c2);
		mc.execute();
		assertEquals(2, e.getClipboard().get().size());
		assertEquals(0, e.getShapes().size());
	}
}
