package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.UserInputController;
import controller.commands.ICommand;

public class EventBoxListener implements MouseListener, MouseMotionListener{


	private EventBox box;

	public EventBoxListener(EventBox triggerField) {
		// TODO Auto-generated constructor stub
		box=triggerField;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		UserInputController control=UserInputController.getController();
	
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_ENTERED, null);
		
				control.performCommand(command);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			System.out.println("exited");
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_EXITED, null);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("released here");
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_RELEASED, null);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_DRAGGED, null);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_MOVED, null);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
