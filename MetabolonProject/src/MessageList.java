import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to create list of messages fom RSS feed
 * 
 * @author Summer Mims
 */

public class MessageList
{
	private ArrayList<Message> messages;

	public MessageList()
	{
		// initialize empty list
		this.messages = new ArrayList<Message>();
	}

	public MessageList(ArrayList<Message> messageList)
	{
		// initialize empty list
		this.messages = new ArrayList<Message>();
		add(messageList);
	}

	public void add(Message message)
	{
		this.messages.add(message);

	}

	public void add(ArrayList<Message> messageList)
	{
		for (Message m : messageList)
		{
			this.messages.add(m);
		}

	}

	/**
	 * reverse sort messages by date
	 * 
	 *
	 */
	public void sortMessages()
	{
		Collections.sort(this.messages);
		// reverse to descending dates
		Collections.reverse(this.messages);
	}

	/**
	 * 
	 * get latest message
	 *
	 * @return Message: the last message by date
	 */
	public Message getLastMessage()
	{
		sortMessages();
		return this.messages.get(0);
	}
}
