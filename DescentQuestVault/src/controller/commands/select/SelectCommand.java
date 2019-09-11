package controller.commands.select;

import controller.commands.BasicCommand;
import controller.stack.StackElements.SelectstackElement;
import view.Items.Map.MapItem;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewTile;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class SelectCommand extends BasicCommand {

	private SelectAble item;
	
	/*
	public SelectCommand( item) {
		//mainStack.addStackElement(new SelectstackElement(item));
		this.item=item;
	}
	*/

	public SelectCommand(SelectAble containingTile) {
		item=containingTile;
	}
	

	public SelectCommand(MapItem topItem) {
		item=topItem;
	}

	public void perform() {
		System.out.println("new tile selected");
		view.setSelected(item);
		if(item.getKind()==SelectKind.VIEWMONSTER) {
			ViewMonster monster=(ViewMonster) item;
			control.startMonsterPlacement(monster.getPlaceMonsterSquares(), monster);
		}
	}
}
