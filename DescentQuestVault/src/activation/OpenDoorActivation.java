package activation;

import java.util.ArrayList;

import model.Activation;
import model.event.Trigger;
import view.game.GameDoor;

public class OpenDoorActivation extends Activation {

	
	private Trigger opendoortrigger;
	
	public OpenDoorActivation(GameDoor gameDoor) {
		opendoortrigger=gameDoor.getOpenDoorTrigger();
	}

	public void trigger() {
		opendoortrigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Open door";
	}
}
