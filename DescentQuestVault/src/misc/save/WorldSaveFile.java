package misc.save;

import java.io.Serializable;
import java.util.ArrayList;

import controller.BaseEventController;
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
	private BaseEventController control;

	private ArrayList<Univent> univentList=new ArrayList<Univent>();
	
	
	public ArrayList<CustomValue> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(ArrayList<CustomValue> customValues) {
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
		customValues.add(value);
	}

	public void setBaseEventControl(BaseEventController baseEventControl) {
		// TODO Auto-generated method stub
		control=baseEventControl;
	}

}
