package controller;

import model.event.Trigger;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextStop;

public class ContinueElement {
	
	private StopAble trigger;

	public ContinueElement(StopAble trigger2) {
		super();
		this.trigger = trigger2;
	}

	public StopAble getStop() {
		return trigger;
	}

	public void setStop(StopAble trigger) {
		this.trigger = trigger;
	}

}
