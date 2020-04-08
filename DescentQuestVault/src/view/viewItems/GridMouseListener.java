package view.viewItems;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import controller.UserInputController;
import controller.commands.ChangeTileColorCommand;
import controller.commands.ICommand;
import controller.commands.addTileToViewSquareCommand;
import frame.SubContainer;
import model.Tile.Tile;
import view.Items.Map.ViewSquare;

public class GridMouseListener implements MouseListener,MouseMotionListener{

	
	 private Point origin;
	private GridPanel container;
	
	public GridMouseListener(GridPanel subContainer) {
		super();
		container=subContainer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//create selecttile function
		int x=e.getX();
		int y= e.getY();
		container.getSquareAt(x,y);
		
		//makecommand and sent it to controller
		
		ViewSquare tile= container.getSquareAt(x,y);
		/*
		if(tile!=null){
			return new addTileToViewSquareCommand("018B",tile);
		}
		return null;
		*/
		
		UserInputController control=UserInputController.getController();
		ICommand command;
		System.out.println("clicked on tile");
		System.out.println("x "+tile.getxID());
		System.out.println("y "+tile.getyID());
		try {
			command = control.generateGridCommand(x,y,e,MouseEvent.MOUSE_CLICKED,tile);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ICommand command=new addTileToViewSquareCommand("018B",tile);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		UserInputController control=UserInputController.getController();
		ICommand command;
		try {
			command = control.generateGridCommand(e.getX(), e.getY(),e, MouseEvent.MOUSE_EXITED, null);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		 origin = new Point(e.getPoint());
		int x=e.getX();
		int y= e.getY();
		ViewSquare tile= container.getSquareAt(x,y);
		UserInputController control=UserInputController.getController();
		ICommand command;
		try {
			System.out.println("bulkyzardy");
			command = control.generateGridCommand(e.getX(), e.getY(),e, MouseEvent.MOUSE_PRESSED, tile);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//create drag drop function for moving panels
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x=e.getX();
		int y= e.getY();
		container.getSquareAt(x,y);
		
		//makecommand and sent it to controller
		
		ViewSquare tile= container.getSquareAt(x,y);
		System.out.println("mouse is released");
		UserInputController control=UserInputController.getController();
		ICommand command;
		try {
			command = control.generateGridCommand(e.getX(), e.getY(),e, MouseEvent.MOUSE_RELEASED, tile);
			System.out.println(command);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		//System.out.println("griddragged");
		UserInputController control=UserInputController.getController();
		ViewSquare tile=container.getSquareAt(arg0.getX(), arg0.getY());
		ICommand command=null;
		try {
			command = control.generateGridCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_DRAGGED, tile);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(command==null) {
			if(tile!=null) {
				//System.out.println("this hapens "+tile);
				container.moveMap(origin,arg0);
			}
		
		}
	
		
    }
		// TODO Auto-generated method stub
	

	

	@Override
	public void mouseMoved(MouseEvent arg0) {
		UserInputController control=UserInputController.getController();
		ViewSquare tile=container.getSquareAt(arg0.getX(), arg0.getY());
		ICommand command;
		try {
			command = control.generateGridCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_MOVED, tile);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
	}

	public void mouseClicked(MouseEvent e, Point point) {
		// TODO Auto-generated method stub
		int x=point.x;
		int y= point.y;
		container.getSquareAt(x,y);
		
		//makecommand and sent it to controller
		
		ViewSquare tile= container.getSquareAt(x,y);
		/*
		if(tile!=null){
			return new addTileToViewSquareCommand("018B",tile);
		}
		return null;
		*/
		UserInputController control=UserInputController.getController();
		ICommand command;
		System.out.println("clicked on tile");
		System.out.println("x "+tile.getxID());
		System.out.println("y "+tile.getyID());
		try {
			command = control.generateGridCommand(x,y,e,MouseEvent.MOUSE_CLICKED,tile);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
	}

	public void mouseReleased(MouseEvent e, Point point) {
		System.out.println("mouse is released");
		UserInputController control=UserInputController.getController();
		ICommand command;
		try {
			command = control.generateGridCommand(point.x, point.y,e, MouseEvent.MOUSE_RELEASED, null);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void mouseMoved(MouseEvent arg0, Point point) {
		UserInputController control=UserInputController.getController();
		ViewSquare tile=container.getSquareAt(point.x, point.y);
		ICommand command;
		try {
			
			command = control.generateGridCommand(point.x, point.y,arg0, MouseEvent.MOUSE_MOVED, tile);
			control.performCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void mouseDragged(MouseEvent e, Point point) {
	
		System.out.println("the mouse dragged");
		UserInputController control=UserInputController.getController();
		ViewSquare tile=container.getSquareAt(point.x, point.y);
		ICommand command;
		try {
			command = control.generateGridCommand(point.x, point.y,e, MouseEvent.MOUSE_DRAGGED, tile);
			control.performCommand(command);
		} catch (Exception b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	

}
