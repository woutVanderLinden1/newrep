package model.Hero;

import model.Item;
import view.viewItems.ItemBox.ItemOptions;

public class Hero extends Item{
	
	public Hero(String name) {
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
		return 2;
	}



	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 2;
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
		return null;
	}




}
