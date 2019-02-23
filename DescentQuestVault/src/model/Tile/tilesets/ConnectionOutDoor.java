

	package model.Tile.tilesets;

	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;

public class ConnectionOutDoor extends OutDoorTile {

	private static int availability =9;
	
		public ConnectionOutDoor() {
			super("OutDoorConnection",availability);
			int[][] mat ={ { 4, 4, }, 
	                		{0,0}};
	                
		shape=mat;
		exits.add(new TileExit(new Point(0,0),new Point(1,0),Direction.UP,this.getTheming()));
		exits.add(new TileExit(new Point(1,0),new Point(0,0),Direction.DOWN,this.getTheming()));
		
		}

		@Override
		public double getScaleWidth() {
			// TODO Auto-generated method stub
			return 0.5;
		}

		@Override
		public double getScaleHeight() {
			// TODO Auto-generated method stub
			return 0.25;
		}

		public int getRightOff() {
			return 0;
		}
		public int getBottomOff() {
			return -15;
		}

		@Override
		public int getLeftOff() {
			return 0;
		}

		@Override
		public int getTopOff() {
			
			return -15;
		}

		public int getAvailability() {
			return availability;
		}
		
		
		public void availabilityIncreased() {
			// TODO Auto-generated method stub
			
			availability++;
			super.availabilityIncreased();
		}


		public void availabilityDecreased() {
			
			super.availabilityDecreased();
			availability--;
		}
		

	}


