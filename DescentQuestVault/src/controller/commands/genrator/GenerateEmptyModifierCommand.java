package controller.commands.genrator;

import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.Event;
import model.event.modifier.Modifier;

public class GenerateEmptyModifierCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		control.performCommand(new AddTriggerToTriggerFieldCommand(new Modifier(),null));

	}
	
	public String getStringName() {
		return "create empty modifier";
	}

}
