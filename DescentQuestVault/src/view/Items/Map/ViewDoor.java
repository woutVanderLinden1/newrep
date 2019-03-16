package view.Items.Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import frame.SubContainer;
import model.Activation;
import model.event.Event;
import model.event.OpenDoorTrigger;
import model.event.PlaceDoorEvent;
import model.event.PlaceTileEvent;
import model.event.RemoveDoorEvent;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.DoorItem;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class ViewDoor extends MapItem implements SelectAble {

	private int scalefactor=260;
	private PlaceDoorEvent placeDoorEvent;
	private OpenDoorTrigger openDoorTrigger;
	private RemoveDoorEvent removeDoorEvent;
	
	private static int doornr=0;
	
	private static String giveDoorName() {
		doornr++;
		return "door"+doornr;
	}
	
	public PlaceDoorEvent getPlaceDoorEvent() {
		return placeDoorEvent;
	}

	public void setPlaceDoorEvent(PlaceDoorEvent placeDoorEvent) {
		this.placeDoorEvent = placeDoorEvent;
	}

	public OpenDoorTrigger getOpenDoorTrigger() {
		return openDoorTrigger;
	}

	public void setOpenDoorTrigger(OpenDoorTrigger openDoorTrigger) {
		this.openDoorTrigger = openDoorTrigger;
	}

	public RemoveDoorEvent getRemoveDoorEvent() {
		return removeDoorEvent;
	}

	public void setRemoveDoorEvent(RemoveDoorEvent removeDoorEvent) {
		this.removeDoorEvent = removeDoorEvent;
	}

	public ViewDoor(DoorItem door, ViewSquare baseSquare, int i, int j) {
		super(door,baseSquare,i,j);
		this.setName(giveDoorName());
		setPlaceDoorEvent(new PlaceDoorEvent(this));
		setOpenDoorTrigger(new OpenDoorTrigger(this));
		setRemoveDoorEvent(new RemoveDoorEvent(this));
		
		
	}






	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWDOOR;
	}


	public int[][] getShape() {
		// TODO Auto-generated method stub
		return ((ShapeItem)this.getImageItem()).getShape();
	}
	public void setSquare(ViewSquare square) {
		System.out.println("square set "+square.getxID()+" "+square.getyID());
		baseSquare=square;
		point=new Point(square.getX(),square.getY());
	}

	public String getIDName() {
		// TODO Auto-generated method stub
		return item.getIDName();
	}



	
	public void setTriggers(ViewDoor toplace) {
		this.setOpenDoorTrigger(toplace.getOpenDoorTrigger());
		this.setPlaceDoorEvent(toplace.getPlaceDoorEvent());
		this.setRemoveDoorEvent(toplace.getRemoveDoorEvent());
		
	}


	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		ArrayList<Univent> toreturn=new ArrayList<Univent>();
		toreturn.add(placeDoorEvent);
		toreturn.add(removeDoorEvent);
		toreturn.add(this.openDoorTrigger);
		return toreturn; 
	}

	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		
	}
	
	



	

}
