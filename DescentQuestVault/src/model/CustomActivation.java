package model;

import model.event.Trigger;
import view.events.ActivationTrigger;

public class CustomActivation extends Activation {
	private String name;
	private ActivationTrigger activationTrigger=new ActivationTrigger(); 
	public CustomActivation(String string) {
		// TODO Auto-generated constructor stub
		name=string;
		activationTrigger.setName(string);
		activationTrigger.setIDName(string);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		activationTrigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public ActivationTrigger getActivationTrigger() {
		return activationTrigger;
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		name=text;
		activationTrigger.setName(text);
		activationTrigger.setIDName(text);
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return activationTrigger;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		activationTrigger=(ActivationTrigger) trig;
	}
	

}
