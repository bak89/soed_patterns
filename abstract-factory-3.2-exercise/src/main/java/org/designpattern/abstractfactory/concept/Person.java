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
public interface Person {

	public String getName();

	public Set<Reservation> getReservations();

	public void addReservation(Reservation res);

	public void removeReservation(Reservation res);
}
