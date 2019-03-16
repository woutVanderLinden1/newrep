package view.hero;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class DefeatHeroCommand extends BasicCommand implements ICommand {

	private GameHero hero;
	
	public DefeatHeroCommand(GameHero defeated) {
		
		hero=defeated;
	}

	@Override
	public void perform() {
	
		view.defeatHero(hero);
		control.defeatHero(hero);
	}

}
