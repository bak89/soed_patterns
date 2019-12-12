/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.inmemory;

import java.util.Set;

import org.designpattern.abstractfactory.concept.Factory;
import org.designpattern.abstractfactory.concept.Person;
import org.designpattern.abstractfactory.concept.Reservation;
import org.designpattern.abstractfactory.concept.Resource;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public class InMemoryFactory implements Factory {

    /**
     * @see org.designpattern.abstractfactory.concept.Factory#makePerson(java.lang.String)
     */
    @Override
    public Person makePerson(String name) {
        return new PersonImpl(name);
    }

    /**
     * @see org.designpattern.abstractfactory.concept.Factory#makeResource(java.lang.String)
     */
    @Override
    public Resource makeResource(String name) {
        return new ResourceImpl(name);
    }

    /**
     * @see org.designpattern.abstractfactory.concept.Factory#makeReservation(java.util.Set,
     * org.designpattern.abstractfactory.concept.Person,
     * ch.bfh.ti.daterange.DateRange)
     */
    @Override
    public Reservation makeReservation(Set<Resource> rs, Person p, DateRange dr) {
        Reservation res = new ReservationImpl(rs, p, dr);
        //link resources with this reservation
        for (Resource r: rs){
            r.addReservation(res);
        }
        //link person with the reservation
        p.addReservation(res);
        return res;
    }
}
