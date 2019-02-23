package model.event;

import java.io.Serializable;
import java.util.ArrayList;

import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectKind;

public abstract class Univent implements EventBase,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String IDName;
	private String name;
	
	@Override
	public abstract SelectKind getKind();

	@Override
	public void select() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deselect() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setIDName(String newIDName) {
		IDName=newIDName;

	}

	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return IDName;
	}
	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public abstract void trigger();

	public abstract void initialise(QuestCreator questCreator);

	public void setName(String newname) {
		name=newname;
		triggerNameChangeListeners(newname);
	}
	
	
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	
	public void addNameChangeListener(NameChangeListener listen) {
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}
	public String getName() {
		return name;
	}
	
	public void changeName(String newname) {
		this.setName(newname);
	}

	public boolean isStopEvent() {
		// TODO Auto-generated method stub
		return false;
	}
	public void makeInvisible() {
		
	}
	public void makeVisible() {
		
	}

	public abstract Univent copy();
}
