package misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Hero.Hero;
import model.values.CustomInteger;
import model.values.CustomValue;

/**
 * contains base information for starting a game.
 * @author User
 *
 */
public class BaseFile implements Serializable {

	private int nrHeroes;
	private int money;
	private int fame;
	private int despair;
	private int hope;
	
	public int getHope() {
		return hope;
	}

	public void setHope(int hope) {
		this.hope = hope;
	}

	private String teamName;
	
	private HashMap<String,CustomValue> values=new HashMap<String,CustomValue>();
	private ArrayList<Equipment> equipment;
	
	
	private ArrayList<Hero> heroes=new ArrayList<Hero>();
	
	
	public BaseFile(int nrHeroes, int money, int fame, int despair, String teamName, Hero hero1, Hero hero2) {
		super();
		this.nrHeroes = nrHeroes;
		this.money = money;
		this.fame = fame;
		this.despair = despair;
		this.teamName = teamName;
		this.hope=2;
		heroes.add(hero1);
		heroes.add(hero2);
		values.put("money",new CustomInteger("money",money));
		values.put("fame",new CustomInteger("fame",fame));
		values.put("despair",new CustomInteger("despair",despair));
		values.put("hope",new CustomInteger("hope",hope));
	}
	
	public int getNrHeroes() {
		return nrHeroes;
	}
	public void setNrHeroes(int nrHeroes) {
		this.nrHeroes = nrHeroes;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getFame() {
		return fame;
	}
	public void setFame(int fame) {
		this.fame = fame;
	}
	public int getDespair() {
		return despair;
	}
	public void setDespair(int despair) {
		this.despair = despair;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	

	public ArrayList<Hero> getHeroes() {
		// TODO Auto-generated method stub
		return heroes;
	}

	public HashMap getConstantMap() {
		// TODO Auto-generated method stub
		return values;
	}
	
	
	
}
