package view.game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import controller.UserInputController;
import controller.command.game.ShowActivationsInGameCommand;
import misc.ActivateAble;
import model.Activation;
import view.hero.GameHero;

public class HeroMouseListener implements MouseListener {
	
	private GameHero hero;

	public HeroMouseListener(GameHero viewher) {
		// TODO Auto-generated constructor stub
		hero=viewher;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		UserInputController control=UserInputController.getController();
		ArrayList<ActivateAble> activateables=new ArrayList<ActivateAble>(); 
		activateables.add(hero);
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
