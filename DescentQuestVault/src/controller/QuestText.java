package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.turns.GameController;
import frame.SubContainer;
import misc.ActivateAble;
import misc.BaseFile;
import model.ItemController;
import model.event.EventEndListener;
import model.event.MovementString;
import model.event.StartGameListener;
import model.event.extraevents.TextOption;
import model.event.trigger.EndPhaseTrigger;
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

public class QuestText extends SubContainer  {

	private TextMapPanel gamemap;
	private BaseFile basefile;
	private ArrayList<StartGameListener> gamestartlisteners=new ArrayList<StartGameListener>();
	private ArrayList<EndPhaseListener> endphaselisteners=new ArrayList<EndPhaseListener>();
	private GameController control;
	private JPanel textPanel=new JPanel();
	
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
	
	public QuestText(Dimension defaultSize) {
		super(defaultSize);
		//basefile=sampleFile;
		//this.add(gamemap);
		this.setBackground(Color.BLACK);
		
	}

	public QuestText(int w, int h, UserInputController userInputController) {
		super(w,h);
		gamemap=new TextMapPanel(new Dimension(w,h));
		this.add(gamemap);
		this.setBackground(Color.BLACK);
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



	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect,MonsterKind kind,EventEndListener listen) {
		// TODO Auto-generated method stub
		gamemap.showMonsterMovement(monster,movement,continousEffect, kind, listen);
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


	public void removeMapMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		gamemap.removeMapMonster(toremove);
	}

	
}
