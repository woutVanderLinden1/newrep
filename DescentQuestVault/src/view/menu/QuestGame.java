package view.menu;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import controller.EndPhaseListener;
import controller.EndRoundListener;
import controller.turns.GameController;
import frame.SubContainer;
import misc.ActivateAble;
import misc.BaseFile;
import misc.SampleFile;
import model.ItemController;
import model.event.MovementString;
import model.event.StartGameListener;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import model.event.trigger.EndPhaseTrigger;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.game.GameDoor;
import view.game.GameMonster;
import view.game.MonsterKind;
import view.game.mappanel.GameMapPanel;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;

public class QuestGame extends SubContainer implements EndRoundListener {

	private GameMapPanel gamemap;
	private BaseFile basefile;
	private ArrayList<StartGameListener> gamestartlisteners=new ArrayList<StartGameListener>();
	private ArrayList<EndPhaseListener> endphaselisteners=new ArrayList<EndPhaseListener>();
	private GameController control;
	
	public void addGameStartListener(StartGameListener listen) {
		gamestartlisteners.add(listen);
	}
	
	public void notifyGameStartListeners() {
		
		for(StartGameListener listen:gamestartlisteners) {
			 
			listen.gameStarted();
				 
		
			
		}
	}
	
	public void notifyEndPhaseListeners() {
		
		for(EndPhaseListener listen:endphaselisteners) {
			listen.trigger();
		}
	}
	
	public QuestGame(Dimension defaultSize) {
		super(defaultSize);
		//basefile=sampleFile;
		gamemap=new GameMapPanel(defaultSize);
		this.add(gamemap);
		
	}

	public void setGameController(GameController control) {
		control.addEndRoundListener(this);
		control.setGame(this);
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



	public GameMonster addGameMonster(ViewMonster toplace) {
		// TODO Auto-generated method stub
		return gamemap.addGameMonster(toplace);
	}



	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect,MonsterKind kind) {
		// TODO Auto-generated method stub
		gamemap.showMonsterMovement(monster,movement,continousEffect, kind);
	}



	public void initialiseGame(BaseFile sampleFile) {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		control.initialiseFile(sampleFile);
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

	public void startGame() {
		// TODO Auto-generated method stub
		this.notifyGameStartListeners();
	}

	public void addEndPhaseListener(EndPhaseTrigger endtrigger) {
		// TODO Auto-generated method stub
		endphaselisteners.add(endtrigger);
		
	}

	@Override
	public void endRound(int rounds) {
		// TODO Auto-generated method stub
		this.notifyEndPhaseListeners();
	}

	public void removeMapMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		gamemap.removeMapMonster(toremove);
	}


}
