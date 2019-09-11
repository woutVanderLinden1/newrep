package controller.analyzer;

import StoryEditor.DraggAblePanel;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.events.BaseField;

public class StartDragPanelCommand extends BasicCommand implements ICommand {

	private DraggAblePanel todrag;
	private int x;
	private int y;
	private int onscreenX;
	private int onscreenY;
	
	

	public StartDragPanelCommand(DraggAblePanel todrag, int x, int y, int onscreenX, int onscreenY) {
		super();
		this.todrag = todrag;
		this.x = x;
		this.y = y;
		this.onscreenX = onscreenX;
		this.onscreenY = onscreenY;
	}

	@Override
	public void perform() {
		System.out.println("selectddragging");
		view.startDragDragPanel(todrag);
		view.setSelected(todrag);
		
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		
		control.startDrag(todrag,onscreenX,onscreenY);
		control.resetColors();
		
		//color=new Color(255,255,255,97);
	

		//control.keepEventMoveChange(todrag.getTriggerField(),todrag);

	}

}
