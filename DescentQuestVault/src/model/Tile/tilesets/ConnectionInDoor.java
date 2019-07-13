

	package model.Tile.tilesets;

	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;

public class ConnectionInDoor extends InDoorTile {

	
	private static int availability =9;
	
		public ConnectionInDoor() {
			super("ConnectionIndoor",availability);
			int[][] mat ={ { 4, 4, }, 
	                		{0,0}};
	                
		shape=mat;
		exits.add(new TileExit(new Point(0,0),new Point(1,0),Direction.UP));
		exits.add(new TileExit(new Point(1,0),new Point(0,0),Direction.DOWN));
		
		}

		@Override
		public double getScaleWidth() {
			// TODO Auto-generated method stub
			return 0.5;
		}

		@Override
		public double getScaleHeight() {
			// TODO Auto-generated method stub
			return 0.35;
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


