/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class BoundedBufferTest {
	private Buffer<Item> bufferToTest;
	private final int N = 4;
	private boolean reached;

	@Before
	public void setUp() {
		bufferToTest = new BoundedBuffer<Item>();
		bufferToTest.init(N);
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
	public void testPutFull() throws Exception {
		Thread helper = new Thread() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < N; i++) {
						Item item = new Item("item", i);
						bufferToTest.put(item);
					}
					Item additionalItem = new Item("item", N + 1);
					bufferToTest.put(additionalItem);
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
