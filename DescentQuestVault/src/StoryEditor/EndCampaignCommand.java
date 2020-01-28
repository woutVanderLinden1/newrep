package StoryEditor;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class EndCampaignCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.endCampaign();
	}

}
