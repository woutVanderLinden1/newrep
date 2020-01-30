package model.event.advancedevents.peril;

import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.Game.ShowTextCommand;
import model.event.EventEndListener;
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
			UserInputController control=UserInputController.getController();
			control.performCommand(new ShowTextCommand("The game will end in " + value +" turns",new EventEndListener() {

				@Override
				public void eventEnded() {
					triggerEventEndListeners();
				}
				
			}));
		}
		EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
		triggerstack.addNewEvents(totrigger);
		triggerstack.triggerNextStackEvent();
		
	}
}
