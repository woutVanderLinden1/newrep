package model.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Item;
import model.event.MonsterSpecial;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import view.viewItems.ItemBox.ItemOptions;

public abstract class Monster extends Item {

	protected Map<Integer,MonsterSet> map;
	protected MonsterTurnTrigger defaultMovement;
	protected ArrayList<MonsterSpecial> monsterSpecialList=new ArrayList<MonsterSpecial>();
	
	
	public Monster(String name) {
		super(name);
		map=new HashMap<Integer,MonsterSet>();
		// TODO Auto-generated constructor stub
	}
	
	public void addMonsterSpecial(MonsterSpecial special) {
		monsterSpecialList.add(special);
	}


	@Override
	public ItemOptions getItemKind() {
	
		return ItemOptions.Monster;
	}


	public Map<Integer, MonsterSet> getMap() {
		return map;
	}


	public void setMap(Map<Integer, MonsterSet> map) {
		this.map = map;
	}


	public MonsterTurnTrigger getDefaultTurnTrigger() {
		// TODO Auto-generated method stub
		return new MonsterTurnTrigger(this);
	}



	public MonsterTurnTrigger getDefaultTrigger() {
		return defaultMovement;
		
	}


	public abstract int getMonsterLimit();

	public ArrayList<MonsterSpecial> getMonsterSpecials() {
		// TODO Auto-generated method stub
		return monsterSpecialList;
	}
	

	
	

}
