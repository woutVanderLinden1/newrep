package model.door;

import model.Item;

import view.viewItems.ItemBox.ItemOptions;

public abstract class Door extends Item {

	protected int[][] shape;
	private static int availability=5;
	
	public Door(String name) {
		super(name,availability);
		int[][] mat ={ {1,1},{1,1}};
		shape=mat;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return shape ;
	}


	@Override
	public ItemOptions getItemKind() {
		return ItemOptions.Door;
	}

	public abstract boolean isClosedDoor() ;
}
