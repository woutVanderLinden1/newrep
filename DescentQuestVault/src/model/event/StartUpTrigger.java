package model.event;

import controller.UserInputController;
import controller.commands.StartTurnCommand;
import view.menu.QuestCreator;

/**
 * the trigger that happens at startup
 * each panel ahs this as standard placeevent;
 * @author User
 *
 */
public class StartUpTrigger extends Trigger implements StartGameListener{

	public StartUpTrigger(){
		setIDName("StartUp");
		setName("Start Game");
	}
	@Override
	public void gameStarted() {
		// TODO Auto-generated method stub
		trigger();
		UserInputController control=UserInputController.getController();
		control.performCommand(new StartTurnCommand());
	}

	public Univent copy() {
		 StartUpTrigger toreturn=new StartUpTrigger();
		 this.addAllTriggers(toreturn);
		return toreturn;
	}

	
	
	

}
