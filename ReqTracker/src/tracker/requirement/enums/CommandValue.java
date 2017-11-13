package tracker.requirement.enums;

/**
 * enum to hold possible values for command each represents a possible
 * transition for a Requirement
 * 
 * @author Summer Mims
 */
public enum CommandValue
{
	/**
	 * Accepted requirement: Submitted to Accepted
	 */
	ACCEPT,

	/**
	 * Rejected requirement: Submitted to Rejected; Accepted to Rejected;
	 * Working to Rejected; Completed to Rejected; Verified to Rejected;
	 */
	REJECT,

	/**
	 * Revised requirement: Rejected to Submitted;
	 */
	REVISE,

	/**
	 * Working requirement: Accepted to Working; Completed to Working; Verified
	 * to Working;
	 */
	ASSIGN,

	/**
	 * Completed requirement: Working to Completed;
	 */
	COMPLETE,

	/**
	 * Passed requirement: Completed to Verified
	 */
	PASS,

	/**
	 * Failed requirement: Completed to Working
	 */
	FAIL
}
