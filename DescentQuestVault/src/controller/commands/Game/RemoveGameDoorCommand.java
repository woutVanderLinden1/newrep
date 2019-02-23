package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewDoor;

public class RemoveGameDoorCommand extends BasicCommand {

	private ViewDoor door;
	
	
	public RemoveGameDoorCommand(ViewDoor viewDoor) {
		door=viewDoor;
	}

	@Override
	public void perform() {
		view.removeGameDoor(door);
		
	}

}
