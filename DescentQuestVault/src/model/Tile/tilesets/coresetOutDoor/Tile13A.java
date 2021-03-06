package model.Tile.tilesets.coresetOutDoor;



import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile13A extends OutDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile13A() {
		super("13A");
		int[][] mat ={ { 0, 1, 3, 2, 1, 0}, 
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 1, 1, 1, 1},
					   { 1, 1, 0, 0, 0, 0}, 
					   { 2, 3, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP,this.getTheming()));
		
		exits.add(new TileExit(new Point(1,5),new Point(0,5),Direction.DOWN,this.getTheming()));
		
		this.setOrigyheight(6);

		this.setOrigxheight(6);
	
		setScalex(.8);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.5;
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