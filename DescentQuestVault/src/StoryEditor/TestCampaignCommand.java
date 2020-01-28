package StoryEditor;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.ItemController;

public class TestCampaignCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		ItemController control=ItemController.getItemController();
		ProgressStatus status=new ProgressStatus();
		//ItemController itcontrol=ItemController.getItemController();
		//status.setFile(itcontrol.getCampaingFile());
		StoryEditorController storycontrol=StoryEditorController.getStoryEditorController();
		status.setFile(storycontrol.getCampaingFile());
		storycontrol.startStory(status);
		storycontrol.minimizeFrame();
		
		

	}

}
