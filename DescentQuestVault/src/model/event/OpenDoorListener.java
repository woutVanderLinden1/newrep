package model.event;

import view.Items.Map.ViewDoor;
import view.game.GameDoor;

public interface OpenDoorListener {
 
	public void DoorOpened(GameDoor door);
}
