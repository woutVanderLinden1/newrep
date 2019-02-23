package controller.stack.StackElements;

import controller.analyzer.states.IAnalyzerState;
import controller.commands.ICommand;
/**
 * denotes an ellement of the stack of stackmanager
 * this controlls what kind of event will happen
 * @author r0454860
 *
 */
public interface IStackElement {


	/**
	 * perpares a command in the stackelement
	 * 
	 * @param toHandle
	 * the command to handle
	 * @return
	 * the command with initialised parameters
	 */
	public ICommand prepareCommand(ICommand toPerform);

	

	/**
	 * returns the coresponding state for the stackelement
	 * 
	 * @return
	 * the analyzerstate corresponding to the element
	 */
	public IAnalyzerState getCorrespondingState();



	public void reset();



	public boolean hasSelected();

	



}
