package controller.stack;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class EndGameCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		control.endGame();

	}

}
