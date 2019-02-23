package controller.commands;

import view.Items.Map.MapItem;
import view.viewItems.ItemBox.SelectAble;

public class makeVisibleCommand extends BasicCommand implements ICommand {

	private SelectAble mapit;
	
	public makeVisibleCommand(SelectAble viewtoken) {
		// TODO Auto-generated constructor stub
		mapit=viewtoken;
	}

	@Override
	public void perform() {
		view.makeVisible(mapit);
		
	}

}
