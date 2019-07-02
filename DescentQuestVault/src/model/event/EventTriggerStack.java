package model.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class EventTriggerStack {

	private Stack<Univent> stack;
	
	public static  EventTriggerStack EventTriggerStack;
	
	public static EventTriggerStack getTriggerStack() {
		if(EventTriggerStack==null) {
			EventTriggerStack=new EventTriggerStack();
		}
		return EventTriggerStack;
	}
	public EventTriggerStack() {
		stack=new Stack<Univent>();
	}
	
	public void triggerNextStackEvent() {
		if(!stack.isEmpty()) {
			Univent vent=stack.pop();
			vent.trigger();
			if(!vent.isStopEvent()) {
				this.triggerNextStackEvent();
			}
		}
		
	}
	
	public void addNewEvents(ArrayList<Univent> newevents) {
		ArrayList<Univent> list=new ArrayList<Univent>(newevents);
		Collections.reverse(list);
		stack.addAll(list);
	}
}
