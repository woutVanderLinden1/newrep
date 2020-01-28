package StoryEditor;

import model.values.CustomValue;
import view.viewItems.ItemBox.ItemInfoContainer;

public abstract class DataCondition {

	private CustomValue tocompare;
	
	public DataCondition(CustomValue tocompare) {
		this.tocompare=tocompare;
	}
	
	public abstract boolean isCondition();
	
	public abstract void addEventSpecifics(ItemInfoContainer itemInfoText);

	public abstract String getName();

	
	
}
