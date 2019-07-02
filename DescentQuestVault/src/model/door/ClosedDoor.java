package model.door;

public class ClosedDoor extends Door {

private static int availability=2;
	
	public ClosedDoor() {
		super("closedDoor");
		//this.setName("closed door");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.08;
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
		return 0;
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

	@Override
	public boolean isClosedDoor() {
		// TODO Auto-generated method stub
		return true;
	}
	


}
