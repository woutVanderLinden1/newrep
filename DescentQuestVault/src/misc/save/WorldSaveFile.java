package misc.save;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import controller.BaseEventController;
import misc.CampaignFile;
import model.event.StartUpTrigger;
import model.event.Univent;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.CustomValue;

public class WorldSaveFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private ArrayList<CustomInteger> customIntegers;
//	private ArrayList<CustomBoolean> customBooleans;
	private HashMap<String,CustomValue> customValues=new HashMap<String,CustomValue>();
	private BaseEventController control;
	private String name;
	

	private ArrayList<Univent> univentList=new ArrayList<Univent>();
	
	
	public HashMap<String,CustomValue> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(HashMap<String,CustomValue> customValues) {
		this.customValues = customValues;
	}

	
	
	

	public BaseEventController getControl() {
		return control;
	}

	public void setControl(BaseEventController control) {
		this.control = control;
	}

	public void addUnivent(Univent univent) {
		univentList.add(univent);
		
	}

	public ArrayList<Univent> getUnivents() {
		// TODO Auto-generated method stub
		return univentList;
	}

	public void addCustomValue(CustomValue value) {
		customValues.put(value.getName().toLowerCase(),value);
	}

	public void setBaseEventControl(BaseEventController baseEventControl) {
		// TODO Auto-generated method stub
		control=baseEventControl;
	}

	public void saveCustomValues(HashMap<String, CustomValue> customvalues2) {
		// TODO Auto-generated method stub
		
		customValues.putAll(customvalues2);
	}

	public void setName(String name) {
		this.name=name;
		
	}

	public String getName() {
		return name;
	}


}
