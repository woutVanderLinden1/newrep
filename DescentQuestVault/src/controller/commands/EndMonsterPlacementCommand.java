package controller.commands;

import java.util.ArrayList;

import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;

public class EndMonsterPlacementCommand extends BasicCommand implements ICommand {

	private ArrayList<ViewSquare> chekedsquares;
	private ViewMonster monster;
	
	public EndMonsterPlacementCommand(ArrayList<ViewSquare> chekedMainSquare,ViewMonster monster) {
		chekedsquares=chekedMainSquare;
		this.monster=monster;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.endMonsterPlacement();
		monster.setAsPlaceMentSquares(chekedsquares);
	}

}
