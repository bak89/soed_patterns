/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.xml;

import java.util.Set;

import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public class PersistableReservation implements Reservation {
	private Set<Resource> resources;
	private Person owner;
	private DateRange dr;

	/**
	 * @param rs a set of resources such as rooms
	 * @param p a person
	 * @param dr a date range
	 */
	PersistableReservation(Set<Resource> rs, Person p, DateRange dr) {
		this.resources = rs;
		this.owner = p;
		this.dr = dr;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#getResources()
	 */
	@Override
	public Set<Resource> getResources() {
		return this.resources;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#addRessource(org.designpattern.abstractfactory.concept.Resource)
	 */
	@Override
	public void addRessource(Resource r) {
		this.resources.add(r);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#removeResource(org.designpattern.abstractfactory.concept.Resource)
	 */
	@Override
	public void removeResource(Resource r) {
		this.resources.remove(r);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#getDateRange()
	 */
	@Override
	public DateRange getDateRange() {
		return this.dr;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#setDateRange(ch.bfh.ti.daterange.DateRange)
	 */
	@Override
	public void setDateRange(DateRange dr) {
		this.dr = dr;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Reservation#getOwner()
	 */
	@Override
	public Person getOwner() {
		return this.owner;
	}
}
