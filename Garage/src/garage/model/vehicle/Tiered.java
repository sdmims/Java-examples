package garage.model.vehicle;

/**
 * Interface to describe behaviors of objects that can be ordered by numerical
 * tiers. Higher numbers correspond with higher tiers.
 * 
 * @author Summer Mims
 */
public interface Tiered
{
	/**
	 * 
	 * get tier of Tiered object
	 *
	 * @return the index of the Tiered element
	 */
	public int getTier();

	/**
	 * 
	 * Compare the tier status of Vehicle to another tiered object.
	 * 
	 * @param t
	 *            the tiered item to compare this to
	 * @return int Returns 0 if they match, negative is the tier status of this
	 *         object is less than the other, and positive if the tier status of
	 *         this is greater than the other.
	 */
	public int compareToTier(Tiered t);

}
