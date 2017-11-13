/**
 * 
 * 
 * @author Summer Mims
 */
package student_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import student_scheduler.course.Activity;
import student_scheduler.course.ConflictException;
import student_scheduler.course.Course;
import student_scheduler.course.Event;
import student_scheduler.io.ActivityRecordIO;
import student_scheduler.io.CourseRecordIO;

/**
 * Class to maintain StudentScheduler state and behaviors
 * 
 * Creates schedule to add Activities to
 * 
 * creates CourseCatalog to choose courses from
 * 
 * @author (Summer Mims)
 *
 */

public class StudentScheduler
{

	private ArrayList<Course> courseCatalog;
	private ArrayList<Activity> schedule;
	private String title;

	/**
	 * Constructs a new StudentScheduler object. construct course catalog array
	 * list construct schedule array list set title to default "My Schedule" add
	 * Course objects from input file
	 * 
	 * throws illegal argument exception if error
	 * 
	 * @param fileName
	 *            file to export to
	 */
	public StudentScheduler(String fileName)
	{
		setTitle("My Schedule");
		// construct course catalog array list
		courseCatalog = new ArrayList<Course>();
		// construct schedule array list
		schedule = new ArrayList<Activity>();
		// add Course objects from input file,throws illegal argument exception
		try
		{
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		}
		catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * get specified Course from courseCatalog if Course does not exist, return
	 * null
	 * 
	 * @param name
	 *            the name of the Course to find
	 * @param section
	 *            the section of the Course to find
	 * @return Course
	 */
	public Course getCourseFromCatalog(String name, String section)
	{
		for (int j = 0; j < courseCatalog.size(); j++)
		{
			if (section.equals(courseCatalog.get(j).getSection())
					&& name.equals(courseCatalog.get(j).getName()))
			{
				return courseCatalog.get(j);
			}
		}
		// if course not found return null
		return null;
	}

	/**
	 * add course to student's schedule returns true
	 * 
	 * if Course can be added throws illegal argument exception
	 * 
	 * if Course with same name exists in schedule if course is not in catalog,
	 * return false
	 *
	 * @param name
	 *            the name of Course
	 * @param section
	 *            the section of Course
	 * @return boolean returns true if Course is added
	 * @throws IllegalArgumentException
	 *             when time conflict
	 */
	public boolean addCourse(String name, String section)
	{
		boolean findCourse = false;
		Course c = null;
		for (int j = 0; j < courseCatalog.size(); j++)
		{
			if (courseCatalog.get(j).getName().equals(name)
					&& courseCatalog.get(j).getSection().equals(section))
			{
				findCourse = true;
				c = courseCatalog.get(j);
			}
		}
		// if course is in catalog, check in schedule
		if (c != null)
		{

			for (int i = 0; i < schedule.size(); i++)
			{

				// check if already in schedule
				if (schedule.get(i).isDuplicate(c))
				{
					throw new IllegalArgumentException(
							"You are already enrolled in " + name);
				}
				// check for time conflict
				Activity s = schedule.get(i);
				try
				{
					s.checkConflict(c);
				}
				catch (ConflictException e)
				{
					throw new IllegalArgumentException(
							"The course cannot be added due to a conflict.");
				}
			}

			// add to schedule
			schedule.add(c);
			findCourse = true;
		}
		return findCourse;
	}

	/**
	 * add event to Schedule
	 * 
	 * if duplicate event throw IllegalArgumentexception
	 * 
	 * @param title
	 *            the Event title
	 * 
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
	 * @throws IllegalArgumentException
	 *             if Event is duplicate
	 */
	public void addEvent(String title, String meetingDays, int startTime,
			int endTime, int weeklyRepeat, String eventDetails)
	{
		Event e1 = new Event(title, meetingDays, startTime, endTime,
				weeklyRepeat, eventDetails);
		for (int i = 0; i < schedule.size(); i++)
		{
			if (e1.isDuplicate(schedule.get(i)))
			{
				throw new IllegalArgumentException(
						"You have already created an event called " + title);
			}
			// check for time conflict
			Activity s = schedule.get(i);
			try
			{
				s.checkConflict(e1);
			}
			catch (ConflictException e)
			{
				throw new IllegalArgumentException(
						"The event cannot be added due to a conflict.");
			}
		}
		schedule.add(e1);
	}

	/**
	 * removes Activity from student's schedule
	 * 
	 * @param idx
	 *            the index of the Activity
	 *
	 * @return boolean return true is Activity is removed
	 */
	public boolean removeActivity(int idx)
	{
		boolean removed = false;
		try
		{

			for (int i = 0; i < schedule.size(); i++)
			{
				if (schedule.get(idx).isDuplicate(schedule.get(i)))
				{
					schedule.remove(i);
					removed = true;
				}
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			// skip line
		}
		return removed;
	}

	/**
	 * creates empty ArrayList and has schedule field work with new ArrayList
	 */
	public void resetSchedule()
	{
		schedule = new ArrayList<Activity>();
	}

	/**
	 * returns 2D String array of catalog row for each Course three columns for
	 * name, section, and title if no Courses in catalog, empty String array is
	 * returned
	 *
	 * @return courseCatalogArray the courseCatalog
	 */
	public String[][] getCourseCatalog()
	{
		String[][] courseCatalogArray = new String[courseCatalog.size()][4];
		if (courseCatalog.size() == 0)
		{
			Arrays.fill(courseCatalogArray, "");
			return courseCatalogArray;
		}
		for (int i = 0; i < courseCatalog.size(); i++)
		{
			courseCatalogArray[i][0] = courseCatalog.get(i).getName();
			courseCatalogArray[i][1] = courseCatalog.get(i).getSection();
			courseCatalogArray[i][2] = courseCatalog.get(i).getTitle();
			courseCatalogArray[i][3] = courseCatalog.get(i).getMeetingString();
		}
		return courseCatalogArray;
	}

	/**
	 * returns 2D String array of student schedule row for each Course three
	 * columns for name, section, and title if no Courses in schedule, empty
	 * String array is returned
	 *
	 * @return scheduleArray the schedule of Activities
	 */
	public String[][] getScheduledActivities()
	{
		String[][] scheduleArray = new String[schedule.size()][3];
		if (schedule.size() == 0)
		{
			Arrays.fill(scheduleArray, "");
			return scheduleArray;
		}
		for (int i = 0; i < schedule.size(); i++)
		{
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduleArray;
	}

	/**
	 * returns 2D String array of student schedule row for each Course six
	 * columns for name, section, title, credits, instructorId, meetingDays
	 * String if no Courses in schedule, empty String array is returned
	 *
	 * @return scheduleFullArray the full array of Activities
	 */
	public String[][] getFullScheduledActivities()
	{
		String[][] scheduleArray = new String[schedule.size()][6];
		if (schedule.size() == 0)
		{
			Arrays.fill(scheduleArray, "");
			return scheduleArray;
		}
		for (int i = 0; i < schedule.size(); i++)
		{
			scheduleArray[i] = schedule.get(i).getLongDisplayArray();
		}
		return scheduleArray;
	}

	/**
	 * exports schedule to file uses writeCourseRecors() to export file throws
	 * IllegalArgumentException if IOException is thrown; error message "The
	 * file cannot be saved."
	 *
	 * @param fileName
	 *            file name to export to
	 * @throws IllegalArgumentException
	 *             if file cannot be saved
	 */
	public void exportSchedule(String fileName)
	{
		try
		{
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		}
		catch (IllegalArgumentException e)
		{
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		catch (IOException e)
		{
			// skip line
		}
	}

	/**
	 * get title of schedule
	 *
	 * @return title of schedule
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * set title of schedule throw illegal exception if null or empty String
	 *
	 * @param title
	 *            title of schedule
	 */
	public void setTitle(String title)
	{
		if (title == null)
		{
			throw new IllegalArgumentException("Title cannot be null.");
		}
		if (title.length() == 0)
		{
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

}
