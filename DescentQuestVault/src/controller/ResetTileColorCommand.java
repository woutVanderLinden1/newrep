package controller;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class ResetTileColorCommand extends BasicCommand implements ICommand {

	public void perform() {
		control.resetColors();
	}
}
