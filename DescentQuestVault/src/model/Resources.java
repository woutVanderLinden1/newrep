package model;

import java.util.ArrayList;
import java.util.Arrays;

import model.Monster.*;
import model.door.ClosedDoor;
import model.door.NormalDoor;
import model.event.Event;
import model.event.*;
import model.event.Trigger;
import model.event.advancedevents.PlaceSpecialMonsterEvent;
import model.event.advancedevents.SearchEffectEvent;
import model.event.advancedevents.peril.ConditionEffect;
import model.event.advancedevents.peril.DamageMajorPerilEvent;
import model.event.advancedevents.peril.MajorMonsterPerilEvent;
import model.event.advancedevents.peril.PerilDamageEvent;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextStop;
import model.event.extraevents.TextTrigger;
import model.search.ObjectiveToken;
import model.search.SearchToken;
import model.values.Modification;
import view.events.EventItem;

public class Resources {
	/*
	public static Event[] events={
			new PlaceSpecialMonsterEvent(),
			
			
	};
	*/
	public static Trigger[] triggers={
			
			new TextTrigger(1, new ArrayList<TextOption>(), "test"),
			
	};
	
	public static ArrayList<Item> AvailableMonsters=new ArrayList<Item>(Arrays.asList(
			new Zombie(),
			new Barghest(),
			new Spider(),
			new Merroid(),
			new GoblinArcher(),
			new FleshMoulder(),
			new Ettin(),
			new Elemental(),
			new ShadowDragon(),
			new Tentacle()
			
			));
	
	public static ArrayList<Event> AvailableEvents=new ArrayList<Event>(Arrays.asList(
			new PlaceSpecialMonsterEvent(),
			new TextStop(),
			new PerilDamageEvent(2),
			new MajorMonsterPerilEvent(),
			new DamageMajorPerilEvent(8),
			new ConditionEffect(Condition.Poison),
			new SearchEffectEvent(),
			new ModifyGoldEvent(Modification.PLUS),
			new ModifyFameEvent(Modification.PLUS),
			new ModifyHopeEvent(Modification.PLUS),
			new ModifyPerilEvent(Modification.PLUS),
			new ModifyDespairEvent(Modification.PLUS)
			
			
			));
	public static ArrayList<Item> AvailableTokens=new ArrayList<Item>(Arrays.asList(
			new SearchToken(),
			new ObjectiveToken()
			));
	public static ArrayList<Item> AvailableDoors=new ArrayList<Item>(Arrays.asList(
			new NormalDoor(), 
			new ClosedDoor()
			
			));
	
	public static ArrayList<Item> getAvailableMonsters() {
		// TODO Auto-generated method stub
		return AvailableMonsters;
	}

	public static ArrayList<Event> getAvailableEvents(){
		return AvailableEvents;
	}
	
	public static ArrayList<Item> getAvailableTokens() {
		// TODO Auto-generated method stub
		return AvailableTokens;
	}

	public static ArrayList<Item> getAvailableDoors() {
		// TODO Auto-generated method stub
		return AvailableDoors;
	}

}
