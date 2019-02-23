package controller.commands;

import view.Items.Map.MapItem;
import view.Items.Map.ViewTile;

public class MakeTileInvisibleCommand extends BasicCommand implements ICommand {

	private MapItem mapit;
	public MakeTileInvisibleCommand(ViewTile toplace) {
		mapit=toplace;
	}	

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.makeItemInVisible(mapit);
	}

}
