package model.event.advancedevents.peril;

import java.util.ArrayList;

import model.event.EventTriggerStack;
import model.event.GameLoseEvent;
import model.event.Univent;
import model.event.extraevents.TextStop;

public class TextPeril extends Peril {

	private String perilEffect;
	
	public void trigger() {
		ArrayList<Univent> totrigger=new ArrayList<Univent>();
		
		totrigger.add(new TextStop("a peril is happening"));
		
		
		this.actions.addAll(totrigger);
		super.trigger();
		
	}
}
