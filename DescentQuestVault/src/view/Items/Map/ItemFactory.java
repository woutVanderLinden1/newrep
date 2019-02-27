package view.Items.Map;

import model.Item;
import model.generators.Generator;
import model.generators.ModifierGenerator;
import model.generators.ModifierGeneratorItem;
import model.generators.TextGenerator;
import model.generators.TextGeneratorItem;
import model.generators.ValueGenerator;
import model.generators.ValueGeneratorItem;
import model.values.BooleanValueItem;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.CustomValue;
import model.values.IntegerValueItem;
import model.values.ValueItem;
import view.viewItems.ItemBox.ImageItem;

public class ItemFactory {

	public static ImageItem createItem(CustomValue tile) {
		// TODO Auto-generated method stub
		switch(tile.getValueKind()) {
		case INTEGER:
			return createItem((CustomInteger)tile);
			
		
		case BOOLEAN:
			return createItem((CustomBoolean)tile);
		
		}
		
		return null;
	}

	
	public static ImageItem createItem(CustomBoolean tile) {
		// TODO Auto-generated method stub
		return new BooleanValueItem(tile);
	}
	
	public static ImageItem createItem(CustomInteger tile) {
		// TODO Auto-generated method stub
		return new IntegerValueItem(tile);
	}


	public static ImageItem createItem(Generator tile) {
		// TODO Auto-generated method stub
		switch(tile.getGeneratorKind()) {
		case Value:
			return new ValueGeneratorItem((ValueGenerator) tile);
		
		case Text:
			return new TextGeneratorItem((TextGenerator) tile);
		case Event:
			return new ModifierGeneratorItem((ModifierGenerator) tile);
		
	
		default:
			break;
		
		}
		return null;
	}
}
