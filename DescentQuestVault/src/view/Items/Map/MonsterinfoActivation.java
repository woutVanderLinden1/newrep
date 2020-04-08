package view.Items.Map;

import model.Activation;
import model.event.Event;
import model.event.Trigger;
import view.game.MonsterActivation;

public class MonsterinfoActivation extends MonsterActivation {

	/**
	 * 
	 */
	private static final long serialVersionUID =  -4340174148978131750L;
	private Event monsterinfoEvent;
	public MonsterinfoActivation(Event monsterInfoEvent) {
		// TODO Auto-generated constructor stu
		this.monsterinfoEvent=monsterInfoEvent;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		monsterinfoEvent.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Info";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activation clone() {
		// TODO Auto-generated method stub
		return new MonsterinfoActivation(monsterinfoEvent);
	}

}
