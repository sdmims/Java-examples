package tracker.requirement;

import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * Concrete class that represents a command (assign, reject, and so on) that the
 * user enters from the GUI to be handled by the internal FSM.
 * 
 * @author Summer Mims
 */
public class Command
{

	/**
	 * Requirement summary
	 * 
	 * cannot be null
	 */
	private String summary;

	/**
	 * Requirement acceptance test id
	 * 
	 * cannot be null
	 */
	private String acceptanceTestId;

	/**
	 * Requirement priority
	 * 
	 * cannot be less than 0 or greater than 3
	 * 
	 * if in Accepted, Working, Completed, or Verified state, must be non-zero
	 * 
	 * if in Submitted or Rejected state, priority is 0
	 */
	private int priority;

	/**
	 * Requirement estimate
	 * 
	 * if in Accepted, Working, Completed, or Verified state, must be non-null
	 */
	private String estimate;

	/**
	 * Requirement developer id
	 * 
	 * If in Working, Completed, or Verified states, must be non-null
	 */
	private String developerId;

	/**
	 * the command transition indicated by the user
	 */
	private CommandValue userCommand;
	/**
	 * the rejection reason set by user
	 */
	private Rejection rejectionReason;

	/**
	 * Constructs a new Command object.
	 * 
	 * @param userCommand
	 *            the transition indicated by user
	 * @param summary
	 *            the requirement summary
	 * @param acceptanceTestId
	 *            the acceptance test id for requirement
	 * @param priority
	 *            the priority for the requirement
	 * @param estimate
	 *            the requirement estimate
	 * @param developerId
	 *            the developer for a requirement
	 * @param rejectionReason
	 *            the reason for rejection
	 */
	public Command(CommandValue userCommand, String summary,
			String acceptanceTestId, int priority, String estimate,
			String developerId, Rejection rejectionReason)
	{
		// command cannot be null
		if (userCommand == null)
		{
			throw new IllegalArgumentException("Command is null.");
		}
		// if accept -> transition from submitted to accepted
		// must have estimate
		// must have priority between [1-3]
		if ((userCommand == CommandValue.ACCEPT))
		{
			if ((estimate == null) || estimate.equalsIgnoreCase(""))
			{
				throw new IllegalArgumentException("Invalid estimate.");
			}
			if (priority < 1 || priority > 3)
			{
				throw new IllegalArgumentException("Invalid priority.");
			}
		}

		// if assign ->transition from accepted to working
		// must have developer
		// must have estimate -> should have estimate from prior transition
		// must have priority between [1-3] -> should have priority from prior
		// transition
		if ((userCommand == CommandValue.ASSIGN) && ((developerId == null)
				|| (developerId.equalsIgnoreCase(""))))
		{
			throw new IllegalArgumentException("Invalid developer id.");
		}

		// if complete -> transition from working to complete
		// no parameters to check from this command

		// if pass -> transition from complete to verified
		// no parameters to check for

		// if fail -> transition from complete to working
		// no parameter to check for

		// if rejected
		// must have rejection reason
		if ((userCommand == CommandValue.REJECT) && (rejectionReason == null))
		{
			throw new IllegalArgumentException("Rejection Reason is null.");
		}

		// if revised
		// must have summary and acceptance test if
		if ((userCommand == CommandValue.REVISE))
		{
			if (summary == null || summary.equalsIgnoreCase(""))
			{
				throw new IllegalArgumentException("Invalid summary.");
			}
			if (acceptanceTestId == null
					|| acceptanceTestId.equalsIgnoreCase(""))
			{
				throw new IllegalArgumentException(
						"Invalid acceptance test id.");
			}
		}

		this.userCommand = userCommand;
		this.summary = summary;
		this.acceptanceTestId = acceptanceTestId;
		this.priority = priority;
		this.estimate = estimate;
		this.developerId = developerId;
		this.rejectionReason = rejectionReason;
	}

	/**
	 * get the summary for the requirement
	 * 
	 * @return the summary
	 */
	public String getSummary()
	{
		return summary;
	}

	/**
	 * get the acceptanceTestId for the requirement
	 * 
	 * @return the acceptanceTestId
	 */
	public String getAcceptanceTestId()
	{
		return acceptanceTestId;
	}

	/**
	 * get the priority for a requirement
	 * 
	 * @return the priority
	 */
	public int getPriority()
	{
		return priority;
	}

	/**
	 * get the estimate for a requirement
	 * 
	 * @return the estimate
	 */
	public String getEstimate()
	{
		return estimate;
	}

	/**
	 * get the developer for a requirement
	 * 
	 * @return the developerId
	 */
	public String getDeveloperId()
	{
		return developerId;
	}

	/**
	 * 
	 * get the command for the transition
	 *
	 * @return userCommand the command transition set by user
	 */
	public CommandValue getCommand()
	{
		return userCommand;

	}

	/**
	 * 
	 * get the rejection reason
	 *
	 * @return rejectionReason for the transition
	 */
	public Rejection getRejectionReason()
	{
		return rejectionReason;

	}
}