/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.PersistenceManager;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Resource;
import org.designpattern.abstractfactory.conroller.ReservationManager;
import org.designpattern.abstractfactory.inmemory.InMemoryPersistenceManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.bfh.ti.daterange.DateRangeFactory;

/**
 * @author Eric Dubuis
 */
public class ReservationManagerWithInMemoryFactoryTest {
	private PersistenceManager pm;
	private ReservationManager rm;

	@BeforeEach
	public void setUp() throws Exception {
		this.pm = new InMemoryPersistenceManager();
		this.pm.init();
		Factory fac = this.pm.getFactory();
		this.rm = new ReservationManager(fac, pm);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.pm.close();
	}

	@Test
	public void testInitialization() {
		assertEquals(3, this.rm.getPersons().size());
		assertEquals(3, this.rm.getResources().size());
		assertEquals(0, this.rm.getReservations().size());
	}

	@Test
	public void makeReservation() {
		Person[] pers = this.rm.getPersons().toArray(new Person[this.rm.getPersons().size()]);
		Resource[] resources = this.rm.getResources().toArray(new Resource[this.rm.getResources().size()]);
		Calendar c = new GregorianCalendar();

		// 9 - 11
		c.clear();
		c.set(2015, 10, 1, 9, 0);
		Date begin = new Date(c.getTimeInMillis());
		c.clear();
		c.set(2015, 10, 1, 11, 0);
		Date end = new Date(c.getTimeInMillis());
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		boolean success = this.rm.makeReservation(resources[0], pers[0], f.createDateRange(begin, end));
		assertTrue(success);
		assertEquals(1, this.rm.getReservations().size());

		// 10 - 11 ==> overlaps existing reservation
		c.clear();
		c.set(2015, 10, 1, 10, 0);
		begin = new Date(c.getTimeInMillis());
		c.clear();
		c.set(2015, 10, 1, 11, 0);
		end = new Date(c.getTimeInMillis());
		success = this.rm.makeReservation(resources[0], pers[0], f.createDateRange(begin, end));
		assertFalse(success);
		assertEquals(1, this.rm.getReservations().size());
	}

	@Test
	public void testListReservations() {
		Person[] pers = this.rm.getPersons().toArray(new Person[this.rm.getPersons().size()]);
		Resource[] resources = this.rm.getResources().toArray(new Resource[this.rm.getResources().size()]);
		Calendar c = new GregorianCalendar();
		// 9 - 11
		c.clear();
		c.set(2015, 10, 1, 9, 0);
		Date begin = new Date(c.getTimeInMillis());
		c.clear();
		c.set(2015, 10, 1, 11, 0);
		Date end = new Date(c.getTimeInMillis());
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		boolean success = this.rm.makeReservation(resources[0], pers[0], f.createDateRange(begin, end));

		// 14 - 16 ==> two reservations
		c.clear();
		c.set(2015, 10, 1, 14, 0);
		begin = new Date(c.getTimeInMillis());
		c.clear();
		c.set(2015, 10, 1, 16, 0);
		end = new Date(c.getTimeInMillis());
		success = this.rm.makeReservation(resources[0], pers[0], f.createDateRange(begin, end));
		assertTrue(success);
		assertEquals(2, this.rm.getReservations().size());
		assertEquals(2, pers[0].getReservations().size());
	}

	@Test
	public void testIsBooked() {
		Person[] pers = this.rm.getPersons().toArray(new Person[this.rm.getPersons().size()]);
		Resource[] resources = this.rm.getResources().toArray(new Resource[this.rm.getResources().size()]);
		Calendar c = new GregorianCalendar();
		// 9 - 11
		c.clear();
		c.set(2015, 10, 1, 9, 0);
		Date begin = new Date(c.getTimeInMillis());
		c.clear();
		c.set(2015, 10, 1, 11, 0);
		Date end = new Date(c.getTimeInMillis());
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		this.rm.makeReservation(resources[0], pers[0], f.createDateRange(begin, end));
		assertTrue(this.rm.isBooked(resources[0], f.createDateRange(begin, end)));
	}
}
