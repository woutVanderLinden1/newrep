package model.Tile.tilesets.coresetOutDoor;


import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OutDoorTile;

public class Tile18A extends OutDoorTile{
	


	public Tile18A() {
		super("18A");
		int[][] mat ={ { 3, 2, 0, 0 }, 
                       { 1, 1, 0, 0 },
                       { 1, 1, 1, 3 },
                       { 1, 1, 1, 2 }}; 
		shape=mat;
		exits.add(new TileExit(new Point(0,0),new Point(1,0),Direction.UP,this.getTheming()));
		exits.add(new TileExit(new Point(3,2),new Point(3,3),Direction.RIGHT,this.getTheming()));
		this.setOrigxheight(4);
		this.setOrigyheight(4);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	
	public int getRightOff() {
		return -15;
	}
	public int getBottomOff() {
		return 0;
	}

	@Override
	public int getLeftOff() {
		return 0;
	}

	@Override
	public int getTopOff() {
		
		return -15;
	}

}
