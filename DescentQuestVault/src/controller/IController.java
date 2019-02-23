package controller;

import controller.commands.ICommand;
import controller.stack.StackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IDrawWindow;
import view.IView;

/**
 * the controller of the diagrams
 * accepts input of events and handles them
 * 
 * @author Wout van der Linden
 *
 */
public interface IController {

	
	

	/**
	 * handles a mouseevent in the controller
	 * 
	 * @param id
	 * the id of the mouseaction
	 * @param x
	 * the xlocation where is clicked
	 * @param y
	 * the y location where is clicked
	 * @param clickCount
	 * the number of clicks
	 */
	void handleMouseEvent(int id, int x, int y, int clickCount);

	/**
	 * handles a keyevent in the controller
	 * 
	 * @param id
	 * the id of the keyevent
	 * @param keyText
	 * the text that is typed
	 * @param keyCode
	 * the code of the key pressed
	 * @param modifier
	 * the modifier denots wether shirt/control is pressed
	 * @param keyChar
	 * the pressed char
	 */
	void handleKeyEvent(int id, String keyText, int keyCode,int modifier, char keyChar);

	/**
	 * returns the view
	 * 
	 * @return
	 * the view
	 */
	IView getTheView();

	/**
	 * returns the stackmanager
	 * 
	 * @return
	 * the stackmanager
	 */
	StackManager getStackManager();

	/**
	 * performs a command
	 * @param comm
	 * the command to perform
	 */
	void performCommand(ICommand comm);

	/**
	 * returns the active window
	 * @return
	 * the active window if there is one
	 */
	IDrawWindow getActiveWindow();

	/**
	 * returns the current most important element of the stack
	 * if a stack is used
	 * @return
	 * the top of the stack
	 */
	IStackElement getCurrentElement();

	/** 
	 * returns the model
	 * @return
	 * the model if present
	 */
	IModel getTheModel();

	

	
	
	
}
