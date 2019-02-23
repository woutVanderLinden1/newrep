package model.Tile.tilesets.coresetOutDoor;



import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile12A extends OutDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile12A() {
		super("12A");
		int[][] mat ={ { 1, 1, 1, 1, 0}, 
					   { 1, 1, 1, 1, 3},
					   { 1, 1, 1, 1, 2},
					   { 1, 1, 1, 0, 0},
					   { 0, 2, 3, 0, 0} }; 
					
		shape=mat;
		exits.add(new TileExit(new Point(0,2),new Point(0,1),Direction.RIGHT,this.getTheming()));
		
		exits.add(new TileExit(new Point(4,4),new Point(3,4),Direction.DOWN,this.getTheming()));
		
		
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