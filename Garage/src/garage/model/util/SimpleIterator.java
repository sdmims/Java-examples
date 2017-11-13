package garage.model.util;

/**
 * Interface to describe behaviors of generic type iterator. Includes method
 * next and hasNext
 * 
 * @param <E>
 *            the generic parameter to parameterize the list
 * @author Summer Mims
 */
public interface SimpleIterator<E>
{
	/**
	 * 
	 * Return the next element in the list and move cursor forward by 1.
	 *
	 * @return E the next element in the list
	 */
	public E next();

	/**
	 * 
	 * Check if there is another element in the list.
	 *
	 * @return boolean returns true is there is another element; returns false
	 *         if not
	 */
	public boolean hasNext();
}
