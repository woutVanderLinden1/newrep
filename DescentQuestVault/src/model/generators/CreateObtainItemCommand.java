package model.generators;

import StoryEditor.AddItemEvent;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.events.AddExpToHeroesEvent;

public class CreateObtainItemCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.performCommand(new AddEventToTriggerFieldCommand(new AddItemEvent(),control.getSelected()));

		
	}
	 @Override
	public String getStringName() {
		return "Obtain item";
		
	}

}
