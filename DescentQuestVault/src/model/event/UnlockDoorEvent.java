package model.event;

import view.Items.Map.ViewDoor;
import view.game.GameDoor;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class UnlockDoorEvent extends Event implements NameChangeListener {
	
	private GameDoor door;
	private ViewDoor viewdoor;

	public UnlockDoorEvent(ViewDoor viewDoor) {
		this.setName("unlock door "+ viewDoor.getName());
		this.setIDName("unlock door "+ viewDoor.getIDName());
		this.viewdoor=viewDoor;
		viewdoor.addNameChangeListener(this);
		// TODO Auto-generated constructor stub
	}

	public UnlockDoorEvent(GameDoor door2, ViewDoor viewdoor2) {
		door=door2;
		viewdoor=viewdoor2;
	}

	public GameDoor getDoor() {
		return door;
	}

	public void setDoor(GameDoor door) {
		this.door = door;
	}

	public ViewDoor getViewdoor() {
		return viewdoor;
	}

	public void setViewdoor(ViewDoor viewdoor) {
		this.viewdoor = viewdoor;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	@Override
	public void trigger() {
		door.UnlockDoor();
	}
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new UnlockDoorEvent(door,viewdoor);
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.setName("unlock door "+ viewdoor.getName());
	//	this.setIDName("unlock door"+ viewdoor.getIDName());
	}

	

}
