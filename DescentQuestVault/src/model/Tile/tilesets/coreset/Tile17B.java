package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile17B extends InDoorTile{
	


	public Tile17B() {
		super("17B");
		int[][] mat ={ { 0, 0, 3, 2 }, 
                       { 0, 0, 1, 1 },
                       { 2, 1, 1, 1 },
                       { 3, 1, 1, 1 }}; 
		shape=mat;
		exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP));
		exits.add(new TileExit(new Point(0,3),new Point(0,2),Direction.LEFT));
		this.setOrigxheight(4);
		this.setOrigyheight(4);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	
	public int getRightOff() {
		return 0;
	}
	public int getBottomOff() {
		return 0;
	}

	@Override
	public int getLeftOff() {
		return -15;
	}

	@Override
	public int getTopOff() {
		
		return -15;
	}

}
