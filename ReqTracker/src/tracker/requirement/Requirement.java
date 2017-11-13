package tracker.requirement;

import edu.ncsu.csc216.tracker.xml.Req;
import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * Concrete class that represents a requirement tracked by the REQKEEPER system.
 * Requirement is the Context class of the State design pattern. It maintains
 * the requirement's current state and delegates commands for the current state
 * to handle.
 * 
 * @author Summer Mims
 */

public class Requirement implements RequirementState
{
	// state names
	/**
	 * Name to display when a requirement is in the submitted state
	 */
	public static final String SUBMITTED_NAME = "Submitted";
	/**
	 * Name to display when a requirement is in the accepted state
	 */
	public static final String ACCEPTED_NAME = "Accepted";
	/**
	 * Name to display when a requirement is in the rejected state
	 */
	public static final String REJECTED_NAME = "Rejected";
	/**
	 * Name to display when a requirement is in the assigned state
	 */
	public static final String WORKING_NAME = "Working";
	/**
	 * Name to display when a requirement is in the completed state
	 */
	public static final String COMPLETED_NAME = "Completed";
	/**
	 * Name to display when a requirement is in the verified state
	 */
	public static final String VERIFIED_NAME = "Verified";

	// rejection names
	/**
	 * instance variable to hold rejection reason
	 */
	private Rejection rejectionReason;
	/**
	 * Name to display when a requirement is rejected as a duplicate
	 */
	public static final String DUPLICATE_NAME = "Duplicate";
	/**
	 * Name for display when a requirement is rejected as infeasible
	 */
	public static final String INFEASIBLE_NAME = "Infeasible";
	/**
	 * Name for display when a requirement is rejected as too large
	 */
	public static final String TOO_LARGE_NAME = "Too Large";
	/**
	 * Name for display when a requirement is rejected as out of scope
	 */
	public static final String OUT_OF_SCOPE_NAME = "Out of Scope";
	/**
	 * Name for display when a requirement is rejected as inappropriate
	 */
	public static final String INAPPROPRIATE_NAME = "Inappropriate";

	// Requirement instance variables
	/**
	 * Requirement id
	 * 
	 * unique to each requirement; increment by 1 each new requirement
	 * 
	 * must be greater than or equal to zero
	 * 
	 */
	private int requirementId;
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
	 * Requirement developer
	 * 
	 * If in Working, Completed, or Verified states, must be non-null
	 */
	private String developer;

	/**
	 * counter to increment requirement id as requirements are added to list
	 */
	private static int counter;

	/**
	 * Requirement state
	 * 
	 * can be Submitted, Accepted, Working, Completed, Verified, Rejected
	 * 
	 * cannot be null
	 */
	private RequirementState currentState;

	/**
	 * instance of acceptedState
	 */
	private final RequirementState acceptedState = new AcceptedState();
	/**
	 * instance of submittedState
	 */
	private final RequirementState submittedState = new SubmittedState();
	/**
	 * instance of workingState
	 */
	private final RequirementState workingState = new WorkingState();
	/**
	 * instance of completedState
	 */
	private final RequirementState completedState = new CompletedState();
	/**
	 * instance of verifiedState
	 */
	private final RequirementState verifiedState = new VerifiedState();
	/**
	 * instance of rejectedState
	 */
	private final RequirementState rejectedState = new RejectedState();

	/**
	 * Constructor for Requirement class
	 * 
	 * requirement knows its requirementId, state, summary, estimate, developer,
	 * acceptance test Id, and rejection reason.
	 * 
	 * new requirement starts in the submitted state, is assigned a Id number,
	 * and must have a summary and an acceptance test id.
	 * 
	 * @param summary
	 *            summary description of the requirement
	 * @param acceptanceTestId
	 *            test id for tracking acceptance
	 */
	public Requirement(String summary, String acceptanceTestId)
	{
		setAcceptanceTestId(acceptanceTestId);
		setSummary(summary);
		setPriority(0);
		// set id to counter
		requirementId = counter;
		// then increase counter
		incrementCounter();
		// new requirement must be in submitted state
		setState("Submitted");
	}

	/**
	 * Constructor for Requirement class
	 * 
	 * A requirement knows its requirementId, state, summary, estimate,
	 * developer, acceptance test Id, and rejection reason.
	 * 
	 * Creates requirements loaded from an XML file. Fields are assigned from
	 * the XML information
	 * 
	 * @param r
	 *            Requirement from the XML file
	 */
	public Requirement(Req r)
	{

		this.requirementId = r.getId();
		if (r.getState().equals(SUBMITTED_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
		}
		else if (r.getState().equals(ACCEPTED_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
			setEstimate(r.getEstimate());
			setPriority(r.getPriority());
		}
		else if (r.getState().equals(WORKING_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
			setEstimate(r.getEstimate());
			setPriority(r.getPriority());
			setDeveloper(r.getDeveloper());
		}
		else if (r.getState().equals(COMPLETED_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
			setEstimate(r.getEstimate());
			setPriority(r.getPriority());
			setDeveloper(r.getDeveloper());
		}
		else if (r.getState().equals(VERIFIED_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
			setEstimate(r.getEstimate());
			setPriority(r.getPriority());
			setDeveloper(r.getDeveloper());
		}
		else if (r.getState().equals(REJECTED_NAME))
		{
			setSummary(r.getSummary());
			setAcceptanceTestId(r.getTest());
			setRequirementId(r.getId());
			setState(r.getState());
			setEstimate(null);
			setPriority(0);
			setDeveloper(null);
			setRejectionReason(r.getRejection());
		}
		else
		{
			throw new IllegalArgumentException("Invalid state.");
		}

	}

	/**
	 * 
	 * method to initialize counter with integer
	 * 
	 * @param i
	 *            integer value to set counter to
	 *
	 */
	public static void setCounter(int i)
	{
		if (i < 0)
		{
			throw new IllegalArgumentException("Invalid counter.");
		}
		counter = i;
	}

	/**
	 * method to increment counter by one to provide unique id's for
	 * requirements
	 */
	private static void incrementCounter()
	{
		counter++;
	}

	/**
	 * get the requirement id
	 * 
	 * @return the requirementId
	 */
	public int getRequirementId()
	{
		return requirementId;
	}

	/**
	 * Requirement id
	 * 
	 * id must be unique for each requirement
	 * 
	 * @param requirementId
	 *            the requirementId to set
	 */
	public void setRequirementId(int requirementId)
	{
		if (requirementId < 0)
		{
			throw new IllegalArgumentException("Invalid requirement id.");
		}

		this.requirementId = requirementId;
	}

	/**
	 * get the summary for a requirement
	 * 
	 * @return the summary
	 */
	public String getSummary()
	{
		return summary;
	}

	/**
	 * summary description for requirement
	 * 
	 * summary cannot be, throws illegalArgumentException if null
	 * 
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary)
	{
		if (summary == null)
		{
			throw new IllegalArgumentException("Invalid summary.");
		}
		this.summary = summary;
	}

	/**
	 * get the acceptance test id for a requirement
	 * 
	 * @return the acceptanceTestId
	 */
	public String getAcceptanceTestId()
	{
		return acceptanceTestId;
	}

	/**
	 * the acceptanceTestId for a requirement
	 * 
	 * if null, throws IllegalArgumentException
	 * 
	 * @param acceptanceTestId
	 *            the acceptanceTestId to set
	 */
	public void setAcceptanceTestId(String acceptanceTestId)
	{
		if (acceptanceTestId == null)
		{
			throw new IllegalArgumentException("Invalid acceptance test id.");
		}
		this.acceptanceTestId = acceptanceTestId;
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
	 * set the priority for a requirement
	 * 
	 * if in Accepted, Working, Completed, or Verified state must be non-zero
	 * 
	 * if Submitted or Rejected, must be 0
	 * 
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
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
	 * set the estimate for a requirement
	 * 
	 * 
	 * @param estimate
	 *            the estimate to set
	 */
	public void setEstimate(String estimate)
	{
		this.estimate = estimate;
	}

	/**
	 * get the developer for the requirement
	 * 
	 * @return the developer the developer for the requirement
	 */
	public String getDeveloper()
	{
		return developer;
	}

	/**
	 * set the developer for a requirement
	 * 
	 * must be non-null for states Working, Completed, Verified
	 * 
	 * @param developer
	 *            the developer to set
	 */
	public void setDeveloper(String developer)
	{
		this.developer = developer;
	}

	/**
	 * 
	 * set state of requirement
	 * 
	 * must be State of Submitted, Completed, Accepted, WOrking, Verified ,or
	 * Rejected
	 * 
	 * if not one of these states, throws IllegalArgumentException
	 *
	 * @param setState
	 *            the state to set the requirement to
	 */
	private void setState(String setState)
	{
		if (setState.equalsIgnoreCase(SUBMITTED_NAME))
		{
			currentState = submittedState;
		}
		else if (setState.equalsIgnoreCase(ACCEPTED_NAME))
		{
			currentState = acceptedState;
		}
		else if (setState.equalsIgnoreCase(WORKING_NAME))
		{
			currentState = workingState;
		}
		else if (setState.equalsIgnoreCase(COMPLETED_NAME))
		{
			currentState = completedState;
		}
		else if (setState.equalsIgnoreCase(VERIFIED_NAME))
		{
			currentState = verifiedState;
		}
		else if (setState.equalsIgnoreCase(REJECTED_NAME))
		{
			currentState = rejectedState;
		}
	}

	/**
	 * 
	 * get current state of requirement
	 * 
	 * @return currentState the current state of the requirement
	 *
	 */
	public RequirementState getState()
	{
		return currentState;
	}

	/**
	 * method to update state of requirement based on command transition
	 */
	@Override
	public void update(Command c)
	{
		currentState.update(c);

	}

	/**
	 * get String representation of State for requirement
	 */
	@Override
	public String getStateName()
	{
		return currentState.getStateName();
	}

	/**
	 * 
	 * retrieve a requirement from a XML file and create a new requirement in
	 * the system
	 *
	 * @return r the requirement from the XML file
	 */
	public Req getXMLReq()
	{
		Req r = new Req();
		r.setId(this.getRequirementId());
		r.setState(currentState.getStateName());
		r.setSummary(this.getSummary());
		r.setTest(this.getAcceptanceTestId());
		r.setEstimate(this.getEstimate());
		r.setDeveloper(this.getDeveloper());
		r.setRejection(this.getRejectionReasonString());
		return r;
	}

	/**
	 * get the String output for the rejection reason
	 *
	 * @return rejectionReason.name() the rejectionReason name from enum
	 */
	public String getRejectionReasonString()
	{
		if (rejectionReason == Rejection.DUPLICATE)
		{
			return DUPLICATE_NAME;
		}
		else if (rejectionReason == Rejection.INAPPROPRIATE)
		{
			return INAPPROPRIATE_NAME;
		}
		else if (rejectionReason == Rejection.INFEASIBLE)
		{
			return INFEASIBLE_NAME;
		}
		else if (rejectionReason == Rejection.OUT_OF_SCOPE)
		{
			return OUT_OF_SCOPE_NAME;
		}
		else if (rejectionReason == Rejection.TOO_LARGE)
		{
			return TOO_LARGE_NAME;
		}
		else
		{
			return null;
		}
	}

	/**
	 * get rejection reason for requirement
	 *
	 * @return rejectionReason the rejection for the requirement
	 */
	public Rejection getRejectionReason()
	{
		return rejectionReason;
	}

	/**
	 * 
	 * set rejection reason for a requirement
	 * 
	 * if requirement is not in rejected state, set rejection reason to null
	 * 
	 * if rejectionReason is not enum list, throw IllegalArgumentException
	 *
	 * @param reason
	 *            the reason for rejection
	 */
	public void setRejectionReason(String reason)
	{
		if (currentState == rejectedState)
		{
			if (DUPLICATE_NAME.equalsIgnoreCase(reason))
			{
				rejectionReason = Rejection.DUPLICATE;
			}
			else if (INFEASIBLE_NAME.equalsIgnoreCase(reason))
			{
				rejectionReason = Rejection.INFEASIBLE;
			}
			else if (TOO_LARGE_NAME.equalsIgnoreCase(reason))
			{
				rejectionReason = Rejection.TOO_LARGE;
			}
			else if (OUT_OF_SCOPE_NAME.equalsIgnoreCase(reason))
			{
				rejectionReason = Rejection.OUT_OF_SCOPE;
			}
			else if (INAPPROPRIATE_NAME.equalsIgnoreCase(reason))
			{
				rejectionReason = Rejection.INAPPROPRIATE;
			}
		}
		else
		{
			rejectionReason = null;
		}
	}

	/**
	 * sub class of state to represent Submitted state
	 * 
	 * all new requirements enter in the submitted state
	 * 
	 * can transition to rejected or accepted from submitted
	 * 
	 */
	private class SubmittedState implements RequirementState
	{
		/**
		 * from submitted state can transition to accepted or rejected
		 * 
		 * if accepted, must have estimate and priority
		 * 
		 * estimate cannot be null - provided from command and checked in
		 * command constructor
		 * 
		 * priority must be between 1 and 3 - provided from command and checked
		 * in command constructor
		 * 
		 * if rejected, use rejectRequirement method to check for rejection
		 * validation
		 * 
		 * @param c
		 *            the command to change the state to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.ACCEPT)
			{
				setPriority(c.getPriority());
				setEstimate(c.getEstimate());
				setState(ACCEPTED_NAME);
			}
			else if (c.getCommand() == CommandValue.REJECT)
			{
				rejectRequirement(c.getRejectionReason());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}

		}

		/**
		 * return string representation of the Submitted state
		 */
		@Override
		public String getStateName()
		{
			return SUBMITTED_NAME;
		}
	}

	/**
	 * method to reject a requirement
	 * 
	 * set estimate, priority, and developer to null change state to rejected
	 * set rejection reason
	 * 
	 * rejection reason cannot be null
	 * 
	 * @param rejectionReason
	 *            the reason to reject a requirement
	 * @throws IllegalArgumentException
	 *             thrown if rejection reason is null
	 */
	private void rejectRequirement(Rejection rejectionReason)
	{
		this.rejectionReason = rejectionReason;
		setEstimate(null);
		setDeveloper(null);
		setPriority(0);
		setState(REJECTED_NAME);
	}

	/**
	 * sub class of State to represent Accepted state
	 * 
	 * Requirement enters Accepted from Submitted
	 * 
	 * From accepted the requirement can transition to rejected or working to
	 * transition to working, must have developer id to transition to rejection,
	 * use rejectRequirement to validate transition
	 * 
	 * 
	 */
	private class AcceptedState implements RequirementState
	{
		/**
		 * can transition to working or rejected
		 * 
		 * if transition to working, must have developer id
		 * 
		 * @param c
		 *            the command to change the state to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.ASSIGN)
			{
				setDeveloper(c.getDeveloperId());
				setState(WORKING_NAME);
			}
			else if (c.getCommand() == CommandValue.REJECT)
			{
				rejectRequirement(c.getRejectionReason());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}

		}

		/**
		 * return string representation of the accepted state
		 */
		@Override
		public String getStateName()
		{
			return ACCEPTED_NAME;
		}
	}

	/**
	 * sub class of state for working state
	 * 
	 * a requirement can enter working from accepted state
	 * 
	 * a requirement can transition to completed or rejected from the working
	 * state
	 */
	private class WorkingState implements RequirementState
	{
		/**
		 * can transition to rejected or completed from working no new fields
		 * are required to transition to completed if rejected, use
		 * rejectRequirement to validate transition
		 * 
		 * @param c
		 *            the command to change the state to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.COMPLETE)
			{
				setState(COMPLETED_NAME);
			}
			else if (c.getCommand() == CommandValue.REJECT)
			{
				rejectRequirement(c.getRejectionReason());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}

		}

		/**
		 * return string representation of the working state
		 */
		@Override
		public String getStateName()
		{
			return WORKING_NAME;
		}
	}

	/**
	 * sub class of state for completed state
	 * 
	 * a requirement can enter completed from working state
	 * 
	 * 
	 * if transition to completed, must have developer id, estimate, and
	 * priority all of these required fields should have been filled in from
	 * prior transitions if rejected, use rejectionRequirement to validate
	 * transition
	 * 
	 * a completed requirement can pass, fail, assign, or rejected pass
	 */
	private class CompletedState implements RequirementState
	{
		/**
		 * a complete requirement can transition with fail, pass, assign, or
		 * reject this will change the state to working, verified, working, or
		 * rejected to assign again in working, must have developer id
		 * 
		 * @param c
		 *            the command to change the state to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.FAIL)
			{
				setState(WORKING_NAME);
			}
			else if (c.getCommand() == CommandValue.PASS)
			{
				setState(VERIFIED_NAME);
			}
			else if (c.getCommand() == CommandValue.ASSIGN)
			{
				setDeveloper(c.getDeveloperId());
				setState(WORKING_NAME);
			}
			else if (c.getCommand() == CommandValue.REJECT)
			{
				rejectRequirement(c.getRejectionReason());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * return string representation of the completed state
		 */
		@Override
		public String getStateName()
		{
			return COMPLETED_NAME;
		}
	}

	/**
	 * sub class of state to represent Verified state
	 * 
	 * a requirement enters this state from working
	 * 
	 * 
	 */
	private class VerifiedState implements RequirementState
	{
		/**
		 * can transition to working or rejected from verified
		 * 
		 * developer id must be non-null to transition to working
		 * 
		 * use rejectRequirement to validate rejection
		 * 
		 * @param c
		 *            the command to change the state to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.ASSIGN)
			{
				setDeveloper(c.getDeveloperId());
				setState(WORKING_NAME);
			}
			else if (c.getCommand() == CommandValue.REJECT)
			{
				rejectRequirement(c.getRejectionReason());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}

		}

		/**
		 * return string representation of the verified state
		 */
		@Override
		public String getStateName()
		{
			return VERIFIED_NAME;
		}
	}

	/**
	 * sub class of state to represent rejected state
	 * 
	 * a requirement enters rejected from any other state
	 * 
	 * a requirement can transition to submitted from rejected state
	 */
	private class RejectedState implements RequirementState
	{
		/**
		 * change the current state of requirement based on command can update
		 * rejected to submitted with new summary and acceptanceTestId
		 * 
		 * @param c
		 *            the command to indicate the new state to transition to
		 * @throws UnsupportedOperationException
		 *             when the command is not valid
		 */
		@Override
		public void update(Command c)
		{
			if (c.getCommand() == CommandValue.REVISE)
			{
				setState(SUBMITTED_NAME);
				setRejectionReason(null);
				setSummary(c.getSummary());
				setAcceptanceTestId(c.getAcceptanceTestId());
			}
			else
			{
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * return string representation of the rejected state
		 */
		@Override
		public String getStateName()
		{
			return REJECTED_NAME;
		}
	}

}