package view.Items.Map;

import model.Activation;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import view.game.MonsterActivation;

public class ForceMonsterActivation extends MonsterActivation {

	private MonsterTurnTrigger trigger;
	
	public ForceMonsterActivation(MonsterTurnTrigger turnTrigger) {
		trigger=turnTrigger;
	}


	@Override
	public void trigger() {
		trigger.trigger();// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Force Activation";
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
		this.trigger=(MonsterTurnTrigger) trig;
	}

}
