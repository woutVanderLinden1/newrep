package model.search;

import view.Items.Map.MapItem;
import view.Items.Map.ViewSearchToken;
import view.Items.Map.ViewToken;

public class SearchToken extends BasicToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	@Override
	public MapItem createItem() {
		return new ViewSearchToken(this);
	}
	@Override
	public boolean isSearch() {
		// TODO Auto-generated method stub
		return true;
	}
}
