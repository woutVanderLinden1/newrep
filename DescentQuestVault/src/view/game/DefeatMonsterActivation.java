package view.game;

import model.Activation;

public class DefeatMonsterActivation extends Activation {
	
	private GameMonster monster;

	public DefeatMonsterActivation(GameMonster gameMonster) {
		// TODO Auto-generated constructor stub
		monster=gameMonster;
	}

	@Override
	public void trigger() {
		//remove the monster
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "defeated";
	}

}
