package edu.ncsu.csc216.garage.model.dealer;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import garage.model.dealer.ServiceManager;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;

/**
 * Junit tests for functionality of ServiceManager
 * 
 * @author Summer Mims
 */
public class ServiceManagerTest
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
	private ServiceManager sm;

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
		sm = new ServiceManager();

	}

	/**
	 * test for constructor of ServiceManager. Test putOnWaitingList and
	 * getWaitingItem methods
	 *
	 */
	@Test
	public void putOnWaitingListTest()
	{
		assertNull(sm.getWaitingItem("", 0));
		sm.putOnWaitingList(v0);
		assertEquals(v0, sm.getWaitingItem("", 0));
		sm.putOnWaitingList("E", "AAWD1101", "Hanes, Mary", 3);
		assertEquals("E Platinum AAWD1101 Hanes, Mary",
				sm.getWaitingItem("", 0).toString());
		sm.putOnWaitingList("R", "AAWD1101", "Hanes, Mary", 0);
		assertEquals("R None     AAWD1101 Hanes, Mary",
				sm.getWaitingItem("", 2).toString());

		sm.putOnWaitingList("R", "AWD@", "New, Person", 0);
		assertEquals("R None     AAWD1101 Hanes, Mary",
				sm.getWaitingItem("", 2).toString());

	}

	/**
	 * test for constructor of ServicemManager with Scanner
	 */
	@Test
	public void constructorScannerTest()
	{
		try
		{
			sm = new ServiceManager(
					new Scanner(new FileInputStream("test-files/cars.txt")));
			// new waiting list
			String s = "";
			s += "R Platinum HI-01345 Rhyne, Lauren" + "\n";
			s += "R Platinum NC-DUR1  Nicholson, Henry" + "\n";
			s += "R Platinum SC-RT098 Richards, Fiona" + "\n";
			s += "E Gold     NC-5678  Emerson, Jane" + "\n";
			s += "R Gold     VIRG0122 Jones, Francis" + "\n";
			s += "E Gold     0987-NC  Nelson, Richard" + "\n";
			s += "R Gold     IL20987  Masters, Burt" + "\n";
			s += "E Gold     FL-M3456 McKeel, Kenneth" + "\n";
			s += "E Gold     ND-12345 Young, Blake" + "\n";
			s += "R Gold     AL-03456 Barber, David" + "\n";
			s += "R Gold     IO-WA987 Ledbetter, Jeanne" + "\n";
			s += "E Gold     MA-0987  Wilson, Richard" + "\n";
			s += "R Silver   VA-121A  Henderson, William" + "\n";
			s += "R Silver   MN-20134 McKeel, Robyn" + "\n";
			s += "R Silver   SC-0I033 Smith, Amos" + "\n";
			s += "R Silver   678431   Bell, Frank" + "\n";
			s += "E Silver   WX-01292 Wall, Susan" + "\n";
			s += "R Silver   NC-RAL0  Lamb, Bill" + "\n";
			s += "E Silver   ND-34511 Young, Charlotte" + "\n";
			s += "R Silver   98234-RI Bell, Richard" + "\n";
			s += "R None     NC-122   Doe, John" + "\n";
			s += "E None     FL-09876 Peterson, Keith" + "\n";
			s += "R None     SD-0     Young, Charles" + "\n";
			s += "R None     TN-3245  Lyons, Linda" + "\n";
			s += "E None     ME-78653 Smith, Sandra" + "\n";
			s += "E None     CN09822  Hughes, Jack" + "\n";
			s += "E None     NC-1233  Doe, Baby John" + "\n";
			assertEquals(s, sm.printWaitList(""));
			// same service bays
			String sb = "";
			sb += "108: EMPTY" + "\n";
			sb += "106: EMPTY" + "\n";
			sb += "105: EMPTY" + "\n";
			sb += "103: EMPTY" + "\n";
			sb += "102: EMPTY" + "\n";
			sb += "E01: EMPTY" + "\n";
			sb += "E04: EMPTY" + "\n";
			sb += "E07: EMPTY" + "\n";
			assertEquals(sb, sm.printServiceBays());
		}
		catch (FileNotFoundException e)
		{
			fail();
		}
	}

	/**
	 * test method addNewBay
	 */
	@Test
	public void addNewBayTest()
	{
		sm.addNewBay();
		sm.addNewBay();
		String s = "";
		s += "109: EMPTY" + "\n";
		s += "108: EMPTY" + "\n";
		s += "106: EMPTY" + "\n";
		s += "105: EMPTY" + "\n";
		s += "103: EMPTY" + "\n";
		s += "102: EMPTY" + "\n";
		s += "E01: EMPTY" + "\n";
		s += "E04: EMPTY" + "\n";
		s += "E07: EMPTY" + "\n";
		s += "E10: EMPTY" + "\n";

		assertEquals(s, sm.printServiceBays());
	}

	/**
	 * test fillServiceBays and releaseFromService methods
	 */
	@Test
	public void fillServiceBaysTest2()
	{
		// 5 regular, 5 hybrid - able to fill all bays
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v0);

		sm.fillServiceBays();
		assertEquals(v0, sm.getWaitingItem("", 0));
	}

	/**
	 * test fillServiceBays and releaseFromService methods
	 */
	@Test
	public void fillServiceBaysTest()
	{
		// 4 regular, 4 hybrid - able to fill all bays
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v7);
		sm.fillServiceBays();
		assertNull(sm.getWaitingItem("", 0));
		String s1 = "";
		s1 += "108: AAWD1101 Jones, Mary" + "\n";
		s1 += "106: AWD1101  Jones, Mary" + "\n";
		s1 += "105: AWD-101  Jones, Mary" + "\n";
		s1 += "103: AWD101   Jones, Mary" + "\n";
		s1 += "102: AWD101   Hanes, Mary" + "\n";
		s1 += "E01: AWD-101  Hanes, Mary" + "\n";
		s1 += "E04: AWD1101  Hanes, Mary" + "\n";
		s1 += "E07: AAWD1101 Hanes, Mary" + "\n";
		assertEquals(s1, sm.printServiceBays());
		assertEquals("", sm.printWaitList(""));

		// try with all hybrid
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v7);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v7);
		String s2 = "";
		s2 += "108: AWD101   Hanes, Mary" + "\n";
		s2 += "106: AWD101   Hanes, Mary" + "\n";
		s2 += "105: AWD-101  Hanes, Mary" + "\n";
		s2 += "103: AWD-101  Hanes, Mary" + "\n";
		s2 += "102: AWD1101  Hanes, Mary" + "\n";
		s2 += "E01: AWD1101  Hanes, Mary" + "\n";
		s2 += "E04: AAWD1101 Hanes, Mary" + "\n";
		s2 += "E07: AAWD1101 Hanes, Mary" + "\n";
		sm.fillServiceBays();
		// nothing changed
		assertEquals(v7, sm.getWaitingItem("", 0));
		assertEquals(s1, sm.printServiceBays());

		// remove all vehicles
		assertEquals(v3, sm.releaseFromService(0));
		assertEquals(v7, sm.releaseFromService(7));
		assertEquals(v2, sm.releaseFromService(1));
		assertEquals(v1, sm.releaseFromService(2));
		assertEquals(v0, sm.releaseFromService(3));
		assertEquals(v6, sm.releaseFromService(6));
		assertEquals(v5, sm.releaseFromService(5));
		assertEquals(v4, sm.releaseFromService(4));
		String s = "";
		s += "108: EMPTY" + "\n";
		s += "106: EMPTY" + "\n";
		s += "105: EMPTY" + "\n";
		s += "103: EMPTY" + "\n";
		s += "102: EMPTY" + "\n";
		s += "E01: EMPTY" + "\n";
		s += "E04: EMPTY" + "\n";
		s += "E07: EMPTY" + "\n";
		assertEquals(s, sm.printServiceBays());
		sm.fillServiceBays();
		assertEquals(s2, sm.printServiceBays());
		// remove all
		assertEquals(v7, sm.releaseFromService(7));
		assertEquals(v7, sm.releaseFromService(6));
		assertEquals(v6, sm.releaseFromService(5));
		assertEquals(v6, sm.releaseFromService(4));
		assertEquals(v5, sm.releaseFromService(3));
		assertEquals(v5, sm.releaseFromService(2));
		assertEquals(v4, sm.releaseFromService(1));
		assertEquals(v4, sm.releaseFromService(0));

		// try with all regular cars
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.fillServiceBays();
		String s3 = "";
		s3 += "108: AAWD1101 Jones, Mary" + "\n";
		s3 += "106: AAWD1101 Jones, Mary" + "\n";
		s3 += "105: AWD1101  Jones, Mary" + "\n";
		s3 += "103: AWD1101  Jones, Mary" + "\n";
		s3 += "102: AWD-101  Jones, Mary" + "\n";
		s3 += "E01: EMPTY" + "\n";
		s3 += "E04: EMPTY" + "\n";
		s3 += "E07: EMPTY" + "\n";
		assertEquals(s3, sm.printServiceBays());
	}

	/**
	 * printServiceBays test
	 */
	@Test
	public void printServiceBaysTest()
	{
		String s = "";
		s += "108: EMPTY" + "\n";
		s += "106: EMPTY" + "\n";
		s += "105: EMPTY" + "\n";
		s += "103: EMPTY" + "\n";
		s += "102: EMPTY" + "\n";
		s += "E01: EMPTY" + "\n";
		s += "E04: EMPTY" + "\n";
		s += "E07: EMPTY" + "\n";

		assertEquals(s, sm.printServiceBays());
	}

	/**
	 * test printWaitList method
	 */
	@Test
	public void printWaitingListTest()
	{
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v7);
		String reg = "R Platinum AAWD1101 Jones, Mary\nR Gold     AWD1101  Jones, Mary\nR Silver   AWD-101  Jones, Mary\nR None     AWD101   Jones, Mary\n";
		String hybrid = "E Platinum AAWD1101 Hanes, Mary\nE Gold     AWD1101  Hanes, Mary\nE Silver   AWD-101  Hanes, Mary\nE None     AWD101   Hanes, Mary\n";
		assertEquals("", sm.printWaitList("F"));
		assertEquals(reg, sm.printWaitList("J"));
		assertEquals(hybrid, sm.printWaitList("H"));

	}

	/**
	 * test remove method
	 */
	@Test
	public void removeTest()
	{
		sm.putOnWaitingList(v0);
		sm.putOnWaitingList(v1);
		sm.putOnWaitingList(v2);
		sm.putOnWaitingList(v3);
		sm.putOnWaitingList(v4);
		sm.putOnWaitingList(v5);
		sm.putOnWaitingList(v6);
		sm.putOnWaitingList(v7);

		// remove head
		assertEquals(v3, sm.remove("", 0));

		// remove not meet filter
		assertEquals(null, sm.remove("F", 0));
		// remove end
		assertEquals(v4, sm.remove("", 6));

		// remove middle
		assertEquals(v1, sm.remove("", 3));

		// remove all
		assertEquals(v7, sm.remove("", 0));
		assertEquals(v2, sm.remove("", 0));
		assertEquals(v6, sm.remove("", 0));
		assertEquals(v5, sm.remove("", 0));
		assertEquals(v0, sm.remove("", 0));

	}
}
