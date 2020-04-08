package view.hero;

import controller.UserInputController;
import model.Activation;
import model.event.DefeatHeroTrigger;
import model.event.Trigger;

public class DefeatActivation extends Activation {

	private GameHero defeated;
	private DefeatHeroTrigger defeattrigger;
	
	public DefeatActivation(GameHero defeated) {
		super();
		defeattrigger=new DefeatHeroTrigger(defeated);
		this.defeated = defeated;
	}

	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		control.performCommand(new DefeatHeroCommand(defeated));
		defeattrigger.trigger();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Defeated";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return defeattrigger;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		defeattrigger=(DefeatHeroTrigger) trig;
	}

	@Override
	public Activation clone() {
		// TODO Auto-generated method stub
		return new DefeatActivation(defeated);
	}

}
