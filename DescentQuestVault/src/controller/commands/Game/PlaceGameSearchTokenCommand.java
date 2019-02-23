package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewToken;

public class PlaceGameSearchTokenCommand extends BasicCommand {

	private ViewToken toplace;
	
	public PlaceGameSearchTokenCommand(ViewToken viewToken) {
		toplace=viewToken;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.addGameToken(toplace);
	}

}
