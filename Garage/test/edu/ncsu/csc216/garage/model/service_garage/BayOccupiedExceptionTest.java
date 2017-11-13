package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import garage.model.service_garage.BayOccupiedException;

/**
 * Junit test for creation of BayOccupiedException
 * 
 * @author Summer Mims
 */
public class BayOccupiedExceptionTest
{
	/**
	 * 
	 * test construction of BayOccupiedException
	 *
	 */
	@Test
	public void constructorTest()
	{
		BayOccupiedException b = new BayOccupiedException();
		assertEquals("Bay is occupied.", b.getMessage());
		b = new BayOccupiedException("New");
		assertEquals("New", b.getMessage());
	}
}
