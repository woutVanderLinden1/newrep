package view.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.UserInputController;
import controller.commands.ICommand;
import view.Items.Map.ViewSquare;

public class GameGridMouseListener implements MouseMotionListener, MouseListener {

	private GameGrid thegrid;
	
	public GameGridMouseListener(GameGrid thisthing) {
		thegrid=thisthing;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//create selecttile function
		thegrid.moveTemporariesToBack();
		int x=e.getX();
		int y= e.getY();
		thegrid.getSquareAt(x,y);
		
		//makecommand and sent it to controller
		
		GameSquare tile= thegrid.getSquareAt(x,y);
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
			int newx=e.getXOnScreen();
			int newy=e.getYOnScreen();
			command = control.generateGameGridCommand(newx,newy,e,MouseEvent.MOUSE_CLICKED,tile);
			control.performCommand(command);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ICommand command=new addTileToViewSquareCommand("018B",tile);
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
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
