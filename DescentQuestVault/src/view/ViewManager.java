package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import StoryEditor.CampaignSaveFile;
import StoryEditor.DraggAblePanel;
import controller.BaseEventController;
import controller.UserInputController;
import controller.commands.ICommand;
import frame.MainFrame;
import misc.ActivateAble;
import misc.SampleFile;
import misc.save.WorldSaveFile;
import model.Activation;
import model.event.MonsterTurnTrigger;
import model.event.MovementString;
import model.event.Trigger;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import model.values.CustomInteger;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.BaseField;
import view.events.TriggerField;
import view.game.GameDoor;
import view.game.GameMonster;
import view.game.GameTile;
import view.game.GameToken;
import view.game.MonsterKind;
import view.hero.GameHero;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.Menus;
import view.menu.QuestCreator;
import view.menu.QuestGame;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;
/**
 * the view manager
 * manages what will be shown
 * @author kingbaruk
 *
 */
public class ViewManager implements IView {

	private MainFrame frame;
	private MenuManager menu;
	private QuestCreator quester;
	private QuestGame game;
	
	
	@Override
	public IDrawWindow getActiveWindow() {
		// TODO Auto-generated method stub
		return null;
	} 

	public ViewManager(MainFrame frame){
		setFrame(frame);
		//setMenu(new MenuManager(frame));
	
	}
	public void prepareInitialMenu(){
		setMenu(new MenuManager(frame));
	}

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public MenuManager getMenu() {
		return menu;
	}

	public void setMenu(MenuManager menu) {
		this.menu = menu;
	}

	
	public void changeMenu(Menus newMenu){
		menu.GoToMenu(newMenu);
	}
	@Override
	public void exit() {
		frame.exit();
		frame.show(false);
		
	}

	@Override
	public void goBackInMenu() {
		menu.goBackMenu();
		
	}

	@Override
	public void startQuestEditor() {
		quester=new QuestCreator(frame.getWidth(),frame.getHeight(),frame.getUserInputController());
		frame.startQuestEditor(quester);
		
	}
	@Override
	public void loadGame(WorldSaveFile g) {
		quester.loadGame(g);
		
	}

	public void StartQuestGame() {
		 game=new QuestGame(new Dimension(frame.getWidth(),frame.getHeight()), new SampleFile());
		 //should be done in controller
		 frame.startTestGame(game);
	}

	@Override
	public ViewSquare getTileAt(int x, int y) {
		// TODO Auto-generated method stub
		return quester.getTileAt(x,y);
	}

	@Override
	public void addTileToSquare(TileItem tileName, ViewSquare square) {
		quester.addTile(tileName,square);
		
		
	}

	@Override
	public void changeTileColor(ViewSquare tile, Color color) {
		quester.changeColor(tile,color);
		
	}
	@Override
	public boolean isLegalOccupied(ViewSquare square, SelectAble selected) {
		// TODO Auto-generated method stub
		return quester.isLegalOccupied(square,selected);
	}

	@Override
	public ArrayList<ViewSquare> changeShapeColor(ShapeItem selected, ViewSquare square, Color color) {
		 return quester.changeShapeColor(selected,square,color);
		
	}

	@Override
	public ICommand getPlaceSelectedCommand(ViewSquare tile) {
		// TODO Auto-generated method stub
		return quester.getSelectedCommand(tile);
	}

	@Override
	public void setSelected(SelectAble item) {
		quester.setSelected(item);
		
	}

	@Override
	public void rotateSelected() {
		quester.rotateSelected();
		
	}
	@Override
	public void deleteSelected() {
		// TODO Auto-generated method stub
		quester.deleteSelected();
	}

	@Override
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return quester.hasSelected();
	}

	@Override
	public SelectAble getSelected() {
		
		return quester.getSelected();
	}

	@Override
	public boolean isConnected(ViewSquare square, TileItem selected) {
		// TODO Auto-generated method stub
		return quester.isConnected(square,selected);
	}

	@Override
	public ArrayList<ViewTileExit> getConnected(ViewSquare square,TileItem selected) {
		// TODO Auto-generated method stub
		return quester.getConnected(square,selected);
	}

	@Override
	public ArrayList<Connection> getConnections(ViewSquare square,TileItem selected) throws Exception {
		// TODO Auto-generated method stub
		return quester.getConnections(square,selected);
	}

	@Override
	public void fillExits() {
		// TODO Auto-generated method stub
		quester.fillExits();
	}

	@Override
	public void removeTile(ViewTile toselect) {
		quester.removeTile(toselect);
		
	}

	@Override
	public void addViewTileToSquare(ViewTile tile, ViewSquare square) {
		quester.addViewTileToSquare(tile,square);
		
	}

	@Override
	public void unselect() {
		// TODO Auto-generated method stub
		quester.unselect();
	}
	@Override
	public void addDoorToSquare(DoorItem door, ViewSquare square) {
		quester.addDoorToSquare(door,square);
		
	}

	@Override
	public void addGameTile(ViewTile toplace) {
		// TODO Auto-generated method stub
		game.addGameTile(toplace);
	}
	@Override
	public void addGameDoor(ViewDoor toplace) {
		// TODO Auto-generated method stub
		game.addGameDoor(toplace);
	}

	@Override
	public void showActivateAbles(Point point,ArrayList<ActivateAble> list) {
		game.showActivateAbles(point,list);
		
	}



	public void startGame(QuestGame gamemenu) {
		game=gamemenu;
		
	}

	@Override
	public void removeItem(MapItem toselect) {
		// TODO Auto-generated method stub
		quester.removeItem(toselect);
	}

	@Override
	public void addViewDoorToSquare(ViewDoor viewdoor, ViewSquare square) {
		quester.addViewDoorToSquare(viewdoor,square);
	}

	@Override
	public void startDragItem(MapItem toselect) {
		quester.startDragitem(toselect);
		
	}

	@Override
	public void startDragTile(ViewTile toselect) {
		// TODO Auto-generated method stub
		quester.startDragTile(toselect);
	}



	@Override
	public void startDragItem(BaseField todrag) {
		
		quester.startDragEvent(todrag);
	}

	@Override
	public void addSelectedToTriggerField(TriggerField field) {
		// TODO Auto-generated method stub
		quester.addSelectedToTriggerField(field);
	}

	@Override
	public void addEventToTriggerField(BaseField field, TriggerField basetrigger) {
		// TODO Auto-generated method stub
		quester.addEventToTriggerField(field,basetrigger);
	}

	@Override
	public void ShowSelectedInTriggerField(TriggerField whereshow) {
		// TODO Auto-generated method stub
		quester.showSelectedInTriggerField(whereshow);
	}

	@Override
	public void removeShownInField(TriggerField thefield) {
		// TODO Auto-generated method stub
		quester.removeShownInField(thefield);
	}

	@Override
	public void addTokenToSquare(TokenItem token, ViewSquare square) {
		// TODO Auto-generated method stub
		quester.addTokenToSquare(token,square);
	}

	@Override
	public void addViewTokenToSquare(ViewToken viewtoken, ViewSquare square) {
		// TODO Auto-generated method stub
		quester.addViewTokenToSquare(viewtoken,square);
	}

	@Override
	public MapItem addItemToSquare(MonsterItem monster, ViewSquare square) {
		return quester.addItemToSquare(monster,square);
		
	}

	@Override
	public MapItem addViewItemToSquare(ViewMonster viewmonster, ViewSquare square) {
		return quester.addViewItemToSquare(viewmonster,square);
		
	}

	@Override
	public void endDragEvent(DraggAblePanel todrag) {
		quester.endDragEvent(todrag);
		
	}

	@Override
	public void removeGameDoor(GameDoor door) {
		// TODO Auto-generated method stub
		game.removeGameDoor(door);
	}

	@Override
	public WorldSaveFile saveGame() {
		// TODO Auto-generated method stub
		return quester.saveThis();
		
	}

	@Override
	public void addStartTriggers() {
		quester.addStartTriggers();
	}

	@Override
	public void addGameToken(ViewToken toplace) {
		// TODO Auto-generated method stub
		game.addGameToken(toplace);
	}

	@Override
	public void removeGameToken(GameToken token) {
		// TODO Auto-generated method stub
		game.removeGameToken(token);
	}

	@Override
	public void removeGameTile(GameTile tile) {
		// TODO Auto-generated method stub
		game.removeGameTile(tile);
	}

	@Override
	public ArrayList<ViewSquare> getOccupiedSquares(ViewSquare square, ViewMonster themonster) {
		// TODO Auto-generated method stub
		return quester.getOccupiedSquares(square,themonster);
	}

	@Override
	public TriggerField getFieldAt(int x, int y) {
		// TODO Auto-generated method stub
		return quester.getFieldAt(x,y);
	}

	@Override
	public boolean eventBoxContains(int x, int y) {
		// TODO Auto-generated method stub
		return quester.eventBoxContains(x,y);
	}

	@Override
	public void showTextDialog(String text) {
		// TODO Auto-generated method stub
		game.showTextDialog(text);
	}

	@Override
	public void showTextDialog(String text, ArrayList<TextOption> newoptions) {
		if(game==null) {
			UserInputController control=UserInputController.getController();
			control.showTextDialog(text,newoptions);
		}
		else {
			game.showTextDialog(text,newoptions);
		}
	
	}

	@Override
	public GameMonster addGameMonster(ViewMonster toplace) {
		// TODO Auto-generated method stub
		return game.addGameMonster(toplace);
	}



	@Override
	public void makeInvisible(SelectAble mapit) {
		// TODO Auto-generated method stub
		quester.makeInvisible(mapit);
	}

	@Override
	public void makeVisible(SelectAble mapit) {
		// TODO Auto-generated method stub
		quester.makeVisible(mapit);
	}

	@Override
	public void makeItemVisible(MapItem mapit) {
		// TODO Auto-generated method stub
		quester.makeItemVisible(mapit);
	}

	@Override
	public void makeItemInVisible(MapItem mapit) {
		// TODO Auto-generated method stub
		quester.makeItemInvisible(mapit);
	}

	@Override
	public void renewItemList() {
		// TODO Auto-generated method stub
		quester.renewItemList();
	}

	@Override
	public void refreshSelected() {
		quester.refreshSelected();
		
	}

	@Override
	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect,
			MonsterKind kind) {
		// TODO Auto-generated method stub
		game.showMonsterMovement(monster,movement,continousEffect,kind);
	}

	@Override
	public void clearEventBox() {
		// TODO Auto-generated method stub
		quester.clearEventBox();
	}

	@Override
	public MonsterTurnTrigger getMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void defeatHero(GameHero hero) {
		// TODO Auto-generated method stub
		hero.defeat();
		
	}

	@Override
	public void removeGameMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		game.removeGameMonster(toremove);
	}

	@Override
	public void addActivationToActivateAble(ActivateAble active) {
		// TODO Auto-generated method stub
		quester.addActivationToActivateAble(active);
	}

	@Override
	public void removeActivationFromActivateAble(Activation activation, ActivateAble active) {
		// TODO Auto-generated method stub
		quester.removeActivationFromActivateAble(activation,active);
	}

	@Override
	public void initialiseBaseEvents(BaseEventController baseEventController) {
		// TODO Auto-generated method stub
		if(quester!=null) {
			quester.initialiseBaseEvents(baseEventController);
		}
	
	}

	@Override
	public void removeMapMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		game.removeMapMonster(toremove);
	}

	@Override
	public void startDragDragPanel(DraggAblePanel todrag) {
		
	}

	@Override
	public void setGame(QuestGame game) {
		// TODO Auto-generated method stub
		this.game=game;
	}

	@Override
	public void loadCampaignGame(CampaignSaveFile g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCityEventFile(CampaignSaveFile g) {
		// TODO Auto-generated method stub
		
	}



	

	




	
	



	
}
