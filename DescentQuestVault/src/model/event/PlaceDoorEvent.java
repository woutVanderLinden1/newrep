package model.event;

import java.awt.Color;

import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.PlaceGameTileCommand;
import view.Items.Map.ViewDoor;
import view.game.GameDoor;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class PlaceDoorEvent extends Event implements NameChangeListener {

	private ViewDoor toplace;
	private GameDoor gamedoor;
	private PlaceGameDoorCommand command;
	private boolean namebased=true;
	
	public PlaceDoorEvent(ViewDoor viewDoor) {
		toplace=viewDoor;
		setCommand(new PlaceGameDoorCommand(viewDoor));
		commands.add(command);
		setIDName("placeDoor "+ toplace.getIDName());
		setName("place door "+ toplace.getName());
		viewDoor.addNameChangeListener(this);
		
	}

	public ViewDoor getToplace() {
		return toplace;
	}

	public void setToplace(ViewDoor toplace) {
		this.toplace = toplace;
	}

	public PlaceGameDoorCommand getCommand() {
		return command;
	}

	public void setCommand(PlaceGameDoorCommand command) {
		this.command = command;
	}
	
	public void select() {
		super.select();
		toplace.select();
	}
	public void deselect() {
		super.deselect();
		toplace.deselect();
	}

	//place the door in the gamegrid
	@Override
	public void initialise(QuestCreator questCreator) {
		toplace.initialise();
		ViewDoor door=questCreator.addViewDoorToSquare(toplace,toplace.getBaseSquare());
		door.deselect();
	}
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("place door "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new PlaceDoorEvent(toplace);
	}

	public void setGameDoor(GameDoor gameDoor) {
		// TODO Auto-generated method stub
		gamedoor=gameDoor;
	}
	

}
