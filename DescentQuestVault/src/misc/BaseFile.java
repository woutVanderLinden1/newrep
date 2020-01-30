package misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ItemEditor.SavedItem;
import model.Hero.Hero;
import model.values.CustomInteger;
import model.values.CustomValue;
import view.game.mappanel.ValueChanger;

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
	
	private String teamName;
	
	private HashMap<String,CustomValue> values=new HashMap<String,CustomValue>();
	private ArrayList<SavedItem> equipment=new ArrayList<SavedItem>();
	private HashMap<String,Integer> amountmap=new HashMap<String,Integer>();
	
	
	
	private ArrayList<Hero> heroes=new ArrayList<Hero>();
	
	public ArrayList<SavedItem> getEquipment() {
		return equipment;
	}

	public void addHero(Hero hero) {
		heroes.add(hero);
		
	}
	public void setEquipment(ArrayList<SavedItem> equipment) {
		this.equipment = equipment;
	}

	public void setHeroes(ArrayList<Hero> heroes) {
		this.heroes = heroes;
	}

	public int getHope() {
		return ((CustomInteger) values.get("hope")).getTheInteger();
	}

	public void setHope(int hope) {
		values.get("hope").setValue(hope);
	}


	
	
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
	
	public BaseFile(int nrofheroes, int i, int j, int k, String teamname2) {
		super();
		this.nrHeroes = nrofheroes;
		this.money = i;
		this.fame = j;
		this.despair = k;
		this.teamName = teamname2;
		this.hope=2;
		
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
		return ((CustomInteger) values.get("money")).getTheInteger();
	}
	public void setMoney(int money) {
		values.get("money").setValue(money);
	}
	public int getFame() {
		return ((CustomInteger) values.get("fame")).getTheInteger();
	}
	public void setFame(int fame) {
		values.get("fame").setValue(fame);
	}
	public int getDespair() {
		return ((CustomInteger) values.get("despair")).getTheInteger();
	}
	public void setDespair(int despair) {
		values.get("despair").setValue(despair);
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

	public void addValue(CustomValue val) {
		// TODO Auto-generated method stub
		
		if(!val.getName().equals("peril") && values.get(val.getName())==null) {
			values.put(val.getName(), val);
		}
		
	}

	public void removeItem(SavedItem value) {
		if(amountmap.get(value.getName())==null) {
			return;
			
		}
		else {
			int amount=amountmap.get(value.getName());
			amount--;
			amountmap.put(value.getName(),amount);
			if(amount==0) {
				equipment.remove(value);
			}
		}
		
		
	}

	public void addItem(SavedItem value) {
		equipment.add(value);
		if(amountmap.get(value.getName())!=null) {
			amountmap.put(value.getName(),1);
		}
		else {
			amountmap.put(value.getName(),amountmap.get(value.getName()));
		}
	}

	public void initialiseHeroes(ArrayList<Hero> chosenHeroes) {
		this.setHeroes(chosenHeroes);
		for(Hero her:chosenHeroes) {
			equipment.addAll(her.getItems());
		}
		
	}

	public void addHeroExp(int amount) {
		for(Hero her:heroes) {
			her.addExp(amount);
		}
	}

	public ValueChanger getMoneyValue() {
		// TODO Auto-generated method stub
		return values.get("money");
	}

	public HashMap<String, CustomValue> getValues() {
		// TODO Auto-generated method stub
		return values;
	}

	public boolean hasFullEquipment(SavedItem i) {
		if(i==null) {
			return true;
		}
		if(amountmap==null) {
			amountmap=new HashMap<String,Integer>();
		}
		System.out.println(i.getName());
		int a =0;
		if(amountmap.containsKey(i.getName())) {
			a=amountmap.get(i.getName());
		}
		
		if(a>=i.getAmount()) {
			return true;
		}
		return false;
	}

	
	
	
}
