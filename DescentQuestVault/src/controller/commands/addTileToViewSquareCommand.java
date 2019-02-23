package controller.commands;


import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.viewItems.TileItem;

public class addTileToViewSquareCommand extends BasicCommand implements ICommand {

	
	private String tileName;
	private ViewSquare square;
	private ViewTile tile;
	private TileItem item;
	
	public addTileToViewSquareCommand(String string,ViewSquare tile) {
		tileName="Images/Tiles/"+string+".png";
		square=tile;
		
	}

	public addTileToViewSquareCommand(TileItem holded, ViewSquare tile) {
		item=holded;
		square=tile;
	}

	public addTileToViewSquareCommand(ViewTile holded, ViewSquare square) {
		tile=holded;
		this.square=square;
	}

	public void perform(){
		System.out.println("placed tile");
		if(item!=null) {
			view.addTileToSquare(item,square);
		}
		else {
			view.addViewTileToSquare(tile,square);
		}
		
		control.endTileMove();
	}
}
