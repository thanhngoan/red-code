// CommandLineInterface.java
/*
 Provided class that presents a command-line interface for the DB classes.
 It does simple i/o with the console and sends messages to the DB
 to do all the work. All the code is in main().
 Edit the code so it interfaces with the DB classes.
*/

import java.io.*;

public class CommandLineInterface {
	public static void main(String[] args) {

		DBTable db = new DBTable();	// DBTable to use for everything
		
		try {
			// Create reader for typed input on console
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line;
			
			while (true) {
				int length = 0;
				int selectedLength = 0;
				length = db.getRecordCount();
				selectedLength = db.getRecordSelectedCount();
				System.out.println("\n" + length + " records (" + selectedLength + " selected)");
				System.out.println("r read, p print, sa select and, so select or, da ds du delete, c clear sel");
				System.out.print("db:");
				line = reader.readLine().toLowerCase();
				
				if (line.equals("r")) {
					System.out.println("read");
					String fname;
					System.out.print("Filename:");
					fname = reader.readLine();
					BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
					db.readEncodedRecords(infile);
					System.out.println("Read in database.");
				}
				else if (line.equals("p")) {
					System.out.println("print");
					for (DBRecord record : db.getAllRecords())
						System.out.println((record.getSelected() ? "*" : "") + record.toString());
				}
				else if (line.equals("da")) {
				System.out.println("delete all");
				db.deleteSelectedRecords(DBRecord.SELECTED_OR_NOT);
				}
				else if (line.equals("ds")) {
					System.out.println("delete selected");
					db.deleteSelectedRecords(DBRecord.SELECTED);
				}
				else if (line.equals("du")) {
					System.out.println("delete unselected");
					db.deleteSelectedRecords(DBRecord.UNSELECTED);
				}
				else if (line.equals("c")) {
					System.out.println("clear selection");
					db.unselectAllRecords();
				}
				else if (line.equals("so") || line.equals("sa")) {
					boolean and_mode_p = false;
					
					if (line.equals("so")) {
						and_mode_p = false;
						System.out.println("select or");
					}
					else {
						and_mode_p = true;
						System.out.println("select and");
					}
					
					System.out.print("Criteria record:");
					String text = reader.readLine();  // get text line from user
					DBRecord queryRecord = new DBRecord(text);
					db.runBasicSelectAgainstRecord(queryRecord, and_mode_p);
				}
				else if (line.equals("q") || line.equals("quit")) {
					System.out.println("quit");
					break;
				}
				else {
					System.out.println("sorry, don't know that command");
				}
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}
