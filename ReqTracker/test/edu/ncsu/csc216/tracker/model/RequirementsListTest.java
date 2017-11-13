
package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.tracker.xml.Req;
import tracker.model.RequirementsList;
import tracker.requirement.Command;
import tracker.requirement.Requirement;
import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * Junit test for functionality of RequirementsList
 * 
 * @author Summer Mims
 */
public class RequirementsListTest
{

	/**
	 * 
	 * test constructing a RequirementsList and adding new requirements
	 *
	 */
	@Test
	public void testAddRequirements()
	{
		// create new Requirements list
		RequirementsList rList = new RequirementsList();

		// add requirements
		// valid
		int r1 = rList.addRequirement("Summary", "Id 1");
		assertEquals(1, rList.getRequirements().size());
		assertEquals("Summary", rList.getRequirementById(r1).getSummary());
		assertEquals("Id 1",
				rList.getRequirementById(r1).getAcceptanceTestId());
		// invalid - null summary
		try
		{
			rList.addRequirement(null, "Id 1");
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(1, rList.getRequirements().size());
			assertEquals("Invalid summary.", e.getMessage());
		}
		// invalid - null acceptance test id
		try
		{
			rList.addRequirement("Summary 1", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(1, rList.getRequirements().size());
			assertEquals("Invalid acceptance test id.", e.getMessage());
		}
		// valid
		int r2 = rList.addRequirement("Summary 2", "Id 2");
		assertEquals(2, rList.getRequirements().size());
		assertEquals("Summary 2", rList.getRequirementById(r2).getSummary());
		assertEquals("Id 2",
				rList.getRequirementById(r2).getAcceptanceTestId());
	}

	/**
	 * 
	 * test deleting requirements
	 *
	 */
	@Test
	public void testDeleteRequirements()
	{
		// create new Requirements list
		RequirementsList rList = new RequirementsList();

		// try deleting from an empty list
		rList.deleteRequirementById(0);

		// add requirements
		// valid
		rList.addRequirement("Summary", "Id 1");
		assertEquals(1, rList.getRequirements().size());
		assertEquals("Summary", rList.getRequirementById(0).getSummary());
		assertEquals("Id 1", rList.getRequirementById(0).getAcceptanceTestId());
		// add requirement 2
		rList.addRequirement("Summary 2", "Id 2");
		assertEquals(2, rList.getRequirements().size());
		assertEquals("Summary 2", rList.getRequirementById(1).getSummary());
		assertEquals("Id 2", rList.getRequirementById(1).getAcceptanceTestId());

		// remove element 1
		rList.deleteRequirementById(0);
		assertEquals(1, rList.getRequirements().size());
		// remove element 2
		rList.deleteRequirementById(1);
	}

	/**
	 * test execute command
	 */
	@Test
	public void testExecuteCommand()
	{
		// create and add requirements
		RequirementsList rList = new RequirementsList();
		int r1 = rList.addRequirement("Summary 1", "Id 1");
		int r2 = rList.addRequirement("Summary 2", "Id 2");

		// check initial states
		assertEquals("Submitted", rList.getRequirementById(r1).getStateName());
		assertEquals("Submitted", rList.getRequirementById(r2).getStateName());

		// accept element 1
		Command cAccept = new Command(CommandValue.ACCEPT,
				rList.getRequirementById(r1).getSummary(),
				rList.getRequirementById(r1).getAcceptanceTestId(), 1, "1",
				"smims", Rejection.DUPLICATE);
		rList.executeCommand(r1, cAccept);
		assertEquals("Accepted", rList.getRequirementById(r1).getStateName());

		// reject element 2
		Command cReject = new Command(CommandValue.REJECT,
				rList.getRequirementById(r2).getSummary(),
				rList.getRequirementById(r2).getAcceptanceTestId(), 2, "2",
				"smims", Rejection.DUPLICATE);
		rList.executeCommand(r2, cReject);
		assertEquals("Rejected", rList.getRequirementById(r2).getStateName());
	}

	/**
	 * test adding XML Reqs
	 */
	@Test
	public void testAddReqs()
	{
		// create new Requirements list
		RequirementsList rList = new RequirementsList();

		// create List<Req>
		List<Req> reqList = new ArrayList<Req>();
		assertEquals(0, reqList.size());

		// create new Req
		Req reqXML = new Req();
		reqXML.setId(10);
		reqXML.setState(Requirement.SUBMITTED_NAME);
		reqXML.setSummary("Summary 2");
		reqXML.setTest("Id 2");
		// add to list
		reqList.add(reqXML);
		assertEquals(1, reqList.size());

		// add XML Req
		rList.addXMLReqs(reqList);
		assertEquals(1, rList.getRequirements().size());
	}

	/**
	 * test getRequirementById
	 */
	@Test
	public void getRequirementById()
	{
		// create new Requirements list
		RequirementsList rList = new RequirementsList();
		assertNull(rList.getRequirementById(0));

		// add requirements
		rList.addRequirement("Summary", "Id 1");
		rList.addRequirement("Summary 2", "Id 2");

		assertEquals(0, rList.getRequirementById(0).getRequirementId());
		assertEquals(1, rList.getRequirementById(1).getRequirementId());
		// test with element not in list
		assertEquals(null, rList.getRequirementById(2));

	}

	/**
	 * test getRequirements
	 * 
	 */
	@Test
	public void getRequirementsTest()
	{
		RequirementsList rList = new RequirementsList();
		assertEquals(0, rList.getRequirements().size());
	}
}
