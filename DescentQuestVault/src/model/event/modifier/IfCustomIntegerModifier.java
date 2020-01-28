package model.event.modifier;

import StoryEditor.CustomIntegerCondition;
import model.values.Comparison;
import model.values.CustomInteger;
import model.values.IntegerValueItem;
import view.viewItems.ItemBox.ItemInfoContainer;

//a class that betters te old ifmodifier by making it able to compare with other integer values
public class IfCustomIntegerModifier extends Modifier {

	private CustomIntegerCondition cond;
	public IfCustomIntegerModifier(Comparison comp, IntegerValueItem theItem, CustomInteger item2) {
		super();
		cond=new CustomIntegerCondition(theItem.getVal(),comp,item2);
		
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		cond.addEventSpecifics(itemInfoText);
	}
	
	

}
