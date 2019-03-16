package view.game;

import java.awt.Point;

import model.event.PlaceTileEvent;
import model.event.RemoveTileEvent;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.viewItems.TileItem;

public class GameTile extends ViewTile {
	
	private ViewTile tilebasic;

	public GameTile(TileItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		
		// TODO Auto-generated constructor stub
	}

	public GameTile(GameSquare square, ViewTile toplace) {
		super((TileItem) toplace.getImageItem(),square,0,0);
		//just like viewTile
		tilebasic=toplace;
		toplace.getRemoveTileEvent().setGameTile(this);
		setPlaceTileEvent(toplace.getPlaceTileEvent());
		setRemoveTileEvent(toplace.getRemoveTileEvent());
	}

	public Point getPointOff() {
		// TODO Auto-generated method stub
		return super.getPointOff();
	}

	public boolean isBaseTile(ViewTile tile) {
		return (tile.equals(tilebasic));
	}

	
}
