package view.game;

import java.util.ArrayList;

import activation.OpenDoorActivation;
import misc.ActivateAble;
import model.Activation;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewSquare;
import view.viewItems.DoorItem;

public class GameDoor extends ViewDoor implements ActivateAble {

	private ViewDoor doorbasic;
	
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	
	public GameDoor(ViewSquare square, ViewDoor toplace) {
		super((DoorItem)toplace.getImageItem(),square,0,0);
		this.setTriggers(toplace);
		doorbasic=toplace;
		activationList.add(new OpenDoorActivation(this));
	}

	@Override
	public boolean isActivateAble() {
		return true;
	}

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}
	
	public boolean isBaseDoor(ViewDoor door) {
		return (door.equals(doorbasic));
	}

}
