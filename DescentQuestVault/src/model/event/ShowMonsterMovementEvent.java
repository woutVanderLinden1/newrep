package model.event;

import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.Game.ShowMonsterMovementCommand;
import view.game.MonsterKind;
import view.menu.QuestCreator;
import view.viewItems.MonsterItem;

public class ShowMonsterMovementEvent extends Event {

	private MonsterItem mon;
	private ArrayList<MovementString> minionmovement;
	private MovementString continousMinionEffect;
	private MonsterKind minion;
	

	public ShowMonsterMovementEvent(MonsterItem mon, ArrayList<MovementString> minionmovement,
			MovementString continousMinionEffect, MonsterKind minion) {
		super();
		this.mon = mon;
		this.minionmovement = minionmovement;
		this.continousMinionEffect = continousMinionEffect;
		this.minion = minion;
	}
	
	public void trigger() {
		UserInputController control=UserInputController.getController();
		
		control.performCommand(new ShowMonsterMovementCommand(mon,minionmovement,continousMinionEffect,minion));
			
		
		
		
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isStopEvent() {
		return true;
	}

}
