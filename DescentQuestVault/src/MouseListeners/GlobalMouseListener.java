package MouseListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.UserInputController;

public class GlobalMouseListener implements MouseListener,MouseMotionListener {

	private static GlobalMouseListener mouseListen;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(arg0.getComponent());
		UserInputController.getController().handleMouseEvent(arg0.getID(), arg0.getX(), arg0.getY(), arg0.getClickCount());

	}

	

	public static MouseListener getMouseListener() {
		if(mouseListen==null) {
			mouseListen=new GlobalMouseListener();
		}
		return mouseListen ;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
