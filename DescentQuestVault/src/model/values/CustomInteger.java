package model.values;

import java.util.ArrayList;

import model.ItemController;
import model.event.IfIntegerTrigger;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ValueChangeListener;

public class CustomInteger extends CustomValue<Integer> {

	
	private int theInteger=0;
	private transient ArrayList<ValueChangeListener> valuechangelisteners=new ArrayList<ValueChangeListener>();
	
	private static int nrintegers=0;
	
	private static String getNewName() {
		nrintegers++;
		return("CustomInteger"+nrintegers);
	}
	
	public CustomInteger(String name, Integer value) {
		super(name, value);
		setTheInteger(value);
		// TODO Auto-generated constructor stub
	}
	

	public CustomInteger() {
		super(getNewName(), 0);
		// TODO Auto-generated constructor stub
	}

	public int getTheInteger() {
		return theInteger;
	}

	public void setTheInteger(int theInteger) {
		this.theInteger = theInteger;
		
		triggerValueChangeListeners();
	}

	private void triggerValueChangeListeners() {
		if(valuechangelisteners==null) {
			return;
		}
		for(ValueChangeListener listen: valuechangelisteners) {
			System.out.println("valuechangelisteners are "+listen);
			listen.valueChanged(theInteger);
		}
		
	}

	@Override
	public ValueKind getValueKind() {
		// TODO Auto-generated method stub
		return ValueKind.INTEGER;
	}

	public void addValueChangeListener(ValueChangeListener itemController) {
		// TODO Auto-generated method stub
		if(valuechangelisteners==null) {
			valuechangelisteners=new ArrayList<ValueChangeListener>();
		}
		valuechangelisteners.add(itemController);
	}

	public void addValue(int value) {
		
		setTheInteger(theInteger+value);
	}

	@Override
	public void setTo(CustomValue value2) {
		// TODO Auto-generated method stub
		setTheInteger(((CustomInteger) value2).getTheInteger());
	}
}
