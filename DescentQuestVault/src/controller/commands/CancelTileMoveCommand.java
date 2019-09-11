package controller.commands;

import view.events.BaseField;

public class CancelTileMoveCommand extends BasicCommand implements ICommand {

	public void perform() {
	//	System.out.println("canceled tilemove");
		//control.endEventMove((BaseField) view.getSelected());
		view.getSelected().setVisible();
		control.removeOldTemporary();
		control.cancelMove();
	}
}
