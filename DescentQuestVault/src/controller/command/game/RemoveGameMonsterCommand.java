package controller.command.game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.game.GameMonster;

public class RemoveGameMonsterCommand extends BasicCommand implements ICommand {
	private GameMonster toremove;

	public RemoveGameMonsterCommand(GameMonster monster) {
		toremove=monster;
		System.out.println(monster);
		System.out.println(" in event "+toremove.getName());
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.removeGameMonster(toremove);
		control.removeMonster(toremove);
	}

}
