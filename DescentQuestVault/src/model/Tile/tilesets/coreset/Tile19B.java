package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile19B extends InDoorTile {

	public Tile19B() {
		super("19B");
		int[][] mat ={ { 1, 1, 1, 1, 1, 3}, 
					   { 2, 1, 1, 1, 1, 2},
					   { 3, 1, 1, 1, 1, 1},
					   { 0, 0, 0, 0, 0, 0},
					   { 0, 0, 0, 0, 0, 0}, 
					   { 0, 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(5,0),new Point(5,1),Direction.RIGHT));
		exits.add(new TileExit(new Point(0,2),new Point(0,1),Direction.LEFT));
		this.setOrigxheight(6);
		this.setOrigyheight(3);
		
		
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 1.5;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .75;
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
		return -1;
	}


}
