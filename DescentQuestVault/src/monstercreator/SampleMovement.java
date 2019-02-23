package monstercreator;

import java.io.Serializable;

public class SampleMovement extends SingleMovement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SampleMovement() {
		this.addToMinionMovement("move to the closest hero");
		this.addToMinionMovement("attack the closest hero");
		this.addToMasterMovement("move to the closest hero");
		this.addToMasterMovement("attack to the closest hero");
	
	}
}
