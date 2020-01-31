package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OutDoorTile;

public class Tile7A extends OutDoorTile {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile7A() {
		super("7A");
		int[][] mat ={ { 1, 1, 1, 3, 2, 1}, 
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 2, 3, 1, 1, 1},
					   { 0, 0, 0, 0, 0, 0}, 
					   { 0, 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(3,0),new Point(4,0),Direction.UP,this.getTheming()));
		
		exits.add(new TileExit(new Point(2,3),new Point(1,3),Direction.DOWN,this.getTheming()));
		this.setOrigxheight(6);
		this.setOrigyheight(4);
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.4;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 1;
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
