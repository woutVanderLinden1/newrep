package model.event.monster;

import model.Monster.Monster;
import model.event.MonsterTurnTrigger;
import model.event.SingleMovementEvent;
import model.event.Trigger;
import view.viewItems.MonsterItem;

public class DefaultZombieMoveTrigger extends MonsterTurnTrigger {

	
	public DefaultZombieMoveTrigger(Monster mon) {
		super(mon);
		this.addEvent(new SingleMovementEvent(new MonsterItem(mon)));
		this.setName("Zombie Movement");
		this.setIDName("ZombieMovement");
	}
}
