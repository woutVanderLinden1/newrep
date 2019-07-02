package model.event.advancedevents.peril;

import java.util.ArrayList;

import model.event.EventTriggerStack;
import model.event.GameLoseEvent;
import model.event.Univent;
import model.event.extraevents.TextStop;

public class EndGamePeril extends Peril {

	
	
	public void trigger(int value) {
		ArrayList<Univent> totrigger=new ArrayList<Univent>();
		if(value<=0) {
			totrigger.add(new GameLoseEvent());
		}
		else {
			totrigger.add(new TextStop("The game will end in " + value +" turns"));
		}
		EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
		triggerstack.addNewEvents(totrigger);
		triggerstack.triggerNextStackEvent();
		
	}
}
