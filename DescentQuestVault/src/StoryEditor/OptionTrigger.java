package StoryEditor;

import model.event.Trigger;
import model.event.Univent;

public class OptionTrigger extends Trigger {
	
	private static int nr;
	public OptionTrigger() {
		name="Option"+nr;
	}

	public OptionTrigger(String name) {
		this.name=name;
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new OptionTrigger(name);
	}

}
