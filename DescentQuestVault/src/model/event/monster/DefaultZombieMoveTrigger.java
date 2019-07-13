package model.event.monster;

import java.util.ArrayList;

import model.Monster.Monster;
import model.event.MonsterSpecial;
import model.event.MonsterTurnTrigger;
import model.event.SingleMovementEvent;
import model.event.Trigger;
import view.viewItems.MonsterItem;

public class DefaultZombieMoveTrigger extends MonsterTurnTrigger {

	
	public DefaultZombieMoveTrigger(Monster mon) {
		super(mon);
		this.addEvent(new SingleMovementEvent(new MonsterItem(mon)));
		this.setName("Monster Movement");
		this.setIDName("MonsterMovement");
		this.addMonsterSpecials(mon.getMonsterSpecials());
	
	}

	
}
