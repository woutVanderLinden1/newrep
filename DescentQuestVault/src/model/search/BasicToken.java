package model.search;

import model.Item;
import view.Items.Map.MapItem;
import view.Items.Map.ViewToken;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ItemOptions;
//name should become path and token separately
public class BasicToken  extends Item{

	protected int[][] shape;

	
	public BasicToken(String name, int availability) {
		super(name,availability);
		int[][] mat ={ {1}};
		shape=mat;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return shape;
	}



	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return -33;
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
		return ItemOptions.Token;
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.24;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 0.24;
	}

	

	@Override
	public MapItem createItem() {
		return new ViewToken(this);
	}

	public boolean isSearch() {
		// TODO Auto-generated method stub
		return false;
	}

}
