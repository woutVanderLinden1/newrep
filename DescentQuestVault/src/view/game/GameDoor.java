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
		activationList=toplace.getActivations();
		this.setOpenact(toplace.getOpenact());
		if(toplace.isClosed()) {
			activationList.clear();
		}

		
	}

	
	public void setTriggers(ViewDoor toplace) {
		toplace.getOpenDoorTrigger().setGameDoor(this);
		this.setOpenDoorTrigger(toplace.getOpenDoorTrigger());
		toplace.getPlaceDoorEvent().setGameDoor(this);
		this.setPlaceDoorEvent(toplace.getPlaceDoorEvent());
		toplace.getRemoveDoorEvent().setGameDoor(this);
		this.setRemoveDoorEvent(toplace.getRemoveDoorEvent());
		if(toplace.getUnlockDoorEvent()!=null) {
			toplace.getUnlockDoorEvent().setDoor(this);
			this.setUnlockDoorEvent(toplace.getUnlockDoorEvent());
			
		}
		
	}
	@Override
	public boolean isActivateAble() {
		return true;
	}

	@Override
	public void UnlockDoor() {
		//openact=new OpenDoorActivation(this);
		super.UnlockDoor();
		if(this.activationList==null) {
			activationList=new ArrayList<Activation>();
		}
		activationList.add(openact);
		
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
