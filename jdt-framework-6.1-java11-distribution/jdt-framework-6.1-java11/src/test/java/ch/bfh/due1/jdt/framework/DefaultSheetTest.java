/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;


import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.util.DefaultSheet;

/**
 * Tests the pseudo default sheet.
 * @author Eric Dubuis
 */
public class DefaultSheetTest {
	private static Sheet sheet;

	@BeforeClass
	public static void initializeSheet() {
		sheet = new DefaultSheet();
	}

	@Test
	public void testAddShape() {
		sheet.addShape(null);
	}

	@Test
	public void testRemoveShape() {
		sheet.removeShape(null);
	}

	@Test
	public void testDraw() {
		sheet.draw(null);
	}

	@Test
	public void testAddSheetChangedListener() {
		sheet.addSheetChangedListener(null);
	}

	@Test
	public void testRemoveSheetChangedListener() {
		sheet.removeSheetChangedListener(null);
	}

	@Test
	public void testGetShapes() {
		sheet.getShapes();
	}

	@Test
	public void testGetShapesByStackingOrder() {
		sheet.getShapesByStackingOrder(null);
	}
}
