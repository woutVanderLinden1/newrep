package controller.stack;

import controller.commands.ICommand;

import controller.stack.StackElements.IStackElement;
import view.IDrawWindow;


public abstract class SideStackManager extends StackManager{
	
	protected IDrawWindow sub;

	public SideStackManager(IDrawWindow window) {
		super();
		this.sub=window;
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
		
		topChanged=false;
		//comm.setSideStackManager(this);
		//comm.setWindow(sub);
		//comm.setStackElement(top);
		return top.prepareCommand(toPerform);
		//should update the stack
		//this is done in the command itself
	
	}

	@Override
	public abstract IStackElement getBasicStackElement();

	
}
