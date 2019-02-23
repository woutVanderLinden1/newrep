package monstercreator;

import java.io.Serializable;
import java.util.ArrayList;

import model.event.MovementString;

public class SingleMovement implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected MovementString continousMinionEffect;
	protected MovementString continousMasterEffect;
	protected ArrayList<MovementString> minionmovement;
	protected ArrayList<MovementString> mastermovement;
	private boolean masterfirst=false;
	
	public boolean isMasterfirst() {
		return masterfirst;
	}
	public void setMasterfirst(boolean masterfirst) {
		this.masterfirst = masterfirst;
	}
	
	public SingleMovement() {
		continousMinionEffect=new MovementString("");
		continousMasterEffect=new MovementString("");
		minionmovement=new ArrayList<MovementString>();
		mastermovement=new ArrayList<MovementString>();
	}
	
	public void addToMinionMovement(String toadd) {
		minionmovement.add(new MovementString(toadd));
	}
	
	public void addToMasterMovement(String toadd) {
		mastermovement.add(new MovementString(toadd));
	}

	public MovementString getContinousMinionEffect() {
		return continousMinionEffect;
	}

	public void setContinousMinionEffect(String continousMinionEffect) {
		this.continousMinionEffect = new MovementString(continousMinionEffect);
	}

	public MovementString getContinousMasterEffect() {
		return continousMasterEffect;
	}

	public void setContinousMasterEffect(String continousMasterEffect) {
		this.continousMasterEffect = new MovementString(continousMasterEffect);
	}

	public ArrayList<MovementString> getMinionmovement() {
		return minionmovement;
	}

	public void setMinionmovement(ArrayList<MovementString> minionmovement) {
		this.minionmovement = minionmovement;
	}

	public ArrayList<MovementString> getMastermovement() {
		return mastermovement;
	}

	public void setMastermovement(ArrayList<MovementString> mastermovement) {
		this.mastermovement = mastermovement;
	}
	public void removeFromMasterMovement() {
		mastermovement.remove(mastermovement.size()-1);
		
	}
	public void removeFromMinionMovement() {
		minionmovement.remove(minionmovement.size()-1);
		
	}
	
	

	
}
