package tracker.model;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.tracker.xml.Req;
import tracker.requirement.Command;
import tracker.requirement.Requirement;

/**
 * class to maintain a list of requirements and associated behaviors
 * 
 * @author Summer Mims
 */
public class RequirementsList
{
	/**
	 * private instance variable to hold list of requirements
	 */
	private List<Requirement> reqList;

	/**
	 * 
	 * Constructs a new RequirementsList object. creates a new requirement array
	 * list resets counter to zero
	 */
	public RequirementsList()
	{
		reqList = new ArrayList<Requirement>();
		Requirement.setCounter(0);
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
	 * @return requirement id of requirement added to list
	 */
	public int addRequirement(String summary, String acceptanceTestId)
	{
		Requirement r = new Requirement(summary, acceptanceTestId);
		reqList.add(r);
		return r.getRequirementId();
	}

	/**
	 * 
	 * add a list of requirement from an xml file
	 * 
	 * when adding from XML file, set id counter to max requirement id from the
	 * file
	 *
	 * @param rList
	 *            the list of requirements to add from XML file
	 */
	public void addXMLReqs(List<Req> rList)
	{
		int maxId = 0;

		for (int i = 0; i < rList.size(); i++)
		{
			// create and add requirement to reqList
			Requirement r = new Requirement(rList.get(i));
			reqList.add(r);
			// set to maximum
			if (r.getRequirementId() > maxId)
			{
				maxId = r.getRequirementId();
			}
		}
		// reset counter
		Requirement.setCounter(maxId);
	}

	/**
	 * 
	 * get list of requirements
	 *
	 * @return List<Requirement> the list of requirements
	 * 
	 */
	public List<Requirement> getRequirements()
	{
		return reqList;
	}

	/**
	 * 
	 * retrieve a requirement by its unique id from a requirement list
	 * 
	 * return null if no requirement is located
	 *
	 * @param id
	 *            the requirement id of the requirement to retrieve
	 * @return the requirement associated with that id
	 */
	public Requirement getRequirementById(int id)
	{
		for (int i = 0; i < reqList.size(); i++)
		{
			if (reqList.get(i).getRequirementId() == id)
			{
				return reqList.get(i);
			}
		}
		return null;
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
		if (getRequirementById(id) != null)
		{
			getRequirementById(id).update(c);
		}
	}

	/**
	 * 
	 * delete a requirement by its unique id from a requirement list
	 *
	 * @param id
	 *            the requirement id of the requirement to retrieve
	 */
	public void deleteRequirementById(int id)
	{
		for (int i = 0; i < reqList.size(); i++)
		{
			if (reqList.get(i).getRequirementId() == id)
			{
				reqList.remove(i);
			}
		}
	}

}