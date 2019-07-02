package activation;

import java.util.ArrayList;

import model.Activation;
import model.event.Trigger;
import view.Items.Map.ViewDoor;
import view.game.GameDoor;

public class OpenDoorActivation extends Activation {

	
	private Trigger opendoortrigger;
	
	public OpenDoorActivation(ViewDoor viewDoor) {
		opendoortrigger=viewDoor.getOpenDoorTrigger();
		//this.setName
	}

	public void trigger() {
		opendoortrigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Open door";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return opendoortrigger;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		opendoortrigger=trig;
	}

	
}
