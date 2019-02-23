package controller.commands;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;

public class GoBackCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		view.goBackInMenu();

	}


	
	@Override
	public void setStackElement(IStackElement stateStackElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setController(UserInputController userInputController) {
		// TODO Auto-generated method stub

	}

}
