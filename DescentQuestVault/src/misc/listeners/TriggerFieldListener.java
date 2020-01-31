package misc.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import controller.UserInputController;
import controller.commands.ICommand;
import controller.commands.select.SelectCommand;
import view.Items.Map.ViewSquare;
import view.events.TriggerContainer;
import view.events.TriggerField;

public class TriggerFieldListener implements MouseListener,MouseMotionListener,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TriggerField thefield;

	private boolean enabled=true;
	
	public TriggerFieldListener(TriggerField triggerField) {
		// TODO Auto-generated constructor stub
		thefield=triggerField;
	}

	public TriggerFieldListener(TriggerContainer triggerContainer, boolean b) {
		// TODO Auto-generated constructor stub
		thefield=triggerContainer;
		enabled=b;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		control.performCommand(new SelectCommand(thefield));
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		UserInputController control=UserInputController.getController();
	
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_ENTERED, thefield);
			
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
		
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_EXITED, thefield);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("triggerfield pressed "+thefield);
		if(thefield.isTemporary()) {
			thefield.released(arg0);
		}
		
		else {
			if(enabled) {
				UserInputController control=UserInputController.getController();
				
				ICommand command=null;
				try {
					command=control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_PRESSED, thefield);
					control.performCommand(command);
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
		}
	

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(thefield.isTemporary()||thefield.isMinimized()) {
			thefield.released(arg0);
		}
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_RELEASED, thefield);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(!enabled) {
			System.out.println("mouse dragged over this");
		}
	

		if(thefield.isTemporary()||thefield.isMinimized()) {
			System.out.println("the field is temporary");
			thefield.dragged(arg0);
		}
		else {
			UserInputController control=UserInputController.getController();
			
			ICommand command=null;
			try {
				command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_DRAGGED, thefield);
				control.performCommand(command);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		
		ICommand command=null;
		try {
			command = control.generateTriggerFieldCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_MOVED, thefield);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
