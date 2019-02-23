package model;

import java.util.ArrayList;

import model.values.CustomValue;

public class GameFile {

	private ArrayList<CustomValue> gamevalues=new ArrayList<CustomValue>();
	
	
	public GameFile() {
		
	}
	
	public void addCustomValue(CustomValue val) {
		gamevalues.add(val);
	}
}
