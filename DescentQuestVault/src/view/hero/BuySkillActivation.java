package view.hero;

import controller.UserInputController;
import model.Activation;
import model.event.Trigger;

public class BuySkillActivation extends Activation {
	private GameHero hero;

	public BuySkillActivation(GameHero gameHero) {
		this.hero=gameHero;
	}

	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		control.startSkillShop(hero);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Learn Skill";
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

	}

}
