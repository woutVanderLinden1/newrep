package model.event;

import controller.commands.ICommand;
import controller.stack.EndGameCommand;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextStop;
import model.event.extraevents.TextTrigger;
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
	public void trigger() {
		TextStop stop=new TextStop("you won the game",new TextOption("continue", new EndGameCommand()));
		Thread th=new Thread() {
			public void run() {
				stop.trigger();
			}
		};
		th.start();
		
	
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
