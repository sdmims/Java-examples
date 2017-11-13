
package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.tracker.xml.Req;
import tracker.requirement.Command;
import tracker.requirement.Requirement;
import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * JUnit tests for function of Requirement
 * 
 * @author Summer Mims
 */
public class RequirementTest
{
	private Requirement r1;
	private Requirement rXML;
	private Req reqXML;

	/**
	 * create requirements for testing
	 */
	@Before
	public void setUp()
	{
		Requirement.setCounter(0);

		reqXML = new Req();
		reqXML.setId(10);
		reqXML.setState(Requirement.SUBMITTED_NAME);
		reqXML.setSummary("Summary 2");
		reqXML.setTest("Id 2");

		r1 = new Requirement("Summary 1", "Acceptance ID 1");
		rXML = new Requirement(reqXML);
	}

	/**
	 * 
	 * test Requirement constructor requiring two parameters
	 *
	 */
	@Test
	public void requirementConstructorTest()
	{
		// check r1
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(0, r1.getRequirementId());
		assertEquals(null, r1.getDeveloper());
		assertEquals(null, r1.getEstimate());
		assertEquals(0, r1.getPriority());
		assertEquals("Submitted", r1.getState().getStateName());
		assertEquals(null, r1.getRejectionReason());
		// check rXML
		assertEquals("Summary 2", rXML.getSummary());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals(10, rXML.getRequirementId());
		assertEquals(null, rXML.getDeveloper());
		assertEquals(null, rXML.getEstimate());
		assertEquals(0, rXML.getPriority());
		assertEquals("Submitted", rXML.getState().getStateName());
		assertEquals(null, rXML.getRejectionReason());

		// null summary parameter
		try
		{
			r1 = new Requirement(null, "Acceptance ID 2");
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid summary.", e.getMessage());
		}
		// null acceptance test id parameter
		try
		{
			r1 = new Requirement("Summary 2", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid acceptance test id.", e.getMessage());
		}
	}

	/**
	 * 
	 * test Requirement constructor requiring two parameters
	 *
	 */
	@Test
	public void requirementConstructorReqTest()
	{
		reqXML = new Req();
		reqXML.setId(10);
		reqXML.setSummary("Summary 2");
		reqXML.setTest("Id 2");
		reqXML.setEstimate("1");
		reqXML.setPriority(1);
		reqXML.setDeveloper("smims");
		reqXML.setRejection("Duplicate");

		// test with submitted state
		reqXML.setState(Requirement.SUBMITTED_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Submitted", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals(null, rXML.getEstimate());
		assertEquals(null, rXML.getDeveloper());
		assertEquals(0, rXML.getPriority());
		assertEquals(null, rXML.getRejectionReasonString());

		// test with accepted state
		reqXML.setState(Requirement.ACCEPTED_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Accepted", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals("1", rXML.getEstimate());
		assertEquals(null, rXML.getDeveloper());
		assertEquals(1, rXML.getPriority());
		assertEquals(null, rXML.getRejectionReasonString());

		// test with working state
		reqXML.setState(Requirement.WORKING_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Working", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals("1", rXML.getEstimate());
		assertEquals("smims", rXML.getDeveloper());
		assertEquals(1, rXML.getPriority());
		assertEquals(null, rXML.getRejectionReasonString());

		// test with completed state
		reqXML.setState(Requirement.COMPLETED_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Completed", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals("1", rXML.getEstimate());
		assertEquals("smims", rXML.getDeveloper());
		assertEquals(1, rXML.getPriority());
		assertEquals(null, rXML.getRejectionReasonString());

		// test with verified state
		reqXML.setState(Requirement.VERIFIED_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Verified", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals("1", rXML.getEstimate());
		assertEquals("smims", rXML.getDeveloper());
		assertEquals(1, rXML.getPriority());
		assertEquals(null, rXML.getRejectionReasonString());

		// test with rejected state
		reqXML.setState(Requirement.REJECTED_NAME);
		rXML = new Requirement(reqXML);
		assertEquals("Rejected", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals(null, rXML.getEstimate());
		assertEquals(null, rXML.getDeveloper());
		assertEquals(0, rXML.getPriority());
		assertEquals(Requirement.DUPLICATE_NAME,
				rXML.getRejectionReasonString());

		// test with invalid state
		try
		{
			reqXML.setState("NONE");
			rXML = new Requirement(reqXML);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid state.", e.getMessage());
		}
	}

	/**
	 * test set counter method
	 */
	@Test
	public void setCounterTest()
	{
		// valid counter set
		Requirement.setCounter(10);
		Requirement r2 = new Requirement("Summary 10", "Id 10");
		assertEquals(10, r2.getRequirementId());

		// invalid
		try
		{
			Requirement.setCounter(-1);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(10, r2.getRequirementId());
			assertEquals("Invalid counter.", e.getMessage());
		}
	}

	/**
	 * test set requirement id method
	 */
	@Test
	public void setRequirementIdTest()
	{
		Requirement r2 = new Requirement("Summary 10", "Id 10");
		assertEquals(1, r2.getRequirementId());

		// set valid
		r2.setRequirementId(10);
		assertEquals(10, r2.getRequirementId());

		// set invalid
		try
		{
			r2.setRequirementId(-1);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(10, r2.getRequirementId());
			assertEquals("Invalid requirement id.", e.getMessage());
		}
	}

	/**
	 * test set priority method
	 */
	@Test
	public void setPriorityTest()
	{
		// set valid
		r1.setPriority(0);
		assertEquals(0, r1.getPriority());
		// set valid
		r1.setPriority(1);
		assertEquals(1, r1.getPriority());
	}

	/**
	 * test set estimate method
	 */
	@Test
	public void setEstimateTest()
	{
		r1.setEstimate("1");
		assertEquals("1", r1.getEstimate());
	}

	/**
	 * test set summary method
	 */
	@Test
	public void setSummaryTest()
	{
		r1.setSummary("New summary");
		assertEquals("New summary", r1.getSummary());

		// null summary
		try
		{
			r1.setSummary(null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("New summary", r1.getSummary());
			assertEquals("Invalid summary.", e.getMessage());
		}
	}

	/**
	 * test set acceptance test id method
	 */
	@Test
	public void setAcceptanceTestIdTest()
	{
		r1.setAcceptanceTestId("New acceptanceTestId");
		assertEquals("New acceptanceTestId", r1.getAcceptanceTestId());

		// null acceptanceTestId
		try
		{
			r1.setAcceptanceTestId(null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("New acceptanceTestId", r1.getAcceptanceTestId());
			assertEquals("Invalid acceptance test id.", e.getMessage());
		}
	}

	/**
	 * test update and setState methods
	 */
	@Test
	public void testUpdate()
	{
		Command cAccept = new Command(CommandValue.ACCEPT, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		Command cAssign = new Command(CommandValue.ASSIGN, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		Command cComplete = new Command(CommandValue.COMPLETE, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		Command cPass = new Command(CommandValue.PASS, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		Command cReject = new Command(CommandValue.REJECT, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		Command cRevise = new Command(CommandValue.REVISE, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);

		// invalid command in submitted state
		try
		{
			r1.update(cComplete);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Submitted", r1.getStateName());
		}

		// update to accepted
		r1.update(cAccept);
		assertEquals("Accepted", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority()); // should update
		assertEquals("1", r1.getEstimate()); // should update
		assertEquals(null, r1.getDeveloper());
		assertEquals(null, r1.getRejectionReasonString());

		// invalid command in accepted state
		try
		{
			r1.update(cComplete);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Accepted", r1.getStateName());
		}
		// updated to assigned
		r1.update(cAssign);
		assertEquals("Working", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority());
		assertEquals("1", r1.getEstimate());
		assertEquals("smims", r1.getDeveloper()); // should update
		assertEquals(null, r1.getRejectionReasonString());

		// invalid command in working state
		try
		{
			r1.update(cAccept);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Working", r1.getStateName());
		}

		// update to completed
		r1.update(cComplete);
		assertEquals("Completed", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority());
		assertEquals("1", r1.getEstimate());
		assertEquals("smims", r1.getDeveloper());
		assertEquals(null, r1.getRejectionReasonString());

		// invalid command in completed state
		try
		{
			r1.update(cComplete);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Completed", r1.getStateName());
		}

		// update to pass
		r1.update(cPass);
		assertEquals("Verified", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority());
		assertEquals("1", r1.getEstimate());
		assertEquals("smims", r1.getDeveloper());
		assertEquals(null, r1.getRejectionReasonString());

		// invalid command in verified state
		try
		{
			r1.update(cComplete);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Verified", r1.getStateName());
		}

		// update to reject from verified
		r1.update(cReject);
		assertEquals("Rejected", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(0, r1.getPriority()); // should update
		assertEquals(null, r1.getEstimate()); // should update
		assertEquals(null, r1.getDeveloper()); // should update
		assertEquals("Duplicate", r1.getRejectionReasonString()); // should
																  // update
		// invalid command in rejected state
		try
		{
			r1.update(cComplete);
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals("Invalid command.", e.getMessage());
			assertEquals("Rejected", r1.getStateName());
		}

		// update to revise from rejected
		r1.update(cRevise);
		assertEquals("Submitted", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(0, r1.getPriority());
		assertEquals(null, r1.getEstimate());
		assertEquals(null, r1.getDeveloper());
		assertEquals(null, r1.getRejectionReasonString());

		// update to assign from verified
		r1.update(cAccept);
		assertEquals("Accepted", r1.getStateName());
		r1.update(cAssign);
		assertEquals("Working", r1.getStateName());
		r1.update(cComplete);
		assertEquals("Completed", r1.getStateName());
		r1.update(cPass);
		assertEquals("Verified", r1.getStateName());
		r1.update(cAssign);
		assertEquals("Working", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority());
		assertEquals("1", r1.getEstimate());
		assertEquals("smims", r1.getDeveloper()); // should update
		assertEquals(null, r1.getRejectionReasonString());

		// update to complete then fail
		r1.update(cComplete);
		assertEquals("Completed", r1.getStateName());
		Command cFail = new Command(CommandValue.FAIL, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		r1.update(cFail);
		assertEquals("Working", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(1, r1.getPriority());
		assertEquals("1", r1.getEstimate());
		assertEquals("smims", r1.getDeveloper());
		assertEquals(null, r1.getRejectionReasonString());

		// reject form working
		r1.update(cReject);
		assertEquals("Rejected", r1.getStateName());
		assertEquals(0, r1.getRequirementId());
		assertEquals("Summary 1", r1.getSummary());
		assertEquals("Acceptance ID 1", r1.getAcceptanceTestId());
		assertEquals(0, r1.getPriority()); // should update
		assertEquals(null, r1.getEstimate()); // should update
		assertEquals(null, r1.getDeveloper()); // should update
		assertEquals("Duplicate", r1.getRejectionReasonString()); // should
																  // update

		// reject from submitted
		Command cRejectXML = new Command(CommandValue.REJECT, rXML.getSummary(),
				rXML.getAcceptanceTestId(), 1, "1", "smims",
				Rejection.DUPLICATE);
		rXML.update(cRejectXML);
		assertEquals("Rejected", rXML.getStateName());
		assertEquals(10, rXML.getRequirementId());
		assertEquals("Summary 2", rXML.getSummary());
		assertEquals("Id 2", rXML.getAcceptanceTestId());
		assertEquals(0, rXML.getPriority()); // should update
		assertEquals(null, rXML.getEstimate()); // should update
		assertEquals(null, rXML.getDeveloper()); // should update
		assertEquals("Duplicate", rXML.getRejectionReasonString()); // should
																	// update

		// assign from complete
		r1.update(cRevise);
		r1.update(cAccept);
		r1.update(cAssign);
		r1.update(cComplete);
		r1.update(cAssign);
		assertEquals("Working", r1.getStateName());

		// reject form complete
		r1.update(cComplete);
		r1.update(cReject);
		assertEquals("Rejected", r1.getStateName());

		// reject from accepted
		r1.update(cRevise);
		r1.update(cAccept);
		r1.update(cReject);
		assertEquals("Rejected", r1.getStateName());

	}

	/**
	 * test setRejectionReason
	 */
	@Test
	public void setRejectionReasonTest()
	{
		// check when not in rejected state
		rXML.setRejectionReason(null);
		assertEquals(null, rXML.getRejectionReason());
		rXML.setRejectionReason("Duplicate");
		assertEquals(null, rXML.getRejectionReason());

		// check in rejected state
		Command cRejectXML = new Command(CommandValue.REJECT, rXML.getSummary(),
				rXML.getAcceptanceTestId(), 1, "1", "smims",
				Rejection.DUPLICATE);
		rXML.update(cRejectXML);
		assertEquals("Duplicate", rXML.getRejectionReasonString());
		// set rejection reason
		rXML.setRejectionReason("Duplicate");
		assertEquals("Duplicate", rXML.getRejectionReasonString());
		rXML.setRejectionReason("Inappropriate");
		assertEquals("Inappropriate", rXML.getRejectionReasonString());
		rXML.setRejectionReason("Infeasible");
		assertEquals("Infeasible", rXML.getRejectionReasonString());
		rXML.setRejectionReason("Too large");
		assertEquals("Too Large", rXML.getRejectionReasonString());
		rXML.setRejectionReason("Out of scope");
		assertEquals("Out of Scope", rXML.getRejectionReasonString());
	}

	/**
	 * test getXMLReq
	 * 
	 */
	@Test
	public void getXMLReqTest()
	{
		Req r2 = rXML.getXMLReq();
		assertEquals(r2.getSummary(), rXML.getSummary());
		assertEquals(r2.getEstimate(), rXML.getEstimate());
		assertEquals(r2.getDeveloper(), rXML.getDeveloper());
		assertEquals(r2.getId(), rXML.getRequirementId());
		assertEquals(r2.getPriority(), rXML.getPriority());
		assertEquals(r2.getState(), rXML.getStateName());
		assertEquals(r2.getTest(), rXML.getAcceptanceTestId());
	}
}
