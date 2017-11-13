package garage.model.service_garage;

import garage.model.vehicle.RegularCar;
import garage.model.vehicle.Vehicle;

/**
 * Concrete class that extends ServiceBay and can only accomodate hybrid or
 * electric vehicles.
 * 
 * @author Summer Mims
 */
public class HybridElectricBay extends ServiceBay
{
	/**
	 * 
	 * Constructs a new HybridElectricBay object. Creates a new empty bay for
	 * servicing hybrid and electric vehicles according to the current bay
	 * numbering and then increments that number. The prefix is "E".
	 */
	public HybridElectricBay()
	{
		super("E");
	}

	/**
	 * Occupies the service bay with the given vehicle
	 * 
	 * @param v
	 *            the vehicle to occupy the service bay
	 * @throws BayOccupiedException
	 *             if service bay is occupied
	 * @throws BayCarMismatchException
	 *             if vehicle is not a hybrid or electric
	 */
	@Override
	public void occupy(Vehicle v) throws  BayOccupiedException,
	BayCarMismatchException
	{
		if (v instanceof RegularCar)
		{
			throw new BayCarMismatchException();
		}

		super.occupy(v);
	}
}
