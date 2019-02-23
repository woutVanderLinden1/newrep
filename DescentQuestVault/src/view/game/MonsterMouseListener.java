package view.game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import controller.UserInputController;
import controller.command.game.ShowActivationsInGameCommand;
import misc.ActivateAble;

public class MonsterMouseListener implements MouseListener {
	private GameMonster monster;

	public MonsterMouseListener(GameMonster viewher) {
		// TODO Auto-generated constructor stub
		monster=viewher;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		UserInputController control=UserInputController.getController();
		ArrayList<ActivateAble> activateables=new ArrayList<ActivateAble>(); 
		activateables.add(monster);
		Point point=new Point(arg0.getLocationOnScreen().x,arg0.getLocationOnScreen().y-60);
		control.performCommand(new ShowActivationsInGameCommand(point,activateables));
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

}
