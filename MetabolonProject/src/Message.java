import java.time.LocalDate;

/**
 * Class to construct message object from a RSS message
 * 
 * @author Summer Mims
 */

public class Message implements Comparable<Message>
{
	/*
	 * assume it has attributes of title, link, description, pubDate
	 * 
	 * Title, link, and description are required by W3 Publish date is required
	 * for the purposes of this project
	 * 
	 */
	private String title;
	private String description;
	private String link;
	private LocalDate pubDate;

	/**
	 * Constructs a new Message object.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param pubDate
	 */
	public Message(String title, String description, String link, LocalDate pubDate)
	{
		this.title = title;
		this.description = description;
		this.link = link;
		this.pubDate = pubDate;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink()
	{
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link)
	{
		this.link = link;
	}

	/**
	 * @return the pubDate
	 */
	public LocalDate getPubDate()
	{
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(LocalDate pubDate)
	{
		this.pubDate = pubDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Message [title=" + title + ", description=" + description
				+ ", link=" + link + ", pubDate=" + pubDate + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Message m)
	{
		// TODO Auto-generated method stub
		return getPubDate().compareTo(m.getPubDate());
	}

}
