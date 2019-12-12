/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.Thread.State;

import org.junit.Before;
import org.junit.Test;

public class UnboundedBufferTest {
	private Buffer<Item> bufferToTest;
	private boolean reached;

	@Before
	public void setUp() {
		bufferToTest = new UnboundedBuffer<Item>();
		reached = false;
	}

	@Test
	public void testPutGet() throws Exception {
		Item item = new Item("item", 1);
		bufferToTest.put(item);
		assertSame(item, bufferToTest.get());
	}

	@Test
	public void testNPutNGet() throws Exception {
		int n = 4;
		for (int i = 0; i < n; i++) {
			Item item = new Item("item", i);
			bufferToTest.put(item);
		}
		for (int i = 0; i < n; i++) {
			Item item = bufferToTest.get();
			assertEquals("bufferToTest put()/get(): mangling ordering", i, item.getNumber());
		}
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
	public void testGetEmptyThenNonEmpty() throws Exception {
		Thread helper = new Thread() {
			@Override
			public void run() {
				try {
					bufferToTest.get();
					// should be reached after main thread inserted item
					reached = true;
				} catch (InterruptedException e) {
					// Intentionally left empty -- nothing to do but to return.
				}
			}
		};
		helper.start();
		Thread.sleep(10);
		State threadState = helper.getState();
		assertFalse(threadState.equals(State.RUNNABLE));
		bufferToTest.put(new Item("item", 1));
		Thread.sleep(10);
		helper.interrupt();
		assertTrue("bufferToTest.get() not block in its first place, and then continue, failed", reached);
	}
}
