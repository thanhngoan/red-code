
public class DTextModel extends DShapeModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5937757333038862948L;
	String text;
	String fontFamily;
	

	public void mimic(DShapeModel other) {
		super.mimic(other);
		this.text = ((DTextModel)other).text;
		this.fontFamily = ((DTextModel)other).fontFamily;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		if (text.equals(this.text) == false)
		{
			this.text = text;
			notifyListenersOfChange();
		}
	}

	/**
	 * @return the font
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * @param font the font to set
	 */
	public void setFontFamily(String font) {
		if (font.equals(this.fontFamily) == false)
		{
			this.fontFamily = font;
			notifyListenersOfChange();
		}
	}

}
