package model.event.advancedevents.peril;

import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.Game.ShowTextCommand;
import model.event.EventEndListener;
import model.event.EventTriggerStack;
import model.event.GameLoseEvent;
import model.event.Univent;
import model.event.extraevents.TextStop;

public class TextPeril extends Peril {

	private String perilEffect;
	
	public void trigger() {
		ArrayList<Univent> totrigger=new ArrayList<Univent>();
		UserInputController control=UserInputController.getController();
		control.performCommand(new ShowTextCommand("a peril is happening",new EventEndListener() {

			@Override
			public void eventEnded() {
				triggerEventEndListeners();
			}
			
		}));
		
		
		this.actions.addAll(totrigger);
		super.trigger();
		
	}
}
