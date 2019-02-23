package controller.analyzer;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.events.TriggerField;

public class ShowSelectedInTriggerFieldCommand extends BasicCommand implements ICommand {

	private TriggerField whereshow;
	
	public ShowSelectedInTriggerFieldCommand(TriggerField thefield) {
		// TODO Auto-generated constructor stub
		whereshow=thefield;
	}

	public void perform() {
		System.out.println("showing selected in triggerfield "+ whereshow );
		control.removeOldTemporary();
		view.ShowSelectedInTriggerField(whereshow);
		control.keepNewTemporary(whereshow);
	}
}
