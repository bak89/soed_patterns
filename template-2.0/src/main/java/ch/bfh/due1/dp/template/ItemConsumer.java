/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public final class ItemConsumer implements java.lang.Runnable {
	private int count;
	private Buffer<Item> buf;
	private boolean debug;

	public ItemConsumer(int number, int count, Buffer<Item> buf, boolean debug) {
		this.count = count;
		this.buf = buf;
		this.debug = debug;
		Thread t = new Thread(this, "Consumer" + number);
		// Starting the thread here should not harm since this
		// class is final.
		t.start();
	}

	@Override
	public void run() {
		// Consume count items and return unless interrupted.
		boolean done = false;
		while (!Thread.currentThread().isInterrupted() && !done) {
			int i;
			Item item;
			for (i = 0; i < count; i++) {
				try {
					item = buf.get();
				} catch (InterruptedException e) {
					// We've been interrupted, terminate run method.
					return;
				}
				if (debug)
					System.out.println(
							Thread.currentThread().getName() + ": Consumed item " + item.getNumber() + " from " + item.getName());
				try {
					Thread.sleep((int) (Math.random() * 200));
				} catch (java.lang.InterruptedException ex) {
					// We've been interrupted, terminate run method.
					return;
				}
			}
			done = true;
		}
	}
}
