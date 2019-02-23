package controller.commands.select;

import controller.commands.BasicCommand;
import view.events.EventField;

public class SelectEventCommand extends BasicCommand{

	
	private EventField toselect;
	
	public SelectEventCommand(EventField field) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.setSelected(toselect);
	}

}
