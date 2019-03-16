package controller.commands.Game;

import controller.commands.BasicCommand;
import view.Items.Map.ViewTile;
import view.game.GameTile;

public class RemoveGameTileCommand extends BasicCommand {

	private GameTile tile;
	
	public RemoveGameTileCommand(GameTile viewTile) {
		tile=viewTile;
	}

	@Override
	public void perform() {
		view.removeGameTile(tile);

	}

}
