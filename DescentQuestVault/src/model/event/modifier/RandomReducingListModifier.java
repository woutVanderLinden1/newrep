package model.event.modifier;

import java.util.ArrayList;

import misc.Tools;
import model.event.Univent;

public class RandomReducingListModifier extends Modifier {

	private ArrayList<Univent>superlist=new ArrayList<Univent>();
	
	public RandomReducingListModifier() {
		super();
		this.setIDName("reducing list");
		this.setName("reducing list");
	}
	
	public void trigger() {
		if(superlist.isEmpty()) {
			superlist.addAll(actions);
		}
		int r=Tools.getRandomInt(superlist.size());
	
		superlist.get(r).trigger();
		superlist.remove(r);
	}
	@Override
	public Univent copy() {
		return new RandomReducingListModifier();
		
	}
}
