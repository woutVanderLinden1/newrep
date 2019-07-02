package controller.command.game;

import controller.commands.BasicCommand;
import view.game.GameMonster;

public class RemoveMapMonsterCommand extends BasicCommand {

	private GameMonster toremove;
	
	public RemoveMapMonsterCommand(GameMonster monster) {
		toremove=monster;
		System.out.println(monster);
		System.out.println(" in event "+toremove.getName());
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.removeMapMonster(toremove);
	}

}
