package controller.commands.genrator;

import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.modifier.OneOutModifier;
import model.event.modifier.RandomReducingListModifier;

public class GenerateReducingRandomListModifierCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		control.performCommand(new AddTriggerToTriggerFieldCommand(new RandomReducingListModifier(),null));

	}
	
	public String getStringName() {
		return "reducing list";
	}
}
