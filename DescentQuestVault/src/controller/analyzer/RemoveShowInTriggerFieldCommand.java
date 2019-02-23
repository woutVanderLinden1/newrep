package controller.analyzer;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.events.TriggerField;

public class RemoveShowInTriggerFieldCommand extends BasicCommand implements ICommand {

	private TriggerField thefield;
	
	public RemoveShowInTriggerFieldCommand(TriggerField thefield) {
		this.thefield=thefield;
	}
	
	public void perform() {
		System.out.println(thefield);
		view.removeShownInField(thefield);
	}

}
