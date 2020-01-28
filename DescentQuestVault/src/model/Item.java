package model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;

import controller.IController;
import controller.UserInputController;
import controller.command.RemoveItemFromListsCommand;
import controller.commands.RenewItemListsCommand;
import view.Items.Map.MapItem;
import view.viewItems.ItemBox.AvailabilityChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ListContainer;

public abstract class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -175861789918526868L;
	protected String name;
	protected boolean available=true;
	private transient ArrayList<AvailabilityChangeListener> availabilityChangeListeners=new ArrayList<AvailabilityChangeListener>();
	
	protected int availability;


	public Item(String name) {
		this.name=name;
		this.availability=1;
	}
	
	public Item(String name,int availability) {
		this.name=name;
		this.availability=availability;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public abstract int[][] getShape() ;


	public abstract double getScaleWidth();

	public abstract double getScaleHeight();


	public int getXoff() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getYoff() {
		// TODO Auto-generated method stub
		return -15;
	}


	public abstract int getLeftOff();
	public abstract int getRightOff();
	public abstract int getBottomOff();
	public abstract int getTopOff();


	public abstract ItemOptions getItemKind();


	public void increaseAvailability() {
		ItemController control=ItemController.getItemController();
		control.increaseAvailability(this);
		
	}


	public void decreaseAvailability() {
		ItemController control=ItemController.getItemController();
		control.decreaseAvailability(this);
	}

	public void availabilityIncreased() {
		// TODO Auto-generated method stub
		availability++;
		if(this.getAvailability()==1) {
			//add this back to available elements
			available=true;
			//RenewItemListsCommand comm=new RenewItemListsCommand(this);
			//UserInputController control= UserInputController.getController();
			//control.performCommand(comm);
			notifyAvailabilityListenersAddition();
		}
	}


	private void notifyAvailabilityListenersAddition() {
		for(AvailabilityChangeListener listen:availabilityChangeListeners) {
			listen.added(this);
		}
		
	}

	private int getAvailability() {
		// TODO Auto-generated method stub
		return availability;
	}


	public void availabilityDecreased() {
		availability--;
		System.out.println("availablility decreased");
		// TODO Auto-generated method stub
		if(this.getAvailability()<=0) {
			//remove the item from the lists
			available=false;
			//RenewItemListsCommand comm=new RenewItemListsCommand(this);
			//UserInputController control= UserInputController.getController();
			//control.performCommand(comm);
			noyifyAvailabilityListenersDepletion();
		}
	}




	private void noyifyAvailabilityListenersDepletion() {
		for(AvailabilityChangeListener listen:availabilityChangeListeners) {
			listen.depleted(this);
		}
		
	}

	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return available;
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		
	}

	public void addAvailabilityChangeListener(ListContainer listContainer) {
		// TODO Auto-generated method stub
		if(availabilityChangeListeners==null) {
			availabilityChangeListeners=new ArrayList<AvailabilityChangeListener>();
		}
		availabilityChangeListeners.add(listContainer);
	}

	public MapItem createItem() {
		// TODO Auto-generated method stub
		return null;
	}



}
