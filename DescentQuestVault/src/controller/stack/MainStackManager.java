package controller.stack;

import controller.commands.ICommand;

import model.IModel;
import view.IView;

public class MainStackManager extends StackManager {
	
	private IView view;
	private IModel model;

	public MainStackManager(IView view2, IModel mod) {
		super();
		view=view2;
		model=mod;
		InitialStackElement el=new InitialStackElement();
		
		addStackElement(el);
	}
	


	/**
	 * performs a command
	 * sets the stackmanager,the view and the model of the command
	 * then sends it to the topelement to be exectuted
	 * @param toPerform
	 * the command to perform
	 */
	public ICommand prepareCommand(ICommand toPerform){
	
		if(toPerform==null){
			return toPerform;
		}
		//System.out.println("received command: "+ toPerform.getClass().getName() );
		topChanged=false;
		toPerform.setMainStackManager(this);
		toPerform.setView(view);
		toPerform.setModel(model);
		return top.prepareCommand(toPerform);
		//should update the stack
		//this is done in the command itself
	
	}



	/**
	 * adapts the top of the stackmanager to the current active window in the view
	 */
	public void adaptTop() {
	
	}
	@Override
	public void removeStackTop() {
		this.getTop().reset();
		super.removeStackTop();
	}

}
