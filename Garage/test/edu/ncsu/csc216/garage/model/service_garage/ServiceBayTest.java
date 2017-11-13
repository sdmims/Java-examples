package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.ServiceBay;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;

/**
 * 
 * JUnit tests for functionality of ServiceBay
 * 
 * @author Summer Mims
 */
public class ServiceBayTest
{
	/** instance variables to use throughout test cases */
	private ServiceBay s1;
	private Vehicle v0;
	private Vehicle v1;

	/**
	 * create instance variables to use throughout tests
	 *
	 * @throws java.lang.Exception
	 *             if an exception occurs during setup
	 */
	@Before
	public void setUp() throws Exception
	{
		ServiceBay.startBayNumberingAt101();
		s1 = new ServiceBay();
		v0 = new RegularCar("AWD101", "Jones, Mary", 0);
		v1 = new HybridElectricCar("AWD-101", "Jones, Mary", 1);

	}

	/**
	 * test constructor methods of ServiceBay
	 * 
	 */
	@Test
	public void constructorTest()
	{
		assertEquals("101", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("S");
		assertEquals("S02", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("");
		assertEquals("103", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("SS");
		assertEquals("S04", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay(null);
		assertEquals("105", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("SS");
		assertEquals("S06", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("SS");
		assertEquals("S07", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("SS");
		assertEquals("S08", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("SS");
		assertEquals("S09", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("S");
		assertEquals("S10", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new ServiceBay("Ss");
		assertEquals("S11", s1.getBayID());
		assertFalse(s1.isOccupied());
	}

	/**
	 * test occupy and release Vehicle methods
	 */
	@Test
	public void occupyAndReleaseTest()
	{
		// occupy with regular car
		try
		{
			s1.occupy(v0);
			assertTrue(s1.isOccupied());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
		// occupy while full
		try
		{
			s1.occupy(v0);
			fail();
		}
		catch (BayOccupiedException e)
		{
			assertTrue(s1.isOccupied());
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
		// release
		assertEquals(v0, s1.release());
		assertFalse(s1.isOccupied());

		// occupy with hybrid car
		try
		{
			s1.occupy(v1);
			assertTrue(s1.isOccupied());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
		// release
		assertEquals(v1, s1.release());
		assertFalse(s1.isOccupied());
	}

	/**
	 * test to String method
	 */
	@Test
	public void toStringTest()
	{
		assertEquals("101: EMPTY", s1.toString());
		try
		{
			s1.occupy(v0);
			assertEquals("101: AWD101   Jones, Mary", s1.toString());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
		s1.release();
		assertEquals("101: EMPTY", s1.toString());
		try
		{
			s1.occupy(v1);
			assertEquals("101: AWD-101  Jones, Mary", s1.toString());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
	}
}
