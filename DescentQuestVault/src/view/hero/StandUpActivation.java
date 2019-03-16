package view.hero;

import controller.UserInputController;
import controller.commands.Game.StandUpHeroCommand;
import model.Activation;
import model.event.Trigger;
import view.viewItems.NameChangeListener;

public class StandUpActivation extends Activation  {

	private GameHero defeated;
	private StandUpTrigger trig;
	
	
	public StandUpActivation(GameHero gameHero) {
		defeated=gameHero;
		trig=new StandUpTrigger(gameHero);
		this.changeName("stand up "+defeated.getName());
	}

	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		control.performCommand(new StandUpHeroCommand(defeated));
		

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Stand Up";
	}

	

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return trig;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		this.trig=(StandUpTrigger) trig;
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	

}
