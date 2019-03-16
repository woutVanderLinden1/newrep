package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



import misc.save.WorldSaveFile;
import model.Tile.tilesets.ConnectionInDoor;
import model.Tile.tilesets.ConnectionOutDoor;
import model.Tile.tilesets.EndGapOutDoor;
import model.Tile.tilesets.EndGapTile;
import model.Tile.tilesets.EntranceIndoor;
import model.Tile.tilesets.EntranceOutDoor;
import model.Tile.tilesets.ExitIndoor;
import model.Tile.tilesets.ExitOutDoor;
import model.Tile.tilesets.OrginalTile;
import model.Tile.tilesets.coreset.*;
import model.Tile.tilesets.coresetOutDoor.*;
import model.event.StartUpTrigger;
import model.generators.ModifierGenerator;
import model.generators.TextGenerator;
import model.generators.ValueGenerator;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.CustomValue;
import view.events.BaseField;
import view.viewItems.ItemBox.ListContainer;
import view.viewItems.ItemBox.ValueChangeListener;

public  class ItemController {

    private HashMap<String, Item> biMap = new HashMap<String, Item>();

    private CustomInteger hope=new CustomInteger("Hope",0);
	public CustomInteger getPeril() {
		return peril;
	}


	public void setPeril(CustomInteger peril) {
		this.peril = peril;
	}


	public CustomInteger getFame() {
		return fame;
	}


	public void setFame(CustomInteger fame) {
		this.fame = fame;
	}


	public CustomInteger getGold() {
		return gold;
	}


	public void setGold(CustomInteger gold) {
		this.gold = gold;
	}


	public CustomInteger getDespair() {
		return despair;
	}


	public void setDespair(CustomInteger despair) {
		this.despair = despair;
	}


	public CustomInteger getHope() {
		return hope;
	}


	private CustomInteger peril=new CustomInteger("peril",0);
	private CustomInteger fame=new CustomInteger("fame",0);
	private CustomInteger gold=new CustomInteger("gold",0);
	private CustomInteger despair=new CustomInteger("despair",0);
    private ArrayList<ValueChangeListener> valuelisteners=new ArrayList<ValueChangeListener>();
    private static ArrayList<Item> coresetOutDoorTiles=new ArrayList<Item>(Arrays.asList(
			new Tile1A(),
			new Tile2A(),
			new Tile3A(),
			new Tile4A(),
			new Tile5A(),
			new Tile6A(),
			new Tile7A(),
			new Tile8A(),
			new Tile9A(),
			new Tile10A(),
			new Tile11A(),
			new Tile12A(),
			new Tile13A(),
			new Tile14A(),
			new Tile15A(),
			new Tile16A(),
			new Tile17A(),
			new Tile18A(),
			new Tile19A(),
			new Tile20A(),
			new Tile21A(),
			new Tile22A(),
			new Tile23A(),
			new Tile24A(),
			new Tile25A(),
			new Tile26A(),
			new Tile27A(),
			new Tile28A(),
			new Tile29A(),
			new Tile30A()
			));
	private static ArrayList<Item> coresetTiles=new ArrayList<Item>(Arrays.asList(
			new Tile1B(),
			new Tile2B(),
			new Tile3B(),
			new Tile4B(),
			new Tile5B(),
			new Tile6B(),
			new Tile7B(),
			new Tile8B(),
			new Tile9B(),
			new Tile10B(),
			new Tile11B(),
			new Tile12B(),
			new Tile13B(),
			new Tile14B(),
			new Tile15B(),
			new Tile16B(),
			new Tile17B(),
			new Tile18B(),
			new Tile19B(),
			new Tile20B(),
			new Tile21B(),
			new Tile22B(),
			new Tile23B(),
			new Tile24B(),
			new Tile25B(),
			new Tile26B(),
			new Tile27B(),
			new Tile28B(),
			new Tile29B(),
			new Tile30B()
			));
	private static ArrayList<Item> essentialTiles=new ArrayList<Item>(Arrays.asList(
			new EndGapTile(),
			new ExitIndoor(),
			new EntranceIndoor(),
			new ConnectionInDoor(),
			new EndGapOutDoor(),
			new ExitOutDoor(),
			new EntranceOutDoor(),
			new ConnectionOutDoor()
			
			
			));
	
    private ArrayList<Item> customvalues=new ArrayList<Item>(
    		Arrays.asList(
    				hope,
    				peril,
    				despair,
    				fame,
    				gold
    		));
	private ArrayList<Item> generators=new ArrayList<Item>(
			Arrays.asList(
			new ModifierGenerator()	,
			new TextGenerator()	,
			new ValueGenerator()
			));
     
    private static ItemController control;
    
    public static ItemController getItemController() {
    	if(control==null) {
    		control=new ItemController();
    	}
    	return control;
    }
	
	
    public ItemController() {

        biMap.put("1A",coresetTiles.get(0) );
        biMap.put("2A",coresetTiles.get(1));
        biMap.put("3A",coresetTiles.get(2) );
        biMap.put("4A",coresetTiles.get(3));
        biMap.put("5A",coresetTiles.get(4));
        biMap.put("6A",coresetTiles.get(5));
        biMap.put("7A",coresetTiles.get(6));
        biMap.put("8A",coresetTiles.get(7));
        biMap.put("9A",coresetTiles.get(8));
        biMap.put("10A",coresetTiles.get(9));
        biMap.put("11A",coresetTiles.get(10));
        biMap.put("12A",coresetTiles.get(11));
        biMap.put("13A",coresetTiles.get(12));
        biMap.put("14A",coresetTiles.get(13));
        biMap.put("15A",coresetTiles.get(14));
        biMap.put("16A",coresetTiles.get(15));
        biMap.put("17A",coresetTiles.get(16));
        biMap.put("18A",coresetTiles.get(17));
        biMap.put("19A",coresetTiles.get(18));
        biMap.put("20A",coresetTiles.get(19));
        biMap.put("21A",coresetTiles.get(20));
        biMap.put("22A",coresetTiles.get(21));
        biMap.put("23A",coresetTiles.get(22));
        biMap.put("24A",coresetTiles.get(23));
        biMap.put("25A",coresetTiles.get(24));
        biMap.put("26A",coresetTiles.get(25));
        biMap.put("27A",coresetTiles.get(26));
        biMap.put("28A",coresetTiles.get(27));
        biMap.put("29A",coresetTiles.get(28));
        biMap.put("30A",coresetTiles.get(29));
        biMap.put("1B",coresetOutDoorTiles.get(0));
        biMap.put("2B",coresetOutDoorTiles.get(1));
        biMap.put("3B",coresetOutDoorTiles.get(2));
        biMap.put("4B",coresetOutDoorTiles.get(3));
        biMap.put("5B",coresetOutDoorTiles.get(4));
        biMap.put("6B",coresetOutDoorTiles.get(5));
        biMap.put("7B",coresetOutDoorTiles.get(6));
        biMap.put("8B",coresetOutDoorTiles.get(7));
        biMap.put("9B",coresetOutDoorTiles.get(8));
        biMap.put("10B",coresetOutDoorTiles.get(9));
        biMap.put("11B",coresetOutDoorTiles.get(10));
        biMap.put("12B",coresetOutDoorTiles.get(11));
        biMap.put("13B",coresetOutDoorTiles.get(12));
        biMap.put("14B",coresetOutDoorTiles.get(13));
        biMap.put("15B",coresetOutDoorTiles.get(14));
        biMap.put("16B",coresetOutDoorTiles.get(15));
        biMap.put("17B",coresetOutDoorTiles.get(16));
        biMap.put("18B",coresetOutDoorTiles.get(17));
        biMap.put("19B",coresetOutDoorTiles.get(18));
        biMap.put("20B",coresetOutDoorTiles.get(19));
        biMap.put("21B",coresetOutDoorTiles.get(20));
        biMap.put("22B",coresetOutDoorTiles.get(21));
        biMap.put("23B",coresetOutDoorTiles.get(22));
        biMap.put("24B",coresetOutDoorTiles.get(23));
        biMap.put("25B",coresetOutDoorTiles.get(24));
        biMap.put("26B",coresetOutDoorTiles.get(25));
        biMap.put("27B",coresetOutDoorTiles.get(26));
        biMap.put("28B",coresetOutDoorTiles.get(27));
        biMap.put("29B",coresetOutDoorTiles.get(28));
        biMap.put("30B",coresetOutDoorTiles.get(29));
        
        
    	
    }


	public void increaseAvailability(Item item) {
		// TODO Auto-generated method stub
		item.availabilityIncreased();
		Item t=biMap.get(item.getName());
		/*
		if(t==null) {
			t=biMap.inverse().get(item);
		}
		*/
		if(t!=null){
			t.availabilityIncreased();
		}
		
	}


	public void decreaseAvailability(Item item) {
		// TODO Auto-generated method stub
		item.availabilityDecreased();
		Item t=biMap.get(item.getName());
		/*
		if(t==null) {
			t=biMap.inverse().get(item);
		}
		*/
		if(t!=null){
			t.availabilityDecreased();
		}
	}


	public ArrayList<Item> getCoresetOutDoorTiles() {
		// TODO Auto-generated method stub
		return coresetOutDoorTiles;
	}


	public ArrayList<Item> getCoresetTiles() {
		// TODO Auto-generated method stub
		return coresetTiles;
	}


	public ArrayList<Item> getEssentialTiles() {
		// TODO Auto-generated method stub
		return essentialTiles;
	}


	public void addValue(Item customBoolean) {
		// TODO Auto-generated method stub
		customvalues.add(customBoolean);
		
	}


	public ArrayList<Item> getValues() {
		// TODO Auto-generated method stub
		return customvalues;
	}


	public ArrayList<Item> getGenerators() {
		// TODO Auto-generated method stub
		return generators;
	}


	public void saveThis(WorldSaveFile file) {

			// TODO Auto-generated method stub
			for(Item value:customvalues) {
				file.addCustomValue((CustomValue) value);
			}
			//file.setBaseTrigger((StartUpTrigger)basetrigger);
			
		
	}


	public void readValues(WorldSaveFile g) {
		System.out.println("values are read");
		for(CustomValue v:g.getCustomValues()) {
			System.out.println(v);
		}
		customvalues.addAll(g.getCustomValues());
		triggerValueChangeListeners();
		
	}


	private void triggerValueChangeListeners() {
		for(ValueChangeListener valuelistener:valuelisteners) {
			valuelistener.trigger();
		}
		
	}


	public void addChangeValueListener(ValueChangeListener listContainer) {
		valuelisteners.add(listContainer);
		
	}


	public static void reset() {
		control=null;
		// TODO Auto-generated method stub
		
	}


	public void addAllValues(CustomInteger[] hope) {
		customvalues.addAll(Arrays.asList(hope));
	}


	public void setHope(CustomInteger hope) {
		// TODO Auto-generated method stub
		this.hope=hope;
	}
    
}
