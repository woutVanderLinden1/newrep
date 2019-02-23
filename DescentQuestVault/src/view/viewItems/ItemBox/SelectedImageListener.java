package view.viewItems.ItemBox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.UserInputController;
import controller.commands.ICommand;

public class SelectedImageListener implements MouseListener,MouseMotionListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		UserInputController control=UserInputController.getController();
		ICommand command=control.generateSelectCommand(MouseEvent.MOUSE_PRESSED,e.getXOnScreen(),e.getYOnScreen(),e);
		control.performCommand(command);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
