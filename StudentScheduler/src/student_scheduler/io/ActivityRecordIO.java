package student_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import student_scheduler.course.Activity;

/**
 * class to maintain output of Activities
 * 
 * supporting output class for CourseRecordIO
 * 
 * 
 * 
 * @author Summer Mims
 */
public class ActivityRecordIO
{

	/**
	 * Writes the given list of activities to
	 * 
	 * @param fileName
	 *            file name to write activities to
	 * @param activities
	 *            array list of activities
	 * @throws IOException
	 *             if cannot write to file
	 */
	public static void writeActivityRecords(String fileName,
			ArrayList<Activity> activities) throws IOException
	{
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < activities.size(); i++)
		{
			fileWriter.println(activities.get(i).toString());
		}

		fileWriter.close();

	}

}
