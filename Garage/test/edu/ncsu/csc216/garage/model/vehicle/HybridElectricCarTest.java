package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.NoAvailableBayException;
import garage.model.vehicle.BadVehicleInformationException;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.Vehicle;

/**
 * JUnit tests for functionality of HybridElectricCar
 * 
 * @author Summer Mims
 */
public class HybridElectricCarTest
{
	/** instance variables to use throughout test cases */
	private Vehicle v0;
	private Vehicle v1;
	private Vehicle v2;
	private Vehicle v3;

	/**
	 * create instance variables to use throughout tests
	 *
	 * @throws java.lang.Exception
	 *             if an exception occurs during setup
	 */
	@Before
	public void setUp() throws Exception
	{
		v0 = new HybridElectricCar("AWD101", "Jones, Mary", 0);
		v1 = new HybridElectricCar("AWD-101", "Jones, Mary", 1);
		v2 = new HybridElectricCar("AWD1101", "Jones, Mary", 2);
		v3 = new HybridElectricCar("AAWD1101", "Jones, Mary", 3);
	}

	/**
	 * 
	 * test constructor for Vehicle and HybridElectricCar as well as getters for
	 * Vehicle. Also test toString for both parent and child class.
	 *
	 */
	@Test
	public void hybridElectricCarConstructorTest()
	{
		// tier 0
		assertEquals("AWD101", v0.getLicense());
		assertEquals("Jones, Mary", v0.getName());
		assertEquals(0, v0.getTier());
		assertEquals("E None     AWD101   Jones, Mary", v0.toString());
		// tier 1
		assertEquals("AWD-101", v1.getLicense());
		assertEquals("Jones, Mary", v1.getName());
		assertEquals(1, v1.getTier());
		assertEquals("E Silver   AWD-101  Jones, Mary", v1.toString());
		// tier 2
		assertEquals("AWD1101", v2.getLicense());
		assertEquals("Jones, Mary", v2.getName());
		assertEquals(2, v2.getTier());
		assertEquals("E Gold     AWD1101  Jones, Mary", v2.toString());
		// tier 3
		assertEquals("AAWD1101", v3.getLicense());
		assertEquals("Jones, Mary", v3.getName());
		assertEquals(3, v3.getTier());
		assertEquals("E Platinum AAWD1101 Jones, Mary", v3.toString());
	}

	/**
	 * test set license method
	 */
	@Test
	public void setLicenseTest()
	{
		// set null
		try
		{
			v0 = new HybridElectricCar(null, "Jones, Mary", 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("License cannot be blank.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set empty
		try
		{
			v0 = new HybridElectricCar("", "Jones, Mary", 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("License cannot be blank.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set longer than 8 characters
		try
		{
			v0 = new HybridElectricCar("AWDA11011", "Jones, Mary", 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("License cannot be more than 8 characters.",
					e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set with invalid character
		try
		{
			v0 = new HybridElectricCar("A@", "Jones, Mary", 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("Invalid license.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set with valid characters
		try
		{
			v0 = new HybridElectricCar("AWD", "Jones, Mary", 0);
			assertEquals("AWD", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}
		catch (BadVehicleInformationException e)
		{
			fail();
		}

		// set with valid numbers
		try
		{
			v0 = new HybridElectricCar("111", "Jones, Mary", 0);
			assertEquals("111", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}
		catch (BadVehicleInformationException e)
		{
			fail();
		}
	}

	/**
	 * test setName
	 */
	@Test
	public void setNameTest()
	{
		// set null
		try
		{
			v0 = new HybridElectricCar("AWD101", null, 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("Invalid name.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set null
		try
		{
			v0 = new HybridElectricCar("AWD101", "", 0);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("Invalid name.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set valid
		try
		{
			v0 = new HybridElectricCar("AWD101", "Tell, Johnson", 0);
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Tell, Johnson", v0.getName());
			assertEquals(0, v0.getTier());
		}
		catch (BadVehicleInformationException e)
		{
			fail();

		}
	}

	/**
	 * test setTier
	 */
	@Test
	public void setTierName()
	{
		// set negative
		try
		{
			v0.setTier(-1);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("Invalid tier.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set over 3
		try
		{
			v0.setTier(4);
			fail();
		}
		catch (BadVehicleInformationException e)
		{
			assertEquals("Invalid tier.", e.getMessage());
			// check nothing changed
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(0, v0.getTier());
		}

		// set new valid
		try
		{
			v0.setTier(3);
			assertEquals("AWD101", v0.getLicense());
			assertEquals("Jones, Mary", v0.getName());
			assertEquals(3, v0.getTier());
		}
		catch (BadVehicleInformationException e)
		{
			fail();

		}
	}

	/**
	 * test compare tier
	 */
	@Test
	public void compareToTierTest()
	{
		assertEquals(0, v0.compareToTier(v0));
		assertEquals(-1, v0.compareToTier(v1));
		assertEquals(1, v1.compareToTier(v0));
		assertEquals(1, v3.compareToTier(v0));
	}

	/**
	 * test meets filter
	 */
	@Test
	public void meetsFilterTest()
	{
		// try null filter
		assertTrue(v0.meetsFilter(null));
		assertTrue(v0.meetsFilter(""));
		assertTrue(v0.meetsFilter("J"));
		assertTrue(v0.meetsFilter("Jo"));
		assertTrue(v0.meetsFilter("Jon"));
		assertTrue(v0.meetsFilter("Jone"));
		assertTrue(v0.meetsFilter("Jones"));
		assertTrue(v0.meetsFilter("J"));
		assertFalse(v0.meetsFilter("M"));
	}

	/**
	 * test pick service bay
	 * 
	 */
	@Test
	public void testPickServiceBay()
	{
		Garage g = new Garage();
		try
		{
			v0.pickServiceBay(g);
			assertTrue(g.getBayAt(7).isOccupied());
			assertEquals(g.numberOfEmptyBays(), 7);
		}
		catch (NoAvailableBayException e)
		{
			fail();
		}
		catch (BayOccupiedException e)
		{
			fail();
		}
		catch (BayCarMismatchException e)
		{
			// TODO Auto-generated catch block
			fail();
		}
	}

}
