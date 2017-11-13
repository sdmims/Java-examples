package garage.model.vehicle;

import java.util.NoSuchElementException;
import java.util.Scanner;

import garage.model.util.SimpleIterator;

/**
 * Maintains vehicles in a linked list. This is a custom linked list.
 * 
 * @author Summer Mims
 */
public class VehicleList
{
	/** list node */
	private Node front;
	/** the size of the list */
	private int size;

	/**
	 * 
	 * Constructs a new VehicleList object. Creates an empty list of Vehicles.
	 */
	public VehicleList()
	{
		front = new Node(null, null);
		this.size = 0;
	}

	/**
	 * 
	 * Constructs a new VehicleList object. Creates a list of vehicles from the
	 * Scanner. From Vehicle - Construct vehicle out of string of license
	 * followed by name then integer value for tier status.
	 * 
	 * @param in
	 *            the Scanner to read in from
	 */
	public VehicleList(Scanner in)
	{
		String type = "";
		String license = "";
		String name = "";
		int tier = -1;
		while (in.hasNextLine())
		{
			type = in.next();
			if (type.equalsIgnoreCase("R"))
			{
				tier = in.nextInt();
				license = in.next();
				name = in.next() + in.nextLine();

				try
				{
					add(new RegularCar(license, name, tier));
				}
				catch (BadVehicleInformationException e)
				{
					// skip line of bad input and move on to next
				}
			}
			else if (type.equalsIgnoreCase("E"))
			{
				tier = in.nextInt();
				license = in.next();
				name = in.next() + in.nextLine();

				try
				{
					add(new HybridElectricCar(license, name, tier));
				}
				catch (BadVehicleInformationException e)
				{
					// skip line of bad input and move on to next
				}
			}
		}
	}

	/**
	 * 
	 * Initialize a list iterator to the first element in the Vehicle list.
	 *
	 * @return SimpleIterator returns a SimpleIterator initialized to first
	 *         element in the list
	 */
	public SimpleIterator<Vehicle> iterator()
	{
		return new Cursor();
	}

	/**
	 * 
	 * Add vehicle to the list.
	 *
	 * @param v
	 *            the vehicle to add to the list
	 */
	public void add(Vehicle v)
	{
		if (v == null)
		{
			throw new NullPointerException();
		}
		// empty list add head
		if (size == 0)
		{
			front = new Node(v, null);
			size++;
		}
		else if (v.compareToTier(front.data) > 0)
		{
			front = new Node(v, front);
			size++;
		}
		else
		{
			Node current = front.next; // next element
			Node previous = front; // first element
			while (current != null && v.compareToTier(current.data) <= 0)
			{
				previous = current;
				current = current.next;
			}
			previous.next = new Node(v, current);
			size++;
		}
	}

	/**
	 * 
	 * Remove a vehicle that appears in the given position with specified
	 * filter.
	 *
	 * @param filter
	 *            the prefix filter
	 * @param index
	 *            the index to remove a vehicle at
	 * @return Vehicle the vehicle removed from the list
	 */
	public Vehicle remove(String filter, int index)
	{

		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		// if index 0 remove head
		if (index == 0)
		{
			Vehicle v = front.data;
			if (v.meetsFilter(filter))
			{
				front = front.next;
				size--;
				return v;
			}

		}
		else
		{
			Node current = front;
			Node previous = null;
			int position = 0;

			while (current != null)
			{

				if (current.data.meetsFilter(filter) && position == index)
				{
					previous.next = current.next;
					size--;
					return current.data;
				}
				position++;
				previous = current;
				current = current.next;
			}
		}
		return null;
	}

	/**
	 * 
	 * Return vehicle at given position with specified filter.
	 *
	 * @param filter
	 *            the prefix filter
	 * @param index
	 *            the index to get a vehicle at
	 * @return Vehicle the vehicle at the given index
	 */
	public Vehicle get(String filter, int index)
	{
		// check for empty list and index
		if (front == null || size == 0)
		{
			return null;
		}

		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}

		Cursor c = new Cursor();
		int position = 0;
		while (c.hasNext())
		{
			Vehicle v = c.next();
			if (v.meetsFilter(filter) && position == index)
			{
				return v;
			}
			position++;
		}

		return null;
	}

	/**
	 * 
	 * String representation of all vehicles that meet the filter. Each
	 * substring of a vehicle is terminated by a newline character.
	 *
	 * @param filter
	 *            the prefix filter
	 * @return String the String representation of all vehicles that meet the
	 *         filter
	 */
	public String filteredList(String filter)
	{
		String s = "";
		Cursor c = new Cursor();
		while (c.hasNext())
		{
			Vehicle v = c.next();
			if (v.meetsFilter(filter))
			{
				s += v.toString() + "\n";
			}
		}
		return s;
	}

	/**
	 * Class to represent current location of the iterator within the list.
	 * Implements SimpleIterator interface.
	 */
	private class Cursor implements SimpleIterator<Vehicle>
	{
		/** private Node to act as cursor in List */
		private Node place;

		/**
		 * 
		 * Constructs a new Cursor object. Initialize to front of list.
		 */
		private Cursor()
		{
			place = front;
		}

		/**
		 * 
		 * Check if there is another element in the list. If the current place
		 * is not null, returns true.
		 *
		 * @return boolean returns true if there is another element; returns
		 *         false if not
		 */
		public boolean hasNext()
		{
			return place != null;
		}

		/**
		 * 
		 * Return the next element in the list and move cursor forward by 1.
		 *
		 * @return Vehicle the next Vehicle on the list
		 */
		public Vehicle next()
		{
			if (place == null)
			{
				throw new NoSuchElementException();
			}
			Vehicle v = place.data;
			place = place.next;
			return v;
		}
	}

	/**
	 * Constructs a new Node object.
	 */
	private class Node
	{
		private Vehicle data;
		private Node next;

		/**
		 * 
		 * Constructs a new Node object.
		 * 
		 * @param v
		 *            the Vehicle data
		 * @param n
		 *            the next node
		 */
		public Node(Vehicle v, Node n)
		{
			this.data = v;
			this.next = n;

		}
	}
}
