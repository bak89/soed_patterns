/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public final class Item {
	private final String name;
	private final int number;

	public Item(String aName, int aNumber) {
		name = aName;
		number = aNumber;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", number=" + number + "]";
	}
}
