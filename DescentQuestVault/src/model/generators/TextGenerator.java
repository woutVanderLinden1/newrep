package model.generators;

import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class TextGenerator extends Generator {

	public TextGenerator() {
		super("Text generator");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemOptions getGeneratorKind() {
		// TODO Auto-generated method stub
		return ItemOptions.Text;
	}



}
