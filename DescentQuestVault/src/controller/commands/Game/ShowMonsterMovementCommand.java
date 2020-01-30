package controller.commands.Game;

import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.EventEndListener;
import model.event.MovementString;
import model.event.SingleMovementEvent;
import model.event.extraevents.StopAble;
import view.Items.Map.ViewMonster;
import view.game.GameMonster;
import view.game.MonsterKind;
import view.viewItems.MonsterItem;

public class ShowMonsterMovementCommand extends BasicCommand implements ICommand {

	private MonsterItem monster;
	private ArrayList<MovementString> movement;
	private MovementString continousEffect;
	private MonsterKind kind;
	private EventEndListener listen;

	
	public ShowMonsterMovementCommand(MonsterItem mon, ArrayList<MovementString> arrayList,
		MovementString movementString,MonsterKind monsterkind,EventEndListener listen) {
		monster=mon;
		movement=arrayList;
		continousEffect=movementString;
		kind=monsterkind;
		this.listen=listen;
		
	}

	@Override
	public void perform() {
		
		view.showMonsterMovement(monster,movement,continousEffect,kind,listen);

	}

}
