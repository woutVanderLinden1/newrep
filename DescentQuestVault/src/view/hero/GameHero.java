package view.hero;

import java.util.ArrayList;

import controller.turns.TurnHolder;
import misc.ActivateAble;
import model.Activation;
import model.Hero.Hero;

public class GameHero extends ViewHero  implements ActivateAble,TurnHolder{

	
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	private Hero hero;
	
	public GameHero(Hero hero) {
		super(hero);
		this.hero=hero;
		activationList.add(new EndTurnActivation());
		activationList.add(new DefeatActivation());
	}

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}

	@Override
	public boolean isActivateAble() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void startTurn() {
		//do nothing a hero turn is normally processed.
		// TODO Auto-generated method stub
		
	}

}
