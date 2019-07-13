package model.event;

import java.io.Serializable;

public class MonsterSpecial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MonsterSpecial(String string) {
		text=string;
	}

}
