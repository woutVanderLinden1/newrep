package view.Items.Map;

import controller.UserInputController;
import controller.commands.BasicCommand;

public class OpenMainMenuCommand extends BasicCommand {

	@Override
	public void perform() {
		UserInputController control=UserInputController.getController();
		control.openMainMenu();
		

	}

}
