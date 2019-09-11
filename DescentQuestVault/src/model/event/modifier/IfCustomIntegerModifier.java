package model.event.modifier;

import model.values.Comparison;
import model.values.IntegerValueItem;

//a class that betters te old ifmodifier by making it able to compare with other integer values
public class IfCustomIntegerModifier extends IfIntegerModifier {

	public IfCustomIntegerModifier(Comparison comp, IntegerValueItem theItem, int compvalue) {
		super(comp, theItem, compvalue);
		// TODO Auto-generated constructor stub
	}

}
