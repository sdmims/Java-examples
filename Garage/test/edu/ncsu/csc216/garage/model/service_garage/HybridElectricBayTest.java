package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.HybridElectricBay;
import garage.model.service_garage.ServiceBay;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;

/**
 * 
 * JUnit tests for functionality of HybridElectricBay
 * 
 * @author Summer Mims
 */
public class HybridElectricBayTest
{
	/** instance variables to use throughout test cases */
	private HybridElectricBay s1;
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
		s1 = new HybridElectricBay();
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
		assertEquals("E01", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E02", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E03", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E04", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E05", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E06", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E07", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E08", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E09", s1.getBayID());
		assertFalse(s1.isOccupied());

		s1 = new HybridElectricBay();
		assertEquals("E10", s1.getBayID());
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
			fail();
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			assertFalse(s1.isOccupied());
		}
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

		// occupy while full
		try
		{
			s1.occupy(v1);
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
		assertEquals(v1, s1.release());
		assertFalse(s1.isOccupied());
	}

	/**
	 * test to String method
	 */
	@Test
	public void toStringTest()
	{
		assertEquals("E01: EMPTY", s1.toString());
		try
		{
			s1.occupy(v1);
			assertEquals("E01: AWD-101  Jones, Mary", s1.toString());
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
