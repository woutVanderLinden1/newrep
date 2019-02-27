package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.ItemController;
import model.values.CustomBoolean;
import model.values.CustomInteger;

public class CreateIntegerValueCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		CustomInteger newinteger=new CustomInteger();
		ItemController itemcontrol=ItemController.getItemController();
		itemcontrol.addValue(newinteger);
		view.renewItemList();

	}

	public String getStringName() {
		return "create integer value";
	}
}
