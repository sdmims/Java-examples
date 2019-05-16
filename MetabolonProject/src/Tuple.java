/**
 * 
 * 
 * @author Summer Mims
 */

/**
 * class to represent a tuple of company and list of messages
 * 
 * add final modifier to emulate a true immutable tuple
 * 
 * This could be generalized to a generic Tuple Ex. Tuple<E,t>
 */
public class Tuple<String, MessageList>
{
	private final String company;
	private final MessageList messages;

	/**
	 * Constructs a new Tuple object consisting of company name and message list
	 * 
	 * @param company
	 * @param rssFeed
	 */
	public Tuple(String company, MessageList messages)
	{
		this.company = company;
		this.messages = messages;
	}

	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 * @return the messages
	 */
	public MessageList getMessages()
	{
		return messages;
	}

}