package activation;

import model.Activation;
import model.event.Trigger;
import view.game.GameDoor;
import view.game.GameToken;

public class SearchTokenActivation extends Activation {
	private Trigger trigger;
	
	public SearchTokenActivation(GameToken token) {
		trigger=token.getSearchTokenTrigger();
	}

	public void trigger() {
		trigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Search";
	}
}
