package controller.commands;

import model.ItemController;

public class AddGoldCommand extends BasicCommand {

	private int value;
	public AddGoldCommand(int value) {
		this.value=value;
	}
	@Override
	public void perform() {
		ItemController control=ItemController.getItemController();
		control.addGold(value);
	}

}
