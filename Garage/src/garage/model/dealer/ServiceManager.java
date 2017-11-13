
package garage.model.dealer;

import java.util.Scanner;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.NoAvailableBayException;
import garage.model.util.SimpleIterator;
import garage.model.vehicle.BadVehicleInformationException;
import garage.model.vehicle.HybridElectricCar;
import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Tiered;
import garage.model.vehicle.Vehicle;
import garage.model.vehicle.VehicleList;

/**
 * Concrete class that implements Manageable.
 * 
 * 
 * @author Summer Mims
 */
public class ServiceManager implements Manageable
{

	private VehicleList waitList;
	private Garage garage;

	/**
	 * 
	 * Constructs a new ServiceManager object. Create empty waitList and new
	 * garage that initializes with 8 bays.
	 */
	public ServiceManager()
	{
		waitList = new VehicleList();
		garage = new Garage();
	}

	/**
	 * 
	 * Constructs a new ServiceManager object.
	 * 
	 * @param in
	 *            the Scanner to read file or input from
	 */
	public ServiceManager(Scanner in)
	{
		garage = new Garage();
		waitList = new VehicleList(in);
	}

	/**
	 * add a new customer vehicle to wait list. Create the vehcile depending on
	 * type and then add to wait list.
	 * 
	 * @param type
	 *            vehicle type, hybrid/electric or regular; E or R
	 * @param license
	 *            vehicle license
	 * @param customerName
	 *            customer's name
	 * @param tier
	 *            the service tier for a customer
	 */
	@Override
	public void putOnWaitingList(String type, String license,
			String customerName, int tier)
	{
		Vehicle v = null;

		try
		{
			if (type.equalsIgnoreCase("R"))
			{
				v = new RegularCar(license, customerName, tier);
			}
			if (type.equalsIgnoreCase("E"))
			{
				v = new HybridElectricCar(license, customerName, tier);
			}

			waitList.add(v);
		}
		catch (BadVehicleInformationException e)
		{
			// nothing added
		}

	}

	/**
	 * add a vehicle to the waiting list
	 * 
	 * @param v
	 *            vehicle to add to waiting list; must implement Tiered
	 */
	@Override
	public void putOnWaitingList(Tiered v)
	{
		waitList.add((Vehicle) v);
	}

	/**
	 * get the waiting Vehicle at given index with specific filter
	 * 
	 * @param filter
	 *            the filter to use
	 * @param position
	 *            the index to retrieve
	 * @return Tiered the vehicle at the given position with constraints
	 */
	@Override
	public Tiered getWaitingItem(String filter, int position)
	{
		// use VehicleList functionality
		return waitList.get(filter, position);
	}

	/**
	 * remove the waiting Vehicle at given index with specific filter
	 * 
	 * @param filter
	 *            the filter to use
	 * @param position
	 *            the index to retrieve
	 * @return Tiered the vehicle at the given position with constraints
	 */
	@Override
	public Tiered remove(String filter, int position)
	{
		// use VehicleList functionality to remove a vehicle
		return waitList.remove(filter, position);
	}

	/**
	 * go through waiting list of vehicles and fill the Garage with those
	 * vehicles
	 */
	@Override
	public void fillServiceBays()
	{
		// must have empty bays
		// must have vehicles
		SimpleIterator<Vehicle> waitIterator = waitList.iterator();
		int index = 0;
		while (garage.numberOfEmptyBays() > 0 && waitIterator.hasNext())
		{
			Vehicle v = waitIterator.next();
			try
			{
				// put in bay
				v.pickServiceBay(garage);
				// remove from wait list
				waitList.remove("", index);
			}
			catch (NoAvailableBayException e)
			{
				// regular car with only hybrid bays available
				// will stay at front of list until
				index++;
			}
			catch (BayOccupiedException e)
			{
				// TODO Auto-generated catch block
				
			}
			catch (BayCarMismatchException e)
			{
				// TODO Auto-generated catch block
				
			}
			
		}

	}

	/**
	 * Release a vehicle from a service bay
	 * 
	 * @param index
	 *            the ServiceBay to release the vehicle from
	 * @return Tiered the vehicle released from the bay
	 */
	@Override
	public Tiered releaseFromService(int index)
	{
		return garage.getBayAt(index).release();
	}

	/**
	 * add a new service bay to the garage
	 */
	@Override
	public void addNewBay()
	{
		garage.addRepairBay();
	}

	/**
	 * Get formatted string of Vehicle waiting list
	 * 
	 * @return String the formatted String
	 */
	@Override
	public String printWaitList(String filter)
	{
		String s = "";
		SimpleIterator<Vehicle> waitIterator = waitList.iterator();
		while (waitIterator.hasNext())
		{
			Vehicle v = waitIterator.next();
			if (v.meetsFilter(filter))
			{
				s += v.toString() + "\n";
			}
		}
		return s;
	}

	/**
	 * Get the formatted String of ServiceBays
	 * 
	 * @return the formatted String
	 */
	@Override
	public String printServiceBays()
	{
		String s = "";
		for (int i = 0; i < garage.getSize(); i++)
		{
			s += garage.getBayAt(i) + "\n";
		}
		return s;
	}

}
