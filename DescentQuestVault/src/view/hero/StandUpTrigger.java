package view.hero;

import model.event.Trigger;
import model.event.Univent;
import view.viewItems.NameChangeListener;

public class StandUpTrigger extends Trigger implements NameChangeListener {

	private GameHero hero;
	
	public StandUpTrigger(GameHero gameHero) {
		hero=gameHero;
		this.setName("stand up "+hero.getName());
		hero.addNameChangeListener(this);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.setName("stand up "+hero.getName());
	}

}
