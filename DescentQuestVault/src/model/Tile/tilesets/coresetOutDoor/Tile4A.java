package model.Tile.tilesets.coresetOutDoor;



	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;
import model.Tile.tilesets.InDoorTile;
import model.Tile.tilesets.OutDoorTile;

	public class Tile4A extends OutDoorTile {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Tile4A() {
			super("4A");
			int[][] mat ={ { 1, 1, 3, 2, 1, 1}, 
						   { 1, 1, 1, 1, 1, 1},
						   { 1, 1, 1, 1, 1, 3}, 
						   { 1, 1, 1, 1, 1, 2}, 
						   { 1, 1, 1, 1, 1, 1},
						   { 1, 1, 1, 1, 1, 1}}; 
						
			shape=mat;
			exits.add(new TileExit(new Point(2,0),new Point(3,0),Direction.UP,this.getTheming()));
			
			exits.add(new TileExit(new Point(5,2),new Point(5,3),Direction.RIGHT,this.getTheming()));
			
			
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
			return 0;
		}

		@Override
		public int getTopOff() {
			// TODO Auto-generated method stub
			return -15;
		}

	}


