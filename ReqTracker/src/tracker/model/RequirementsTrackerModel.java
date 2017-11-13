package tracker.model;

import java.util.Arrays;

import edu.ncsu.csc216.tracker.xml.RequirementIOException;
import edu.ncsu.csc216.tracker.xml.RequirementsReader;
import edu.ncsu.csc216.tracker.xml.RequirementsWriter;
import tracker.requirement.Command;
import tracker.requirement.Requirement;

/**
 * model for requirement list
 * 
 * maintains singleton instance of RequirementTrackerModel
 * 
 * provides method to interact with GUI
 * 
 * @author Summer Mims
 */
public class RequirementsTrackerModel
{
	/** RequirementsTrackerModel instance to be singleton */
	private static RequirementsTrackerModel instance;
	/**
	 * list to hold requirements
	 */
	private RequirementsList reqList;

	/**
	 * 
	 * Constructs a new RequirementsTrackerModel object.
	 * 
	 * initialize new reqList
	 */
	public RequirementsTrackerModel()
	{
		reqList = new RequirementsList();
	}

	/**
	 * 
	 * public method to retrieve instance of RequirementsTrackerModel
	 * 
	 * ensures singleton model and allows only one instance
	 * 
	 * returns instance if one is created if one does not exist, creates an
	 * instance
	 *
	 * @return instance the instance of RequirementsTrackerModel
	 */
	public static RequirementsTrackerModel getInstance()
	{
		if (instance == null)
		{
			instance = new RequirementsTrackerModel();
		}
		return instance;
	}

	/**
	 * 
	 * write requirements to a file
	 *
	 * @param filename
	 *            file to save requirements list to
	 * @throws IllegalArgumentException
	 *             when there is an error writing to the file
	 */
	public void saveRequirementsToFile(String filename)
	{
		try
		{
			RequirementsWriter fileOut = new RequirementsWriter(filename);
			for (Requirement r : reqList.getRequirements())
			{
				fileOut.addReq(r.getXMLReq());
			}
			fileOut.marshal();
		}
		catch (RequirementIOException e)
		{
			throw new IllegalArgumentException("Error saving to file.");
		}
	}

	/**
	 * 
	 * read requirement from file
	 *
	 * @param filename
	 *            the file to load requirements from
	 */
	public void loadRequirementsFromFile(String filename)
	{
		try
		{
			RequirementsReader fileOut = new RequirementsReader(filename);
			reqList.addXMLReqs(fileOut.getReqs());
		}
		catch (RequirementIOException e)
		{
			throw new IllegalArgumentException("Error loading to file.");
		}
	}

	/**
	 * 
	 * create a new empty requirements list
	 *
	 */
	public void createNewRequirementsList()
	{
		reqList = new RequirementsList();
	}

	/**
	 * 
	 * create array of requirements columns for the requirementId, currentState,
	 * and summary
	 *
	 * @return Object[][] the array representation of the requirement list
	 */
	public Object[][] getRequirementListAsArray()
	{
		int size = reqList.getRequirements().size();
		Object[][] reqArray = new Object[size][3];
		if (size == 0)
		{
			Arrays.fill(reqArray, "");
			return reqArray;
		}
		for (int i = 0; i < size; i++)
		{
			reqArray[i][0] = reqList.getRequirements().get(i)
					.getRequirementId();
			reqArray[i][1] = reqList.getRequirements().get(i).getStateName();
			reqArray[i][2] = reqList.getRequirements().get(i).getSummary();
		}
		return reqArray;
	}

	/**
	 * 
	 * retrieve a requirement by it unique id
	 *
	 * @param id
	 *            the requirement id
	 * @return Requirement the requirement associated with that id
	 */
	public Requirement getRequirementById(int id)
	{
		return reqList.getRequirementById(id);
	}

	/**
	 * 
	 * change the state of a requirement in a list by identifying it by its id
	 *
	 * @param id
	 *            the id of the requirement
	 * @param c
	 *            the command value to change the state of the requirement
	 *            associated with the id
	 */
	public void executeCommand(int id, Command c)
	{
		getRequirementById(id).update(c);
	}

	/**
	 * 
	 * delete a requirement in a list by finding it unique id
	 *
	 * @param id
	 *            the requirement id to delete
	 */
	public void deleteRequirementById(int id)
	{
		reqList.deleteRequirementById(id);
	}

	/**
	 * 
	 * create and add a new requirement with its summary and acceptance test id.
	 * A new unique requirement id is created at this time for the requirement.
	 *
	 * @param summary
	 *            summary description of the requirement
	 * @param acceptanceTestId
	 *            test id for tracking acceptance
	 */
	public void addRequirement(String summary, String acceptanceTestId)
	{
		reqList.addRequirement(summary, acceptanceTestId);
	}
}