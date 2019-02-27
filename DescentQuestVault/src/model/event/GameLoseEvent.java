package model.event;

import view.menu.QuestCreator;

public class GameLoseEvent extends Event {

	
	public GameLoseEvent() {
		this.setName("Lose Game");
		this.setIDName("Lose Game Event");
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
