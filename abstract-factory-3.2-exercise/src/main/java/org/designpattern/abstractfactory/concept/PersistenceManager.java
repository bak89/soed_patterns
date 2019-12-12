/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.concept;

import java.util.Set;

/**
 * @author Eric Dubuis
 */
public interface PersistenceManager {
	public Factory getFactory();

	public void init() throws Exception;

	public void close() throws Exception;

	public Set<Person> getPersons();

	public Set<Resource> getResources();

	public Set<Reservation> getReservations();

	public void persistPerson(Person p);

	public void persistResource(Resource r);

	public void persistReservation(Reservation res);
}
