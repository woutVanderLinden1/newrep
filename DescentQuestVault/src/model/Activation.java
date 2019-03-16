package model;

import model.event.Trigger;

public abstract  class Activation {

	public abstract void trigger();

	public abstract  String getName();

	public abstract void changeName(String text);

	public abstract Trigger getTrigger();
	public abstract void setTrigger(Trigger trig);
}
