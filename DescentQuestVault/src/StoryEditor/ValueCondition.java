package StoryEditor;

import model.values.Comparison;
import model.values.IntegerValueItem;

public class ValueCondition {

	private Comparison comp;
	private IntegerValueItem item;
	private int invalue;
	public Comparison getComp() {
		return comp;
	}
	public void setComp(Comparison comp) {
		this.comp = comp;
	}
	public IntegerValueItem getItem() {
		return item;
	}
	public void setItem(IntegerValueItem item) {
		this.item = item;
	}
	public int getInvalue() {
		return invalue;
	}
	public void setInvalue(int invalue) {
		this.invalue = invalue;
	}
	
	public boolean evaluate() {
		return comp.compare(item.getVal().getValue(),invalue);
	}
	public ValueCondition(Comparison comp, IntegerValueItem item, int invalue) {
		super();
		this.comp = comp;
		this.item = item;
		this.invalue = invalue;
	}
	
}
