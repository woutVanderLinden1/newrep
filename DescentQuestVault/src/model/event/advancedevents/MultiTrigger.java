package model.event.advancedevents;

import java.util.ArrayList;

import model.event.EventTriggerStack;
import model.event.Trigger;
import model.event.Univent;
import model.event.extraevents.TextOption;
import view.events.BaseField;
import view.menu.QuestCreator;

public class MultiTrigger extends Trigger {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2944084166067614602L;
	private ArrayList<Trigger> triggerchoices=new ArrayList<Trigger>();
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void triggerHere(ArrayList<Univent> totrigger) {
		//do nothing
		
		/*
		    	for(Univent ev:totrigger) {
		    		ev.trigger();
		    	}
		    	*/
	
	
		//keep them as a continue prepared
		//if continue pressed trigger the rest
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		for(Trigger vent:triggerchoices) {
			//questCreator.addUniventToTrigger(vent,this,false);
			vent.initialise(questCreator);
		}
	}
	public ArrayList<Trigger> getTriggerchoices() {
		if(this.triggerchoices==null) {
			this.triggerchoices=new ArrayList<Trigger>();
		}
		return triggerchoices;
	}
	public void setTriggerchoices(ArrayList<Trigger> triggerchoices) {
		
		this.triggerchoices = triggerchoices;
	}
	public void addTriggerChoice(Trigger choice) {
		triggerchoices.add(choice);
	}
	protected void removeTriggerChoice(TextOption opt) {
		triggerchoices.remove(opt);
		
	}




}
