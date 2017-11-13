package student_scheduler.course;

/**
 * interface to provide abstract method checkConflict for Activity
 * 
 * @author Summer Mims
 */

public interface Conflict
{
	/**
	 * 
	 * method to check for conflicts with activities in the schedule and a new
	 * activity to add to schedule
	 * 
	 * if there is overlap of even one day or one minute, a ConflictException is
	 * thrown
	 *
	 * @param possibleConflictingActivity
	 *            the Activity to check if it conflicts with activities already
	 *            in the Student Schedule
	 * @throws ConflictException
	 *             if there is a conflict with the schedule
	 */
	void checkConflict(Activity possibleConflictingActivity)
			throws ConflictException;

}
