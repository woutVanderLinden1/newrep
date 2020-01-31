package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile15B extends InDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile15B() {
		super("15B");
		int[][] mat ={ { 0, 3, 2, 0, 0}, 
					   { 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1},
					   { 0, 0, 0, 2, 3},
					   { 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(1,0),new Point(2,0),Direction.UP));
		
		exits.add(new TileExit(new Point(4,2),new Point(3,2),Direction.DOWN));
		
		this.setOrigxheight(6);
		this.setOrigyheight(4);
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
