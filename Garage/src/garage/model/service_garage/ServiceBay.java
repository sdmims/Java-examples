package garage.model.service_garage;

import garage.model.vehicle.Vehicle;

/**
 * Concrete class representing service bay in the garage. Service bays can be
 * regular or hybrid/electric. Regular can service any vehicle type while hybrid
 * can service only hybrid or electric vehicles. A service bay has an id, empty
 * of the vehicle.
 * 
 * Uses custom array based list of service bays.
 * 
 * @author Summer Mims
 */
public class ServiceBay
{
	/** name of Service bay */
	private String bayID;
	/** prefix for bay naming */
	private String prefix;
	/** String for license and customer name OR EMPTY */
	private Vehicle carInBay;
	/** true if bay is occupied */
	private boolean occupied;
	/** static int to track numbering of bays */
	private static int bayNum;

	/**
	 * 
	 * Constructs a new ServiceBay object. Creates new empty service bay
	 * according to current bay numbering then increments that number. The
	 * prefix is determined by the user.
	 * 
	 * @param prefix
	 *            the first non whitespace character for the service bay name
	 *            prefix
	 */
	public ServiceBay(String prefix)
	{
		if (prefix == null || prefix.trim().isEmpty())
		{
			this.prefix = "1";
		}
		else
		{
			// can only be one character
			this.prefix = prefix.trim().substring(0, 1);
		}
		carInBay = null;
		occupied = false;
		nameBay();
		// after creation increment bayNum
		bayNum++;
	}

	/**
	 * 
	 * Constructs a new ServiceBay object. Creates new empty service bay
	 * according to current bay numbering then increments that number. The
	 * prefix is "1".
	 */
	public ServiceBay()
	{
		prefix = "1";
		carInBay = null;
		occupied = false;
		nameBay();
		bayNum++;
	}

	/**
	 * private helper to name bays. If bay number is less than 10, need to pad
	 * with 0 to maintain 2 digits.
	 */
	private void nameBay()
	{
		if (bayNum < 10)
		{
			this.bayID = prefix + "0" + bayNum;
		}
		else
		{
			this.bayID = prefix + bayNum;
		}
	}

	/**
	 * 
	 * Static method that resets service bay numbering to start from 1.
	 * Convenience for testing but can also be used in Garage constructor.
	 *
	 */
	public static void startBayNumberingAt101()
	{
		bayNum = 1;
	}

	/**
	 * 
	 * get the bay id
	 *
	 * @return bayId the id of the bay
	 */
	public String getBayID()
	{
		return bayID;
	}

	/**
	 * 
	 * Method to check is service bay is occupies
	 *
	 * @return boolean returns true if the service bay is occupied; false if
	 *         empty
	 */
	public boolean isOccupied()
	{
		return occupied;
	}

	/**
	 * 
	 * Removes vehicle currently in service bay and returns it
	 *
	 * @return the vehicle that is released from the service bay
	 */
	public Vehicle release()
	{
		Vehicle gone = carInBay;
		carInBay = null;
		occupied = false;
		return gone;
	}

	/**
	 * 
	 * Occupies service bay with the given vehicle
	 *
	 * @param v
	 *            the vehicle to occupy the service bay with
	 * @throws BayOccupiedException
	 *             is the service bay is already occupied
	 * @throws BayCarMismatchException
	 *             if vehicle is not compatible with bay type; only thrown in
	 *             HybridElectricBay children
	 */
	public void occupy(Vehicle v)
			throws BayOccupiedException, BayCarMismatchException
	{
		if (occupied)
		{
			throw new BayOccupiedException();
		}
		carInBay = v;
		occupied = true;
	}

	/**
	 * Formats the string representation of the service bay. Returns a string in
	 * the form bay id: license name or EMPTY
	 * 
	 * @return String the formatted String for serviceBay
	 */
	public String toString()
	{
		if (occupied)
		{
			StringBuilder licensePadding = new StringBuilder("");
			if (carInBay.getLicense().length() < 8)
			{
				for (int j = 0; j < 8 - carInBay.getLicense().length(); j++)
				{
					licensePadding.append(" ");
				}
			}
			return bayID + ": " + carInBay.getLicense().trim() + licensePadding
					+ " " + carInBay.getName();
		}
		else
		{
			return bayID + ": EMPTY";
		}
	}
}
