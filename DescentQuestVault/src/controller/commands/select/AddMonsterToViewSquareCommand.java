package controller.commands.select;

import java.awt.Color;
import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.commands.ShowOccupiedCommand;
import controller.stack.StackElements.ChangedColorStackElement;
import view.Items.Map.MapItem;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;

public class AddMonsterToViewSquareCommand extends BasicCommand implements ICommand {

	private MonsterItem monster;
	private ViewMonster viewmonster;
	private ViewSquare square;
	
	

	public AddMonsterToViewSquareCommand(MonsterItem holded, ViewSquare square) {
		monster=holded;
		this.square=square;
	}

	public AddMonsterToViewSquareCommand(ViewMonster holded, ViewSquare square2) {
		viewmonster=holded;
		this.square=square2;
	}

	public void perform() {
		System.out.println("placed tile");
		MapItem it=null;
		if(monster!=null) {
			it=view.addItemToSquare(monster,square);
		}
		else {
			it=view.addViewItemToSquare(viewmonster,square);
		}
		
		control.endTileMove();
		control.startMonsterPlacement(((ViewMonster) it).getPlaceMonsterSquares(),(ViewMonster) it);
		
		//	view.addDoorToSquare(door,square);
		control.performCommand(new ShowOccupiedCommand(square, (ShapeItem) monster.getImageItem(), new Color(40,225,40,95)));
		
	}
}
