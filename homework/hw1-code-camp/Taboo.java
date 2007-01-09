
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(Collection<T> rules) {
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		 return null;
	}
	
	/**
	 * Removes elements in the given collection that
	 * violate the rules (see handout).
	 * @param coll collection to reduce
	 */
	public void reduce(Collection<T> coll) {
	}
}
