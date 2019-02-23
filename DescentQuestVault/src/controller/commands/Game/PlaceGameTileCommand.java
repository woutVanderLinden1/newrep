package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewTile;

public class PlaceGameTileCommand extends BasicCommand {
	
	ViewTile toplace;

	public PlaceGameTileCommand(ViewTile toplace) {
		setToplace(toplace);
	}

	public ViewTile getToplace() {
		return toplace;
	}

	public void setToplace(ViewTile toplace) {
		this.toplace = toplace;
	}

	public void perform() {
		//add game tile to screen
		view.addGameTile(toplace);
	}
}
