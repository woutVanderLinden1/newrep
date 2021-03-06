package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile28B extends InDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile28B() {
		super("28B");
		int[][] mat ={ {  3, 2, 1, 1}, 
					   {  1, 1, 2, 3},
					   {  0, 0, 0, 0},
					   {  0, 0, 0, 0}};
					  
					
		shape=mat;
		exits.add(new TileExit(new Point(0,0),new Point(0,1),Direction.UP));
		exits.add(new TileExit(new Point(2,1),new Point(3,1),Direction.DOWN));
		this.setOrigxheight(4);
		this.setOrigyheight(2);
		
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