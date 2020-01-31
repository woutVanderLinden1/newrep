package model.Tile.tilesets;

import java.awt.Point;

import model.Tile.Direction;
import model.Tile.TileExit;

public class EndGapTile extends InDoorTile {

	private static int availability =10;
	
	public EndGapTile() {
		super("EndIndoor",availability);
		int[][] mat ={ { 3, 2, }, 
                		{0,0}};
                
		shape=mat;
		exits.add(new TileExit(new Point(0,0),new Point(1,0),Direction.UP));
		this.setOrigxheight(2);
		this.setOrigyheight(1);
	
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.48;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 0.3;
	}

	public int getRightOff() {
		return 0;
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
