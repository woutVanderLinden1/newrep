package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import monstercreator.SingleMovement;

public class RemoveMinionMovementOptionCommand extends BasicCommand implements ICommand {
	private SingleMovement ment;
	public RemoveMinionMovementOptionCommand(SingleMovement ment2) {
		this.ment=ment;
	}
	@Override
	public void perform() {
		ment.removeFromMinionMovement();
		view.refreshSelected();
	}

}
