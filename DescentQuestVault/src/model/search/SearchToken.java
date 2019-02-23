package model.search;

public class SearchToken extends BasicToken{

	protected static int availability=8;
	public SearchToken() {
		super("search", availability);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.25;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 0.25;
	}
	
	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return -32;
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
}
