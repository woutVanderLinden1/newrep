package controller.commands;



import view.Items.Map.ViewDoor;
import view.Items.Map.ViewSquare;
import view.viewItems.DoorItem;

public class AddDoorToViewSquareCommand extends BasicCommand implements ICommand {

	private DoorItem door;
	private ViewSquare square;
	private ViewDoor viewdoor;
	
	public DoorItem getDoor() {
		return door;
	}

	public void setDoor(DoorItem door) {
		this.door = door;
	}

	public ViewSquare getSquare() {
		return square;
	}

	public void setSquare(ViewSquare square) {
		this.square = square;
	}

	public AddDoorToViewSquareCommand(DoorItem holded, ViewSquare square) {
		setSquare(square);
		setDoor(holded);
	}
	
	public AddDoorToViewSquareCommand(ViewDoor holded, ViewSquare square2) {
		viewdoor=holded;
		setSquare(square2);
	}


	public void perform() {
		System.out.println("placed tile");
		if(door!=null) {
			view.addDoorToSquare(door,square);
		}
		else {
			view.addViewDoorToSquare(viewdoor,square);
		}
		
		control.endTileMove();
	//	view.addDoorToSquare(door,square);
	}



}
