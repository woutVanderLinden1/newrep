package controller.commands.select;

import controller.UserInputController;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;
import view.events.BaseField;
import view.events.TriggerField;

public class AddSelectedToTriggerFieldCommand extends BasicCommand{

	private TriggerField field;
	
	public AddSelectedToTriggerFieldCommand(TriggerField thefield) {
		field=thefield;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		System.out.println("added selected to triggerfield");
		// TODO Auto-generated method stub
		control.removeOldTemporary();
		control.endOldMove((BaseField) view.getSelected());
		view.addSelectedToTriggerField(field);
		control.endTileMove();
	}


}
