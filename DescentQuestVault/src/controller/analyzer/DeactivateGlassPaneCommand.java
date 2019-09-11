package controller.analyzer;

import StoryEditor.DragPanel;
import controller.commands.BasicCommand;

public class DeactivateGlassPaneCommand extends BasicCommand {

	private DragPanel todrag;
	
	public DeactivateGlassPaneCommand(DragPanel parent) {
		todrag=parent;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		todrag.deactivateGlassPane();
	}

}
