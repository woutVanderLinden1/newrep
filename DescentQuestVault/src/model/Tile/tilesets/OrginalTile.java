package model.Tile.tilesets;

import java.util.ArrayList;

import model.Item;
import model.Tile.Theme;
import model.Tile.TileExit;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public abstract class OrginalTile extends Item{
	
	protected ArrayList<TileExit> exits=new ArrayList<TileExit>();
	
	
	
	public ArrayList<TileExit> getExits() {
		return cloneList(exits);
	}
	public void setExits(ArrayList<TileExit> exits) {
		this.exits = cloneList(exits);
	}
	private static ArrayList<TileExit> cloneList(ArrayList<TileExit> exits2) {
		ArrayList<TileExit> toReturn=new ArrayList<TileExit>();
		for(TileExit extra:exits2) {
			toReturn.add(extra.clone());
		}
		return toReturn;
	}
	public OrginalTile(String name) {
		super(name);
	
		
	}


	public OrginalTile(String string, int availability) {
		super(string,availability);
	}


	protected int[][] shape;
	//some way to represent it's shape
	//0 is empty 1 represent occupied 2 represent connections 3 represents 2nd connection 4 represent blocked
	//3 goes in 2
	

	public int[][] getShape() {
		return cloneArray(shape);
	}
	public void setShape(int[][] shape) {
		this.shape = shape;
	}

	public static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	@Override
	public ItemOptions getItemKind() {
		return ItemOptions.Tile;
	}
	
	public abstract Theme getTheming();
	
	

}
