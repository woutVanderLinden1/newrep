package controller.commands.select;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import monstercreator.SingleMovement;

public class AddMinionMovementOptionCommand extends BasicCommand implements ICommand {
	
	private SingleMovement ment;
	private String newname;
	
	public AddMinionMovementOptionCommand(SingleMovement ment, String string) {
		this.ment=ment;
		newname=string;
	}

	@Override
	public void perform() {
		ment.addToMinionMovement(newname);
		view.refreshSelected();
	}

}
