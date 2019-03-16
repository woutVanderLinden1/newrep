package model.event;

import controller.turns.TurnHolder;
import view.hero.GameHero;
import view.viewItems.NameChangeListener;

public class EndTurnTrigger extends Trigger implements NameChangeListener {

	private GameHero hero;
	
	public EndTurnTrigger(GameHero hero) {
		this.hero=hero;
		this.setName("End Turn "+hero.getName());
		hero.addNameChangeListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.setName("End Turn "+hero.getName());
	}

}
