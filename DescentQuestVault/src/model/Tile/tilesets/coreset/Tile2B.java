package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile2B extends InDoorTile {

	public Tile2B() {
		super("2B");
		int[][] mat ={ { 1, 1, 3, 2, 1, 1}, 
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1}, 
					   { 1, 1, 1, 1, 1, 1}, 
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 2, 3, 1, 1}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP));
		
		exits.add(new TileExit(new Point(3,5),new Point(2,5),Direction.DOWN));
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.4;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1.5;
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
		return -15;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return -15;
	}

}
