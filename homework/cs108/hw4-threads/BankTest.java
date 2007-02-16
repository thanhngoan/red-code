import junit.framework.TestCase;


public class BankTest extends TestCase {
	public void testExamples()
	{
		Bank bank = new Bank();
		bank.processFile("5k.txt", 1);
		for (int i=0; i < 20; i++)
			assertEquals(0, bank.findAccountById(i).getBalance());
		bank.processFile("100k.txt", 1);
		for (int i=0; i < 20; i++)
			assertEquals(0, bank.findAccountById(i).getBalance());
	}
}
