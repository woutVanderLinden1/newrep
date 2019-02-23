package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile1A extends OutDoorTile {

	public Tile1A() {
		super("1A");
		int[][] mat ={ { 1, 1, 1, 3, 2, 1, 1, 1 }, 
					   { 1, 1, 1, 1, 1, 1, 1, 1 },
					   { 1, 1, 1, 1, 1, 1, 1, 3 },
					   { 1, 1, 1, 1, 1, 1, 1, 2 },
					   { 1, 1, 1, 1, 1, 1, 1, 1 },
					   { 1, 1, 1, 2, 3, 1, 1, 1 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 }}; 
		shape=mat;
		exits.add(new TileExit(new Point(3,0),new Point(4,0),Direction.UP,this.getTheming()));
		exits.add(new TileExit(new Point(7,3),new Point(7,2),Direction.RIGHT,this.getTheming()));
		exits.add(new TileExit(new Point(4,5),new Point(3,5),Direction.DOWN,this.getTheming()));
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 2;
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
		return -15;
	}

}
