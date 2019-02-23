package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import controller.UserInputController;
import controller.commands.ExitCommand;
import controller.commands.ICommand;
import misc.listeners.*;

public class CommandButton extends JButton {
	
	private ICommand command;
	private ArrayList<ButtonPressedListener> buttonListeners =new ArrayList<ButtonPressedListener>();

	public CommandButton(String string, ICommand exitCommand) {
		super(string);
		command=exitCommand;
	
	
		addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	          performButton();
	        }

		
	    });
	}
	

    
	private void performButton() {
		
		buttonListeners.forEach(action->action.buttonPressed(this));
	
	}
	public void addButtonPressedListener(UserInputController controller) {
		buttonListeners.add(controller);
		
	}
	public ICommand getCommand() {
		// TODO Auto-generated method stub
		return command;
	}
	
	

}
