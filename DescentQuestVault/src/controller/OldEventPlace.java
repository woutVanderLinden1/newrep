package controller;

import model.event.Trigger;
import view.events.BaseField;
import view.events.TriggerField;

public class OldEventPlace {

	private TriggerField baseTrigger;
	private BaseField field;
	
	public TriggerField getBaseTrigger() {
		return baseTrigger;
	}

	public void setBaseTrigger(TriggerField baseTrigger) {
		this.baseTrigger = baseTrigger;
	}

	public BaseField getField() {
		return field;
	}

	public void setField(BaseField field) {
		this.field = field;
	}

	public OldEventPlace(TriggerField baseTrigger, BaseField todrag) {
		setField(todrag);
		setBaseTrigger(baseTrigger);
	}

}
