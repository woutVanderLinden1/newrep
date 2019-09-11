package view.menu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import StoryEditor.DraggAblePanel;
import controller.BaseEventController;
import controller.UserInputController;
import controller.commands.ICommand;
import frame.SubContainer;
import misc.ActivateAble;
import misc.save.WorldSaveFile;
import model.Activation;
import model.CustomActivation;
import model.ItemController;
import model.event.Trigger;
import model.event.Univent;
import model.values.CustomInteger;
import view.Items.Map.MapItem;
import view.Items.Map.MapPanel;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.ActivationTrigger;
import view.events.BaseField;
import view.events.EventPanel;
import view.events.TriggerField;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TitleBox;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoPanel;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;

public class QuestCreator extends SubContainer implements Serializable{


	protected TitleBox titleBox;
	protected ItemInfoPanel itemPanel;
	protected MapPanel mapPanel;
	protected EventPanel eventPanel;
	

	public QuestCreator(int w, int h, UserInputController userInput){
		super(w,h);
		
		this.setBackground(Color.BLACK);
		titleBox=new TitleBox("QuestEditor",w,80);
		itemPanel=new ItemInfoPanel(3*(w-50)/12,h-160);
		mapPanel=new MapPanel(6*(w-50)/12,h-160,userInput);
		eventPanel=new EventPanel(3*(w-50)/12,h-160);
		
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,itemPanel,mapPanel);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane1,eventPanel);
		this.add(titleBox);
		this.add(splitPane);
		this.setFocusable(true);
		mapPanel.addTilePlaceListener(itemPanel.getselectedArea());
		mapPanel.addTilePlaceListener(eventPanel.getEventBox());
		mapPanel.addTileRemoveListener(eventPanel.getEventBox());
		mapPanel.addItemPlaceListener(itemPanel.getselectedArea());
		mapPanel.addItemPlaceListener(eventPanel.getEventBox());
		mapPanel.addItemRemoveListener(eventPanel.getEventBox());
	}


	public ViewSquare getTileAt(int x, int y) {
		// TODO Auto-generated method stub
		return mapPanel.getTileAt(x-mapPanel.getX(),y-mapPanel.getY());
	}


	public void addTile(TileItem tileName, ViewSquare square) {
		mapPanel.addTile(tileName,square);
		
	}


	/*
	public void addTile(ViewSquare tile, Color color) {
		mapPanel.changeTileColor(tile,color);
		
	}
	*/


	public ICommand getSelectedCommand(ViewSquare tile) {
		// TODO Auto-generated method stub
		return itemPanel.getSelectedCommand(tile);
	}


	public void setSelected(SelectAble item) {
		itemPanel.setSelected(item);
		
	}


	public void rotateSelected() {
		SelectAble  selected=itemPanel.getSelected();
		
		switch(selected.getKind()) {
		case DOOR:
			itemPanel.rotateSelected();
			break;
		case EVENT:
			break;
		case TILEITEM:
			itemPanel.rotateSelected();
			break;
		case VIEWTILE:
			ViewTile selectedTile=(ViewTile) selected;
			if(mapPanel.containsSelected(selected)) {
				if(mapPanel.isRotatable(selectedTile)) {
					mapPanel.rotateSelected(selected);
				}
			}
			else {
				itemPanel.rotateSelected();
			}
			break;
		case VIEWDOOR:
			ViewDoor selecteddoor=(ViewDoor) selected;
			if(mapPanel.containsSelected(selected)) {
				if(mapPanel.isRotatable(selecteddoor)) {
					mapPanel.rotateSelected(selected);
				}
			}
			else {
				itemPanel.rotateSelected();
			}
		default:
			break;
		
		}
		//rotate selected in the  gridpanel if it's there
	
		
		
	}
	public void deleteSelected() {
		System.out.println("selected deleted");
		SelectAble selected=itemPanel.getSelected();
		//rotate selected in the  gridpanel if it's there
		if(mapPanel.containsSelected(selected)) {
			mapPanel.deleteSelected(selected);
		}
		itemPanel.deleteSelected();
		eventPanel.deleteSelected(selected);
		
	}


	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return itemPanel.hasSelected();
	}


	public SelectAble getSelected() {
		// TODO Auto-generated method stub
		return itemPanel.getSelected();
	}


	public void changeColor(ViewSquare tile, Color color) {
		mapPanel.changeTileColor(tile,color);
	}


	public ArrayList<ViewSquare> changeShapeColor(ShapeItem selected, ViewSquare square, Color color) {
		return mapPanel.changeShapeColor(selected,square,color);
		
	}


	public boolean isLegalOccupied(ViewSquare square, SelectAble selected) {
		// TODO Auto-generated method stub
		return mapPanel.isLegalOccupied(square,selected);
	}


	public boolean isConnected(ViewSquare square, TileItem selected) {
		// TODO Auto-generated method stub
		return mapPanel.isConnected(square,selected);
	}


	public ArrayList<ViewTileExit> getConnected(ViewSquare square, TileItem selected) {
		// TODO Auto-generated method stub
		return mapPanel.getConnected(square,selected);
	}


	public ArrayList<Connection> getConnections(ViewSquare square, TileItem selected) throws Exception {
		// TODO Auto-generated method stub
		return mapPanel.getConnections(square,selected);
	}


	public void fillExits() {
		mapPanel.fillExits();
		
	}


	public void sendEvent(MouseEvent e,Point p,SelectAble sel) {
		Point point= new Point(p.x-this.getX(),p.y-this.getY());

	
			titleBox.sendEvent(e,p,sel);
		
			itemPanel.sendEvent(e,p,sel);
	
			mapPanel.sendEvent(e,p,sel);
		
		
			eventPanel.sendEvent(e,p,sel);
		
		// TODO Auto-generated method stub
		
	}


	public void removeTile(ViewTile toselect) {
		mapPanel.removeTile(toselect);
		
	}


	public ViewTile addViewTileToSquare(ViewTile tile, ViewSquare square) {
		return mapPanel.addViewTileToSquare(tile,square);
		
	}


	public void unselect() {
		// TODO Auto-generated method stub
		itemPanel.unselect();
	}


	public void addDoorToSquare(DoorItem door, ViewSquare square) {
		// TODO Auto-generated method stub
		mapPanel.addDoorToSquare(door,square);
	}

	public ViewMonster addViewMonsterToSquare(ViewMonster viewmonster, ViewSquare baseSquare) {
		// TODO Auto-generated method stub
		return (ViewMonster) mapPanel.addViewItemToSquare(viewmonster,baseSquare);
	}



	public void removeItem(MapItem toselect) {
		mapPanel.removeItem(toselect);
		
	}


	public ViewDoor addViewDoorToSquare(ViewDoor viewdoor, ViewSquare square) {
		return mapPanel.addViewDoorToSquare(viewdoor,square);
		
	}


	public void startDragitem(MapItem toselect) {
		mapPanel.startDragItem(toselect);
		
	}


	public void startDragTile(ViewTile toselect) {
		mapPanel.startDragTile(toselect);
		
	}


	public void startDragEvent(BaseField todrag) {
		// TODO Auto-generated method stub
		eventPanel.startDragEvent(todrag);
	}


	public void addSelectedToTriggerField(TriggerField field) {
		// TODO Auto-generated method stub
		eventPanel.addEventToTriggerField(getSelected(),field);
	}


	public void addEventToTriggerField(BaseField field, TriggerField basetrigger) {
		// TODO Auto-generated method stub
		eventPanel.addEventToTriggerField(field, basetrigger);
	}


	public void showSelectedInTriggerField(TriggerField whereshow) {
		// TODO Auto-generated method stub
		eventPanel.showInTriggerField(getSelected(),whereshow);
	}


	public void removeShownInField(TriggerField thefield) {
		// TODO Auto-generated method stub
		eventPanel.removeTemporaryShown(thefield);
	}


	public void addTokenToSquare(TokenItem token, ViewSquare square) {
		// TODO Auto-generated method stub
		mapPanel.addTokenToSquare(token,square);
	}


	public void addViewTokenToSquare(ViewToken viewtoken, ViewSquare square) {
		// TODO Auto-generated method stub
		mapPanel.addTokenToSquare(viewtoken,square);
	}


	public MapItem addItemToSquare(MonsterItem monster, ViewSquare square) {
		return mapPanel.addItemToSquare(monster,square);
		
	}


	public MapItem addViewItemToSquare(ViewMonster viewmonster, ViewSquare square) {
		return mapPanel.addViewItemToSquare(viewmonster,square);
		
	}


	public void endDragEvent(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		eventPanel.endDragEvent(todrag);
	}


	public WorldSaveFile saveThis() {
		
		WorldSaveFile thefile=new WorldSaveFile();
		UserInputController UIControl=UserInputController.getController();
		UIControl.saveThis(thefile);
		eventPanel.saveThis(thefile);
		ItemController control=ItemController.getItemController();
		control.saveThis(thefile);
		return thefile;
	}


	//read the savefile
	//place each event and each object connected to that event
	//reinitialise object images.
	public void loadGame(WorldSaveFile g) {
		
		for(Univent vent:g.getUnivents()) {
			//add the univent
			eventPanel.addUniventToTriggerField(vent, null);
			vent.initialise(this);
			
		}
		
		ItemController.getItemController().readValues(g);
		UserInputController control=UserInputController.getController();
		control.initialiseBaseEventController(g.getControl());
		
	}


	public void addUniventToTrigger(Univent vent, Trigger trigger, boolean b) {
		eventPanel.addUniventToTrigger(vent,trigger,b);
		
	}


	public void addStartTriggers() {
		// TODO Auto-generated method stub
		eventPanel.addStartTriggers();
	}


	public ArrayList<ViewSquare> getOccupiedSquares(ViewSquare square, ViewMonster themonster) {
		// TODO Auto-generated method stub
		return mapPanel.getOccupiedSquares(square,themonster);
	}


	public TriggerField getFieldAt(int x, int y) {
		// TODO Auto-generated method stub
		return eventPanel.getFieldAt(x,y);
	}


	public boolean eventBoxContains(int x, int y) {
		// TODO Auto-generated method stub
		return eventPanel.eventBoxContains(x,y);
	}



	public void makeVisible(SelectAble mapit) {
		// TODO Auto-generated method stub
		mapit.makeVisible();
	}


	public void makeInvisible(SelectAble mapit) {
		// TODO Auto-generated method stub
		mapit.makeInvisible();
	}


	public void makeItemVisible(MapItem mapit) {
		// TODO Auto-generated method stub
		mapPanel.makeVisible(mapit);
	}
	public void makeItemInvisible(MapItem mapit) {
		// TODO Auto-generated method stub
		mapPanel.makeInvisible(mapit);
	}


	public void renewItemList() {
		// TODO Auto-generated method stub
		itemPanel.renewItemList();
	}


	public void refreshSelected() {
		itemPanel.refreshSelected();
		
	}


	public void clearEventBox() {
		// TODO Auto-generated method stub
		eventPanel.clearEventBox();
	}


	public void addActivationToActivateAble(ActivateAble active) {
		
		CustomActivation act=new CustomActivation("activate "+active.getName());
		eventPanel.addUniventToTriggerField(act.getActivationTrigger(),null);
		active.addActivation(act);
		
	}


	public void removeActivationFromActivateAble(Activation activation, ActivateAble active) {
		eventPanel.removeTrigger(activation.getTrigger());
		active.removeActivation(activation);
	}


	public void initialiseBaseEvents(BaseEventController baseEventController) {
		// TODO Auto-generated method stub
		eventPanel.setBaseTriggers(baseEventController);
	}









}
