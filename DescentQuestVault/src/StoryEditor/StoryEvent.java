package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import ItemEditor.ActionTaker;
import controller.UserInputController;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.Game.ShowTextCommand;
import misc.ActivateAble;
import model.Activation;
import model.ItemController;
import model.event.Event;
import model.event.MovementString;
import model.event.Trigger;
import model.event.Univent;
import model.event.extraevents.TextOption;
import model.values.Comparison;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public abstract class StoryEvent extends Event {
	
	private transient ArrayList<AddStoryEventListener> addstoryeventlisteners=new ArrayList<AddStoryEventListener>();

	private EndStoryEvent endevent;
	private ArrayList<StoryEvent> nextevents=new ArrayList<StoryEvent>();
	private StoryEventKind storykind;
	private ArrayList<DataCondition> valueconditions=new ArrayList<DataCondition>();
	
	public Univent getEndEvent() {
		// TODO Auto-generated method stub
		return endevent;
	}

	public StoryEventKind getStoryKind() {
		return storykind;
	}
	public void setStoryKind(StoryEventKind kind) {
		this.storykind = kind;
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	public void addAddStoryEventListener(AddStoryEventListener storyEventBox) {
		if(addstoryeventlisteners==null) {
			addstoryeventlisteners=new ArrayList<AddStoryEventListener>();
		}
		addstoryeventlisteners.add(storyEventBox);
		
	}

	public void notifyStoryEventListener(StoryEvent added) {
		
		for(AddStoryEventListener listen:addstoryeventlisteners) {
			listen.storyEventAdded(added);
		}
	}
	public void notifyStoryEventListenerTriggerAdded(Trigger added) {
		for(AddStoryEventListener listen:addstoryeventlisteners) {
			listen.triggerAdded(added);
		}
	}
	public void notifyStoryEventListenerTriggerRemoved(Trigger removed) {
		for(AddStoryEventListener listen:addstoryeventlisteners) {
			listen.triggerRemoved(removed);
		}
		
	}
	public void notifyStoryEventListenerRemoved(StoryEvent removed) {
		for(AddStoryEventListener listen:addstoryeventlisteners) {
			listen.storyEventRemoved(removed);
		}
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		System.out.println("added specifics");
		//add each movement texteditor and new movement adder
		//and option to do minions or masters first
		
		//conditions and kind
		if(this.getStoryKind()!=StoryEventKind.START &&this.getStoryKind()!=StoryEventKind.END) {
			addAddValueConditionButton(itemInfoText);
			addAddBooleanConditionButton(itemInfoText);
			addAddCustomValueConditionButton(itemInfoText);
			addAddCustomBooleanConditionButton(itemInfoText);
			addConditions(itemInfoText);
		}
		
		itemInfoText.addTextBox("next StoryEvents");
		for(StoryEvent ev:nextevents) {
			//show the next events
			//with a remove button
			addRemoveStoryEventButton(itemInfoText,ev);
			
		}
		
		addAddStoryAdventureEventButton(itemInfoText);
		addAddStoryTextEventButton(itemInfoText);
		addAddStoryFreeTimeEventButton(itemInfoText);
		
	
		super.addEventSpecifics(itemInfoText);
	}

	private void addAddCustomBooleanConditionButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewCondition(new CustomBooleanCondition(comboOptions[0],comboOptions[1]));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Customboolean condition");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}
	private void addAddCustomValueConditionButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewCondition(new CustomIntegerCondition(comboOptions[0],Comparison.EQUALS,comboOptions[1]));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Custominteger condition");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}
	private void addAddStoryTextEventButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewNextEvent(new StoryTextEvent());
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("StoryText");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
		
	}
	
	private void addAddStoryFreeTimeEventButton(ItemInfoContainer itemInfoText) {
		itemInfoText.addButton("Story Freetime", "add", new ActionTaker<ActionEvent>() {

			@Override
			public void perform(ActionEvent value) {
				addNewNextEvent(new FreeTimeStoryEvent(0));
				
			}
			
		});
		
	}
	
	private void addAddStoryAdventureEventButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewNextEvent(new StoryAdventureEvent());
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Story Adventure");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
		
	}
	private void addConditions(ItemInfoContainer itemInfoText) {
		for(DataCondition cond:valueconditions) {
			itemInfoText.addTextBox(cond.getName());
			cond.addEventSpecifics(itemInfoText);
			
			addRemoveConditionButton(itemInfoText,cond);
		}
		
	}

	private void addRemoveConditionButton(ItemInfoContainer itemInfoText, DataCondition cond) {
		JButton button=new JButton("remove");
		StoryEvent hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				valueconditions.remove(cond);
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(cond.getName());
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}
	private void addAddBooleanConditionButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewCondition(new BooleanCondition(comboOptions[0],true));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("boolean condition");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}
	protected void addNewCondition(DataCondition booleanCondition) {
		valueconditions.add(booleanCondition);
		
	}
	private void addAddValueConditionButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addNewCondition(new IntegerCondition(comboOptions[0],Comparison.EQUALS,0));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("boolean condition");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
	}
	
	private void addRemoveStoryEventButton(ItemInfoContainer itemInfoText, StoryEvent ev) {
		JButton button=new JButton("remove");
		StoryEvent hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.removeStoryEvent(ev);
				itemInfoText.refreshImage();
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(ev.getName());
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}
	protected void removeStoryEvent(StoryEvent ev) {
		nextevents.remove(ev);
		this.notifyStoryEventListenerRemoved(ev);
		
	}
	private void addActivationAddTriggerToField(Activation act, ItemInfoContainer itemInfoText) {
		
		
	}
	protected void addNewNextEvent(StoryEvent storyTextEvent) {
		nextevents.add(storyTextEvent);
		storyTextEvent.addEndEvent(endevent);
		this.notifyStoryEventListener(storyTextEvent);
		
	}
	
	
	
	@Override
	public abstract Univent copy();

	public void addEndEvent(EndStoryEvent endfield) {
		endevent=endfield;
		
	}
	public void removeOptionFromElement(StoryOptionTextElement elem, Trigger trig) {
		elem.removeOption(trig);
		this.notifyStoryEventListenerTriggerRemoved(trig);
		
		
	}
	public void addOptionToElement(StoryOptionTextElement elem, TextOption textOption) {
		elem.addOption(textOption);
		this.notifyStoryEventListenerTriggerAdded(textOption);
	
	}
	public StoryEvent nextEvent() {
		
		for(StoryEvent nextevent:nextevents) {
			if(nextevent.evaluateConditions()) {
				return nextevent;
			}
		}
		return null;
	}
	private boolean evaluateConditions() {
		for(DataCondition cond:valueconditions) {
			if(!cond.isCondition()) {
				return false;
			}
		}
		return true;
	}
	public  abstract void triggerStoryEvent(ProgressStatus status);

}
