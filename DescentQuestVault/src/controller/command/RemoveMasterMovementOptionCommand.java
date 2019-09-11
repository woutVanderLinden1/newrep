package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import monstercreator.SingleMovement;

public class RemoveMasterMovementOptionCommand extends BasicCommand implements ICommand {

	private SingleMovement ment;
	public RemoveMasterMovementOptionCommand(SingleMovement ment2) {
		this.ment=ment2;
	}
	@Override
	public void perform() {
		ment.removeFromMasterMovement();
		view.refreshSelected();
	}

}
