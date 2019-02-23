package model.Tile.tilesets;

import model.Tile.Theme;

public abstract class InDoorTile extends OrginalTile {

	
	public InDoorTile(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public InDoorTile(String string, int availability) {
		super(string,availability);
	}



	public Theme getTheming() {
		return Theme.INDOOR;
	}

	

}
