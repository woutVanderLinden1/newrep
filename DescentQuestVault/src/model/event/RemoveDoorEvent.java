package model.event;

import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.RemoveGameDoorCommand;
import view.Items.Map.ViewDoor;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class RemoveDoorEvent extends Event implements NameChangeListener{

	
	private ViewDoor toremove;
	private RemoveGameDoorCommand command;
	private boolean namebased=true;
	
	public RemoveDoorEvent(ViewDoor viewDoor) {
		toremove=viewDoor;
		setCommand(new RemoveGameDoorCommand(viewDoor));
		commands.add(command);
		setIDName("removeDoor "+ toremove.getIDName());
		setName("remove door "+ toremove.getName());
		viewDoor.addNameChangeListener(this);
	}

	public ViewDoor getToremove() {
		return toremove;
	}

	public void setToremove(ViewDoor toremove) {
		this.toremove = toremove;
	}

	public RemoveGameDoorCommand getCommand() {
		return command;
	}

	public void setCommand(RemoveGameDoorCommand command) {
		this.command = command;
	}

	public void select() {
		super.select();
		toremove.select();
	}
	public void deselect() {
		super.deselect();
		toremove.deselect();
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		//only place initialises the door
	}
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("remove door "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new RemoveDoorEvent(toremove);
	}
}
