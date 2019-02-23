package MouseListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import controller.UserInputController;
import controller.commands.ICommand;
import controller.commands.select.SelectCommand;
import controller.commands.select.SelectEventCommand;
import controller.commands.select.StartDragCommand;
import controller.commands.select.StartDragEventCommand;
import view.events.BaseField;
import view.events.EventField;
import view.events.MultiTriggerField;
import view.events.Values.BooleanValueField;

public class SelectEventListener implements MouseListener, MouseMotionListener,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseField field;
	
	public SelectEventListener(EventField eventField) {
		field=eventField;
	}

	public SelectEventListener(BaseField booleanValueField) {
		field=booleanValueField;
	}

	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("dragged");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		control.performCommand(new SelectCommand(field));
		//field.reAddImage();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("pressed accepted");
		UserInputController control=UserInputController.getController();
	
		ICommand command=null;
		try {
			command=control.generateSelectFieldCommand(arg0,field);
			control.performCommand(command);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//field.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		field.released(arg0);
	}


}
