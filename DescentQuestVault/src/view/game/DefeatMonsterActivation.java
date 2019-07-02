package view.game;

import controller.UserInputController;
import controller.command.game.RemoveGameMonsterCommand;
import model.Activation;
import model.event.DefeatMonsterTrigger;
import model.event.Trigger;

public class DefeatMonsterActivation extends MonsterActivation {
	
	private DefeatMonsterTrigger trigger;

	public DefeatMonsterActivation(GameMonster gameMonster) {
		// TODO Auto-generated constructor stub
		monster=gameMonster;
		trigger=monster.getDefeatTrigger();
	}
	
	public void setMonster(GameMonster monster) {
		this.monster = monster;
		trigger=monster.getDefeatTrigger();
		//trigger
	}

	public DefeatMonsterActivation() {
		// TODO Auto-generated constructor stub
	}

	public DefeatMonsterActivation(DefeatMonsterTrigger defeatTrigger) {
		trigger=defeatTrigger;
	}

	@Override
	public void trigger() {
		//remove the monster
		monster.defeat();
		trigger.trigger();
		UserInputController controller=UserInputController.getController();
		controller.performCommand(new RemoveGameMonsterCommand(monster) );

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "defeated";
	}

	@Override
	public void changeName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return trigger;
	}

	@Override
	public void setTrigger(Trigger trig) {
		// TODO Auto-generated method stub
		trigger=(DefeatMonsterTrigger) trig;
	}

}
