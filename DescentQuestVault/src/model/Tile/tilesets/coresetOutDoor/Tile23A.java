package model.Tile.tilesets.coresetOutDoor;



import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile23A extends OutDoorTile {

	public Tile23A() {
		super("23A");
		int[][] mat ={ { 2, 1, 1, 1, 1, 3}, 
					   { 3, 1, 2, 3, 1, 2},
					   { 0, 0, 0, 0, 0, 0},
					   { 0, 0, 0, 0, 0, 0},
					   { 0, 0, 0, 0, 0, 0}, 
					   { 0, 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(5,0),new Point(5,1),Direction.RIGHT));
		exits.add(new TileExit(new Point(0,1),new Point(0,0),Direction.LEFT));
		exits.add(new TileExit(new Point(3,1),new Point(2,1),Direction.DOWN));
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.5;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .5;
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
		return -15;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return -1;
	}


}

