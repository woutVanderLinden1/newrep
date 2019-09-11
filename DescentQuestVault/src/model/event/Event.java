package model.event;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.ICommand;
import view.events.EventItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public abstract class Event extends Univent implements EventBase {

	protected ArrayList<ICommand> commands;


	
	public Event() {
		commands=new ArrayList<ICommand>();
	}
	

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.EVENT;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return new EventItem(this);
	}
	

	public void trigger() {
		UserInputController control=UserInputController.getController();
		for(ICommand comm:commands) {
			control.performCommand(comm);
		}
		
	}


	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		
	}

}
