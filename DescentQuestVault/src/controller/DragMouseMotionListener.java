package controller;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import controller.commands.CancelTileMoveCommand;
import controller.commands.ICommand;

public class DragMouseMotionListener implements MouseListener,MouseMotionListener, AWTEventListener {

	private boolean done=false;
	private boolean on=false;
	@Override
	public void mouseDragged(MouseEvent e) {
		/*
		if(!on) {
			return;
		}
		*/

			// TODO Auto-generated method stub
			//paintselected on top
		
			
			UserInputController control=UserInputController.getController();
			//e.getXOnScreen()
			control.dragMouseEvent(e.getXOnScreen(),e.getYOnScreen());
			//MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, theFrame);
	      //  getParent().dispatchEvent(convertMouseEvent);
			control.deactivateAWTEventListeners();
			control.sendDragListenerEvent(e);
			control.activateAWTEventListeners();
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!on) {
			return;
		}
		// TODO Auto-generated method stub
		
		UserInputController control=UserInputController.getController();
		control.dragMouseEvent(e.getXOnScreen(),e.getYOnScreen());
		//control.deactivateAWTEventListeners();
		control.sendDragListenerEvent(e);
		//control.activateAWTEventListeners();
		
	}

	@Override
	public void eventDispatched(AWTEvent arg0) {
		
		
		
		if(!on) {
			
			//System.out.println("global listener off");
		//	System.out.println((MouseEvent) arg0);
			return;
		}
		
		
		
		// TODO Auto-generated method stub
		//System.out.println("event happened dispatch "+arg0.getID());
		//System.out.println(MouseEvent.MOUSE_RELEASED);
		
	//	System.out.println(arg0.getID());
		switch(arg0.getID()) {
			case MouseEvent.MOUSE_RELEASED:
				//System.out.println("it works");
				mouseReleased((MouseEvent) arg0);
				break;
			case MouseEvent.MOUSE_DRAGGED:
				//System.out.println("it works");
				mouseDragged((MouseEvent) arg0);
				break;
			case MouseEvent.MOUSE_MOVED:
				//System.out.println("it works");
				//mouseDragged((MouseEvent) arg0);
				break;
				
		}
		
	//	System.out.println((MouseEvent) arg0);
		//System.out.println("dispatch");
	
		/*
		UserInputController control=UserInputController.getController();
		PointerInfo a = MouseInfo.getPointerInfo();
	    Point p = new Point (0,0);
	    a = MouseInfo.getPointerInfo();
	    p = a.getLocation();
		//control.dragMouseEvent((int)p.getX(),(int)p.getY());
		*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("event happened");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("event happened");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("event happened");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("event happened");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		// TODO Auto-generated method stub
		
			UserInputController control=UserInputController.getController();
			control.releaseDragEvent(e.getX(),e.getY());
			control.deactivateAWTEventListeners();
			control.sendDragListenerEvent(e);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																										
			//control.activateAWTEventListeners();
			control.stopDragging();
			control.cancelMoves();
			
	}

	public void setOff() {
		System.out.println("eventlistener global of");
		// TODO Auto-generated method stub
		on=false;
	}

	public void setOn() {
		System.out.println("eventlistener global on");
		// TODO Auto-generated method stub
		on=true;
	}
}
