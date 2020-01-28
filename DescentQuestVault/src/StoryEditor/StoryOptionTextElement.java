package StoryEditor;

import java.util.ArrayList;

import model.event.Trigger;
import model.event.extraevents.TextOption;

public class StoryOptionTextElement extends StoryTextElement {
	
	private static int nr;
	
	private ArrayList<TextOption> options=new ArrayList<TextOption>();



	public StoryOptionTextElement(int nr) {
		super(nr);
		kind=StoryTextKind.OPTION;
	}



	public ArrayList<TextOption> getTrigOptions() {
		// TODO Auto-generated method stub
		return options;
	}

	public void removeOption(Trigger trig) {
		options.remove(trig);
		
	}

	public void addOption(TextOption textOption) {
		options.add(textOption);
		
	}
	
	
}
