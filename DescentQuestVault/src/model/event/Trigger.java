package model.event;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import controller.UserInputController;
import controller.commands.Game.HoldToContinueCommand;
import view.events.EventItem;
import view.events.TriggerItem;
import view.game.GameDoor;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;

public abstract class Trigger extends Univent implements EventBase {


	/**
	 * 
	 */
	private boolean stopped=false;
	private static final long serialVersionUID = 1L;
	
	private transient ArrayList<AddNewEventListener> addeventlisteners=new ArrayList<AddNewEventListener>();
	public SelectKind getKind() {
		return SelectKind.TRIGGER;
	}
	
	protected ArrayList<Univent> actions;
	
	public Trigger() {
		actions=new ArrayList<Univent>();
	}
	
	public ArrayList<Univent> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Univent> actions) {
		this.actions = actions;
	}

	public void trigger() {
		triggerHere(actions);
		/*
		boolean stopped=false;
		ArrayList<Univent> events=new ArrayList<Univent>();
		for(Univent ev:actions) {
			if(!stopped) {
				ev.trigger();
				if(ev.isStopEvent()) {
					stopped=true;
				}
				else {
					events.add(ev);
				}
			}
			
		}
		*/
		
		//keep them as a continue prepared
		//if continue pressed trigger the rest
	}
	
	public void triggerHere(ArrayList<Univent> totrigger) {
	
		EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
		triggerstack.addNewEvents(totrigger);
		
		triggerstack.triggerNextStackEvent();
		/*
		    	for(Univent ev:totrigger) {
		    		ev.trigger();
		    	}
		    	*/
	
	
		//keep them as a continue prepared
		//if continue pressed trigger the rest
	}

	public void addEvent(Event placeTileEvent) {
		
		if(!actions.contains(placeTileEvent)) {
			actions.add(placeTileEvent);
		}
	
		
	}
	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return new TriggerItem(this);
	}


	public void addTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		if(!actions.contains(trig)) {
			actions.add(trig);
		}
	
		
	}

	public void removeEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		actions.remove(placeTileEvent);
	}

	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		actions.remove(trigger);
	}

	public void removeUnivent(Univent univent) {
		// TODO Auto-generated method stub
		actions.remove(univent);
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		for(Univent vent:actions) {
			//questCreator.addUniventToTrigger(vent,this,false);
			vent.initialise(questCreator);
		}
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Univent> getUnivents() {
		// TODO Auto-generated method stub
		return actions;
	}

	public void addNewEvent(SingleMovementEvent singleMovementEvent) {
		this.addEvent(singleMovementEvent);
		triggerAddNewEventListeners(singleMovementEvent);
	}

	private void triggerAddNewEventListeners(SingleMovementEvent singleMovementEvent) {
		for(AddNewEventListener listen:addeventlisteners) {
			listen.eventAdded(singleMovementEvent);
		}
		
	}
	public void addNewEventListener(AddNewEventListener list) {
		if(addeventlisteners==null) {
			addeventlisteners=new ArrayList<AddNewEventListener>();
		}
		addeventlisteners.add(list);
	}
	protected void addAllTriggers(Trigger toreturn) {
		for(Univent vent:this.getUnivents()) {
			Univent v=vent.copy();
			switch(v.getKind()) {
		
			case EVENT:
				this.addEvent((Event)v);
				break;
			
			case MODIFIER:
			case TRIGGER:
				this.addTrigger((Trigger)v);
				break;
		
			default:
				break;
			
			}
		}
		
	}

	public boolean isMultiTrigger() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	
}
