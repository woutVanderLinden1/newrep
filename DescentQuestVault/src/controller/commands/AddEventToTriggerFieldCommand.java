package controller.commands;

import java.awt.event.ActionListener;

import model.event.Event;
import model.event.SingleMovementEvent;
import model.event.Trigger;
import model.event.modifier.Modifier;
import view.events.BaseField;
import view.events.EventField;
import view.events.TriggerField;

public class AddEventToTriggerFieldCommand extends BasicCommand implements ICommand {

	private BaseField field;
	private TriggerField basetrigger;
	
	public AddEventToTriggerFieldCommand(BaseField field, TriggerField baseTrigger) {
		this.field=field;
		this.basetrigger=baseTrigger;
	}

	
	public AddEventToTriggerFieldCommand(Event vent, TriggerField newfield) {
		this.field=new EventField(vent, 0);
		basetrigger=newfield;
	}


	

	



	public void perform() {
		
		control.removeOldTemporary();
		control.endOldMove(field);
		view.addEventToTriggerField(field,basetrigger);
		control.endTileMove();
	}
}
