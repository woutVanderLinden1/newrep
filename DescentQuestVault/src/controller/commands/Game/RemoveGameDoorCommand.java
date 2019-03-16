package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewDoor;
import view.game.GameDoor;

public class RemoveGameDoorCommand extends BasicCommand {

	private GameDoor door;
	
	
	public RemoveGameDoorCommand(GameDoor viewDoor) {
		door=viewDoor;
	}

	@Override
	public void perform() {
		view.removeGameDoor(door);
		
	}

}
