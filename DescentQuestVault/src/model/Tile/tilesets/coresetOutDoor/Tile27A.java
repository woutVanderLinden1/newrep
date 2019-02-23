package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OutDoorTile;

public class Tile27A extends OutDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile27A() {
		super("27A");
		int[][] mat ={ {  1, 3, 0, 0}, 
					   {  1, 2, 0, 0},
					   {  2, 1, 0, 0},
					   {  3, 1, 0, 0}};
					  
					
		shape=mat;
		exits.add(new TileExit(new Point(0,3),new Point(0,3),Direction.LEFT,this.getTheming()));
		exits.add(new TileExit(new Point(1,0),new Point(1,1),Direction.RIGHT,this.getTheming()));

		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return .5;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return -15;
	}

	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return -15;
	}

	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}

}
