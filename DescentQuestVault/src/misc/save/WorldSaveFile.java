package misc.save;

import java.io.Serializable;
import java.util.ArrayList;

import model.event.StartUpTrigger;
import model.event.Univent;
import model.values.CustomBoolean;
import model.values.CustomInteger;

public class WorldSaveFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CustomInteger> customIntegers;
	private ArrayList<CustomBoolean> customBooleans;
	private ArrayList<Univent> univentList=new ArrayList<Univent>();
	private StartUpTrigger baseTrigger;
	
	
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

}
