package view.hero;

import java.util.ArrayList;

import controller.turns.TurnHolder;
import misc.ActivateAble;
import model.Activation;
import model.Hero.Hero;
import view.game.HeroPanel;

public class GameHero extends ViewHero  implements ActivateAble,TurnHolder{

	
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	private Hero hero;
	private ArrayList<DefeatChangeListener> defeatedchangelisteners=new ArrayList<DefeatChangeListener>();
	private ArrayList<EndTurnListener> endTurnListeners=new ArrayList<EndTurnListener>();
	private boolean defeated;
	private EndTurnActivation endact;
	private DefeatActivation defeatact;
	private StandUpActivation standupact;
	private boolean turnended;
	
	public boolean isTurnended() {
		return turnended;
	}
	public void setTurnended(boolean turnended) {
		this.turnended = turnended;
	}
	public GameHero(Hero hero) {
		super(hero);
		this.hero=hero;
		endact=new EndTurnActivation(this);
		defeatact=new DefeatActivation(this);
		standupact=new StandUpActivation(this);
		addBasicActivations();
	}
	public void addBasicActivations() {
		activationList.clear();
		if(!turnended) {
			activationList.add(endact);
		}
	
		activationList.add(defeatact);
	}
	public void addDefeatActivations() {
		activationList.clear();
		activationList.add(standupact);
	
	}
	

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}

	@Override
	public boolean isActivateAble() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void startTurn() {
		//do nothing a hero turn is normally processed.
		// TODO Auto-generated method stub
		
	}

	public void defeat() {
		defeated=true;
		notifyDefeatChangeListeners(defeated);
		addDefeatActivations();
	}
	private void notifyDefeatChangeListeners(boolean defeated2) {
		for(DefeatChangeListener listen:defeatedchangelisteners) {
			listen.defeated(defeated);
		}
		
	}

	public void standUp() {
		defeated=false;
		notifyDefeatChangeListeners(defeated);
		addBasicActivations();
	}

	public boolean isdefeated() {
		
		return defeated;
	}

	public void addDefeatChangeListener(DefeatChangeListener heroPanel) {
		// TODO Auto-generated method stub
		defeatedchangelisteners.add(heroPanel);
	}
	@Override
	public void endTurn() {
		turnended=true;
		notifyEndTurnListeners();
		addBasicActivations();
		
	}
	private void notifyEndTurnListeners() {
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
		turnended=false;
		notifyEndTurnListeners();
		addBasicActivations();
		
	}
	@Override
	public void addActivation(Activation act) {
		activationList.add(act);
		
	}
	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		activationList.remove(activation);
	}

}
