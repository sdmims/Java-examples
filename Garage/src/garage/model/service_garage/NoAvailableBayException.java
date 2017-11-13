package garage.model.service_garage;

/**
 * Exception to handle when a vehicle attempts to choose a service bay but all
 * appropriate service bays are full.
 * 
 * @author Summer Mims
 */
public class NoAvailableBayException extends Exception
{
	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs a new NoAvailableBayException object. Thrown when no
	 * appropriate service bay is available. Call directly to super constructor.
	 */
	public NoAvailableBayException()
	{
		super("No available service bay.");
	}

	/**
	 * 
	 * Constructs a new NoAvailableBayException object. Thrown when no
	 * appropriate service bay is available. Call directly to super constructor.
	 * 
	 * @param m
	 *            the message for the exception
	 */
	public NoAvailableBayException(String m)
	{
		super(m);
	}

}
