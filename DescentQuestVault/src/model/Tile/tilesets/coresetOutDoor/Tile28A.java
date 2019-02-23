package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OutDoorTile;

public class Tile28A extends OutDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile28A() {
		super("28A");
		int[][] mat ={ {  1, 1, 3, 2}, 
					   {  2, 3, 1, 1},
					   {  0, 0, 0, 0},
					   {  0, 0, 0, 0}};
					  
					
		shape=mat;
		exits.add(new TileExit(new Point(0,2),new Point(0,3),Direction.UP,this.getTheming()));
		exits.add(new TileExit(new Point(1,1),new Point(0,1),Direction.DOWN,this.getTheming()));

		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .5;
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
