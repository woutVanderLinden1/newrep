package model;

import java.util.ArrayList;

import controller.commands.AddDoorToViewSquareCommand;
import controller.commands.ICommand;
import controller.commands.addTileToViewSquareCommand;
import controller.commands.select.AddMonsterToViewSquareCommand;
import controller.commands.select.AddTokenToViewSquareCommand;
import misc.listeners.MapItemPlaceListener;
import misc.listeners.TilePlaceListener;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectedChangeListener;

public class SelectedArea implements TilePlaceListener,MapItemPlaceListener{

	
	
	private SelectAble holded;
	public ArrayList<SelectedChangeListener> selectedChangeListeners=new ArrayList<SelectedChangeListener>();
	
	
	private ItemOptions option;
	
	
	public SelectedArea() {
		
	}
	public SelectAble getHolded() {
		return holded;
	}
	public void setHolded(SelectAble item) {
		System.out.println("deselected "+ holded);
		if(holded!=null) {
			//System.out.println("holded set "+item.getKind());
			holded.deselect();
		}
		this.holded = item;
		if(holded!=null) {
			holded.select();
		}
		notifySelectedChangeListeners();
		//holded.setDragEnabled(true);
	}
	private void notifySelectedChangeListeners() {
		for(SelectedChangeListener listen:selectedChangeListeners) {
			listen.notify(holded);
		}
		
	}
	public SelectedArea(ImageItem item) {
		holded=item;
	}
	public ICommand generateDropCommand(ViewSquare square) {
		if(holded==null) {
			return null;
		}
		//return holded.getDropCommand(square);
		switch (holded.getKind()) {
			case TILEITEM:
				
				return new addTileToViewSquareCommand( (TileItem) holded,square);
			case VIEWTILE:
				
				return new addTileToViewSquareCommand( (ViewTile) holded,square);
			case DOOR:
				return new AddDoorToViewSquareCommand((DoorItem)holded,square);
			case VIEWDOOR:
				return new AddDoorToViewSquareCommand((ViewDoor)holded,square);
			case TOKEN:
				return new AddTokenToViewSquareCommand((TokenItem)holded,square);
			case VIEWTOKEN:
				return new AddTokenToViewSquareCommand((ViewToken)holded,square);
			case MONSTER:
				return new AddMonsterToViewSquareCommand((MonsterItem)holded,square);
			case VIEWMONSTER:
				return new AddMonsterToViewSquareCommand((ViewMonster)holded,square);
			
			default:
				break;
		}
			
			
		return null;
	}
	public void rotate() {
	 //rotates a tile
		
		if(holded!=null) {
			switch(holded.getKind()) {
			case TILEITEM:
				((TileItem) holded).rotate();
				break;
			case VIEWTILE:
				((ViewTile) holded).rotate();
				break;
			case DOOR:
				((DoorItem) holded).rotate();
			default:
				break;
			
			}
			
		}
		
	}
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return (holded!=null);
	}



	@Override
	public void tilePlaced(ViewTile tile) {
		setHolded(tile);
		
	}
	public void addSelectedChangeListener(InfoItemBox infoItemBox) {
		selectedChangeListeners.add(infoItemBox);
		
	}
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return holded.getImageItem();
	}
	@Override
	public void notify(MapItem door) {
		setHolded(door);
		
	}
	
	
	
}
