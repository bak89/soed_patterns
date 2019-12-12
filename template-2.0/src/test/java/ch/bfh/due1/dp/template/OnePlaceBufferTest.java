/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class OnePlaceBufferTest {
	private Buffer<Item> bufferToTest;
	private boolean reached;

	@Before
	public void setUp() {
		bufferToTest = new OnePlaceBuffer<Item>();
		reached = false;
	}

	@Test
	public void testPutGet() throws Exception {
		Item item = new Item("item", 1);
		bufferToTest.put(item);
		assertSame(item, bufferToTest.get());
	}

	@Test
	public void testGetEmpty() throws Exception {
		Thread helper = new Thread() {
			@Override
			public void run() {
				try {
					bufferToTest.get();
					// should not be reached
					reached = true;
				} catch (InterruptedException e) {
					// Intentionally left empty -- nothing to do but to return.
				}
			}
		};
		helper.start();
		Thread.sleep(10);
		helper.interrupt();
		assertFalse("bufferToTest.get() did not block, failed", reached);
	}

	@Test
	public void testPutFull() throws Exception {
		Thread helper = new Thread() {
			@Override
			public void run() {
				try {
					Item item1 = new Item("item", 1);
					bufferToTest.put(item1);
					Item item2 = new Item("item", 2);
					bufferToTest.put(item2);
					// should not be reached
					reached = true;
				} catch (InterruptedException e) {
					// Intentionally left empty -- nothing to do but to return.
				}
			}
		};
		helper.start();
		Thread.sleep(10);
		helper.interrupt();
		assertFalse("bufferToTest.put() did not block, failed", reached);
	}
}
