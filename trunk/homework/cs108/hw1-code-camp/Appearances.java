import java.util.*;

public class Appearances {
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static int sameCount(Collection<?> a, Collection<?> b) {
		int sameCountsSum = 0;
		Map<Object,Integer> countsA = makeElementCounts(a),
							countsB = makeElementCounts(b);
		
		for (Object key : countsA.keySet())
		{
			if (countsA.get(key).equals(countsB.get(key)))
				++sameCountsSum;
		}
		return sameCountsSum;
	}
	
	/**
	 * Takes a collection and returns a map from each particular object in the map to the number
	 * of times it occurs.
	 * @param collection
	 * @return
	 */
	protected static Map<Object,Integer> makeElementCounts(Collection<?> collection)
	{
		HashMap<Object, Integer> counts = new HashMap<Object, Integer>();
		for (Object obj : collection)
		{
			Integer existingNumberOfParticularObject = counts.get(obj);
			if (existingNumberOfParticularObject == null)
				existingNumberOfParticularObject =  new Integer(0);
			
			counts.put(obj, new Integer(existingNumberOfParticularObject + 1));
		}
		return counts;
	}
	
}
