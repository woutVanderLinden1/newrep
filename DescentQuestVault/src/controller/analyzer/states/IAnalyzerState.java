package controller.analyzer.states;

import controller.KeyAction;
import controller.MouseAction;
import controller.commands.ICommand;
import view.IDrawWindow;

import view.IView;
/**
 * the state an analyzer can be in
 * @author r0454860
 *
 */
public interface IAnalyzerState {

	/**
	 * makes a command dependent on this subclass implementation
	 * @param x
	 * the x where the mouse acted
	 * @param y
	 * the y where the mouse acted
	 * @param act
	 * the act for the mouse
	 * @param wind
	 * the subwindow that is currently active
	 * @param view
	 * the view where he mouse acted
	 * @return
	 * dependent on implementation of subclass
	 *  returns null if no command corresponds to the action
	 */
	public ICommand makeCommand(int x, int y, MouseAction act,IDrawWindow wind, IView view);

	/**
	* makes a command dependent on this subclass implementation
	* 
	* @param key
	* the keyaction to analyse
	* @param wind
	* the active window
	* @param view
	* the view
	* @return
	* the command 
	* this is dependent on the implementation
	* returns null if no command corresponds to the action
	*/
	public ICommand makeCommand(KeyAction key,IDrawWindow wind, IView view);

	
	public boolean hasSelected();
}
