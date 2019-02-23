package model.event.modifier;

import misc.Tools;
import model.event.Univent;

public class OrderedListModifier extends Modifier {

	
	private int ordernr=0;
	public OrderedListModifier() {
		super();
		this.setIDName("ordered list");
		this.setName("ordered list");
	}
	
	public void trigger() {
		if(actions.size()>0) {
			if(ordernr>=actions.size()) {
				ordernr=0;
			}
			actions.get(ordernr).trigger();
			ordernr++;
		}
	
	}
	@Override
	public Univent copy() {
		return new OrderedListModifier();
		
	}
}
