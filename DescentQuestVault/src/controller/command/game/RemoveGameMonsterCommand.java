package controller.command.game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.game.GameMonster;

public class RemoveGameMonsterCommand extends BasicCommand implements ICommand {
	GameMonster toremove;

	public RemoveGameMonsterCommand(GameMonster monster) {
		toremove=monster;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.removeGameMonster(toremove);
		control.removeMonster(toremove);
	}

}
