package controller.command.storyeditor;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class OpenStoryEditorCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		control.openStoryEditor();
			

	

	}

}
