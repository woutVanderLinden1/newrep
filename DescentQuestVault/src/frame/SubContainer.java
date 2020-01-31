package frame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import MouseListeners.GlobalKeyListener;
import MouseListeners.GlobalMouseListener;
import misc.listeners.IResizeListeners;
import view.events.ReleasAble;
import view.viewItems.ItemBox.SelectAble;


public class SubContainer extends JPanel implements ReleasAble,IResizeListeners,TemporaryAble {

	
	public SubContainer(int width,int height){
		//System.out.println("w:"+width+ " h:"+height);
		//this.setLayout(mgr);
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		addKeyListener();
		addMouseListener();
		this.setFocusable(true);
	}

	public SubContainer(Dimension defaultSize) {
		int width=defaultSize.width;
		int height=defaultSize.height;
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		addKeyListener();
		addMouseListener();
		this.setFocusable(true);
	}

	@Override
	public void resizePart(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	protected void addKeyListener() {
		this.addKeyListener(GlobalKeyListener.getKeyListener());
	
	}
	protected void addMouseListener() {
		//this.addMouseListener(GlobalMouseListener.getMouseListener());
		//this.addMouseMotionListener((MouseMotionListener) GlobalMouseListener.getMouseListener());
	}

	public void sendEvent(MouseEvent e,Point point, SelectAble selectAble) {
		
		
	}

	public static boolean isMouseWithinComponent(Component c)
	{
		if(((TemporaryAble) c).isTemporary()) {
			return false;
		}
	    Point mousePos = MouseInfo.getPointerInfo().getLocation();
	    Rectangle bounds = c.getBounds();
	    if(c.isDisplayable()) {
	    	bounds.setLocation(c.getLocationOnScreen());
		    return bounds.contains(mousePos);
	    }
	    return false;
	    
	}

	public void released(MouseEvent e) {
		// TODO Auto-generated method stub
	
		((ReleasAble) this.getParent()).released(e);
	}
	public void dragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(this.getParent()!=null) {
			((ReleasAble) this.getParent()).dragged(e);
		}
	
	}

	@Override
	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return false;
	}

	public void increaseHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	public void refreshHeight() {
		// TODO Auto-generated method stub
		
	}
	

}
