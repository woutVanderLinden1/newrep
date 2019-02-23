package model.Tile;

import java.awt.Point;
import java.io.Serializable;

import view.Items.Map.ViewTile;

public class TileExit implements Serializable{

	private Direction direct;
	// currently unused
	private Theme theme;
	private Point square2ShapeLocation;
	private Point square3ShapeLocation;
	

	public TileExit(Point point, Point point2, Direction up,Theme newtheme) {
		setDirect(up);
		square2ShapeLocation=point;
		square3ShapeLocation=point2;
		setTheme(newtheme);
		
	}

	public TileExit(Point point, Point point2, Direction up) {
		setDirect(up);
		square2ShapeLocation=point;
		square3ShapeLocation=point2;
		setTheme(Theme.INDOOR);
	}
	public Direction getDirect() {
		return direct;
	}
	public void setDirect(Direction direct) {
		this.direct = direct;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public Point getSquare2ShapeLocation() {
		return square2ShapeLocation;
	}
	public void setSquare2ShapeLocation(Point square2ShapeLocation) {
		this.square2ShapeLocation = square2ShapeLocation;
	}
	public Point getSquare3ShapeLocation() {
		return square3ShapeLocation;
	}
	public void setSquare3ShapeLocation(Point square3ShapeLocation) {
		this.square3ShapeLocation = square3ShapeLocation;
	}
	
	public TileExit clone() {
		TileExit toReturn =new TileExit((Point)square3ShapeLocation.clone(),(Point)square2ShapeLocation.clone(),direct,this.getTheme());
		return toReturn;
	}
}
