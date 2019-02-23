package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewTile;

public class RemoveGameTileCommand extends BasicCommand {

	private ViewTile tile;
	
	public RemoveGameTileCommand(ViewTile viewTile) {
		tile=viewTile;
	}

	@Override
	public void perform() {
		view.removeGameTile(tile);

	}

}
