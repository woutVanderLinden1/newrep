package controller.commands.genrator;

import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.modifier.Modifier;
import model.event.modifier.OneOutModifier;

public class GenerateOneOffListModifierCommand extends BasicCommand implements ICommand {


	@Override
	public void perform() {
		control.performCommand(new AddTriggerToTriggerFieldCommand(new OneOutModifier(),null));

	}
	
	public String getStringName() {
		return "create one out";
	}

}
