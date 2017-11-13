package garage.model.service_garage;

import garage.model.vehicle.Vehicle;

/**
 * Class to maintain service bays and interact with service manager.
 * 
 * @author Summer Mims
 */
public class Garage
{
	/** maximum number of bays allowed to be open in a station */
	private static final int MAX_BAYS = 30;
	/** the default size in which to open a garage with */
	private static final int DEFAULT_SIZE = 8;
	/** the actual number of bays in a garge */
	private int size;
	/** custom list of bays */
	private ServiceBay[] list;

	/**
	 * 
	 * Constructs a new Garage object. Creates a list of 8 empty service bays.
	 */
	public Garage()
	{
		list = new ServiceBay[MAX_BAYS];
		// initialize numbering system
		ServiceBay.startBayNumberingAt101();
		size = 0;
		initBays(DEFAULT_SIZE);
	}

	/**
	 * 
	 * Initialize the number of bays to initially open with given number.
	 *
	 * @param size
	 *            the number of total bays to open
	 */
	private void initBays(int size)
	{
		for (int i = 0; i < size; i++)
		{
			addRepairBay();
		}
	}

	/**
	 * 
	 * Adds a repair bay. At least one third are dedicated to hybrid/electric.
	 * If the new bay would decrease this percentage, a hybrid bay is created.
	 * Otherwise a regular bay is created.
	 *
	 */
	public void addRepairBay()
	{
		// if garage is not full can add bay
		if (size < MAX_BAYS)
		{
			// need to add hybrid bay
			// need to add to end of list
			if (size % 3 == 0) // new bay 0, 3, 6, ect.
			{
				ServiceBay newBay = new HybridElectricBay();
				// add to end
				list[size] = newBay;
				size++;
			}
			// need to add regular bay
			// add to front of list
			else
			{
				ServiceBay newBay = new ServiceBay();
				// shift all elements right until the desired index is reached
				// and
				// moved
				for (int i = size; i > 0; i--)
				{
					list[i] = list[i - 1];
					// add to front
				}
				// add new element
				list[0] = newBay;
				// increase size of list
				size++;
			}
		}
	}

	/**
	 * 
	 * Number of open service bays in garage
	 *
	 * @return bays the number of empty service bays
	 */
	public int numberOfEmptyBays()
	{
		int bays = 0;
		for (int i = 0; i < this.size; i++)
		{
			// if the bay is not occupied it is empty
			if (!this.getBayAt(i).isOccupied())
			{
				bays++;
			}
		}
		return bays;
	}

	/**
	 * 
	 * Get the service bay at a given index
	 *
	 * @param index
	 *            the index of which to find
	 * @return ServiceBay the service bay at the given index
	 */
	public ServiceBay getBayAt(int index)
	{
		if (index >= 0 && index < size)
		{
			return list[index];
		}
		return null;
	}

	/**
	 * 
	 * Get the total number of service bays
	 *
	 * @return size the total number of service bays
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * 
	 * Remove vehicle from indicated service bay
	 *
	 * @param index
	 *            the index of the service bay in which to release the vehicle
	 * @return Vehicle the vehicle that is released from desired service bay
	 */
	public Vehicle release(int index)
	{
		Vehicle v = this.getBayAt(index).release();
		return v;
	}
}
