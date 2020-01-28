package model;

import java.io.Serializable;

import model.event.Trigger;

public abstract  class Activation  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract void trigger();

	public abstract  String getName();

	public abstract void changeName(String text);

	public abstract Trigger getTrigger();
	public abstract void setTrigger(Trigger trig);
}
