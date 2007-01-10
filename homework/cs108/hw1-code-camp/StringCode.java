// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adjcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if (str.length() == 0)  // le base case.  the code below assumes a string of length >= 0
			return 0;
		
		int max_run_length = 1;
		int working_run_length = 1; //track the current run length.
		for (int i=1; i < str.length(); i++)
		{
			char current_char = str.charAt(i),
				 previous_char = str.charAt(i - 1);
			if (current_char == previous_char) //in the working run if our char matches the previous one
				working_run_length++;
			else //start a new working run
			{
				max_run_length = Math.max(max_run_length, working_run_length);
				working_run_length=1;
			}
		}
		return max_run_length;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		String blown_up_str = "";
		for (int i=0; i < str.length() - 1; i++)
		{
			char current_char = str.charAt(i);
			if (Character.isDigit(current_char))
			{
				char next_char = str.charAt(i+1);
				blown_up_str += charRepeated(next_char,Character.digit(current_char, 10));
			}
			else
				blown_up_str += current_char;
		}
		return blown_up_str + str.charAt(str.length() - 1);
	}
	/**
	 * Given a character, returns a string with that character repeated repeat_count times.
	 * e.g. charRepated('x', 3); //=> "xxx"
	 * @param c character to be repeated
	 * @param repeat_count number of times to repeat
	 * @return
	 */
	protected static String charRepeated(char c, int repeat_count) {
		String rv = "";
		for (int i=0; i < repeat_count; i++)
			rv+=c;
		return rv;
	}
}
