/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.inmemory;

import java.util.HashSet;
import java.util.Set;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.PersistenceManager;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

/**
 * @author Eric Dubuis
 */
public class InMemoryPersistenceManager implements PersistenceManager {
	private Set<Person> persons;
	private Set<Resource> resources;
	private Set<Reservation> reservations;

	public InMemoryPersistenceManager() {
		this.persons = new HashSet<Person>();
		this.resources = new HashSet<Resource>();
		this.reservations = new HashSet<Reservation>();
		generatePersons();
		generateResources();
	}

	/**
	 *
	 */
	private void generateResources() {
		// let's create three resources
		this.resources.add(new ResourceImpl("Room 001"));
		this.resources.add(new ResourceImpl("Room 002"));
		this.resources.add(new ResourceImpl("Room 003"));
	}

	/**
	 *
	 */
	private void generatePersons() {
		// let's create three persons
		this.persons.add(new PersonImpl("David Parnas"));
		this.persons.add(new PersonImpl("Niklaus Wirth"));
		this.persons.add(new PersonImpl("C.A.R. Hoare"));
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#init()
	 */
	@Override
	public void init() {
		// void -- all is done in the default constructor...
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#close()
	 */
	@Override
	public void close() {
		// void -- does nothing...
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getPersons()
	 */
	@Override
	public Set<Person> getPersons() {
		return this.persons;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getResources
	 *      ()
	 */
	@Override
	public Set<Resource> getResources() {
		return this.resources;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      getReservations()
	 */
	@Override
	public Set<Reservation> getReservations() {
		return this.reservations;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistPerson(org.designpattern.abstractfactory.concept.Person)
	 */
	@Override
	public void persistPerson(Person p) {
		this.persons.add(p);
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistResource(org.designpattern.abstractfactory.concept.Resource)
	 */
	@Override
	public void persistResource(Resource r) {
		this.resources.add(r);
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void persistReservation(Reservation res) {
		this.reservations.add(res);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getFactory()
	 */
	@Override
	public Factory getFactory() {
		return new InMemoryFactory();
	}
}
