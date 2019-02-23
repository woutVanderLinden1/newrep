package controller.stack;

import java.util.Stack;

import controller.analyzer.states.IAnalyzerState;
import controller.commands.ICommand;

import controller.stack.StackElements.IStackElement;


import model.IModel;
import model.Tile.Tile;
import view.IView;
/**
 * a stack that manages the commands
 * so that commands will be executed at the right stack level
 * @author r0454860
 *
 */
public abstract class StackManager {

	


	protected Stack<IStackElement> elements;
	protected IStackElement top;
	protected boolean topChanged;

	

	
	/**
	 * initialises the stack and adds the base
	 * element also intialises an activewindow in
	 * communication state
	 * 
	 * @param mod 
	 * the model for the stackmanager to edit or give to a command
	 * @param view2 
	 * the view for the stackmanager to edit or give to a command
	 */
	public StackManager() {
	
		
		elements=new Stack<IStackElement>();
	
		initialiseStack();
	
		
	}
	
	public boolean isTopChanged() {
		return topChanged;
	}

	public void setTopChanged(boolean topChanged) {
		this.topChanged = topChanged;
	}

	/**
	 * prepares command
	 * sets the stackmanager,the view and the model of the command
	 * then sends it to the topelement to be exectuted
	 * the preparation depends on the subclass
	 * @param toPerform
	 * the command to prepare
	 * @return 
	 */
	public ICommand prepareCommand(ICommand toPerform){
	
		return toPerform;
		
	}
	
	/**
	 * add a stackelement to the stack and set it as the top
	 * 
	 * @param newtop
	 * the newtop
	 */
	public void addStackElement(IStackElement newtop){
		elements.push(newtop);
		top=elements.peek();
		topChanged=true;
	}
	
	/**
	 *remove an element from the stack
	 * 
	
	 */
	public void removeStackTop(){
		elements.pop();
		if(!elements.isEmpty()){
			top=elements.peek();
		}
		else{
			top=null;
		}
	
		topChanged=true;
	}
	
	public IAnalyzerState getNewState(){
		return top.getCorrespondingState();
	}


	
	public IAnalyzerState getTopState() {
		return top.getCorrespondingState();
	}


	public IStackElement getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	/**
	 * goes back to the basic stack state
	 * removes all other  elements from the stack
	 */
	public void gotToBasicState() {
		
		elements.clear();
		initialiseStack();
	
	}
	/**
	 * initialises the stack witha  baselement and sets the top to the new top
	 */
	public void initialiseStack(){
		elements.add(getBasicStackElement());
		top=elements.peek();
	}

	/**
	 * returns the basic element of the stack
	 * @return
	 * the basic element of the stack
	 */
	public IStackElement getBasicStackElement() {
		
		return new InitialStackElement();
	}

	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return top.hasSelected();
	}
/*
	public Tile getSelected() {
		// TODO Auto-generated method stub
		//return top.getSelected();
	}
*/
	

	
}
