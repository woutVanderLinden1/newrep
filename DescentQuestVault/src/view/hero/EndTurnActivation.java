package view.hero;

import controller.UserInputController;
import controller.command.game.EndTurnCommand;
import model.Activation;


public class EndTurnActivation extends Activation {

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		UserInputController control=UserInputController.getController();
		control.performCommand(new EndTurnCommand());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "End Turn";
	}

}
