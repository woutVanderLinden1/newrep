package controller.commands;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;
/**
 * a command that can be executed in the stackmanager
 * has a perform command and some basic setters
 * @author r0454860
 *
 */
public interface ICommand {
	
	/**
	 * performs the command
	 */
	void perform();

	/**
	 * sets the stackmanager this command can edit
	 * 
	 * @param stackManager
	 * the sidestackmanager that can be editted by the command by either adding or removing elements
	 */
	void setSideStackManager(SideStackManager stackManager);
	/**
	 * sets the stackmanager this command can edit
	 * 
	 * @param stackManager
	 * the mainstackmanager that can be editted by the command by either adding or removing elements
	 */
	void setMainStackManager(MainStackManager stackManager);

	/**
	 * sets the view the command can edit
	 * 
	 * @param view
	 * the view that can be editted
	 */
	void setView(IView view);

	/**
	 * the model this command can edit
	 * @param model
	 * the model this command wil edit
	 */
	void setModel(IModel model);

	/**
	 * sets the stackelement
	 * @param StackElement
	 * the stackelement that this can edit
	 */
	void setStackElement(IStackElement stateStackElement);

	/**
	 * sets the controller for the command
	 * @param userInputController
	 * the new controller
	 */
	void setController(UserInputController userInputController);

	String getStringName();

	

}
