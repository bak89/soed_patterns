/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.PersistenceManager;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

import com.thoughtworks.xstream.XStream;

/**
 * @author Eric Dubuis
 */
public class PersistenceManagerUsingXStream implements PersistenceManager {
	private static String SERIALIZED_DATA = "data.xml";
	private PersistedData pd;

	private static class PersistedData {
		public Set<Person> persons;
		public Set<Resource> resources;
		public Set<Reservation> reservations;
	}

	public PersistenceManagerUsingXStream() {
		this.pd = new PersistedData();
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#init()
	 */
	@Override
	public void init() throws Exception {
		File file = new File(SERIALIZED_DATA);
		if (file.isFile() && !file.isDirectory()) {
			// read objects
			XStream xstream = new XStream();
			this.pd = (PersistedData) xstream.fromXML(file);
		} else {
			this.pd.reservations = new HashSet<>();
			this.pd.resources = new HashSet<>();
			this.pd.persons = new HashSet<>();
		}
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#close()
	 */
	@Override
	public void close() throws Exception {
		XStream xstream = new XStream();
		File file = new File(SERIALIZED_DATA);
		if (!file.exists()) {
			// create new empty file
			file.createNewFile();
		}
		FileOutputStream os = new FileOutputStream(file);
		xstream.toXML(pd, os);
		os.close();
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getPersons()
	 */
	@Override
	public Set<Person> getPersons() {
		return this.pd.persons;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getResources
	 *      ()
	 */
	@Override
	public Set<Resource> getResources() {
		return this.pd.resources;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      getReservations()
	 */
	@Override
	public Set<Reservation> getReservations() {
		return this.pd.reservations;
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistPerson(org.designpattern.abstractfactory.concept.Person)
	 */
	@Override
	public void persistPerson(Person p) {
		this.pd.persons.add(p);
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistResource(org.designpattern.abstractfactory.concept.Resource)
	 */
	@Override
	public void persistResource(Resource r) {
		this.pd.resources.add(r);
	}

	/**
	 *
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#
	 *      persistReservation(org.designpattern.abstractfactory.concept.Reservation)
	 */
	@Override
	public void persistReservation(Reservation res) {
		this.pd.reservations.add(res);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getFactory()
	 */
	@Override
	public Factory getFactory() {
		return new FactoryForXStream();
	}
}
