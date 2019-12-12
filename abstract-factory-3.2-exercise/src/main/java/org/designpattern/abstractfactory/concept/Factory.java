/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.concept;

import java.util.Set;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public interface Factory {

	public Person makePerson(String name);

	public Resource makeResource(String name);

	public Reservation makeReservation(Set<Resource> rs, Person p, DateRange dr);
}
