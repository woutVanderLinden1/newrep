package model.event;

import view.Items.Map.ViewMonster;
import view.viewItems.NameChangeListener;

public class DefeatMonsterTrigger extends Trigger implements NameChangeListener {

	private ViewMonster mon;
	
	
	public DefeatMonsterTrigger(ViewMonster viewMonster) {
		mon=viewMonster;
		this.setName("defeat "+viewMonster.getName());
		viewMonster.addNameChangeListener(this);
	}
	//public void setMonster(mon)

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new DefeatMonsterTrigger(mon);
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.setName("defeat "+mon.getName());
	}

}
