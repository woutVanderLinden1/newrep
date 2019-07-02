package model.event.trigger;

import controller.EndPhaseListener;
import model.event.Trigger;
import model.event.Univent;

public class EndPhaseTrigger extends Trigger implements EndPhaseListener {

	
	public EndPhaseTrigger() {
		super();
		this.setName("End Round");
	}
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
