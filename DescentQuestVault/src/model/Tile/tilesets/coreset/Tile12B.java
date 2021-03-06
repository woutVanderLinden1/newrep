package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile12B extends InDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile12B() {
		super("12B");
		int[][] mat ={ { 0, 1, 1, 1, 1}, 
					   { 2, 1, 1, 1, 1},
					   { 3, 1, 1, 1, 1},
					   { 0, 0, 1, 1, 1},
					   { 0, 0, 2, 3, 0} }; 
					
		shape=mat;
		exits.add(new TileExit(new Point(0,2),new Point(0,1),Direction.LEFT));
		
		exits.add(new TileExit(new Point(3,4),new Point(2,4),Direction.DOWN));
		
		this.setOrigxheight(5);
		this.setOrigyheight(5);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.25;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1.25;
	}

	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return -15;
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
		return 0;
	}

}