
public class DBBinding {
	protected String key;
	protected String value;
	
	/**
	 * Initializes a binding with the given key and value
	 * @param k key
	 * @param v value
	 */
	public DBBinding(String k, String v)
	{
		key = k; value = v;
	}
	
	/**
	 * Creates a DBBinding from an encoded string of the form " key    :  value   "
	 * @param encodedRecord
	 */
	public DBBinding(String encodedRecord)
	{
		String[] keyval = encodedRecord.split(":");
		key = keyval[0].trim();
		value = keyval[1].trim();
	}

	/**
	 * @return key
	 */
	public String getKey() { return key; }
	
	/**
	 * @return value
	 */
	public String getValue() { return value; }
	
	/**
	 * Returns true if this binding matches another one.  This is used by a primitive
	 * query interface.
	 * @param other
	 * @return whether this binding corresponds exactly to another binding.
	 */
	public boolean matchesOtherBinding(DBBinding other)
	{
		return key.equals(other.key) && value.equals(other.value);
	}
}
