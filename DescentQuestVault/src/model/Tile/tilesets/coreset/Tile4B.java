
	package model.Tile.tilesets.coreset;

	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;

	public class Tile4B extends InDoorTile {

		public Tile4B() {
			super("4B");
			int[][] mat ={ { 1, 1, 3, 2, 1, 1}, 
						   { 1, 1, 1, 1, 1, 1},
						   { 2, 1, 1, 1, 1, 1}, 
						   { 3, 1, 1, 1, 1, 1}, 
						   { 1, 1, 1, 1, 1, 1},
						   { 1, 1, 1, 1, 1, 1}}; 
						
			shape=mat;
			exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP));
			
			exits.add(new TileExit(new Point(0,3),new Point(0,2),Direction.LEFT));
			
			
		}

		@Override
		public double getScaleWidth() {
			// TODO Auto-generated method stub
			return 1.4;
		}

		@Override
		public double getScaleHeight() {
			// TODO Auto-generated method stub
			return 1.5;
		}

		@Override
		public int getLeftOff() {
			// TODO Auto-generated method stub
			return -15;
		}

		@Override
		public int getRightOff() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getBottomOff() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getTopOff() {
			// TODO Auto-generated method stub
			return -15;
		}

	}


