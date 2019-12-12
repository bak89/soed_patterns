/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.conroller;

import java.util.Collections;
import java.util.Set;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.PersistenceManager;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public class ReservationManager {
	private Factory fac = null;

	private PersistenceManager pm;

	public ReservationManager(Factory f, PersistenceManager pm) throws Exception {
		this.fac = f;
		this.pm = pm;
	}

	public Set<Person> getPersons() {
		return this.pm.getPersons();
	}

	public Set<Resource> getResources() {
		return this.pm.getResources();
	}

	public Set<Reservation> getReservations() {
		return this.pm.getReservations();
	}

	public boolean makeReservation(Resource r, Person p, DateRange dr) {
		if (!r.isOccupied(dr)) {
			Set<Resource> res = Collections.singleton(r);
			Reservation rv = this.fac.makeReservation(res, p, dr);
			this.pm.persistReservation(rv);
			return true;
		} else {
			return false;
		}
	}

	public boolean isBooked(Resource r, DateRange dr) {
		return r.isOccupied(dr);
	}
}
