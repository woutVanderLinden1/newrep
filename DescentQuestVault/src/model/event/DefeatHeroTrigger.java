package model.event;

import view.hero.GameHero;
import view.viewItems.NameChangeListener;

public class DefeatHeroTrigger extends Trigger implements NameChangeListener {
	
	private GameHero hero;

	public DefeatHeroTrigger(GameHero defeated) {
		hero=defeated;
		this.setName("defeat "+hero.getName());
		hero.addNameChangeListener(this);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nameChanged(String newname) {
		this.setName("defeat "+hero.getName());
	}

}
