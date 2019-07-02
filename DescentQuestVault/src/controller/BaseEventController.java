package controller;

import model.event.StartUpTrigger;
import model.event.advancedevents.PerilEvent;
import model.event.advancedevents.PerilTiming;
import model.event.trigger.EndPhaseTrigger;

public class BaseEventController  {

	private StartUpTrigger startuptrigger;
	private EndPhaseTrigger endtrigger;
	public StartUpTrigger getStartuptrigger() {
		return startuptrigger;
	}
	public void setStartuptrigger(StartUpTrigger startuptrigger) {
		this.startuptrigger = startuptrigger;
	}
	public EndPhaseTrigger getEndtrigger() {
		return endtrigger;
	}
	public void setEndtrigger(EndPhaseTrigger endtrigger) {
		this.endtrigger = endtrigger;
	}
	public BaseEventController(StartUpTrigger startuptrigger, EndPhaseTrigger endtrigger) {
		super();
		this.startuptrigger = startuptrigger;
		this.endtrigger = endtrigger;
		endtrigger.addTrigger(new PerilEvent(PerilTiming.TEST));
	}
	
	
	
	
}
