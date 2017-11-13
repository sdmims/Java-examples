package garage.model.service_garage;

/**
 * Class that extends Exception and is thrown if vehicle attempts to choose a
 * service bay that is empty but of the wrong type for that vehicle.
 * 
 * @author Summer Mims
 */
public class BayCarMismatchException extends Exception
{
	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs a new BayCarMismatchException object. Thrown when vehicle
	 * attempts to choose a service bay that is empty but of the wrong type for
	 * that vehicle. Call directly to super constructor.
	 */
	public BayCarMismatchException()
	{
		super("Wrong service bay type.");
	}

	/**
	 * 
	 * Constructs a new BayCarMismatchException object. Thrown when vehicle
	 * attempts to choose a service bay that is empty but of the wrong type for
	 * that vehicle. Call directly to super constructor.
	 * 
	 * @param m
	 *            the message for the exception
	 */
	public BayCarMismatchException(String m)
	{
		super(m);
	}
}
