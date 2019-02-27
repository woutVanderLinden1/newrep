package model.generators;

import view.viewItems.ItemBox.ItemOptions;

public class ValueGenerator extends Generator{

	public ValueGenerator() {
		super("Value generator");
		
	}

	@Override
	public ItemOptions getGeneratorKind() {
		// TODO Auto-generated method stub
		return ItemOptions.Value;
	}
}
