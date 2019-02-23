package model.event.modifier;

import model.event.Trigger;
import model.event.Univent;
import view.viewItems.ItemBox.SelectKind;

public class Modifier extends Trigger {

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
	
	

	
	
}
