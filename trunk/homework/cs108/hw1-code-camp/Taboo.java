
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	protected Map<T, Set<T>> nofollowTable;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(Collection<T> rules) {
		nofollowTable = new HashMap<T, Set<T>>();
		Iterator<T> iter =  rules.iterator();
		T currentElem = null, nofollowObject = null;
		while (iter.hasNext())
		{
			currentElem = nofollowObject != null ? nofollowObject : iter.next();
			nofollowObject = iter.hasNext() ? iter.next() : null;
			if (nofollowObject != null) // do NOT match null objects
			{
				Set<T> nofollowObjects = nofollowTable.get(currentElem);
				if (nofollowObjects == null)
				{
					nofollowObjects = new HashSet<T>();
					nofollowTable.put(currentElem, nofollowObjects);
				}
				nofollowObjects.add(nofollowObject);
			}
		}
	}
	
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		Set<T> theSet = nofollowTable.get(elem);
		return theSet != null ? theSet : new HashSet<T>();
	}
	
	/**
	 * Removes elements in the given collection that
	 * violate the rules (see handout).
	 * "The reduce(List<T>) operation takes in a list, iterates over the list, and deletes the second element
	 * of any adjacent elements during the iteration that violate the rules."
	 * That is not the clearest explanation, but I think this does what is described.
	 * @param coll collection to reduce
	 */
	public void reduce(Collection<T> coll) {
		T currentElem = null, nextElem = null;
		Iterator<T> iter =  coll.iterator();
		/*
		 * This is a common loop idiom for looking at successive elements in a list.
		 */
		while (iter.hasNext())
		{
			currentElem = nextElem != null ? nextElem : iter.next();
			nextElem = iter.hasNext() ? iter.next() : null;
			
			//remove the next element until it doesn't violate a rule
			while (nextElem != null && noFollow(currentElem).contains(nextElem))
			{
				iter.remove();
				nextElem = iter.hasNext() ? iter.next() : null;
			}
		}
	}
	
	
}
