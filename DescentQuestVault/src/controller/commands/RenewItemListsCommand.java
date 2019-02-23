package controller.commands;

import model.Item;

public class RenewItemListsCommand extends BasicCommand {

	public RenewItemListsCommand(Item item) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.renewItemList();
	}

}
