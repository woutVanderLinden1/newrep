package controller.commands;

import view.Items.Map.MapItem;
import view.Items.Map.ViewTile;

public class MakeTileVisibleCommand extends BasicCommand implements ICommand {

	private MapItem mapit;
	
	public MakeTileVisibleCommand(ViewTile toplace) {
		mapit=toplace;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.makeItemVisible(mapit);
	}

}
