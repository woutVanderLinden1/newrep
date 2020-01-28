package view.Items.Map;

import javax.swing.JButton;

public class MapButton extends JButton {
	private MapLocation location;

	public MapButton(String string, MapLocation loc) {
		super(string);
		location=loc;
	}

	public MapLocation getMapLocation() {
		return location;
	}

	public void setMapLocation(MapLocation location) {
		this.location = location;
	}

}
