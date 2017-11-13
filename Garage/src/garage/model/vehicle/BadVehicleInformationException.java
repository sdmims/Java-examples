package garage.model.vehicle;

/**
 * Class that extends Exception. Thrown when user attempts to add or edit
 * vehicle with invalid information including the tier, type, name, or license.
 * 
 * @author Summer Mims
 */
public class BadVehicleInformationException extends Exception
{
	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs a new BadVehicleInformationException object. Thrown when user
	 * attempts to add or edit vehicle with invalid information including the
	 * tier, type, name, or license. Call directly to super constructor.
	 */
	public BadVehicleInformationException()
	{
		super("Bad vehicle information.");
	}

	/**
	 * 
	 * Constructs a new BadVehicleInformationException object. Thrown when user
	 * attempts to add or edit vehicle with invalid information including the
	 * tier, type, name, or license. Call directly to super constructor.
	 * 
	 * @param m
	 *            the message for the exception
	 */
	public BadVehicleInformationException(String m)
	{
		super(m);
	}
}
