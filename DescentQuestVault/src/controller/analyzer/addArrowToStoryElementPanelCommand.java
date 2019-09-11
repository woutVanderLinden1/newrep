package controller.analyzer;

import StoryEditor.DragPanel;
import StoryEditor.DraggAblePanel;
import controller.commands.BasicCommand;

public class addArrowToStoryElementPanelCommand extends BasicCommand {
	
	private DraggAblePanel targetpanel;
	private DragPanel parent;

	public addArrowToStoryElementPanelCommand(DraggAblePanel hovered, DragPanel parent) {
		targetpanel=hovered;
		this.parent=parent;
	}

	@Override
	public void perform() {
		parent.addArrowToDefault( targetpanel);
		
	}

}
