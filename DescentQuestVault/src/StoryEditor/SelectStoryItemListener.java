package StoryEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import controller.UserInputController;
import controller.commands.ICommand;
import controller.commands.select.SelectCommand;
import controller.commands.select.StartDragEventCommand;
import view.events.BaseField;

public class SelectStoryItemListener implements MouseListener, MouseMotionListener,Serializable {

	private static final long serialVersionUID = 1L;
	private StoryItemPanel field;
	private boolean activated=true;
	
	public SelectStoryItemListener(StoryItemPanel storyItemPanel) {
		field=storyItemPanel;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		control.performCommand(new SelectCommand(field));

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
		if(!activated) {
			return;
		}
		if(arg0.getButton() == MouseEvent.BUTTON1) {
			System.out.println("pressed accepted");
			UserInputController control=UserInputController.getController();
		
			ICommand command=null;
			try {
				command=new StartDragStoryItemCommand(field,arg0.getX(),arg0.getY(),arg0.getXOnScreen(),arg0.getYOnScreen());
				control.performCommand(command);
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//field.requestFocus();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		field.released(arg0);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
