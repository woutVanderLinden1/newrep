

	package model.Tile.tilesets;

	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;

public class ExitOutDoor extends OutDoorTile {

	private static int availability =1;
	
		public ExitOutDoor() {
			super("ExitOutDoor");
			int[][] mat ={ { 1, 3}, 
	                	   { 1, 2}};
	                
		shape=mat;
		exits.add(new TileExit(new Point(1,0),new Point(1,1),Direction.RIGHT,this.getTheming()));

		
		}

		@Override
		public double getScaleWidth() {
			// TODO Auto-generated method stub
			return 0.5;
		}

		@Override
		public double getScaleHeight() {
			// TODO Auto-generated method stub
			return 0.5;
		}

		public int getRightOff() {
			return -15;
		}
		public int getBottomOff() {
			return 0;
		}

		@Override
		public int getLeftOff() {
			return 0;
		}

		@Override
		public int getTopOff() {
			
			return 0;
		}

		public int getAvailability() {
			return availability;
		}
		

	}


