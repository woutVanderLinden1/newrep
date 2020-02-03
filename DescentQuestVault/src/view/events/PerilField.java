package view.events;

import model.event.advancedevents.PerilEvent;
import model.event.advancedevents.peril.Peril;
import model.event.extraevents.TextTrigger;

public class PerilField extends MultiTriggerField {

	

	public PerilField(PerilEvent ev, int i, int j) {
		super(ev, j, j);
		if(ev.getTriggerchoices().isEmpty()) {
			ev.addTriggerChoice(ev.getFirstperil());
			ev.addTriggerChoice(ev.getSecondperil());
			TriggerContainer contain=new TriggerContainer(ev.getFirstperil(),this.getWidth()-25,80,this);
			this.addTriggerContainer(contain);
			contain.setName("minor peril");
			TriggerContainer contain2=new TriggerContainer(ev.getSecondperil(),this.getWidth()-25,80,this);
			this.addTriggerContainer(contain2);
			contain2.setName("major peril");
		}
		
		
		//this.addTriggerContainer(contain);
		//this.minimize();
		
	}

}
