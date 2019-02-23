package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewDoor;

public class PlaceGameDoorCommand extends BasicCommand{

	private ViewDoor toplace;
	
	public PlaceGameDoorCommand(ViewDoor viewDoor) {
		toplace=viewDoor;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.addGameDoor(toplace);
	}

}
