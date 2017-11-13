package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import garage.model.service_garage.BayCarMismatchException;

/**
 * Junit test for creation of BayCarMismatchException
 * 
 * @author Summer Mims
 */
public class BayCarMismatchExceptionTest
{
	/**
	 * 
	 * test construction of BayCarMismatchException
	 *
	 */
	@Test
	public void constructorTest()
	{
		BayCarMismatchException b = new BayCarMismatchException();
		assertEquals("Wrong service bay type.", b.getMessage());
		b = new BayCarMismatchException("New");
		assertEquals("New", b.getMessage());
	}
}
