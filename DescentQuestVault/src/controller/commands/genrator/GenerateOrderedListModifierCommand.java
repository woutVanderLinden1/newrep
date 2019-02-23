package controller.commands.genrator;

import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.modifier.OneOutModifier;
import model.event.modifier.OrderedListModifier;

public class GenerateOrderedListModifierCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		control.performCommand(new AddTriggerToTriggerFieldCommand(new OrderedListModifier(),null));

	}
	
	public String getStringName() {
		return "create ordered list";
	}
}
