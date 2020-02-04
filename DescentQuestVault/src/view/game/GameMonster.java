package view.game;

import java.awt.Image;
import java.util.ArrayList;

import controller.UserInputController;
import controller.command.game.EndTurnCommand;
import controller.commands.Game.ShowMonsterMovementCommand;
import controller.turns.TurnHolder;
import misc.ActivateAble;
import model.Activation;
import model.event.EventEndListener;
import model.event.Trigger;
import monstercreator.MonsterMovement;
import monstercreator.SampleMovement;
import monstercreator.SingleMovement;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.hero.DefeatActivation;
import view.hero.DefeatChangeListener;
import view.hero.EndTurnListener;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;

public class GameMonster extends ViewMonster implements ActivateAble ,TurnHolder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ViewMonster theViewMonster;
	
	private MonsterMovement movement;
	private boolean stopped=false;
	private boolean turnEnded;
	private ArrayList<DefeatChangeListener> defeatedchangelisteners=new ArrayList<DefeatChangeListener>();
	private ArrayList<EndTurnListener> endTurnListeners=new ArrayList<EndTurnListener>();
	private ArrayList<GameMonster> mapmonsters=new ArrayList<GameMonster>();
	
	/*
	public GameMonster(MonsterItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		activationList.add(new DefeatMonsterActivation(this));
		// TODO Auto-generated constructor stub
	}
	*/

	
	public GameMonster(ViewMonster toplace,Boolean bool) {
		super((MonsterItem) toplace.getImageItem(),toplace.getBaseSquare(),0,0);
		theViewMonster=toplace;
		setTriggers(toplace);
		
	}
	
	public GameMonster(ViewMonster toplace) {
		super((MonsterItem) toplace.getImageItem(),toplace.getBaseSquare(),0,0);
		activations.clear();
		System.out.println("activationsize "+this.getActivations().size());
		theViewMonster=toplace;
		toplace.getTurnTrigger().clearEventEndListeners();
		this.setAsPlaceMentSquares(toplace.getPlaceMonsterSquares());
		
		// TODO Auto-generated constructor stub
		SampleMovement sample=new SampleMovement();
		ArrayList<SingleMovement> single=new ArrayList<SingleMovement>();
		single.add(sample);
		movement=new MonsterMovement(single);
		this.setTriggers(toplace);
		initiateMonsterActivations(toplace.getMonsterActivations());
		activations=toplace.getActivations();
		
		super.activationList=toplace.getMonsterActivations();
		//monsterActivations
		//this.setRemoveMonsterEvent(toplace.getRemoveMonsterEvent());
		//this.getRemoveMonsterEvent().setGameMonster(this);
		GameMonster mon=this;
		mon.getTurnTrigger().addEventEndListener(new EventEndListener() {
			
		
			

			@Override
			public void eventEnded() {
				UserInputController control=UserInputController.getController();
				control.performCommand(new EndTurnCommand(mon));
			}
		});
		
		System.out.println("toremove monster is "+toplace.getRemoveMonsterEvent().getGameMonster());
		
	}

	



	private void setTriggers(ViewMonster toplace) {
		this.setDefeatTrigger(toplace.getDefeatTrigger());
		toplace.getRemoveMonsterEvent().setGameMonster(this);
		this.setRemoveMonsterEvent(toplace.getRemoveMonsterEvent());
		this.setMonsterInfoEvent(toplace.getMonsterInfoEvent());
	
	}

	private void initiateMonsterActivations(ArrayList<MonsterActivation> monsterActivations) {
		setMonsterActivations(monsterActivations);
		for(MonsterActivation act:monsterActivations) {
			act.setMonster(this);
		}
		
	}

	public MonsterItem getMonsterItem() {
		// TODO Auto-generated method stub
		return (MonsterItem) this.getImageItem();
	}

	public GameMonster copy() {
		// TODO Auto-generated method stub
		return new GameMonster(theViewMonster,false);
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

	@Override
	public void endTurn() {
	
		turnEnded=true;
		notifyEndTurnListeners();
	}

	private void notifyEndTurnListeners() {
		// TODO Auto-generated method stub
		for(EndTurnListener listen:endTurnListeners) {
			listen.TurnEnded();
		}
		
	}
	public void addTurnEndListener(EndTurnListener heroPanel) {
		// TODO Auto-generated method stub
		endTurnListeners.add(heroPanel);
	}

	
	@Override
	public void refreshTurn() {
		// TODO Auto-generated method stub
		turnEnded=false;
		notifyEndTurnListeners();
	}

	public boolean isTurnended() {
		
		return turnEnded;
	}

	public void defeat() {
		// TODO Auto-generated method stub
		
	}

	public Image getScaleImage(int i) {
		// TODO Auto-generated method stub
		return theViewMonster.getScaleImage(i);
	}

	public void addMapMonster(GameMonster monsters) {
		// TODO Auto-generated method stub
		mapmonsters.add(monsters);
	}
	public ArrayList<GameMonster> getMapMonsters(){
		return mapmonsters;
	}

	@Override
	public ArrayList<Activation> getActivations() {
		System.out.println("activations got");
		ArrayList<Activation> acts=new ArrayList<Activation>();
		acts.addAll(super.getActivations());
		return acts;
	}

	public Image getPreciseImage(int i, int j) {
		// TODO Auto-generated method stub
		return theViewMonster.getPreciseImage(i,j);
	}

	

}
