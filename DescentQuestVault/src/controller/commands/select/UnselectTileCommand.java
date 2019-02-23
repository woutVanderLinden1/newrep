package controller.commands.select;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class UnselectTileCommand extends BasicCommand implements ICommand {

	
	public void perform() {
		view.unselect();
	}
}
