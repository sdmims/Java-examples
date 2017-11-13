package garage.model.service_garage;

/**
 * Class that extends Exception and is thrown when a vehicle attempts to choose
 * a service bay that is occupied.
 * 
 * @author Summer Mims
 */
public class BayOccupiedException extends Exception
{
	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs a new BayOccupiedException object. Thrown when vehicle chooses
	 * an occupied bay. Call directly to super constructor.
	 */
	public BayOccupiedException()
	{
		super("Bay is occupied.");
	}

	/**
	 * 
	 * Constructs a new BayOccupiedException object. Thrown when vehicle chooses
	 * an occupied bay. Call directly to super constructor.
	 * 
	 * @param m
	 *            the message for the exception
	 */
	public BayOccupiedException(String m)
	{
		super(m);
	}
}
