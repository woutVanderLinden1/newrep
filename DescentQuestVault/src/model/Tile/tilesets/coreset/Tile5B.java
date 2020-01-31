package model.Tile.tilesets.coreset;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

public class Tile5B extends InDoorTile {

	public Tile5B() {
		super("5B");
		int[][] mat ={ { 1, 1, 1, 1, 1, 1}, 
					   { 2, 1, 1, 1, 1, 3},
					   { 3, 1, 1, 1, 1, 2},
					   { 1, 1, 2, 3, 1, 1},
					   { 0, 0, 0, 0, 0, 0}, 
					   { 0, 0, 0, 0, 0, 0}}; 
					
		shape=mat;
		exits.add(new TileExit(new Point(0,2),new Point(0,1),Direction.LEFT));
		exits.add(new TileExit(new Point(5,1),new Point(5,2),Direction.RIGHT));
		exits.add(new TileExit(new Point(3,5),new Point(2,5),Direction.DOWN));
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
		return -15;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}

}

