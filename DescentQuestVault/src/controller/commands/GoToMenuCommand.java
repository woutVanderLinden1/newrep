package controller.commands;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;
import view.menu.Menus;

public class GoToMenuCommand extends BasicCommand implements ICommand {

	private Menus newMenu;
	
	public GoToMenuCommand(Menus questeditor) {
		newMenu=questeditor;
	}


	@Override
	public void perform() {
		view.changeMenu(newMenu);

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
