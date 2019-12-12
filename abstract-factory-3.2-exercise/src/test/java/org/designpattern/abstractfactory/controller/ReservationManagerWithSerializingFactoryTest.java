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

import java.util.Date;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.PersistenceManager;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Resource;
import org.designpattern.abstractfactory.conroller.ReservationManager;
import org.designpattern.abstractfactory.serialized.SerializingDeserializingPersistenceManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.bfh.ti.daterange.DateRangeFactory;

/**
 * @author Eric Dubuis
 */
public class ReservationManagerWithSerializingFactoryTest {
	private static Factory fac;
	private static PersistenceManager pm;
	private static ReservationManager rm;

	@BeforeAll
	public static void init() throws Exception {
		pm = new SerializingDeserializingPersistenceManager();
		pm.init();
		fac = pm.getFactory();
		rm = new ReservationManager(fac, pm);
	}

	@BeforeEach
	public void setUp() throws Exception {
		//
	}

	@AfterEach
	public void tearDown() {
		//
	}

	@AfterAll
	public static void close() throws Exception {
		pm.close();
	}

	@Test
	public void testMakeReservation() {
		Person p = fac.makePerson("Ian_Sommerville_" + (int) (Math.random() * 100));
		pm.persistPerson(p);
		Resource r = fac.makeResource("Room_" + (int) (Math.random() * 100));
		pm.persistResource(r);
		int resCount = rm.getReservations().size();

		// now
		Date begin = new Date();
		Date end = new Date(begin.getTime() + 100000);
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		boolean success = rm.makeReservation(r, p, f.createDateRange(begin, end));
		assertTrue(success);
		assertEquals(resCount + 1, rm.getReservations().size());

		// now+50000 ==> overlaps existing reservation
		begin = new Date(begin.getTime() + 50000);
		end = new Date(begin.getTime() + 100000);
		success = rm.makeReservation(r, p, f.createDateRange(begin, end));
		assertFalse(success);
		assertEquals(resCount + 1, rm.getReservations().size());
	}

	@Test
	public void testListReservations() {
		Person p = fac.makePerson("Erich_Gamma_" + (int) (Math.random() * 100));
		pm.persistPerson(p);
		Resource r = fac.makeResource("Meeting_Room_" + (int) (Math.random() * 100));
		pm.persistResource(r);
		int resCount = rm.getReservations().size();

		// now
		Date begin = new Date();
		Date end = new Date(begin.getTime() + 100000);
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		boolean success = rm.makeReservation(r, p, f.createDateRange(begin, end));

		// later
		begin = new Date(begin.getTime() + 200000);
		end = new Date(begin.getTime() + 300000);
		success = rm.makeReservation(r, p, f.createDateRange(begin, end));
		assertTrue(success);
		assertEquals(resCount + 2, rm.getReservations().size());
		assertEquals(2, p.getReservations().size());
	}

	@Test
	public void testIsBooked() {
		Person p = fac.makePerson("Kent_Beck_" + (int) (Math.random() * 100));
		pm.persistPerson(p);
		Resource r = fac.makeResource("Test_Room_" + (int) (Math.random() * 100));
		pm.persistResource(r);
		Date begin = new Date();
		Date end = new Date(begin.getTime() + 100000);
		DateRangeFactory f = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		rm.makeReservation(r, p, f.createDateRange(begin, end));
		assertTrue(rm.isBooked(r, f.createDateRange(begin, end)));
	}
}
