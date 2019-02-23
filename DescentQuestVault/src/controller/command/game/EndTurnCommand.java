package controller.command.game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class EndTurnCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.endTurn();
	}

}
