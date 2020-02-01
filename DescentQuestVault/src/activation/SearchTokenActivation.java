package activation;

import model.Activation;
import model.event.Trigger;
import view.Items.Map.ViewSearchToken;
import view.Items.Map.ViewToken;
import view.game.GameDoor;
import view.game.GameToken;

public class SearchTokenActivation extends Activation {
	private Trigger trigger;
	
	
	public SearchTokenActivation(ViewToken viewToken) {
		trigger=((ViewSearchToken) viewToken).getSearchTokenTrigger();
	}

	public void trigger() {
		trigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Search";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return trigger;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		trigger=trig;
	}
}
