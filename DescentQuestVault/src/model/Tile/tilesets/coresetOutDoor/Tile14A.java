package model.Tile.tilesets.coresetOutDoor;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

public class Tile14A extends OutDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile14A() {
		super("14A");
		int[][] mat ={ { 0, 1, 1, 1}, 
					   { 2, 1, 1, 3},
					   { 3, 1, 1, 2},
					   { 0, 1, 1, 1}};
					  
					
		shape=mat;
		
		exits.add(new TileExit(new Point(0,2),new Point(0,1),Direction.LEFT,this.getTheming()));
		exits.add(new TileExit(new Point(3,1),new Point(3,2),Direction.RIGHT,this.getTheming()));
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
