package model.Tile.tilesets;

import model.Tile.Theme;

public abstract class OutDoorTile extends OrginalTile {

	public OutDoorTile(String name) {
		super(name);

		// TODO Auto-generated constructor stub
	}
	
	
	public OutDoorTile(String string, int availability) {
		super(string,availability);
	}


	@Override
	public Theme getTheming() {
		return Theme.OUTDOOR;
	}

	

}
