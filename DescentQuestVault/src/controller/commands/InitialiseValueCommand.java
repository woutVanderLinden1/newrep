package controller.commands;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import model.ItemController;
import model.values.CustomBoolean;
import view.IView;

public class InitialiseValueCommand extends BasicCommand implements ICommand {

	private CustomBoolean customBoolean;
	
	public InitialiseValueCommand(CustomBoolean customBoolean) {
		// TODO Auto-generated constructor stub
		this.customBoolean=customBoolean;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		control.addValue(customBoolean);
	}

}
