package view;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import controller.commands.ICommand;
import misc.ActivateAble;
import misc.save.WorldSaveFile;
import model.Item;
import model.event.MonsterTurnTrigger;
import model.event.MovementString;
import model.event.Trigger;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.BaseField;
import view.events.TriggerField;
import view.game.GameMonster;
import view.game.MonsterKind;
import view.menu.Menus;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;

public interface IView {

	IDrawWindow getActiveWindow();

	void exit();
	
	public void changeMenu(Menus menu);



	void goBackInMenu();

	void startQuestEditor();

	ViewSquare getTileAt(int x, int y);

	void addTileToSquare(TileItem item, ViewSquare square);

	void changeTileColor(ViewSquare tile, Color color);

	ICommand getPlaceSelectedCommand(ViewSquare tile);

	void setSelected(SelectAble item);

	void rotateSelected();

	boolean hasSelected();

	SelectAble getSelected();

	
	boolean isLegalOccupied(ViewSquare square, SelectAble selectAble);

	



	ArrayList<Connection> getConnections(ViewSquare square, TileItem selected)throws Exception;

	void fillExits();

	

	void deleteSelected();

	void addViewTileToSquare(ViewTile tile, ViewSquare square);

	void unselect();

	//void StartQuestGame();

	void addGameTile(ViewTile toplace);

	ArrayList<ViewSquare> changeShapeColor(ShapeItem selected, ViewSquare square, Color color);

	boolean isConnected(ViewSquare square, TileItem selected);

	ArrayList<ViewTileExit> getConnected(ViewSquare square, TileItem selected);

	void removeTile(ViewTile toselect);

	void addDoorToSquare(DoorItem door, ViewSquare square);

	void removeItem(MapItem toselect);

	void addViewDoorToSquare(ViewDoor viewdoor, ViewSquare square);

	void startDragItem(MapItem toselect);

	void startDragTile(ViewTile toselect);

	void startDragItem(BaseField todrag);

	void addSelectedToTriggerField(TriggerField field);

	void addEventToTriggerField(BaseField field, TriggerField basetrigger);

	void ShowSelectedInTriggerField(TriggerField whereshow);

	void removeShownInField(TriggerField thefield);

	void addTokenToSquare(TokenItem token, ViewSquare square);

	void addViewTokenToSquare(ViewToken viewtoken, ViewSquare square);

	MapItem addItemToSquare(MonsterItem monster, ViewSquare square);

	MapItem addViewItemToSquare(ViewMonster viewmonster, ViewSquare square);



	void endDragEvent(BaseField todrag);

	void addGameDoor(ViewDoor toplace);

	void showActivateAbles(Point point,ArrayList<ActivateAble> list);

	void removeGameDoor(ViewDoor door);

	WorldSaveFile saveGame();

	void loadGame(WorldSaveFile g);

	void addStartUpTrigger();

	void addGameToken(ViewToken toplace);

	void removeGameToken(ViewToken token);

	void removeGameTile(ViewTile tile);

	ArrayList<ViewSquare> getOccupiedSquares(ViewSquare square, ViewMonster themonster);

	TriggerField getFieldAt(int x, int y);

	boolean eventBoxContains(int x, int y);

	void showTextDialog(String text);

	void showTextDialog(String text, ArrayList<TextOption> newoptions);

	void addGameMonster(ViewMonster toplace);

	

	void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect, MonsterKind kind, StopAble stop);

	void makeInvisible(SelectAble mapit);

	void makeVisible(SelectAble mapit);

	void makeItemVisible(MapItem mapit);

	void makeItemInVisible(MapItem mapit);

	void renewItemList();

	void refreshSelected();

	void clearEventBox();

	MonsterTurnTrigger getMovement();



	
}
