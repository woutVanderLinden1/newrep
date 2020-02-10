package model.values;

import java.util.ArrayList;

import model.Item;
import model.event.Univent;
import view.game.mappanel.ValueChanger;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;
import view.viewItems.ItemBox.ValueChangeListener;

public abstract class CustomValue<P> extends Item implements ValueChanger {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6673949013681395429L;
	protected P value;
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	
	
	private ArrayList<ValueChangeListener> list=new ArrayList<ValueChangeListener>();
	
	

	public void setName(String name) {
		this.name = name;
		this.triggerNameChangeListeners(name);
	}

	

	public P getValue() {
		return value;
	}



	public void setValue(P value) {
		this.value = value;
		notifylist();
	}



	private void notifylist() {
		for(ValueChangeListener listen:list) {
			listen.valueChanged((int) value);
		}
		
	}



	public CustomValue(String name, P value) {
		super(name);
		//this.setIDName(name);
		this.setName(name);
		this.value=value;
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .33;
	}

	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemOptions getItemKind() {
		// TODO Auto-generated method stub
		return ItemOptions.Value;
	}



	public abstract ValueKind getValueKind();


	@Override
	public void addValueChangeListener(ValueChangeListener listen) {
		// TODO Auto-generated method stub
		list.add(listen);
	}

	public abstract void setTo(CustomValue value2);



	public void addNameChangeListener(NameChangeListener listen) {
		if(namechangelisteners==null) {
			namechangelisteners=new ArrayList<NameChangeListener>();
		}
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}

	


}
