package model.event;

import view.menu.QuestCreator;

public class GameWonEvent extends Event {

	
	public GameWonEvent() {
		this.setName("Game Win");
		this.setIDName("Game Win Event");
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
