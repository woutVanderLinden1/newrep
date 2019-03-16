package controller.command.game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.turns.TurnHolder;
import view.hero.GameHero;

public class EndTurnCommand extends BasicCommand implements ICommand {

	TurnHolder holder;
	
	public EndTurnCommand(TurnHolder hero) {
		holder=hero;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		holder.endTurn();
		control.endTurn();
	}

}
