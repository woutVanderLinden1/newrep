package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile25B extends InDoorTile {

	public Tile25B() {
		super("25B");
		int[][] mat ={ { 1, 1, 1, 1, 1, 3}, 
					   { 2, 3, 1, 1, 1, 2},
					   { 0, 0, 0, 0, 0, 0},
					   { 0, 0, 0, 0, 0, 0},
					   { 0, 0, 0, 0, 0, 0}, 
					   { 0, 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(5,0),new Point(5,1),Direction.RIGHT));
		
		exits.add(new TileExit(new Point(1,1),new Point(0,1),Direction.DOWN));
		this.setOrigxheight(6);
		this.setOrigyheight(2);
		
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
		return -1;
	}


}

