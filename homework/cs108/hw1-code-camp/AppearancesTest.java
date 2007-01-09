import junit.framework.TestCase;

import java.util.*;

public class AppearancesTest extends TestCase {
	
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(""+s.charAt(i));
		}
		return list;
	}
	
	public void testSameAppearances1() {
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));
	}
	
	public void testSameAppearances2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
		// Note: technique here is I write the first assert line, and then make each
		// successive line by making one change.
	}
	
	
}
