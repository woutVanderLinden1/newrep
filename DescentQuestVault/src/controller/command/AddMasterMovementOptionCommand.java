package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import monstercreator.SingleMovement;

public class AddMasterMovementOptionCommand extends BasicCommand implements ICommand {

	private SingleMovement ment;
	private String newname;
	
	public AddMasterMovementOptionCommand(SingleMovement ment, String string) {
		this.ment=ment;
		newname=string;
	}

	@Override
	public void perform() {
		ment.addToMasterMovement(newname);
		view.refreshSelected();
	}

}
