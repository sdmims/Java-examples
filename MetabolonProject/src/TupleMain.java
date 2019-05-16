import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Summer Mims
 */

public class TupleMain
{
	public static ArrayList<String> checkFeed(
			ArrayList<Tuple<String, MessageList>> tupleList, int days)
	{
		ArrayList<String> inactiveCompanies = new ArrayList<String>();
		// for each tuple, check
		for (Tuple<String, MessageList> t : tupleList)
		{
			if (t.getMessages().getLastMessage().getPubDate()
					.compareTo(LocalDate.now().minusDays(days)) > -1)
			{
				inactiveCompanies.add(t.getCompany());
			}
		}
		return inactiveCompanies;
	}

	/**
	 * Main method
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		// create a list of messages to use for companies
		ArrayList<Message> myTestMessages = new ArrayList<Message>();
		// create some tuples to test with
		for (int i = 1; i < 11; i++)
		{
			myTestMessages.add(new Message("Message " + i, "description " + i,
					"myURL.com", LocalDate.now().minusDays(i)));
		}

		// messages for last ten days
		Tuple<String, MessageList> t1 = new Tuple<String, MessageList>("Co1",
				new MessageList(myTestMessages));

		ArrayList<Message> mList = new ArrayList<Message>();
		mList.add(myTestMessages.get(9));
		mList.add(myTestMessages.get(8));
		mList.add(myTestMessages.get(7));
		mList.add(myTestMessages.get(6));

		// messages missing for last 6 days
		Tuple<String, MessageList> t2 = new Tuple<String, MessageList>("Co2",
				new MessageList(mList));

		mList.add(myTestMessages.get(5));
		mList.add(myTestMessages.get(4));

		// messages missing for last 4 days
		Tuple<String, MessageList> t3 = new Tuple<String, MessageList>("Co3",
				new MessageList(mList));

		mList.add(myTestMessages.get(3));
		mList.add(myTestMessages.get(2));
		mList.add(myTestMessages.get(1));

		// messages missing for last 1 day
		Tuple<String, MessageList> t4 = new Tuple<String, MessageList>("Co4",
				new MessageList(mList));

		// tuple list
		ArrayList<Tuple<String, MessageList>> myTupleList = new ArrayList<Tuple<String, MessageList>>();
		myTupleList.add(t1);
		myTupleList.add(t2);
		myTupleList.add(t3);
		myTupleList.add(t4);

		// test actual logic
		int days = 5;
		ArrayList<String> myList = checkFeed(myTupleList, days);
		if (!myList.isEmpty())
		{
			for (String c : myList)
			{
				System.out.println(c);
			}
		}
		else
		{
			System.out.println("All companies active last " + days);
		}
	}

}
