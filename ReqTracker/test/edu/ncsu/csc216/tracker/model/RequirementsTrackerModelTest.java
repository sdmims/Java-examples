
package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import tracker.model.RequirementsTrackerModel;
import tracker.requirement.Command;
import tracker.requirement.Requirement;
import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * JUnit test for functionality of RequirementModelTracker
 * 
 * @author Summer Mims
 */
public class RequirementsTrackerModelTest
{

	private RequirementsTrackerModel m;

	/**
	 * Sets up the RequirementsTrackerModel and clears the data.
	 * 
	 * @throws Exception
	 *             if error
	 */
	@Before
	public void setUp() throws Exception
	{
		m = RequirementsTrackerModel.getInstance();
		m.createNewRequirementsList();
	}

	/**
	 * 
	 * test addRequirement method
	 *
	 */
	@Test
	public void addRequirementTest()
	{
		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");

		assertEquals(0, m.getRequirementById(0).getRequirementId());
		assertEquals("Summary 1", m.getRequirementById(0).getSummary());
		assertEquals("Submitted", m.getRequirementById(0).getStateName());
		assertEquals("Acceptance test 1",
				m.getRequirementById(0).getAcceptanceTestId());

		assertEquals(1, m.getRequirementById(1).getRequirementId());
		assertEquals("Summary 2", m.getRequirementById(1).getSummary());
		assertEquals("Submitted", m.getRequirementById(1).getStateName());
		assertEquals("Acceptance test 2",
				m.getRequirementById(1).getAcceptanceTestId());
	}

	/**
	 * test deleteRequirementById
	 */
	@Test
	public void deleteRequirementByIdTest()
	{
		// delete non-existent element
		m.deleteRequirementById(0);
		assertEquals(0, m.getRequirementListAsArray().length);

		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");
		assertEquals(2, m.getRequirementListAsArray().length);

		// delete non-existent element
		m.deleteRequirementById(3);
		assertEquals(2, m.getRequirementListAsArray().length);
		assertEquals(2, m.getRequirementListAsArray().length);
		m.deleteRequirementById(0);
		assertEquals(1, m.getRequirementListAsArray().length);
		m.deleteRequirementById(1);
		assertEquals(0, m.getRequirementListAsArray().length);
	}

	/**
	 * test executeCommand
	 */
	@Test
	public void executeCommandTest()
	{
		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");

		Command c1 = new Command(CommandValue.ACCEPT,
				m.getRequirementById(0).getSummary(),
				m.getRequirementById(0).getAcceptanceTestId(), 1, "1", "smims",
				Rejection.DUPLICATE);

		m.executeCommand(0, c1);
		assertEquals("Summary 1", m.getRequirementById(0).getSummary());
		assertEquals("Accepted", m.getRequirementById(0).getStateName());
		assertEquals("Acceptance test 1",
				m.getRequirementById(0).getAcceptanceTestId());
		assertEquals("1", m.getRequirementById(0).getEstimate());
		assertEquals(1, m.getRequirementById(0).getPriority());
		assertEquals(null, m.getRequirementById(0).getDeveloper());
		assertEquals(null, m.getRequirementById(0).getRejectionReason());

		Command c2 = new Command(CommandValue.REJECT,
				m.getRequirementById(1).getSummary(),
				m.getRequirementById(1).getAcceptanceTestId(), 1, "1", "smims2",
				Rejection.DUPLICATE);
		m.executeCommand(1, c2);
		assertEquals("Summary 2", m.getRequirementById(1).getSummary());
		assertEquals("Rejected", m.getRequirementById(1).getStateName());
		assertEquals("Acceptance test 2",
				m.getRequirementById(1).getAcceptanceTestId());
		assertEquals(null, m.getRequirementById(1).getEstimate());
		assertEquals(0, m.getRequirementById(1).getPriority());
		assertEquals(null, m.getRequirementById(1).getDeveloper());
		assertEquals("Duplicate",
				m.getRequirementById(1).getRejectionReasonString());
	}

	/**
	 * test getRequirementListAsArray
	 */
	@Test
	public void getRequirementListAsArrayTest()
	{
		Object[][] reqArray = m.getRequirementListAsArray();
		assertEquals(0, reqArray.length);

		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");
		Command c2 = new Command(CommandValue.REJECT,
				m.getRequirementById(1).getSummary(),
				m.getRequirementById(1).getAcceptanceTestId(), 1, "1", "smims2",
				Rejection.DUPLICATE);
		m.executeCommand(1, c2);

		// create new array
		reqArray = m.getRequirementListAsArray();
		assertEquals(2, reqArray.length);

		assertEquals(0, reqArray[0][0]);
		assertEquals(Requirement.SUBMITTED_NAME, reqArray[0][1]);
		assertEquals("Summary 1", reqArray[0][2]);

		assertEquals(1, reqArray[1][0]);
		assertEquals(Requirement.REJECTED_NAME, reqArray[1][1]);
		assertEquals("Summary 2", reqArray[1][2]);
	}

	/**
	 * test create new requirements list
	 */
	@Test
	public void createNewRequirementListTest()
	{
		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");
		assertEquals(2, m.getRequirementListAsArray().length);

		// create new list
		m.createNewRequirementsList();
		assertEquals(0, m.getRequirementListAsArray().length);
	}

	/**
	 * test save requirements to a file
	 */
	@Test
	public void saveRequirementsToFileTest()
	{
		m.addRequirement("Summary 1", "Acceptance test 1");
		m.addRequirement("Summary 2", "Acceptance test 2");
		m.saveRequirementsToFile("test_files/save_file.txt");
		checkFiles("test_files/expected_save_file.txt",
				"test_files/save_file.txt");
		// save to bad file path
		try
		{
			m.saveRequirementsToFile("");
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Error saving to file.", e.getMessage());
		}
	}

	/**
	 * test load requirements from a file
	 */
	@Test
	public void loadRequirementsFromFileTest()
	{
		// load from bad file path
		try
		{
			m.loadRequirementsFromFile("");
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Error loading to file.", e.getMessage());
			assertEquals(0, m.getRequirementListAsArray().length);
		}
		m.loadRequirementsFromFile("test_files/expected_save_file.txt");
		assertEquals(2, m.getRequirementListAsArray().length);
		
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile
	 *            expected output
	 * @param actFile
	 *            actual output
	 */
	private void checkFiles(String expFile, String actFile)
	{
		try
		{
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));

			while (expScanner.hasNextLine())
			{
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		}
		catch (IOException e)
		{
			fail("Error reading files.");
		}
	}
}
