package garage.model.vehicle;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.NoAvailableBayException;

/**
 * Concrete class that extends Vehicle and represents ordinary gasoline or
 * diesel vehicle.
 * 
 * @author Summer Mims
 */
public class RegularCar extends Vehicle
{

	/**
	 * 
	 * Constructs a new RegularCare object.
	 *
	 * @param license
	 *            the license of the vehicle
	 * @param customerName
	 *            the customer name with the vehicle
	 * @param tier
	 *            the service level tier
	 * @throws BadVehicleInformationException
	 *             if Vehicle information is invalid
	 */
	public RegularCar(String license, String customerName, int tier)
			throws BadVehicleInformationException
	{
		super(license, customerName, tier);
	}

	/**
	 * 
	 * Method for vehicle to choose service bay. Goes through list of service
	 * bays starting at front to search for an empty one.
	 * 
	 * @param g
	 *            the garage to choose a bay from
	 * @throws NoAvailableBayException
	 *             if now bay is available
	 * @throws BayCarMismatchException
	 *             if bay is not correct type
	 * @throws BayOccupiedException
	 *             if bay is occupied
	 */

	public void pickServiceBay(Garage g) throws NoAvailableBayException// ,
	// BayOccupiedException, BayCarMismatchException
	{
		if (g.getSize() < 1 || g.numberOfEmptyBays() == 0)
		{
			throw new NoAvailableBayException();
		}
		for (int i = 0; i < g.getSize(); i++)
		{
			// regular vehicle can go here
			try
			{
				g.getBayAt(i).occupy(this);
				return;
			}

			catch (BayOccupiedException e)
			{
				// throw new NoAvailableBayException();
			}
			catch (BayCarMismatchException e)
			{
				// throw new NoAvailableBayException();
			}
		}
		throw new NoAvailableBayException();
	}

	/**
	 * Formatted string representation of RegularCar. Returns a string in the
	 * form R Tier license owner name.
	 * 
	 * @return String the formatted String
	 */
	public String toString()
	{
		return "R " + super.toString();
	}
}
