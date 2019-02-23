package model.event.modifier;

import java.util.Random;

import misc.Tools;
import model.event.Univent;

public class OneOutModifier extends Modifier {

	
	public OneOutModifier() {
		super();
		this.setIDName("One Out list");
		this.setName("One Out List");
	}
	
	public void trigger() {
		int r=Tools.getRandomInt(actions.size());
	
		actions.get(r).trigger();
	}
	@Override
	public Univent copy() {
		return new OneOutModifier();
		
	}
}
