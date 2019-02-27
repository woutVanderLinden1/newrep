package controller.commands;

import model.ItemController;
import model.values.CustomBoolean;

public class CreateBooleanValueCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		CustomBoolean newboolean=new CustomBoolean();
		ItemController itemcontrol=ItemController.getItemController();
		itemcontrol.addValue(newboolean);
		view.renewItemList();
	}

	
	public String getStringName() {
		return "create boolean value";
	}
}
