package model.event;

import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.RemoveGameDoorCommand;
import view.Items.Map.ViewDoor;
import view.game.GameDoor;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class RemoveDoorEvent extends Event implements NameChangeListener{

	
	private ViewDoor toremove;
	private transient GameDoor gamedoor;
	private transient RemoveGameDoorCommand command;
	private boolean namebased=true;
	
	public RemoveDoorEvent(ViewDoor viewDoor) {
		toremove=viewDoor;
	
		setIDName("removeDoor "+ toremove.getIDName());
		setName("remove door "+ toremove.getName());
		viewDoor.addNameChangeListener(this);
	}

	public RemoveDoorEvent(ViewDoor viewDoor, GameDoor gamedoor2) {
		toremove=viewDoor;
		
		//commands.add(command);
		setIDName("removeDoor "+ toremove.getIDName());
		setName("remove door "+ toremove.getName());
		viewDoor.addNameChangeListener(this);
		setGameDoor(gamedoor2);
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
		return new RemoveDoorEvent(toremove,gamedoor);
	}

	public void setGameDoor(GameDoor gameDoor) {
		// TODO Auto-generated method stub
		removeCommand(command);
		gamedoor=gameDoor;
		setCommand(new RemoveGameDoorCommand(gameDoor));
		addCommand(command);
	}
}
