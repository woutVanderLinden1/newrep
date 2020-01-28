package model.generators;

import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.modifier.Modifier;
import view.events.AddExpToHeroesEvent;

public class CreateGainHeroExpCommand extends BasicCommand implements ICommand {

	
	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.performCommand(new AddEventToTriggerFieldCommand(new AddExpToHeroesEvent(1),null));

		
	}


	public String getStringName() {
		return "Add HeroExp event";
	}
}
