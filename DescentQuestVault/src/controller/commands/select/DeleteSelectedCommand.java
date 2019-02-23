package controller.commands.select;

import controller.commands.BasicCommand;

public class DeleteSelectedCommand extends BasicCommand {

	@Override
	public void perform() {
		view.deleteSelected();
	}
}
