package model.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class EventTriggerStack {

	private Stack<Univent> stack;
	
	public static  EventTriggerStack EventTriggerStack;
	
	private ArrayList<EmptyListener> emptyListeners=new ArrayList<EmptyListener>();
	
	
	public static EventTriggerStack getTriggerStack() {
		if(EventTriggerStack==null) {
			EventTriggerStack=new EventTriggerStack();
		}
		return EventTriggerStack;
	}
	public EventTriggerStack() {
		stack=new Stack<Univent>();
	}

	public boolean triggerNextStackEvent() {
		if(!stack.isEmpty()) {
			Univent vent=stack.pop();
			if(vent.isStopEvent()) {
				vent.addEventEndListener(new EventEndListener() {

					@Override
					public void eventEnded() {
						System.out.println("do next");
						triggerNextStackEvent();
						vent.clearEventEndListeners();
					}
					
				});
				vent.trigger();
				//
			}
		
			
			if(!vent.isStopEvent()) {
				vent.trigger();
				this.triggerNextStackEvent();
			}
			
			return true;
		}
		this.triggerEmptyListeners();
		return false;
		
	}
	
	public void addNewEvents(ArrayList<Univent> newevents) {
		ArrayList<Univent> list=new ArrayList<Univent>(newevents);
		Collections.reverse(list);
		stack.addAll(list);
	}
	public void addEmptyListener(EmptyListener listen) {
		emptyListeners.add(listen);
	}
	public void triggerEmptyListeners() {
		for(int i=emptyListeners.size()-1;i>=0;i--) {
			EmptyListener listen=emptyListeners.get(i);
		
			listen.emptied();
		}
	}
	public void removeEmptyListener(EmptyListener emptyListener) {
		emptyListeners.remove(emptyListener);
		
	}
}
