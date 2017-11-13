package student_scheduler.course;

/**
 * Class to create ConflictException ConflictException is thrown when
 * checkConflict method of Activity finds conflict with timing of an Activity to
 * be added to the schedule
 * 
 * Inherits from parent class Exception
 * 
 * @author Summer Mims
 */

public class ConflictException extends Exception
{
	/**
	 * 
	 * Constructs a new ConflictException object.
	 */
	public ConflictException()
	{
		super("Schedule conflict.");
	}

	/**
	 * 
	 * Constructs a new ConflictException object. thrown when there is a time
	 * conflict
	 * 
	 * @param message
	 *            the message to display
	 */
	public ConflictException(String message)
	{
		super(message);
	}

	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

}
