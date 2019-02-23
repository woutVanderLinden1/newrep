package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile20B extends InDoorTile {

	public Tile20B() {
		super("20B");
		int[][] mat ={ { 1, 1, 1, 1, 1, 1, 1, 1 }, 
					   { 1, 1, 1, 1, 1, 1, 1, 3 },
					   { 2, 3, 0, 0, 0, 0, 1, 2 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 }}; 
		shape=mat;
		
		exits.add(new TileExit(new Point(7,1),new Point(7,2),Direction.RIGHT));
		exits.add(new TileExit(new Point(1,2),new Point(0,2),Direction.DOWN));
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .75;
	}

	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return -15;
	}

	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return -15;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}

}
