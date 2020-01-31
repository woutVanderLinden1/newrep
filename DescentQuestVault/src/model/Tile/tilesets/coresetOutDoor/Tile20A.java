package model.Tile.tilesets.coresetOutDoor;



import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile20A extends OutDoorTile {

	public Tile20A() {
		super("20A");
		int[][] mat ={ { 1, 1, 1, 1, 1, 1, 1, 1 }, 
					   { 2, 1, 1, 1, 1, 1, 1, 1 },
					   { 3, 1, 0, 0, 0, 0, 2, 3 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 0, 0, 0, 0 }}; 
		shape=mat;
		
		exits.add(new TileExit(new Point(0,1),new Point(0,2),Direction.LEFT,this.getTheming()));
		exits.add(new TileExit(new Point(6,2),new Point(7,2),Direction.DOWN,this.getTheming()));
		
		this.setOrigxheight(8);
		this.setOrigyheight(3);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 2;
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
