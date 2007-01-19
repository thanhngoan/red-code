// TabooTest.java
// Taboo class tests -- nothing provided.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

public class TabooTest extends TestCase {

	private List<String> stringToList(String s) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(""+s.charAt(i));
		}
		return list;
	}
	
	private String stringListToString(Collection<String> coll) {
		String runningString = "";
		for (String item : coll)
			runningString += item;
		return runningString;
	}

	public String reduceString(String rules, String reduceString)
	{
		Taboo<String> taboo = new Taboo<String>(stringToList(rules));
		List<String> reduceme = stringToList(reduceString);
		taboo.reduce(reduceme);
		return stringListToString(reduceme);
	}
	

	public String reduceString(Taboo<String> taboo, String reduceString)
	{
		List<String> reduceme = stringToList(reduceString);
		taboo.reduce(reduceme);
		return stringListToString(reduceme);
	}

	public void testTaboos1() {
		assertTrue(reduceString("ab","abbbbbb").contentEquals("a"));
		assertTrue(reduceString("abb","abbbbbb").contentEquals("a"));
		assertTrue(reduceString("a","abbbbbb").contentEquals("abbbbbb"));
	}
	public void testEmptyRules() {
		ArrayList<String> strlist = new ArrayList<String>();
		String[] strarr = {"a", "b", null, "a", "b", null, "a", "c", null, "a", "d"};
		strlist.addAll(Arrays.asList(strarr));
		assertTrue(reduceString(new Taboo<String>(strlist),"bcda").
				   contentEquals("bcda"));
		assertTrue(reduceString(new Taboo<String>(strlist),"abd").
				   contentEquals("a"));
		assertTrue(reduceString(new Taboo<String>(strlist),"aba").
				   contentEquals("aa"));
	}
	
	public void testNulledRules() {
		assertTrue(reduceString("","j32432klfsd").contentEquals("j32432klfsd"));
	}
	
	public void testTaboos2() {
		assertTrue(reduceString("121314","1122334455").contentEquals("1155"));
	}
}
