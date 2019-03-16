package view.hero;

import controller.UserInputController;
import controller.command.game.EndTurnCommand;
import model.Activation;
import model.Hero.Hero;
import model.event.EndTurnTrigger;
import model.event.Trigger;


public class EndTurnActivation extends Activation {
	private GameHero hero;
	private EndTurnTrigger trig;

	public EndTurnActivation(GameHero hero) {
		this.hero=hero;
		trig=new EndTurnTrigger(hero);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		UserInputController control=UserInputController.getController();
		control.performCommand(new EndTurnCommand(hero));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "End Turn";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		this.trig=(EndTurnTrigger) trig;
	}

}
