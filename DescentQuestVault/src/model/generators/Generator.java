package model.generators;

import model.Item;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public abstract class Generator extends Item {

	public Generator(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .33;
	}
	
	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemOptions getItemKind() {
		// TODO Auto-generated method stub
		return ItemOptions.Generator;
	}

	public abstract ItemOptions getGeneratorKind();
}
