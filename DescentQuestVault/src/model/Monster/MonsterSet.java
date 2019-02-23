package model.Monster;

import java.io.Serializable;

public class MonsterSet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int masterMonsters;
	private int minionMonsters;
	public MonsterSet(int masterMonsters, int minionMonsters) {
		super();
		this.masterMonsters = masterMonsters;
		this.minionMonsters = minionMonsters;
	}
	public int getMasterMonsters() {
		return masterMonsters;
	}
	public void setMasterMonsters(int masterMonsters) {
		this.masterMonsters = masterMonsters;
	}
	public int getMinionMonsters() {
		return minionMonsters;
	}
	public void setMinionMonsters(int minionMonsters) {
		this.minionMonsters = minionMonsters;
	}
}
