package view.viewItems.ItemBox;

import java.io.Serializable;

public class Connection implements Serializable{

	private ViewTileExit exit1;
	private ViewTileExit exit2;
	
	
	public Connection(ViewTileExit exit1, ViewTileExit exit2) {
		super();
		this.exit1 = exit1;
		this.exit2 = exit2;
	}
	public ViewTileExit getExit1() {
		return exit1;
	}
	public void setExit1(ViewTileExit exit1) {
		this.exit1 = exit1;
	}
	public ViewTileExit getExit2() {
		return exit2;
	}
	
	public void setExit2(ViewTileExit exit2) {
		this.exit2 = exit2;
	}
	
	public void connect() {
		exit1.connect(exit2);
		exit2.connect(exit1);
	}
	public ViewTileExit getOther(ViewTileExit viewTileExit) {
		if(viewTileExit.equals(exit1)) {
			return exit2;
		}
		else {
			return exit1;
		}
		// TODO Auto-generated method stub
		//return null;
	}
}
