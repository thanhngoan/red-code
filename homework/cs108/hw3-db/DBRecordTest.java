import java.util.Iterator;

import junit.framework.TestCase;


public class DBRecordTest extends TestCase {
	public void testSingleTest(){
		DBRecord record = new DBRecord("one :two , three : four");
		assertEquals(record.getSelected(), false);
		record.setSelected(true);
		assertEquals(record.getSelected(), true);
		assertEquals(2, record.getBindings().size());
		assertEquals("two", record.getBindings().iterator().next().getValue());
		Iterator<DBBinding> iter = record.getBindings().iterator();
		iter.next(); 
		assertEquals("four", iter.next().getValue());
	}

	public void testSingleTest2(){
		DBRecord record = new DBRecord("          one     :    two,three : four         ");
		assertEquals(record.getSelected(), false);
		record.setSelected(true);
		assertEquals(record.getSelected(), true);
		assertEquals(2, record.getBindings().size());
		assertEquals("two", record.getBindings().iterator().next().getValue());
		Iterator<DBBinding> iter = record.getBindings().iterator();
		iter.next(); 
		assertEquals("four", iter.next().getValue());
	}

	public void testEmpty(){
		DBRecord record = new DBRecord("                          ");
		assertEquals(0, record.getBindings().size());
	}
	

	public void testMatch(){
		DBRecord red = new DBRecord("   name : red , plays : bridge, eats : grapes                       ");
		DBRecord sean = new DBRecord("   name :sean, plays : bridge, friendswith : red                       ");
		DBRecord sbb = new DBRecord("   name : sbb, plays : bridge, friendswith : red                       ");
		DBRecord blank = new DBRecord("                          ");
		DBRecord mrecord = new DBRecord("                          ");
		assertEquals(false, red.checkIfMatchesOtherRecord(blank, false));
		assertEquals(false, sbb.checkIfMatchesOtherRecord(blank, false));
		assertEquals(false, sean.checkIfMatchesOtherRecord(blank, false));
		assertEquals(false, blank.checkIfMatchesOtherRecord(blank, false));

		assertEquals(true, red.checkIfMatchesOtherRecord(blank, true));
		assertEquals(true, sbb.checkIfMatchesOtherRecord(blank, true));
		assertEquals(true, sean.checkIfMatchesOtherRecord(blank, true));
		assertEquals(true, blank.checkIfMatchesOtherRecord(blank, true));

		assertEquals(true, sean.checkIfMatchesOtherRecord(new DBRecord(" name : sean"), true));
		assertEquals(true, sbb.checkIfMatchesOtherRecord(new DBRecord(" name : sean, friendswith:red"), false));
		assertEquals(false, sbb.checkIfMatchesOtherRecord(new DBRecord(" name : sean, friendswith:red"), true));
		assertEquals(true, sean.checkIfMatchesOtherRecord(new DBRecord(" name : sean, friendswith:red"), true));
	}
}
