package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile13B extends InDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile13B() {
		super("13B");
		int[][] mat ={ { 0, 1, 3, 2, 1, 0}, 
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1},
					   { 0, 0, 0, 0, 1, 1}, 
					   { 0, 0, 0, 0, 2, 3}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP));
		
		exits.add(new TileExit(new Point(5,5),new Point(4,5),Direction.DOWN));
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.5;
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