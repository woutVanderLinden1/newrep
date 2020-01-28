package StoryEditor;

import java.util.ArrayList;

import ItemEditor.ActionTaker;
import misc.CampaignFile;
import misc.save.WorldSaveFile;
import model.event.Event;

public class CampaignSaveFile extends WorldSaveFile {

	private StartStoryEvent startEvent;
	private ArrayList<ActionTaker> cityEvents=new ArrayList<ActionTaker>();

	public ArrayList<ActionTaker> getCityEvents() {
		return cityEvents;
	}

	public void setCityEvents(ArrayList<ActionTaker> cityEvents) {
		this.cityEvents = cityEvents;
	}

	public StartStoryEvent getStartEvent() {
		return startEvent;
	}

	public void setStartEvent(StartStoryEvent startEvent) {
		this.startEvent = startEvent;
	}
	
	public void addCityEvent(ActionTaker event) {
		cityEvents.add(event);
	}


	
	
}
