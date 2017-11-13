package student_scheduler.course;

/**
 * Event subclass of Activity to maintain Event behaviors and state
 * 
 * each Event has a unique title
 * 
 * subclass of Activity which maintains title, meetingDays, startTime, endTime
 * eventDetails and weeklyRepeat are unique to Event
 * 
 * @author Summer Mims
 */

public class Event extends Activity
{
	/** Event's weekly repeating number */
	private int weeklyRepeat;
	/** Event's details */
	private String eventDetails;

	/**
	 * Constructs a new Event object. use call to super Activity
	 * 
	 * @param title
	 *            the Event title
	 * @param meetingDays
	 *            the Event meeting days
	 * @param startTime
	 *            the Event start time
	 * @param endTime
	 *            the Event end time
	 * @param weeklyRepeat
	 *            the Event times to repeat weekly
	 * @param eventDetails
	 *            the Event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime,
			int weeklyRepeat, String eventDetails)
	{
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * get weeklyRepeat number of times Event repeats weekly
	 * 
	 * @return the weeklyRepeat the weeklyRepeat for Event
	 */
	public int getWeeklyRepeat()
	{
		return weeklyRepeat;
	}

	/**
	 * set Event repeat
	 * 
	 * @param weeklyRepeat
	 *            the weeklyRepeat to set
	 * @throws IllegalArgumentException
	 *             if weeklyRepeat is less than 1 or greater than 4
	 */
	public void setWeeklyRepeat(int weeklyRepeat)
	{
		String e = "Invalid weekly repeat";
		if (weeklyRepeat < 1 || weeklyRepeat > 4)
		{
			throw new IllegalArgumentException(e);
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * get the Event details
	 * 
	 * @return the eventDetails the Event details
	 */
	public String getEventDetails()
	{
		return eventDetails;
	}

	/**
	 * set the Event details
	 * 
	 * @param eventDetails
	 *            the eventDetails to set
	 * @throws IllegalArgumentException
	 *             if null
	 */
	public void setEventDetails(String eventDetails)
	{
		String e = "Invalid event details";
		if (eventDetails == null)
		{
			throw new IllegalArgumentException(e);
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * sets the Event's meeting days
	 * 
	 * if contains character other than 'MTWHFSUA' throws illegal argument
	 * exception
	 * 
	 * if contains 'A', it must be the only character or throws illegal argument
	 * exception
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 * @throws IllegalArgumentException
	 *             if invalid character entered
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

		boolean valid = true;
		char[] letters = meetingDays.toCharArray();
		for (char ch : letters)
		{
			valid = (ch == 'M') || (ch == 'T') || (ch == 'W') || (ch == 'H')
					|| (ch == 'F') || (ch == 'S') || (ch == 'U');
			if (!valid)
			{
				throw new IllegalArgumentException();
			}
		}

		super.setMeetingDays(meetingDays);
	}

	/**
	 * check if Event is duplicate in comparison to this object
	 * 
	 * @param activity
	 *            the activity to compare to this object
	 * 
	 * @return boolean returns true is Event is duplicate
	 */
	public boolean isDuplicate(Activity activity)
	{
		boolean duplicate = false;
		if (activity.getClass() == this.getClass())
		{
			Event c = (Event) activity;
			if (c.getTitle().equals(this.getTitle()))
			{
				// duplicate event
				duplicate = true;
			}
		}
		return duplicate;
	}

	/**
	 * returns array of Event with empty string, empty string, title, meeting
	 * string
	 * 
	 * @return Array the array for Event
	 */
	@Override
	public String[] getShortDisplayArray()
	{
		String[] eventArray = new String[4];
		eventArray[0] = "";
		eventArray[1] = "";
		eventArray[2] = getTitle();
		eventArray[3] = getMeetingString();

		return eventArray;
	}

	/**
	 * returns array of Event with empty string, empty string, title, empty
	 * string, empty string, meeting string, eventDetails
	 * 
	 * @return Array the array for Event
	 */
	@Override
	public String[] getLongDisplayArray()
	{
		String[] eventArray = new String[7];
		eventArray[0] = "";
		eventArray[1] = "";
		eventArray[2] = getTitle();
		eventArray[3] = "";
		eventArray[4] = "";
		eventArray[5] = getMeetingString();
		eventArray[6] = eventDetails;

		return eventArray;
	}

	/**
	 * create String representation of Event
	 * 
	 * @return String the meeting String for Event
	 */
	@Override
	public String getMeetingString()
	{
		// TODO Auto-generated method stub
		return super.getMeetingString() + " (every " + weeklyRepeat + " weeks)";
	}

	/**
	 * create String representation of Event
	 * 
	 * @return String the String of Event
	 */
	@Override
	public String toString()
	{
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + ","
				+ getEndTime() + "," + weeklyRepeat + "," + eventDetails;
	}

}
