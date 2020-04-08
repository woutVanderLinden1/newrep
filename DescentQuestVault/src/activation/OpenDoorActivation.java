package activation;

import java.util.ArrayList;

import model.Activation;
import model.event.Trigger;
import view.Items.Map.ViewDoor;
import view.game.GameDoor;

public class OpenDoorActivation extends Activation {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948411201192434462L;
	private Trigger opendoortrigger;
	
	public OpenDoorActivation(ViewDoor viewDoor) {
		opendoortrigger=viewDoor.getOpenDoorTrigger();
		//this.setName
	}

	public OpenDoorActivation(Trigger opendoortrigger2) {
		// TODO Auto-generated constructor stub
		opendoortrigger=opendoortrigger2;
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

	@Override
	public Activation clone() {
		// TODO Auto-generated method stub
		return new OpenDoorActivation(opendoortrigger);
	}

	
}
