package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.ServiceBay;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;

/**
 * JUnit tests for functionality of Garage
 * 
 * @author Summer Mims
 */
public class GarageTest
{

	/** instance variables to use throughout test cases */
	private Garage g;
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
		g = new Garage();
		v0 = new RegularCar("AWD101", "Jones, Mary", 0);
		v1 = new HybridElectricCar("AWD-101", "Jones, Mary", 1);

	}

	/**
	 * test constructor of garage
	 * 
	 */
	@Test
	public void constructorTest()
	{

		assertEquals(8, g.numberOfEmptyBays());
		assertEquals(8, g.getSize());
		assertEquals("108", g.getBayAt(0).getBayID());
		assertEquals("106", g.getBayAt(1).getBayID());
		assertEquals("105", g.getBayAt(2).getBayID());
		assertEquals("103", g.getBayAt(3).getBayID());
		assertEquals("102", g.getBayAt(4).getBayID());
		assertEquals("E01", g.getBayAt(5).getBayID());
		assertEquals("E04", g.getBayAt(6).getBayID());
		assertEquals("E07", g.getBayAt(7).getBayID());

	}

	/**
	 * test addRepairBay method. also tests if added in correct way
	 */
	@Test
	public void addRepairBayTest()
	{
		g.addRepairBay();
		assertEquals(9, g.getSize());
		assertEquals("109", g.getBayAt(0).getBayID());

		g.addRepairBay();
		assertEquals(10, g.getSize());
		assertEquals("E10", g.getBayAt(9).getBayID());

		g.addRepairBay();
		assertEquals(11, g.getSize());
		assertEquals("111", g.getBayAt(0).getBayID());

		g.addRepairBay();
		assertEquals(12, g.getSize());
		assertEquals("112", g.getBayAt(0).getBayID());

		g.addRepairBay();
		assertEquals(13, g.getSize());
		assertEquals("E13", g.getBayAt(12).getBayID());

		// add until over 30
		for (int i = g.getSize(); i < 30; i++)
		{
			g.addRepairBay();
		}
		assertEquals(30, g.getSize());
		// add one more over max
		g.addRepairBay();
		assertEquals(30, g.getSize());
	}

	/**
	 * test getBayAt method
	 */
	@Test
	public void getBayAtTest()
	{
		assertNull(g.getBayAt(-1));
		assertNull(g.getBayAt(8));
	}

	/**
	 * test release method
	 */
	@Test
	public void releaseTest()
	{
		try
		{
			g.getBayAt(0).occupy(v0);
			assertEquals(7, g.numberOfEmptyBays());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}

		try
		{
			g.getBayAt(5).occupy(v1);
			assertEquals(6, g.numberOfEmptyBays());
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			fail();
		}
		// only 0 and 5
		assertNull(g.release(1));
		assertEquals(v0, g.release(0));
		assertEquals(v1, g.release(5));
	}

}
