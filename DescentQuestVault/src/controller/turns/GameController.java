package controller.turns;

import java.util.ArrayList;
import java.util.Random;

import controller.AddGameHeroListener;
import controller.AddGameMonsterListener;
import controller.EndRoundListener;
import controller.IGameController;
import misc.BaseFile;
import view.game.GameMonster;
import view.hero.GameHero;
import view.menu.QuestGame;

public class GameController implements IGameController, AddGameHeroListener,AddGameMonsterListener{

	
	private ArrayList<EndRoundListener> endRoundListeners=new ArrayList<EndRoundListener>();
	private int rounds;
	private BaseFile file;
	private Turn currentTurn;
	private TurnKind lastturnkind;
	private ArrayList<HeroTurn> baseheroturns=new ArrayList<HeroTurn>();
	private ArrayList<MonsterTurn> basemonsterturns=new ArrayList<MonsterTurn>();
	private ArrayList<MonsterTurn> monsterTurnList=new ArrayList<MonsterTurn>();;
	private ArrayList<HeroTurn> heroturns=new ArrayList<HeroTurn>();
	private Random rand=new Random();
	private QuestGame game;
	
	
	public GameController() {
		refreshTurns();
	}
	//when performing a turn it should make a new thread!!


	@Override
	public void addMonster(GameMonster monster) {
		MonsterTurn turn =new MonsterTurn(monster);
		monsterTurnList.add(turn);
		basemonsterturns.add(turn);
	}
	
	public void addSpecialTurn() {
		
	}


	@Override
	public void addHero(GameHero hero) {
		// TODO Auto-generated method stub
		HeroTurn turn =new HeroTurn(hero);
		heroturns.add(turn);
		baseheroturns.add(turn);
	}


	public void startNextTurn() {
		System.out.println("started the next turn");
		if(heroturns.size()+monsterTurnList.size()==0) {
			refreshTurns();
		}
		
		if(heroturns.size()==0) {
			
			processMonsterTurn();
			//get random hero turn
		}
		else {
			if(monsterTurnList.size()==0) {
				processHeroTurn();
				
			}
			else {
				switch(lastturnkind) {
				case HEROES:
					processMonsterTurn();
					break;
				case MONSTER:
					processHeroTurn();
					break;
				default:
					break;
				
				}
			}
		}
		
		
		
		// TODO Auto-generated method stub
		
	}


	private void processHeroTurn() {
		int t=heroturns.size();
		int i=rand.nextInt(t);
		currentTurn=heroturns.get(i);
		lastturnkind=currentTurn.getTurnKind();
		Thread thread=new Thread() {
			public void run() {
				currentTurn.performTurn();
			}
		};
		 thread.start();
		 heroturns.remove(currentTurn);
		//Thre
		
		// TODO Auto-generated method stub
		
	}
	private void processMonsterTurn() {
		// TODO Auto-generated method stub
		int t=monsterTurnList.size();
		if(t<1) {
			return;
		}
		int i=rand.nextInt(t);
		currentTurn=monsterTurnList.get(i);
		lastturnkind=currentTurn.getTurnKind();
		//currentTurn.performTurn();
		
		Thread thread=new Thread() {
			public void run() {
			currentTurn.performTurn();
			}
		};
		
		thread.start();
		monsterTurnList.remove(currentTurn);
	}


	private void refreshTurns() {
		System.out.println("refreshed round "+rounds);
		
		monsterTurnList.addAll(basemonsterturns);
		heroturns.addAll(baseheroturns);
		rounds++;
		for(Turn turn:heroturns) {
			turn.refreshTurn();
		}
		for(Turn turn:monsterTurnList) {
			turn.refreshTurn();
		}
		triggerNextRoundListeners();
		// TODO Auto-generated method stub
		lastturnkind=TurnKind.MONSTER;
	}


	private void triggerNextRoundListeners() {
		for(EndRoundListener listen:endRoundListeners) {
			listen.endRound(rounds);
		}
		
		// TODO Auto-generated method stub
		
	}


	public void removeMonster(GameMonster monster) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<monsterTurnList.size();i++) {
			MonsterTurn turn=monsterTurnList.get(i);
			if(turn.getTurnhold()==monster) {
				monsterTurnList.remove(turn);
				i--;
			}
		
			
		}
		for(int i=0;i<basemonsterturns.size();i++) {
			MonsterTurn turn=basemonsterturns.get(i);
			if(turn.getTurnhold()==monster) {
				basemonsterturns.remove(turn);
				i--;
			}
		
		}
	}


	public void addEndRoundListener(QuestGame questGame) {
		// TODO Auto-generated method stub
		endRoundListeners.add(questGame);
	}


	public void setGame(QuestGame questGame) {
		// TODO Auto-generated method stub
		game=questGame;
	}
	
	

	
}
