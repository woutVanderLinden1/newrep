package model.Tile.tilesets;

import java.util.ArrayList;

import model.Item;
import model.Tile.Theme;
import model.Tile.TileExit;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public abstract class OrginalTile extends Item{
	
	protected ArrayList<TileExit> exits=new ArrayList<TileExit>();
	protected int origxheight;
	protected int origyheight;
	protected int xheight;
	protected int yheight;
	protected double scalex=1;

	protected double scaley=1;
	public double getScalex() {
		return scalex;
	}
	public void setScalex(double scalex) {
		this.scalex = scalex;
	}
	public double getScaley() {
		return scaley;
	}
	public void setScaley(double scaley) {
		this.scaley = scaley;
	}


	
	public int getOrigxheight() {
		return origxheight;
	}
	public void setOrigxheight(int origxheight) {
		this.origxheight = origxheight;
	}
	public int getOrigyheight() {
		return origyheight;
	}
	public void setOrigyheight(int origyheight) {
		this.origyheight = origyheight;
	}
	public int getXheight() {
		return xheight;
	}
	public void setXheight(int xheight) {
		this.xheight = xheight;
	}
	public int getYheight() {
		return yheight;
	}
	public void setYheight(int yheight) {
		this.yheight = yheight;
	}
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
