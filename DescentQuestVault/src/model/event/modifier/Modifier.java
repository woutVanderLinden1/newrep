package model.event.modifier;

import java.util.ArrayList;

import model.event.EventTriggerStack;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.ItemBox.SelectKind;

public class Modifier extends Trigger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7682631752185963974L;

	public Modifier(){
		super();
		this.setIDName("emptymodifier");
		this.setName("emptymodifier");
	}
	
	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.MODIFIER;
	}

	@Override
	public Univent copy() {
		return new Modifier();
		
	}
	
	public void triggerHere(ArrayList<Univent> totrigger) {
		
		EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
		triggerstack.addNewEvents(totrigger);
	}

	
	
}
