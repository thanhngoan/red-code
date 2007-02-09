import java.util.*;


public class DBRecord {
	protected boolean selected;
	protected Collection<DBBinding> bindings; 

	public static int SELECTED = 1;
	public static int UNSELECTED = 2;
	public static int SELECTED_OR_NOT = 3;
	

	/**
	 * constructs a DBRecord from the given string.
	 * @param encodedForm
	 */
	public DBRecord(String encodedForm)
	{
		selected = false;
		bindings = new ChunkList<DBBinding>();
		loadFromEncodedForm(encodedForm);
	}
	
	/**
	 * Loads the record from its form as a series of bindings separated by comas
	 * @param encoded
	 */
	public void loadFromEncodedForm(String encoded)
	{
		for (String encodedbinding : encoded.split(","))
		{
			String trimmed = encodedbinding.trim();
			if (trimmed.length()> 0)
				bindings.add(new DBBinding(encodedbinding));
		}
	}
	
	/**
	 * SELECTED_OR_NOT is a wildcard match
	 * @param type one of SELECTEd, UNSELECTED, or SELECTED_OR_NOT
	 * @return whether this record matches the given type of selection
	 */
	public boolean matchesSelectionType(int type) {
		if (type == DBRecord.SELECTED && selected)
			return true;
		else if (type == DBRecord.UNSELECTED && !selected)
			return true;
		else if (type == DBRecord.SELECTED_OR_NOT)
			return true;
		else
			return false;
	}
	
	public boolean getSelected() { return selected; }
	public void setSelected(boolean val) { selected = val; }
	/**
	 * 
	 */
	public String toString()
	{
		String[] bindingStrings = new String[bindings.size()];
		Collection<String> bindingsStrings = new ChunkList<String>();
		int i=0;
		for (DBBinding binding : bindings)
		{
			bindingStrings[i++] = binding.getKey() + " : " + binding.getValue();
		}
		return RedUtil.join(" , ", bindingStrings);
	}
	
	public Collection<DBBinding> getBindings( ) { return  bindings; }
	

	/**
	 * Returns whether this record matches the other record based on the given
	 * basic criteriea.  If we are operating in AND mode, then the select test is
	 * true for a given record if all of the bindings in the criteria record are
	 * present in the given record.
 
	 * @param record
	 * @param and_mode_p
	 */
	public boolean checkIfMatchesOtherRecord(DBRecord record, boolean and_mode_p)
	{
		if (and_mode_p)
		{
			for (DBBinding other_binding : record.bindings)
				if (!this.hasMatchingBinding(other_binding))
					return false;
			return true;
		}
		else
		{
			for (DBBinding other_binding : record.bindings)
				if (this.hasMatchingBinding(other_binding))
					return true;
			return false;
		}
	}
	
	
	protected boolean hasMatchingBinding(DBBinding binding)
	{
		for (DBBinding ourBinding : bindings)
			if (ourBinding.matchesOtherBinding(binding))
				return true;
			
		return false;
	}
}
