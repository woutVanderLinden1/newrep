package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile27B extends InDoorTile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile27B() {
		super("27B");
		int[][] mat ={ {  2, 1, 0, 0}, 
					   {  3, 1, 0, 0},
					   {  1, 3, 0, 0},
					   {  1, 2, 0, 0}};
					  
					
		shape=mat;
		exits.add(new TileExit(new Point(0,1),new Point(0,0),Direction.LEFT));
		exits.add(new TileExit(new Point(1,2),new Point(1,3),Direction.RIGHT));

		this.setOrigxheight(2);
		this.setOrigyheight(4);
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
