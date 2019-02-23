package controller.commands.select;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.Trigger;
import model.event.extraevents.TextOption;
import view.events.BaseField;
import view.events.ModifierField;
import view.events.TriggerField;

public class AddMultiTriggerToTriggerFieldCommand extends BasicCommand implements ICommand {

	private BaseField field;
	private TriggerField basetrigger;
	






	@Override
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("dothis");
		control.endEventMove(field);
		view.addEventToTriggerField(field,basetrigger);
	}

}
