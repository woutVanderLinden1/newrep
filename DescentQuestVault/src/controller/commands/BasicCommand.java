package controller.commands;

import java.io.Serializable;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;

public abstract class BasicCommand implements ICommand,Serializable{


	protected IModel model;
	protected IView view;
	protected SideStackManager sideStack;
	protected MainStackManager mainStack;
	protected UserInputController control;
	@Override
	public abstract void perform();
	

	@Override
	public void setSideStackManager(SideStackManager stackManager) {
		sideStack=stackManager;
	}

	@Override
	public void setMainStackManager(MainStackManager stackManager) {
		mainStack=stackManager;
		
	}

	@Override
	public void setView(IView view) {
		this.view=view;
		
	}

	@Override
	public void setModel(IModel model) {
		this.model=model;
		
	}



	@Override
	public void setStackElement(IStackElement stateStackElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setController(UserInputController userInputController) {
		control=userInputController;

	}
	
	public String getStringName() {
		return "command";
	}

}
