/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.xml;

import java.util.HashSet;
import java.util.Set;

import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;

/**
 * @author Eric Dubuis
 */
public class PersistablePerson implements Person {
	private String name;
	private Set<Reservation> reservations;

	/**
	 * @param n the name of this person
	 */
	PersistablePerson(String n) {
		this.name = n;
		this.reservations = new HashSet<Reservation>();
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Person#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Person#getReservations()
	 */
	@Override
	public Set<Reservation> getReservations() {
		return this.reservations;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Person#addReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void addReservation(Reservation res) {
		this.reservations.add(res);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Person#removeReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void removeReservation(Reservation res) {
		this.reservations.remove(res);
	}
}
