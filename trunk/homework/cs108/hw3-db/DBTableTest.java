import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import junit.framework.TestCase;


public class DBTableTest extends TestCase {

	/**
	 * returns the answers to the puzzle
	 */
	public Collection<DBRecord> solvePuzzle()
	{
		DBTable db = new DBTable();

		BufferedReader infile;
		try {

			DBRecord criteriaa = new DBRecord("likes: dennis-ritchie, dislikes: imelda-marcos, hair: gray, is: assertive, dislikes: winnie-the-pooh, dislikes: blue, is: quixotic, likes: the-michelin-woman");
			DBRecord criteriab = new DBRecord("hair: blonde, hobby: tetris-playing, is: sweet, dislikes: nice, hobby: puddle-jumping, likes: malcom-x, dislikes: sneezy, is: sensitive, likes: krusty-the-clown");
			DBRecord criteriac = new DBRecord("is: quixotic, hobby: simpsons-watching, dislikes: assertive, is: appealing, hobby: movie-going, dislikes: tax-deductible, likes: lorena-bobbit, hobby: snipe-hunting, likes: winnie-the-pooh");
			
			infile = new BufferedReader(new InputStreamReader(new FileInputStream("people.txt")));
			db.readEncodedRecords(infile);
			db.runBasicSelectAgainstRecord(criteriaa, false);
			db.deleteSelectedRecords(DBRecord.SELECTED);
			db.runBasicSelectAgainstRecord(criteriab, false);
			db.deleteSelectedRecords(DBRecord.UNSELECTED);
			db.runBasicSelectAgainstRecord(criteriac, false);
			db.deleteSelectedRecords(DBRecord.UNSELECTED);
			return db.getAllRecords();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void testPuzzleAnswer()
	{
		Collection<DBRecord> answers = solvePuzzle();
		assertEquals(2, answers.size());
	}
}
