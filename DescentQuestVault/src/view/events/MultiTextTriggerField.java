package view.events;

import java.awt.FlowLayout;

import frame.SubContainer;
import misc.Tools;
import model.event.Event;
import model.event.Trigger;
import model.event.Univent;
import model.event.extraevents.AddOptionListener;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextTrigger;

public class MultiTextTriggerField extends MultiTriggerField implements AddOptionListener {

	private TextTrigger textTrigger;
	/*
	public MultiTextTriggerField(TextTrigger trig, int i, int j) {
		super(trig, i, j);
		
		// TODO Auto-generated constructor stub
	}
	*/

	public MultiTextTriggerField(TextTrigger textOption) {
		super(textOption, 0, 0);
		textTrigger=textOption;
		//add a field for each option
		//addTextOptions();
		//refreshHeight();
		this.createBaseImage();
		textOption.addAddOptionListener(this);
		
		
	}

	private void addTextOptions() {
		// TODO Auto-generated method stub
		
		for(TextOption option:textTrigger.getOptions()) {
			this.initialiseTextOption(option);
		}
		
		
		
	}

	private void initialiseTextOption(TextOption option) {
		//add new subeventfield
		TriggerContainer contain=new TriggerContainer(option,this.getWidth()-25,80,this);
		contain.setName(option.getName());
		addTriggerContainer(contain);
		for(Univent vent:option.getUnivents()) {
			
			switch(vent.getKind()) {
			case DOOR:
				break;
			case EVENT:
				
				contain.addEvent((Event) vent);
				
				break;
			case MODIFIER:
			case TRIGGER:
			
				contain.addTrigger((Trigger) vent,false);
				
				
				break;
		
		
			default:
				break;
			
			}
			
			
		}
		
		//add the mouselistener
		
	}

	@Override
	public void optionAdded(TextOption textOption) {
		initialiseTextOption(textOption);
		createImage(currentColor);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void optionRemoved(TextOption opt) {
		removeTextOption(opt);
		createImage(currentColor);
		this.revalidate();
		this.repaint();
	}

	private void removeTextOption(TextOption opt) {
		TriggerContainer contain=Tools.CorrespondingTriggerField(opt,subEventlist);
		this.removeTriggerContainer(contain);
		
	}

	




	
	

	
	
	

}
