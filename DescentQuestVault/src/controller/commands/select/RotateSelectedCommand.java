package controller.commands.select;

import controller.commands.BasicCommand;

public class RotateSelectedCommand extends BasicCommand {

	
	
	public void perform() {
		view.rotateSelected();
	}
}
