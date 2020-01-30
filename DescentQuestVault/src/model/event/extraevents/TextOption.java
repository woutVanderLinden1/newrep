package model.event.extraevents;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.commands.ContinueCommand;
import controller.commands.ICommand;
import model.event.EventTriggerStack;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.ItemBox.ItemInfoContainer;

public class TextOption extends Trigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private ICommand command;
	private ContinueCommand continueCommand;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TextOption(String text) {
		super();
		this.text = text;
		this.setName(text);
		//triggering this causes the happenings
	}

	public TextOption(String string, ICommand continueCommand) {
		super();
		this.text = string;
		this.setName(text);
		command=continueCommand;
	}

	public void perform() {
		UserInputController control=UserInputController.getController();
		control.performCommand(command);
		// TODO Auto-generated method stub
		
	}
	
	public void trigger() {
		UserInputController control=UserInputController.getController();
		control.performCommand(continueCommand);
		super.trigger();
	}
	public void triggerHere(ArrayList<Univent> totrigger) {
		
		EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
		triggerstack.addNewEvents(totrigger);
		
		//triggerstack.triggerNextStackEvent();
		/*
		    	for(Univent ev:totrigger) {
		    		ev.trigger();
		    	}
		    	*/
	
	
		//keep them as a continue prepared
		//if continue pressed trigger the rest
	}

	public void setContinueCommand(ContinueCommand continueCommand) {
		this.continueCommand=continueCommand;
		
		
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
	
		JLabel lab=new JLabel("option: ");
		 JTextField field = new JFormattedTextField();
		 field.setName(this.getName());
		 field.setColumns(10);
		 field.setText(this.getName());
		 field.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  
				  setName((String)field.getText());
			  }
			});
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
      int w=itemInfoText.getWidth();
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field);
	}

	@Override
	public Univent copy() {
		TextOption toReturn =new TextOption(text,command);
		toReturn.addAllTriggers(this);
		// TODO Auto-generated method stub
		return toReturn;
	}
	
	


}
