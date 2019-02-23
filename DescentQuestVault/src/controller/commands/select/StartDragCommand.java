package controller.commands.select;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;

public class StartDragCommand extends BasicCommand implements ICommand {

	private SelectAble toselect;
	private int x;
	private int y;
	
	public StartDragCommand(SelectAble selected,int x ,int y) {
		toselect=selected;
		this.x=x;
		this.y=y;
	}

	public void perform() {
	
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		control.startDrag(toselect,x,y);
	}
	
}
