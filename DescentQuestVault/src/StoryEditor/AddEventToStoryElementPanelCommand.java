package StoryEditor;

import java.awt.Point;

import controller.analyzer.Analyzer;
import controller.commands.BasicCommand;
import view.events.BaseField;
import view.events.EventField;
import view.viewItems.ItemBox.SelectAble;

public class AddEventToStoryElementPanelCommand extends BasicCommand {

	private SubDragPanel panel;
	private Point point;
	private DraggAblePanel select;
	
	public AddEventToStoryElementPanelCommand(SubDragPanel panel2, Point point, DraggAblePanel selectAble) {
		panel=panel2;
		this.point=point;
		select=selectAble;
	}

	@Override
	public void perform() {
		control.removeOldTemporary();
		control.endOldMove(select);
		// TODO Auto-generated method stub
		panel.addDraggablePanel(point,select);
		control.endTileMove();
		
		
		
	}

}
