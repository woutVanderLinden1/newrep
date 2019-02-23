package monstercreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MonsterMovement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SingleMovement> movements=new ArrayList<SingleMovement>();

	public ArrayList<SingleMovement> getMovements() {
		return movements;
	}

	public void setMovements(ArrayList<SingleMovement> movements) {
		this.movements = movements;
	}

	public MonsterMovement(ArrayList<SingleMovement> movements) {
		super();
		this.movements = movements;
	}
	
	public void addMovement(SingleMovement move) {
		movements.add(move);
	}

	public SingleMovement getRandomMovement() {
		Random rand= new Random();
		int i =rand.nextInt(movements.size());
		// TODO Auto-generated method stub
		return movements.get(i);
	}
}
