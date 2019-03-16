package view.menu;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import controller.GameController;
import frame.SubContainer;
import misc.ActivateAble;
import misc.BaseFile;
import misc.SampleFile;
import model.event.MovementString;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.game.GameDoor;
import view.game.GameMapPanel;
import view.game.GameMonster;
import view.game.MonsterKind;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;

public class QuestGame extends SubContainer {

	private GameMapPanel gamemap;
	private BaseFile basefile;
	
	public QuestGame(Dimension defaultSize) {
		super(defaultSize);
		//basefile=sampleFile;
		gamemap=new GameMapPanel(defaultSize);
		this.add(gamemap);
	}



	public void addGameTile(ViewTile toplace) {
		// TODO Auto-generated method stub
		gamemap.addGameTile(toplace);
	}

	public void addDoorToSquare(DoorItem door, ViewSquare square) {
		//gamemap.addDoorToSquare(door,square);
	}

	public void addGameDoor(ViewDoor toplace) {
		gamemap.addGameDoor(toplace);
		
	}

	public void showActivateAbles(Point point,ArrayList<ActivateAble> list) {
		// TODO Auto-generated method stub
		gamemap.showActivateAbles(point,list);
	}

	public void removeGameDoor(GameDoor door) {
		gamemap.removeGameDoor(door);
	}

	public void addGameToken(ViewToken toplace) {
		gamemap.addGameToken(toplace);
		
	}

	public void removeGameToken(ViewToken token) {
		gamemap.removeGameToken(token);
		
	}

	public void removeGameTile(ViewTile tile) {
		// TODO Auto-generated method stub
		gamemap.removeGameTile(tile);
	}



	public void showTextDialog(String text) {
		gamemap.showTextDialog(text);
		
	}



	public void showTextDialog(String text, ArrayList<TextOption> newoptions) {
		gamemap.showTextDialog(text,newoptions);
		
	}



	public void addGameMonster(ViewMonster toplace) {
		// TODO Auto-generated method stub
		gamemap.addGameMonster(toplace);
	}



	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect,MonsterKind kind, StopAble stop) {
		// TODO Auto-generated method stub
		gamemap.showMonsterMovement(monster,movement,continousEffect, kind,stop);
	}



	public void initialiseGame(BaseFile sampleFile) {
		// TODO Auto-generated method stub
		gamemap.initialiseGame(sampleFile);
	}



	public void addMonsterPlaceListener(GameController gamecontrol) {
		// TODO Auto-generated method stub
		gamemap.addMonsterPlaceListener(gamecontrol);
	}



	public void addHeroPlaceListener(GameController gamecontrol) {
		// TODO Auto-generated method stub
		gamemap.addHeroPlaceListener(gamecontrol);
	}



	public void removeGameMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		gamemap.removeGameMonster(toremove);
	}


}
