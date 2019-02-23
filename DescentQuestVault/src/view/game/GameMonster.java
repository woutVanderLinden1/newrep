package view.game;

import java.util.ArrayList;

import controller.UserInputController;
import controller.command.game.EndTurnCommand;
import controller.commands.Game.ShowMonsterMovementCommand;
import controller.turns.TurnHolder;
import misc.ActivateAble;
import model.Activation;
import model.event.Trigger;
import monstercreator.MonsterMovement;
import monstercreator.SampleMovement;
import monstercreator.SingleMovement;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.hero.DefeatActivation;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;

public class GameMonster extends ViewMonster implements ActivateAble ,TurnHolder{

	private ViewMonster theViewMonster;
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	private MonsterMovement movement;
	private boolean stopped=false;
	
	public GameMonster(MonsterItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		activationList.add(new DefeatMonsterActivation(this));
		// TODO Auto-generated constructor stub
	}

	public GameMonster(ViewMonster toplace) {
		super((MonsterItem) toplace.getImageItem(),toplace.getBaseSquare(),0,0);
		theViewMonster=toplace;
		this.setAsPlaceMentSquares(toplace.getPlaceMonsterSquares());
		activationList.add(new DefeatMonsterActivation(this));
		// TODO Auto-generated constructor stub
		SampleMovement sample=new SampleMovement();
		ArrayList<SingleMovement> single=new ArrayList<SingleMovement>();
		single.add(sample);
		movement=new MonsterMovement(single);
		
	}

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}

	public MonsterItem getMonsterItem() {
		// TODO Auto-generated method stub
		return (MonsterItem) this.getImageItem();
	}

	public GameMonster copy() {
		// TODO Auto-generated method stub
		return new GameMonster(theViewMonster);
	}

	@Override
	public void startTurn() {
		//process monster movement
		this.getTurnTrigger().trigger();
		// TODO Auto-generated method stub
		
	}

	public Trigger getTurnTrigger() {
		// TODO Auto-generated method stub
		return theViewMonster.getTurnTrigger();
	}

	public void continueExecution() {
		stopped=false;
		
	}

}
