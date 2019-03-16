package model.event;

import model.event.extraevents.TextStop;
import view.menu.QuestCreator;

public class GameLoseEvent extends Event {

	
	public GameLoseEvent() {
		this.setName("Lose Game");
		this.setIDName("Lose Game Event");
	}
	
	@Override
	public void trigger() {
		TextStop stop=new TextStop("you lost");
		Thread th=new Thread() {
			public void run() {
				stop.trigger();
			}
		};
		th.start();
		
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
