package student_scheduler.course;

/**
 * Class to maintain Course state and behaviors
 * 
 * each Course has a unique name and section number
 * 
 * Inherits from parent class Activity
 * 
 * Activity maintains title, meetingDays, startTime, endTime
 * 
 * Course maintains name, section, credits, instructorId as they are all unique
 * to Course
 * 
 * @author (Summer Mims)
 *
 */
public class Course extends Activity
{
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Section length */
	private static final int SECTION_LENGTH = 3;
	/** Course's minimum name length */
	private static final int MIN_NAME_LENGTH = 4;
	/** Course's maximum name length */
	private static final int MAX_NAME_LENGTH = 6;
	/** Course's minimum credits */
	private static final int MIN_CREDITS = 1;
	/** Course's maximum credits */
	private static final int MAX_CREDITS = 5;

	/**
	 * Constructs a new Course object. calls on super class constructor for
	 * Activity
	 * 
	 * @param name
	 *            Course's name
	 * @param title
	 *            Course's title
	 * @param section
	 *            Course's section
	 * @param credits
	 *            Course's credits
	 * @param instructorId
	 *            Course's instructor id
	 * @param meetingDays
	 *            Course's meeting days
	 * @param startTime
	 *            Course's starting time
	 * @param endTime
	 *            Course's ending time
	 */
	public Course(String name, String title, String section, int credits,
			String instructorId, String meetingDays, int startTime, int endTime)
	{
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits,
	 * instructorId, and meetingDays for courses that are arranged. calls on
	 * default constructor if times are arranged with preset values
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits,
			String instructorId, String meetingDays)
	{
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or length is less than 4 or greater than 6
	 */
	private void setName(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * return the Course's section
	 * 
	 * @return the section
	 */
	public String getSection()
	{
		return section;
	}

	/**
	 * set the Course's section if the field is null, empty string, or not three
	 * digits long, an IllegalArgumentsException is thrown
	 * 
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section)
	{
		if (section == null)
		{
			throw new IllegalArgumentException();
		}
		if (section.length() != SECTION_LENGTH)
		{
			throw new IllegalArgumentException(
					"Section must be three digits long.");
		}
		for (char ch : section.toCharArray())
		{
			if (!Character.isDigit(ch))
			{
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}

	/**
	 * returns the Course's credit hours
	 * 
	 * @return the credits
	 */
	public int getCredits()
	{
		return credits;
	}

	/**
	 * sets the Course's credit hours if field is less than 1 or grater than 5,
	 * throws IllegalArgumentException
	 * 
	 * @param credits
	 *            the credits to set
	 */
	public void setCredits(int credits)
	{
		if (credits < MIN_CREDITS)
		{
			throw new IllegalArgumentException();
		}
		if (credits > MAX_CREDITS)
		{
			throw new IllegalArgumentException();
		}

		this.credits = credits;
	}

	/**
	 * returns the Course's instructor id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId()
	{
		return instructorId;
	}

	/**
	 * sets the Course's instructor id if null or empty string, throws
	 * IllegalArgumentException
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 */
	public void setInstructorId(String instructorId)
	{
		if (instructorId == null)
		{
			throw new IllegalArgumentException();
		}
		if (instructorId.length() == 0)
		{
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * sets the Course's meeting days
	 * 
	 * if contains character other than 'MTWHFA' throws illegal argument
	 * exception
	 * 
	 * if contains 'A', it must be the only character or throws illegal argument
	 * exception
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	@Override
	public void setMeetingDays(String meetingDays)
	{
		if (meetingDays == null)
		{
			throw new IllegalArgumentException();
		}
		if (meetingDays.length() == 0)
		{
			throw new IllegalArgumentException();
		}

		meetingDays = meetingDays.toUpperCase();

		if (meetingDays.equals("A"))
		{
			if (getStartTime() != 0 || getEndTime() != 0)
			{
				throw new IllegalArgumentException();
			}
		}
		else
		{
			boolean valid = true;
			char[] letters = meetingDays.toCharArray();
			for (char ch : letters)
			{
				valid = (ch == 'M') || (ch == 'T') || (ch == 'W') || (ch == 'H')
						|| (ch == 'F');
				if (!valid)
				{
					throw new IllegalArgumentException();
				}
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * checks if Activity is duplicate of this object
	 * 
	 * @param activity
	 *            the Activity to compare to this
	 * @return boolean returns true if duplicate
	 */
	public boolean isDuplicate(Activity activity)
	{
		boolean duplicate = false;
		if (activity.getClass() == this.getClass())
		{
			Course c = (Course) activity;

			if (c.getName().equals(this.getName()))
			{
				// duplicate course
				duplicate = true;
			}
		}
		return duplicate;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString()
	{
		if (getMeetingDays().equals("A"))
		{

			return name + "," + getTitle() + "," + section + "," + credits + ","
					+ instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + ","
				+ instructorId + "," + getMeetingDays() + "," + getStartTime()
				+ "," + getEndTime();
	}

	/**
	 * generates hashCode for Course using all fields
	 * 
	 * @return hashCode hashCode for Course
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + instructorId.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + section.hashCode();
		return result;
	}

	/**
	 * Compares given object to this object for equality
	 * 
	 * @return true is all fields are the same for both objects
	 * 
	 * @param obj
	 *            the Object to compare
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		else if (!instructorId.equals(other.instructorId))
			return false;
		else if (!name.equals(other.name))
			return false;
		else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * returns array of Course with name, section, title, and meeting days
	 * string
	 * 
	 * @return Array array for Course
	 */
	@Override
	public String[] getShortDisplayArray()
	{
		String[] courseArray = new String[4];
		courseArray[0] = name;
		courseArray[1] = section;
		courseArray[2] = getTitle();
		courseArray[3] = getMeetingString();

		return courseArray;
	}

	/**
	 * returns array of Course with name, section, title, credits, instructorID,
	 * meeting days string, and empty string
	 * 
	 * @return Array the array for Course
	 */
	@Override
	public String[] getLongDisplayArray()
	{
		String[] courseArray = new String[7];
		courseArray[0] = name;
		courseArray[1] = section;
		courseArray[2] = getTitle();
		courseArray[3] = String.valueOf(credits);
		courseArray[4] = instructorId;
		courseArray[5] = getMeetingString();
		courseArray[6] = "";

		return courseArray;
	}

}
