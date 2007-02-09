import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class DBTable {
	protected Collection<DBRecord> records;
	
	/**
	 * boring constructor
	 *
	 */
	public DBTable()
	{
		records = newRecordList();
	}

	/**
	 * Reads several records from a straight string.
	 * @param encodedRecords
	 */
	public void readEncodedRecords(String encodedRecords)
	{
		for (String line : encodedRecords.split("\\n"))
			records.add(new DBRecord(line));
	}
	
	/**
	 * Reads a set of encoded records from a bufferedreader, which is compatable
	 * with a file input stream.
	 * @param data input buffer
	 * @throws IOException
	 */
	public void readEncodedRecords(BufferedReader data) throws IOException
	{
		String line;
		while ((line = data.readLine()) != null)
		{
			records.add(new DBRecord(line));
		}
		dbgPrint();				
	}
	
	/**
	 * used for debugging
	 *
	 */
	public void dbgPrint() {
		for (DBRecord record : getAllRecords())
			System.out.println(record.getSelected() ? "*" : "" + record.toString());
	}
	
	/**
	 *  returns the records with the given selection status
	 * @param selectionType one of SELECTED, UNSELECTEd, SELECTED_OR_NOT
	 * @return list of selected
	 */
	public Collection<DBRecord> retrieveSelectedRecords(int selectionType)
	{
		Collection<DBRecord> rv = newRecordList();
		for (DBRecord record : records)
			if (record.matchesSelectionType(selectionType))
				rv.add(record);
		return rv;
	}
	
	/**
	 * Factory method for creating new lists of records.
	 * @return empty new list for DBRecords
	 */
	static protected Collection<DBRecord> newRecordList()
	{
		//return new ArrayList<DBRecord>();
		return new ChunkList<DBRecord>();
	}
	
	/**
	 * Retrieves the actual DBRecords in the database
	 * @return the DBRecords in the database
	 */
	public Collection<DBRecord> getAllRecords() { return records; }
	
	/**
	 * deselects all the records in the database.
	 *
	 */
	public void unselectAllRecords() {
		for (DBRecord record : records)
			record.setSelected(false);
		
	}
	
	/**
	 * Runs a basic query against the database.  Tries to match this record
	 * against every other record.
	 * @param record record to run query against
	 * @param and_mode_p true if we should select by and mode.  _p is a lisp convention
	 */
	public void runBasicSelectAgainstRecord(DBRecord otherRecord, boolean and_mode_p)
	{
		for (DBRecord record : records)
			record.setSelected(record.checkIfMatchesOtherRecord(otherRecord, and_mode_p));
	}
	
	/**
	 * Deletes all the records that match the given selection criteria.
	 * 
	 * @param selectionType is one of SELECTED, UNSELECTED, SELECTED_OR_NOTE
	 */
	public void deleteSelectedRecords(int selectionType)
	{
		for (Iterator<DBRecord> iter = records.iterator();
			 iter.hasNext();
			 )
		{
			DBRecord record = iter.next();
			if (record.matchesSelectionType(selectionType))
				iter.remove();
		}
	}
	
	
	/**
	 * returns the number of records in the db
	 * @return
	 */
	public int getRecordCount() { return records.size(); }
	
	/**
	 * returns the number of selected records
	 * @return
	 */
	public int getRecordSelectedCount() { 
		int sum =0;
		for (DBRecord record : records)
			if (record.getSelected() == true)
				sum++;
		return sum;
	}
}
