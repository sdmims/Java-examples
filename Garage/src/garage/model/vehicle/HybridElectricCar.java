package garage.model.vehicle;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.NoAvailableBayException;

/**
 * Concrete class that extends Vehicle and represents hybrid or electric car.
 * 
 * @author Summer Mims
 */
public class HybridElectricCar extends Vehicle
{
	/**
	 * 
	 * Constructs a new HybridElectricCar object.
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
	public HybridElectricCar(String license, String customerName, int tier)
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
	@Override
	public void pickServiceBay(Garage g) throws NoAvailableBayException// ,
	// BayOccupiedException, BayCarMismatchException
	{
		if (g.numberOfEmptyBays() == 0)
		{
			throw new NoAvailableBayException();
		}
		// work from back end of list to front for hybrids
		for (int i = g.getSize() - 1; i >= 0; i--)
		{
			// vehicle can go here
			try
			{
				g.getBayAt(i).occupy(this);
				return;
			}
			catch (BayOccupiedException | BayCarMismatchException e)
			{
				// throw new NoAvailableBayException();
			}
			//catch (BayCarMismatchException e)
			//{
				// throw new NoAvailableBayException();
			//}

			// }
		}
	}

	/**
	 * Formatted string representation of HybridElectricCar. Formatted string
	 * representation of RegularCar. Returns a string in the form E Tier license
	 * owner name.
	 * 
	 * @return String the formatted string
	 */
	public String toString()
	{
		return "E " + super.toString();
	}
}
