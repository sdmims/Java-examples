package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import garage.model.vehicle.BadVehicleInformationException;

/**
 * Junit test for creation of BadVehicleInformationException
 * 
 * @author Summer Mims
 */
public class BadVehicleInformationExceptionTest
{
	/**
	 * 
	 * test construction of BadVehicleInformationException
	 *
	 */
	@Test
	public void constructorTest()
	{
		BadVehicleInformationException b = new BadVehicleInformationException();
		assertEquals("Bad vehicle information.", b.getMessage());
		b = new BadVehicleInformationException("New");
		assertEquals("New", b.getMessage());
	}
}
