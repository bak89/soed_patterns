/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public final class ItemProducer implements java.lang.Runnable {
	private int count;
	private Buffer<Item> buf;
	private boolean debug;

	public ItemProducer(int number, int count, Buffer<Item> buf, boolean debug) {
		this.count = count;
		this.buf = buf;
		this.debug = debug;
		// Starting the thread here should not harm since this
		// class is final.
		Thread t = new Thread(this, "Producer" + number);
		t.start();
	}

	@Override
	public void run() {
		// Produce count items and return unless interrupted.
		boolean done = false;
		while (!Thread.currentThread().isInterrupted() && !done) {
			int i;
			Item item;
			for (i = 0; i < count; i++) {
				item = new Item(Thread.currentThread().getName(), i);
				try {
					buf.put(item);
				} catch (java.lang.InterruptedException ex) {
					// We've been interrupted, terminate run method.
					return;
				}
				if (debug)
					System.out.println(Thread.currentThread().getName() + ": Produced item " + item.getNumber());
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (java.lang.InterruptedException ex) {
					// We've been interrupted, terminate run method.
					return;
				}
			}
			done = true;
		}
	}
}
