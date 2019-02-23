package model.event;

import view.Items.Map.ViewDoor;
import view.game.GameDoor;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class OpenDoorTrigger extends Trigger implements OpenDoorListener,NameChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewDoor door;
	private boolean namebased=true;
	
	public ViewDoor getDoor() {
		return door;
	}


	public void setDoor(ViewDoor door) {
		this.door = door;
	}


	public OpenDoorTrigger(ViewDoor viewDoor) {
		super();
		setDoor(viewDoor);
		setIDName("OpenDoor " +door.getIDName());
		restateName(door.getName());
	
		viewDoor.addNameChangeListener(this);
		
	}


	@Override
	public void DoorOpened(GameDoor door) {
		// TODO Auto-generated method stub
		trigger();
	}

	public void select() {
		super.select();
		door.select();
	}
	public void deselect() {
		super.deselect();
		door.deselect();
	}
	
	public void trigger() {
		System.out.println("opendoor triggers");
		for(Univent ev:actions) {
			System.out.println("opendoortrigger"+ev.getIDName());
			ev.trigger();
		}
	}


	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		super.initialise(questCreator);
	}


	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("open door "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}
	public Univent copy() {
		 OpenDoorTrigger toreturn=new OpenDoorTrigger(door);
		 this.addAllTriggers(toreturn);
		return toreturn;
	}

}
