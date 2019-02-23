package controller.command.game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.Items.Map.MapItem;
import view.viewItems.ItemBox.SelectAble;

public class MakeInvisibleCommand extends BasicCommand implements ICommand {

	private SelectAble mapit;
	
	public MakeInvisibleCommand(SelectAble viewtoken) {
		mapit=viewtoken;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.makeInvisible(mapit);
	}

}
