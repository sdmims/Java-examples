package student_scheduler.course;

/**
 * Super class to provide structure for activities for StudentScheduler
 * 
 * maintains title, meetingDays, startTime, endTime state for Activity
 * 
 * provides abstract methods isDuplicate, getShortDisplayArray,
 * getLongDisplayArray
 * 
 * @author Summer Mims
 */

public abstract class Activity implements Conflict
{

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Activity's upper time */
	public final static int UPPER_TIME = 2400;
	/** Activity's upper hour */
	public final static int UPPER_HOUR = 60;

	/**
	 * Constructs a new Activity object.
	 * 
	 * @param title
	 *            Activity's title
	 * @param meetingDays
	 *            Activity's meeting days
	 * @param startTime
	 *            Activity's starting time
	 * @param endTime
	 *            Activity's ending time
	 */
	public Activity(String title, String meetingDays, int startTime,
			int endTime)
	{
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * returns the Activity's title
	 * 
	 * @return title the Activity's title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * sets the Activity title If the name is null, an IllegalArgumentException
	 * is thrown.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		if (title == null)
		{
			throw new IllegalArgumentException();
		}
		if (title.length() == 0)
		{
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * returns the Activity's meeting days
	 * 
	 * @return meetingDays the Activity meeting days
	 */
	public String getMeetingDays()
	{
		return meetingDays;
	}

	/**
	 * sets the Activity's meeting days
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays)
	{
		this.meetingDays = meetingDays;
	}

	/**
	 * returns the Activity's start time
	 * 
	 * @return startTime the Activity start time
	 */
	public int getStartTime()
	{
		return startTime;
	}

	/**
	 * returns the Activity's end time
	 * 
	 * @return endTime the Activity end time
	 */
	public int getEndTime()
	{
		return endTime;
	}

	/**
	 * sets the Activity's start and end time
	 * 
	 * if meeting days is 'A' end and start time must be 0 if either time is
	 * less than 0000 or greater than 2359, throws illegal argument exception
	 * 
	 * if end time is less than start time, throws illegal argument exception if
	 * minutes is greater than 59
	 * 
	 * @param startTime
	 *            the start time to set
	 * @param endTime
	 *            the endTime to set
	 * @throws IllegalArgumentException
	 *             if invalid character is entered
	 */
	public void setActivityTime(int startTime, int endTime)
	{
		if (meetingDays.equals("A"))
		{
			if (startTime != 0)
			{
				throw new IllegalArgumentException();
			}
			if (endTime != 0)
			{
				throw new IllegalArgumentException();
			}
		}
		else
		{
			if (startTime < 0)
			{
				throw new IllegalArgumentException();
			}
			if (startTime > 2359)
			{
				throw new IllegalArgumentException();
			}
			if (endTime < 0)
			{
				throw new IllegalArgumentException();
			}
			if (endTime > 2359)
			{
				throw new IllegalArgumentException();
			}
			if (endTime < startTime)
			{
				throw new IllegalArgumentException();
			}
			String startTimeString = String.valueOf(startTime);
			int startLen = startTimeString.length();
			String startTimeMinutesString = startTimeString
					.substring(startLen - 2);
			if (Integer.parseInt(startTimeMinutesString) > 59)
			{
				throw new IllegalArgumentException();
			}

			String endTimeString = String.valueOf(endTime);
			int endLen = endTimeString.length();
			String endTimeMinutesString = endTimeString.substring(endLen - 2);
			if (Integer.parseInt(endTimeMinutesString) > 59)
			{
				throw new IllegalArgumentException();
			}

		}

		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * generate Meeting string with standard time format
	 *
	 * @return meetingString the String with days and times
	 */
	public String getMeetingString()
	{
		if (meetingDays.equals("A"))
		{
			return "Arranged";
		}
		int startTimeDisplay = startTime;
		String startStandardTime = "AM";
		int endTimeDisplay = endTime;
		String endStandardTime = "AM";

		if (startTime > 1159)
		{
			startStandardTime = "PM";
		}
		if (startTime > 1259)
		{
			startTimeDisplay = startTime - 1200;
		}
		if (endTime > 1159)
		{
			endStandardTime = "PM";
		}
		if (endTime > 1259)
		{
			endTimeDisplay = endTime - 1200;
		}

		String startTimeString = String.valueOf(startTimeDisplay);
		int startLen = startTimeString.length();
		String startTimeMinutesString = startTimeString.substring(startLen - 2);
		String startTimeHoursString = startTimeString.substring(0,
				startLen - 2);

		String endTimeString = String.valueOf(endTimeDisplay);
		int endLen = endTimeString.length();
		String endTimeMinutesString = endTimeString.substring(endLen - 2);
		String endTimeHoursString = endTimeString.substring(0, endLen - 2);

		return meetingDays + " " + startTimeHoursString + ":"
				+ startTimeMinutesString + startStandardTime + "-"
				+ endTimeHoursString + ":" + endTimeMinutesString
				+ endStandardTime;
	}

	/**
	 * abstract method to check if Activity is duplicate
	 * 
	 * @param activity
	 *            the Activity to check
	 * @return boolean returns true if Activity is duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * generates hashCode for Activity using all fields
	 * 
	 * @return hashCode hashCode for Activity
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + meetingDays.hashCode();
		result = prime * result + startTime;
		result = prime * result + title.hashCode();
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * abstract method that returns String array with Activity information
	 * 
	 * @return String[] String array of Activity
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * abstract method returns String array with Activity information
	 * 
	 * @return String[] String array of ACtivity
	 * 
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * abstract method to check new activity to add to schedule for any timing
	 * conflicts
	 * 
	 * @param possibleConflictingActivity
	 *            the Activity to check for conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity)
			throws ConflictException
	{
		// this meetingDays
		String a1Days = this.getMeetingDays();
		// possible activity's meetingDays
		String a2Days = possibleConflictingActivity.getMeetingDays();

		// check for arranged first
		// if true, exit method, there can be conflict
		if (a1Days.equals("A") || a2Days.equals("A"))
		{
			return;
		}

		// this startTime
		int a1Start = this.getStartTime();
		// this endTime
		int a1End = this.getEndTime();
		// possible activity startTime
		int a2Start = possibleConflictingActivity.getStartTime();
		// possible activity endTime
		int a2End = possibleConflictingActivity.getEndTime();

		// store each meeting days as character array
		char[] a1letters = a1Days.toCharArray();
		char[] a2letters = a2Days.toCharArray();

		// check letter by letter for match
		for (char ch1 : a1letters)
		{
			for (char ch2 : a2letters)
			{
				// if the character matches check for time overlaps
				if (ch1 == ch2)
				{
					for (int i = a1Start; i < a1End + 1; i++)
					{
						if (a2Start == i || a2End == i)
						{
							throw new ConflictException();
						}
					}
					for (int i = a2Start; i < a2End + 1; i++)
					{
						if (a1Start == i || a1End == i)
						{
							throw new ConflictException();
						}
					}
					/*
					 * // any time equal; should cover one minute overlaps if
					 * (a2Start == a1Start || a2Start == a1End) { throw new
					 * ConflictException(); } else if (a2End == a1Start || a2End
					 * == a1End) { throw new ConflictException(); } // check if
					 * possible activity start time is in middle of // this else
					 * if (a2Start > a1Start && a2Start < a1End) { throw new
					 * ConflictException(); } // check is possible activity end
					 * time is in middle of this else if (a2End > a1Start &&
					 * a2End < a1End) { throw new ConflictException(); } else if
					 * (a1Start > a2Start && a1Start < a2End) { throw new
					 * ConflictException(); } // check is possible activity end
					 * time is in middle of this else if (a1End > a2Start &&
					 * a1End < a2End) { throw new ConflictException(); }
					 */
				}
			}
		}
	}

}