/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.abstractfactory.concept;

import java.util.Set;

import ch.bfh.ti.daterange.DateRange;

/**
 * @author Eric Dubuis
 */
public interface Reservation {

	public Set<Resource> getResources();

	public void addRessource(Resource r);

	public void removeResource(Resource r);

	public Person getOwner();

	public DateRange getDateRange();

	public void setDateRange(DateRange dr);
}
