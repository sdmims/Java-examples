package tracker.requirement.enums;

/**
 * enum to hold reasons a Requirement may be rejected
 * 
 * rejection reason are limited to those listed in enum
 * 
 * @author Summer Mims
 */
public enum Rejection
{
	/**
	 * Requirement is duplicate of another Requirement
	 */
	DUPLICATE,
	/**
	 * Requirement is infeasible to execute
	 */
	INFEASIBLE,
	/**
	 * Requirement is too large for single Requirement, break into sub cases
	 */
	TOO_LARGE,
	/**
	 * Requirement is out of scope for applicable project
	 */
	OUT_OF_SCOPE,
	/**
	 * Requirement is inappropriate for project
	 */
	INAPPROPRIATE
}
