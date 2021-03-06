

	package model.Tile.tilesets;

	import java.awt.Point;

	import model.Tile.Direction;
	import model.Tile.TileExit;

public class EntranceIndoor extends InDoorTile {

	private static int availability =1;
	
		public EntranceIndoor() {
			super("EntranceIndoor",availability);
			int[][] mat ={ { 1, 3}, 
	                	   { 1, 2}};
	                
		shape=mat;
		exits.add(new TileExit(new Point(1,0),new Point(1,1),Direction.RIGHT));
		this.setOrigxheight(2);
		this.setOrigyheight(2);
		
		}

		@Override
		public double getScaleWidth() {
			// TODO Auto-generated method stub
			return 0.53;
		}

		@Override
		public double getScaleHeight() {
			// TODO Auto-generated method stub
			return 0.47;
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


