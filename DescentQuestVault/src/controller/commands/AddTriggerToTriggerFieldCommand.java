package controller.commands;

import model.event.Trigger;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextTrigger;
import view.events.BaseField;
import view.events.ModifierField;
import view.events.MultiTextTriggerField;
import view.events.TriggerField;

public class AddTriggerToTriggerFieldCommand extends BasicCommand implements ICommand {

	private BaseField field;
	private TriggerField basetrigger;
	
	
	public AddTriggerToTriggerFieldCommand(BaseField field,TriggerField basetrigger) {
		this.field=field;
		this.basetrigger=basetrigger;
	}
	
	public AddTriggerToTriggerFieldCommand(Trigger vent, TriggerField object) {
		switch(vent.getKind()) {
		case MODIFIER:
			this.field=new ModifierField(vent,0,0);
			this.basetrigger=object;
			break;
		case TRIGGER:
			this.field=new TriggerField(vent,0,0);
			this.basetrigger=object;
			break;
		default:
			break;
		
		}
		
	}
	
	public AddTriggerToTriggerFieldCommand(TextTrigger textOption, TriggerField object) {
		field=new MultiTextTriggerField(textOption);
		basetrigger=object;
	}


	@Override
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("dothis");
		control.endEventMove(field);
		view.addEventToTriggerField(field,basetrigger);
	}

}
