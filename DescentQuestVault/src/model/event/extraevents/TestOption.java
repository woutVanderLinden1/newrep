package model.event.extraevents;

import java.util.ArrayList;

public class TestOption extends TextTrigger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestOption() {
		
		super(0, new ArrayList<TextOption>(), "This is a test \n"
				+ " pls press continue");
		setIDName("Test");
		setName("test");
		this.addTextOption(new TextOption("continue"));
		// TODO Auto-generated constructor stub
	}


}
