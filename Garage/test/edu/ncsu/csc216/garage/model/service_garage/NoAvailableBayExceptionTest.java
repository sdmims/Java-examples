package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import garage.model.service_garage.NoAvailableBayException;

/**
 * JUnit test for creation of NoAvailableBayException
 * 
 * @author Summer Mims
 */
public class NoAvailableBayExceptionTest
{
	/**
	 * 
	 * test construction of BadVehicleInformationException
	 *
	 */
	@Test
	public void constructorTest()
	{
		NoAvailableBayException b = new NoAvailableBayException();
		assertEquals("No available service bay.", b.getMessage());
		b = new NoAvailableBayException("New");
		assertEquals("New", b.getMessage());
	}
}
