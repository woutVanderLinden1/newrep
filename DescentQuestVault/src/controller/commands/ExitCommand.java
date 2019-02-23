package controller.commands;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;

public class ExitCommand extends BasicCommand  implements ICommand {

	
	@Override
	public void perform() {
		view.exit();
		
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
