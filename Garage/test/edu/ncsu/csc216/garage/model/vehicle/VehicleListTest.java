package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import garage.model.util.SimpleIterator;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;
import garage.model.vehicle.VehicleList;

/**
 * JUnit test for functionality of VehicleList
 * 
 * @author Summer Mims
 */
public class VehicleListTest
{
	/** instance variables to use throughout test cases */
	private Vehicle v0;
	private Vehicle v1;
	private Vehicle v2;
	private Vehicle v3;
	private Vehicle v4;
	private Vehicle v5;
	private Vehicle v6;
	private Vehicle v7;
	private VehicleList vl;

	/**
	 * create instance variables to use throughout tests
	 *
	 * @throws java.lang.Exception
	 *             if an exception occurs during setup
	 */
	@Before
	public void setUp() throws Exception
	{
		v0 = new RegularCar("AWD101", "Jones, Mary", 0); // 6
		v1 = new RegularCar("AWD-101", "Jones, Mary", 1); // 4
		v2 = new RegularCar("AWD1101", "Jones, Mary", 2); // 2
		v3 = new RegularCar("AAWD1101", "Jones, Mary", 3); // 0
		v4 = new HybridElectricCar("AWD101", "Hanes, Mary", 0); // 7
		v5 = new HybridElectricCar("AWD-101", "Hanes, Mary", 1); // 5
		v6 = new HybridElectricCar("AWD1101", "Hanes, Mary", 2); // 3
		v7 = new HybridElectricCar("AAWD1101", "Hanes, Mary", 3); // 1
		vl = new VehicleList();
	}

	/**
	 * 
	 * test for constructor of VehicleList
	 *
	 */
	@Test
	public void addTest()
	{
		// add null
		try
		{
			vl.add(null);
			fail();
		}
		catch (NullPointerException e)
		{
			assertNull(vl.get("", 0));
		}

		vl.add(v0);
		vl.add(v1);
		vl.add(v2);
		vl.add(v3);
		vl.add(v4);
		vl.add(v5);
		vl.add(v6);
		vl.add(v7);

		assertEquals(v3, vl.get("", 0));
		assertEquals(v7, vl.get("", 1));
		assertEquals(v2, vl.get("", 2));
		assertEquals(v6, vl.get("", 3));
		assertEquals(v1, vl.get("", 4));
		assertEquals(v5, vl.get("", 5));
		assertEquals(v0, vl.get("", 6));
		assertEquals(v4, vl.get("", 7));

		// does not meet filter
		assertNull(vl.get("F", 0));

		// index invalid
		try
		{
			vl.get("", -1);
			fail();
		}
		catch (IndexOutOfBoundsException e)
		{
			assertEquals(v3, vl.get("", 0));
		}
		// index invalid
		try
		{
			vl.get("", 8);
			fail();
		}
		catch (IndexOutOfBoundsException e)
		{
			assertEquals(v3, vl.get("", 0));
		}
	}

	/**
	 * test remove method
	 */
	@Test
	public void removeTest()
	{
		// remove from empty
		try
		{
			vl.remove("", 0);
			fail();
		}
		catch (IndexOutOfBoundsException e)
		{
			assertNull(vl.get("", 0));
		}

		vl.add(v0);
		vl.add(v1);
		vl.add(v2);
		vl.add(v3);
		vl.add(v4);
		vl.add(v5);
		vl.add(v6);
		vl.add(v7);

		// remove head
		assertEquals(v3, vl.remove("", 0));
		assertEquals(v7, vl.get("", 0));

		// remove not meet filter
		assertEquals(null, vl.remove("F", 0));
		// remove end
		assertEquals(v4, vl.remove("", 6));

		// remove middle
		assertEquals(v1, vl.remove("", 3));

		// remove all
		assertEquals(v7, vl.remove("", 0));
		assertEquals(v2, vl.remove("", 0));
		assertEquals(v6, vl.remove("", 0));
		assertEquals(v5, vl.remove("", 0));
		assertEquals(v0, vl.remove("", 0));
	}

	/**
	 * test SimpleIterator method
	 */
	@Test
	public void simpleIteratorTest()
	{
		vl.add(v0);
		vl.add(v1);
		SimpleIterator<Vehicle> s = vl.iterator();
		assertTrue(s.hasNext());
		assertEquals(v1, s.next());
		s.next();
		assertFalse(s.hasNext());
		try
		{
			s.next();
			fail();
		}
		catch (NoSuchElementException e)
		{
			assertFalse(s.hasNext());
		}
	}

	/**
	 * test filteredList method
	 */
	@Test
	public void filteredListTest()
	{
		vl.add(v0);
		vl.add(v1);
		vl.add(v2);
		vl.add(v3);
		vl.add(v4);
		vl.add(v5);
		vl.add(v6);
		vl.add(v7);
		String reg = "R Platinum AAWD1101 Jones, Mary\nR Gold     AWD1101  Jones, Mary\nR Silver   AWD-101  Jones, Mary\nR None     AWD101   Jones, Mary\n";
		String hybrid = "E Platinum AAWD1101 Hanes, Mary\nE Gold     AWD1101  Hanes, Mary\nE Silver   AWD-101  Hanes, Mary\nE None     AWD101   Hanes, Mary\n";
		assertEquals("", vl.filteredList("F"));
		assertEquals(reg, vl.filteredList("J"));
		assertEquals(hybrid, vl.filteredList("H"));

	}
}
