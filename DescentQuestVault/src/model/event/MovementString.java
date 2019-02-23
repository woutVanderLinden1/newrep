package model.event;

import java.io.Serializable;

public class MovementString implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String themovement;

	public String getThemovement() {
		return themovement;
	}

	public void setThemovement(String themovement) {
		this.themovement = themovement;
	}

	public MovementString(String themovement) {
		super();
		this.themovement = themovement;
	}
}
