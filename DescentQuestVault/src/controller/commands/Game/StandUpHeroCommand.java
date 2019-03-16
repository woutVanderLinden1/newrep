package controller.commands.Game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.hero.GameHero;

public class StandUpHeroCommand extends BasicCommand implements ICommand {

	private GameHero hero;
	
	public StandUpHeroCommand(GameHero defeated) {
		hero=defeated;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		hero.standUp();
	}

}
