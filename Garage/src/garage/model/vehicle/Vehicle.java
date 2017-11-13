package garage.model.vehicle;

import garage.model.service_garage.BayCarMismatchException;
import garage.model.service_garage.BayOccupiedException;
import garage.model.service_garage.Garage;
import garage.model.service_garage.NoAvailableBayException;

/**
 * Abstract class to represent vehicle appropriate for servicing in the garage.
 * 
 * @author Summer Mims
 */
public abstract class Vehicle implements Tiered
{
	/** the license of the vehicle */
	private String license;
	/** the customer name associated with the vehicle */
	private String name;
	/** the service level tier for the vehicle */
	private int tier;
	/** final array to correlate tiers with string version */
	public static final String[] TIER_ARRAY = { "None    ", "Silver  ",
			"Gold    ", "Platinum" };

	/**
	 * Create a variable to track the current character index
	 */
	private int charIndex;
	/**
	 * Variable to keep track of the current input character being examined
	 */
	private char c;

	/**
	 * Constructs a new Vehicle object.
	 * 
	 * @param license
	 *            the license of the vehicle
	 * @param name
	 *            the customer name with the vehicle
	 * @param tier
	 *            the service level tier
	 * @throws BadVehicleInformationException
	 *             if license or owner is not valid
	 */
	public Vehicle(String license, String name, int tier)
			throws BadVehicleInformationException
	{
		setLicense(license);
		setName(name);
		setTier(tier);
	}

	/**
	 * getter for license of vehicle
	 * 
	 * @return license the license of the vehicle
	 */
	public String getLicense()
	{
		return license;
	}

	/**
	 * set the license of the vehicle
	 * 
	 * strip trailing and leading whitespace; cannot contain whitespace in
	 * middle
	 * 
	 * cannot contain more than 8 valid characters, alphanumeric or dashes
	 * 
	 * @param license
	 *            the license to set
	 * @throws BadVehicleInformationException
	 *             if license is not formatted correct or is null
	 */
	private void setLicense(String license)
			throws BadVehicleInformationException
	{
		// cannot be null
		if (license == null)
		{
			throw new BadVehicleInformationException(
					"License cannot be blank.");
		}
		// trim leading and trailing whitespace
		license = license.trim();
		// if empty, throw new exception
		if (license.isEmpty())
		{
			throw new BadVehicleInformationException(
					"License cannot be blank.");
		}
		// if longer than 8 characters, throw new exception
		if (license.length() > 8)
		{
			throw new BadVehicleInformationException(
					"License cannot be more than 8 characters.");
		}

		// reset index to 0
		charIndex = 0;

		// Iterate through the license, examining one character at a time
		// if not an alphanumeric or dash, throw exception
		while (charIndex < license.length())
		{
			// Set the current character being examined
			c = license.charAt(charIndex);
			// if not a letter or digit or dash, throw exception
			if (!Character.isLetter(c) && !Character.isDigit(c) && c != '-')
			{
				throw new BadVehicleInformationException("Invalid license.");
			}
			charIndex++;
		}
		this.license = license;
	}

	/**
	 * getter for the customer name
	 * 
	 * @return the name the customer name associated with vehicle
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * setter for customer name
	 * 
	 * @param customerName
	 *            the customerName to set
	 * @throws BadVehicleInformationException
	 *             if name is null or empty
	 */
	private void setName(String name) throws BadVehicleInformationException
	{
		if (name == null || name.trim().isEmpty())
		{
			throw new BadVehicleInformationException("Invalid name.");
		}
		this.name = name.trim();
	}

	/**
	 * getter for service tier level
	 * 
	 * @return tier the service level tier
	 */
	public int getTier()
	{
		return tier;
	}

	/**
	 * set the service level tier must be a 0, 1, 2, or 3
	 * 
	 * @param tier
	 *            the tier to set
	 * @throws BadVehicleInformationException
	 *             if tier is invalid
	 */
	public void setTier(int tier) throws BadVehicleInformationException
	{
		if (tier < 0 || tier > 3)
		{
			throw new BadVehicleInformationException("Invalid tier.");
		}
		this.tier = tier;
	}

	/**
	 * Compare the tier status of Vehicle to another tiered object.
	 * 
	 * @param t
	 *            the Tiered object to compare this to
	 * @return int Returns 0 if they match, negative is the tier status of this
	 *         object is less than the other, and positive if the tier status of
	 *         this is greater than the other.
	 */
	public int compareToTier(Tiered t)
	{
		if (this.getTier() == t.getTier())
		{
			return 0;
		}
		else if (this.getTier() < t.getTier())
		{
			return -1;
		}
		else // if (this.getTier() > t.getTier())
		{
			return 1;
		}

	}

	/**
	 * 
	 * Case sensitive method to check if owner's last name contains the prefix.
	 * A filter of null or null string returns true.
	 *
	 * @param filter
	 *            the prefix to check names by
	 * @return boolean returns true is filter is a prefix of owner's last name
	 *         or filter is null
	 */
	public boolean meetsFilter(String filter)
	{
		if (filter == null || filter.trim().equals(""))
		{
			return true;
		}
		if (name.toUpperCase().startsWith(filter.trim().toUpperCase()))
		{
			return true;
		}
		return false;
	}

	/**
	 * Formatted string representation of Vehicle. Returns a string in the form
	 * Tier license name.
	 * 
	 * @return String the formatted string
	 */
	public String toString()
	{
		// longest tier word is Platinum - 8 characters
		// padding built into array

		// license max 8 characters
		StringBuilder licensePadding = new StringBuilder("");
		if (license.length() < 8)
		{
			for (int j = 0; j < 8 - license.length(); j++)
			{
				licensePadding.append(" ");
			}
		}

		return TIER_ARRAY[tier] + " " + license.trim() + licensePadding + " "
				+ name;
	}

	/**
	 * Abstract method for vehicle to choose service bay
	 * 
	 * @param g
	 *            the garage to choose a bay from
	 * @throws NoAvailableBayException
	 *             if now bay is available
	 * @throws BayCarMismatchException
	 *             if wrong bay type
	 * @throws BayOccupiedException
	 *             is bay is occupied
	 * 
	 */
	public abstract void pickServiceBay(Garage g)
			throws NoAvailableBayException, BayOccupiedException,
			BayCarMismatchException;
}
