/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.serialized;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public class SerializableResource implements Resource, Serializable {
	private static final long serialVersionUID = -1493239026660660835L;
	private String name;
	private Set<Reservation> reservations;

	/**
	 * @param n the name of a resource
	 */
	SerializableResource(String n) {
		this.name = n;
		this.reservations = new HashSet<>();
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Resource#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Resource#isOccupied(ch.bfh.ti.daterange.DateRange)
	 */
	@Override
	public boolean isOccupied(DateRange dr) {
		boolean res = false;
		for (Reservation r : this.reservations) {
			if (dr.overlaps(r.getDateRange())) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Resource#getReservations()
	 */
	@Override
	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(this.reservations);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Resource#addReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void addReservation(Reservation res) {
		this.reservations.add(res);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.Resource#removeReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void removeReservation(Reservation res) {
		this.reservations.remove(res);
	}
}
