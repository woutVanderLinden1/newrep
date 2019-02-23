package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.game.GameMonster;

public class PlaceGameMonsterCommand extends BasicCommand {

	private ViewMonster toplace;
	
	public PlaceGameMonsterCommand(ViewMonster monster) {
		toplace=monster;
		
	}
	
	@Override
	public void perform() {
		view.addGameMonster(toplace);

	}

}
