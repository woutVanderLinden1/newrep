package view.game;

import model.Activation;

public abstract class MonsterActivation extends Activation{


	protected GameMonster monster;
	public GameMonster getMonster() {
		return monster;
	}

	public void setMonster(GameMonster monster) {
		this.monster = monster;
	}

}
