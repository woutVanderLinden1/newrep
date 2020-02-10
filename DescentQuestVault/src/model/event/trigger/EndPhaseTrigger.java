package model.event.trigger;

import controller.EndPhaseListener;
import controller.UserInputController;
import controller.turns.GameController;
import model.ItemController;
import model.event.Trigger;
import model.event.Univent;

public class EndPhaseTrigger extends Trigger implements EndPhaseListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3601594386348528407L;

	public EndPhaseTrigger() {
		super();
		this.setName("End Round");
	}
	public void trigger() {
		super.trigger();
	}
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void intialiseForGame(ItemController vent,GameController trol) {
		UserInputController control=UserInputController.getController();
		
		control.addEndPhaseListener(this);
		super.intialiseForGame(vent);
		//trol.addEndRoundListener(questGame);
	}

}
