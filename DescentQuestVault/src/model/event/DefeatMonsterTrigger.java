package model.event;

import view.Items.Map.ViewMonster;

public class DefeatMonsterTrigger extends Trigger {

	private ViewMonster mon;
	
	
	public DefeatMonsterTrigger(ViewMonster viewMonster) {
		mon=viewMonster;
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new DefeatMonsterTrigger(mon);
	}

}
