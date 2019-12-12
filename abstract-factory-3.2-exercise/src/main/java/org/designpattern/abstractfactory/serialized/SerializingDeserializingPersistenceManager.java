/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.serialized;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class SerializingDeserializingPersistenceManager implements PersistenceManager {
	public static String SERIALIZED_DATA = "data.ser";
	private Set<Person> persons;
	private Set<Resource> resources;
	private Set<Reservation> reservations;

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#init()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws Exception {
		File file = new File(SERIALIZED_DATA);
		if (file.isFile() && !file.isDirectory()) {
			// read objects
			FileInputStream is = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(is);
			this.reservations = (Set<Reservation>) ois.readObject();
			this.resources = (Set<Resource>) ois.readObject();
			this.persons = (Set<Person>) ois.readObject();
			ois.close();
			is.close();
		} else {
			this.reservations = new HashSet<>();
			this.resources = new HashSet<>();
			this.persons = new HashSet<>();
		}
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#close()
	 */
	@Override
	public void close() throws Exception {
		File file = new File(SERIALIZED_DATA);
		if (!file.exists()) {
			// create new empty file
			file.createNewFile();
		}
		FileOutputStream os = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(this.reservations);
		oos.writeObject(this.resources);
		oos.writeObject(this.reservations);
		oos.close();
		os.close();
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getPersons()
	 */
	@Override
	public Set<Person> getPersons() {
		return this.persons;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getResources()
	 */
	@Override
	public Set<Resource> getResources() {
		return this.resources;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#getReservations()
	 */
	@Override
	public Set<Reservation> getReservations() {
		return this.reservations;
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#persistPerson(org.designpattern.abstractfactory.concept.Person)
	 */
	@Override
	public void persistPerson(Person p) {
		this.persons.add(p);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#persistResource(org.designpattern.abstractfactory.concept.Resource)
	 */
	@Override
	public void persistResource(Resource r) {
		this.resources.add(r);
	}

	/**
	 * @see org.designpattern.abstractfactory.concept.PersistenceManager#persistReservation(org.designpattern.abstractfactory.concept.Reservation)
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
		return new SerializingDeserializingFactory();
	}
}
