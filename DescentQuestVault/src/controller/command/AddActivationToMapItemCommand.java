package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import misc.ActivateAble;

public class AddActivationToMapItemCommand extends BasicCommand implements ICommand {

	ActivateAble active;
	
	public AddActivationToMapItemCommand(ActivateAble hold) {
		// TODO Auto-generated constructor stub
		active=hold;
	}

	@Override
	public void perform() {
		
		view.addActivationToActivateAble(active);
	}

}
