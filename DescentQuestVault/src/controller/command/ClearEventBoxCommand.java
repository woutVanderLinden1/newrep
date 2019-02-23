package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class ClearEventBoxCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.clearEventBox();
	}

}
