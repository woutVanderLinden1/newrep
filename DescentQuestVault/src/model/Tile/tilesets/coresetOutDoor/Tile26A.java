package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OutDoorTile;

public class Tile26A extends OutDoorTile{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile26A() {
		super("26A");
		int[][] mat ={ { 0, 3, 2, 0}, 
					   { 1, 1, 1, 1},
					   { 1, 2, 3, 1},
					   { 0, 0, 0, 0}};
					  
					
		shape=mat;
		exits.add(new TileExit(new Point(1,0),new Point(2,0),Direction.UP,this.getTheming()));
		exits.add(new TileExit(new Point(2,2),new Point(1,2),Direction.DOWN,this.getTheming()));

		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
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
