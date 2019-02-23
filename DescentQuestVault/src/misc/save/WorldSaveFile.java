package misc.save;

import java.io.Serializable;
import java.util.ArrayList;

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
	private ArrayList<CustomValue> customValues=new ArrayList<CustomValue>();

	private ArrayList<Univent> univentList=new ArrayList<Univent>();
	private StartUpTrigger baseTrigger;
	
	public ArrayList<CustomValue> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(ArrayList<CustomValue> customValues) {
		this.customValues = customValues;
	}

	
	
	public StartUpTrigger getBaseTrigger() {
		return baseTrigger;
	}

	public void setBaseTrigger(StartUpTrigger baseTrigger) {
		this.baseTrigger = baseTrigger;
	}

	public void addUnivent(Univent univent) {
		univentList.add(univent);
		
	}

	public ArrayList<Univent> getUnivents() {
		// TODO Auto-generated method stub
		return univentList;
	}

	public void addCustomValue(CustomValue value) {
		customValues.add(value);
	}

}
