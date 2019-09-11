package monstercreator;

import java.io.Serializable;

public class SampleMovement extends SingleMovement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SampleMovement() {
		this.addToMinionMovement("Beweeg naar de dichtsbijzijnde held");
		this.addToMinionMovement("Val de dichtsbijzijnde held aan");
		this.addToMasterMovement("Beweeg naar de dichtsbijzijnde held");
		this.addToMasterMovement("Val de dichtsbijzijnde held aan");
	
	}
}
