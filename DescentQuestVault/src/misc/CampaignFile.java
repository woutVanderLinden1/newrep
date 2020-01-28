package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;
import StoryEditor.EndElementPanel;
import StoryEditor.EndStoryEvent;
import StoryEditor.StartElementPanel;
import StoryEditor.StartStoryEvent;
import StoryEditor.StoryEvent;
import StoryEditor.ViewArrow;
import misc.save.WorldSaveFile;
import model.Hero.MikleoHero;
import model.event.Event;
import model.values.CustomValue;
import view.Items.Map.SubQuestFile;
import view.events.EventItem;


public class CampaignFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//has all values this campaing has
	protected HashMap<String,CustomValue> values=new HashMap<String,CustomValue>();
	protected String teamname;
	protected StoryEvent startevent;
	protected StoryEvent	endEvent;
	private BaseFile herofile;
	private ArrayList<SubQuestFile> doneSubQuests=new ArrayList<SubQuestFile>();
	private ArrayList<ActionTaker> cityEvents=new ArrayList<ActionTaker>();
	//protected ArrayList<ViewArrow> arrows;
	
	public BaseFile generateBaseFile() {
		//generate a new basefile were all file values are standard
		BaseFile bas=new BaseFile(2,0,0,0, teamname);
		for(CustomValue val:values.values()) {
			bas.addValue(val);
		}
		herofile=bas;
		return bas;
		
	}
	
	public ArrayList<ActionTaker> getCityEvents() {
		return cityEvents;
	}

	public void setCityEvents(ArrayList<ActionTaker> cityEvents) {
		this.cityEvents = cityEvents;
	}

	public BaseFile generateBaseFile(String teamname,int nrofheroes) {
		//generate a new basefile were all file values are standard
		this.teamname=teamname;
		BaseFile bas=new BaseFile(nrofheroes,0,0,0, teamname);
		for(CustomValue val:values.values()) {
			bas.addValue(val);
		}
		
	
		
		
		herofile=bas;
		return bas;
		
	}
	
	public HashMap<String, CustomValue> getValues() {
		return values;
	}
	public void setValues(HashMap<String, CustomValue> values) {
		this.values = values;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public void saveAll(HashMap<String, CustomValue> customvalues) {
		//values.clear();
		values.putAll(customvalues);
		
	}
	public void addEventPanel(StartElementPanel startPanel) {
		//create a list of the events from the startpanel
		
	}
	public void addStartEventPanel(StartStoryEvent startfield) {
		startevent=startfield;
	
		
	}
	public void addEndEventPanel(EndStoryEvent endPanel) {
		// TODO Auto-generated method stub
		endEvent=endPanel;
	}
	public StoryEvent getNextEvent(StoryEvent currentevent) {
		if(currentevent==null) {
			return startevent;
		}
		return currentevent.nextEvent();
	}


	public String getTeamName() {
		// TODO Auto-generated method stub
		return teamname;
	}

	public StartStoryEvent getStartEvent() {
		// TODO Auto-generated method stub
		return (StartStoryEvent) startevent;
	}

	public void setStartEvent(StartStoryEvent startEvent2) {
		startevent=startEvent2;
		
	}

	public BaseFile getBaseFile() {
		// TODO Auto-generated method stub
		return herofile;
	}

	public void addHeroExp(int amount) {
		// TODO Auto-generated method stub
		herofile.addHeroExp(amount);
	}

	public Collection<SubQuestFile> getDoneSubQuests() {
		// TODO Auto-generated method stub
		return doneSubQuests;
	}

	public void addCityEvent(ActionTaker take) {
		// TODO Auto-generated method stub
		cityEvents.add(take);
	}

	public HashMap<String, CustomValue> getBaseFileValues() {
		// TODO Auto-generated method stub
		return herofile.getValues();
	}



	
	
}
