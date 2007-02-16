import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import junit.framework.TestCase;


public class CrackerTest extends TestCase {
	MessageDigest digester;
	public void setUp()
	{
		try {
			digester = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] digest(String s)
	{
		digester.reset();
		digester.update(s.getBytes());
		return digester.digest();
	}
	public void testSmallCrack() throws InterruptedException
	{
		Cracker cracker = new Cracker();
		cracker.setConfiguration(false, digest("c"), 2);
		cracker.runThreads(4);
		Collection<String> found = cracker.getFoundInputs();
		assertEquals(1, found.size());
		assertEquals(true, found.contains("c"));
	}
	public void testMediumCracks() throws InterruptedException
	{
		Cracker cracker = new Cracker();
		cracker.setConfiguration(false, digest("cok"), 3);
		cracker.runThreads(4);
		Collection<String> found = cracker.getFoundInputs();
		assertEquals(1, found.size());
		assertEquals(true, found.contains("cok"));
	}
	public void testMediumCracks2() throws InterruptedException
	{
		Cracker cracker = new Cracker();
		cracker.setConfiguration(false, digest("cok"), 2);
		cracker.runThreads(4);
		Collection<String> found = cracker.getFoundInputs();
		assertEquals(0, found.size());
	}
	public void testLargeCrack() throws InterruptedException
	{
		Cracker cracker = new Cracker();
		cracker.setConfiguration(false, digest("zebo"), 4);
		cracker.runThreads(4);
		Collection<String> found = cracker.getFoundInputs();
		assertEquals(1, found.size());
		assertEquals(true, found.contains("zebo"));
	}
}
