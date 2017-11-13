
package student_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import student_scheduler.course.Course;

/**
 * class for input and output of Course records as needed from StudentScheduler
 * 
 * Reads Course records from text files. Uses helper method readCourse to read
 * each line
 * 
 * Writes a set of CourseRecords to a file.
 * 
 * @author Summer Mims
 */
public class CourseRecordIO
{

	/**
	 * Reads course records from a file and generates a list of valid Courses.
	 * Any invalid Courses are ignored. If the file to read cannot be found or
	 * the permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException
	 *             if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName)
			throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();

		while (fileReader.hasNextLine())
		{
			try
			{
				Course course = readCourse(fileReader.nextLine());

				boolean duplicate = false;
				if (course != null)
				{
					for (int i = 0; i < courses.size(); i++)
					{
						Course c = courses.get(i);
						if (c.getName().equals(course.getName())
								&& c.getSection().equals(course.getSection()))
						{
							// duplicate course
							duplicate = true;
						}
					}
					if (!duplicate)
					{
						courses.add(course);
					}
				}
			}
			catch (IllegalArgumentException e)
			{
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * create new Course object from file line
	 * 
	 * @param nextLine
	 * @return
	 */
	private static Course readCourse(String nextLine)
	{
		Scanner courseReader = new Scanner(nextLine);
		courseReader.useDelimiter(",");
		Course c = null;
		try
		{
			String name = courseReader.next();
			String title = courseReader.next();
			String section = courseReader.next();
			int credits = courseReader.nextInt();
			String instructorId = courseReader.next();
			String meetingDays = courseReader.next();
			if (courseReader.hasNext())
			{
				int startTime = courseReader.nextInt();
				int endTime = courseReader.nextInt();
				c = new Course(name, title, section, credits, instructorId,
						meetingDays, startTime, endTime);
			}
			else
			{
				c = new Course(name, title, section, credits, instructorId,
						meetingDays);
			}

			courseReader.close();
		}
		catch (NoSuchElementException e)
		{
			// skip line
		}
		catch (IllegalArgumentException e)
		{
			// skip line
		}
		return c;
	}
}
