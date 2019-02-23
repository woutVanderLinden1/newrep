package model.event.extraevents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.commands.ContinueCommand;
import controller.commands.CreateNewOptionCommand;
import controller.commands.RemoveOptionCommand;
import controller.commands.Game.HoldToContinueCommand;
import controller.commands.Game.ShowTextCommand;
import model.event.Trigger;
import model.event.Univent;
import view.events.MultiTextTriggerField;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;
// when triggered opens a textfield with some options
//each option causes something to happen has a subevent for eacht option
//it can also show 1 event and a continue
public class TextTrigger extends Trigger implements Serializable,StopAble{
//
	
	/**
	 * 
	 */
	private boolean stopped=false;
	private static final long serialVersionUID = 1L;
	private int nroptions;
	private ArrayList<TextOption> options;
	private String thetext;
	private ArrayList<AddOptionListener> optionlisteners=new ArrayList<AddOptionListener>();
	
	public int getNroptions() {
		return nroptions;
	}
	public void setNroptions(int nroptions) {
		this.nroptions = nroptions;
	}
	public ArrayList<TextOption> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<TextOption> options) {
		this.options = options;
	}
	public String getThetext() {
		return thetext;
	}
	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		//show the text
		//super.trigger();
		UserInputController control=UserInputController.getController();
		control.performCommand(new HoldToContinueCommand(this));
		

		control.performCommand(new ShowTextCommand(thetext,options));
		stopped=true;
		while(stopped) {
			try {
				System.out.println("here");
				Thread.sleep(500);
				//TimeUnit.SECONDS.sleep((long) .5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void continueStop() {
		// TODO Auto-generated method stub
		stopped=false;
	}
	
	
	
	
	public void setThetext(String thetext) {
		this.thetext = thetext;
	}
	public TextTrigger(int nroptions, ArrayList<TextOption> options, String thetext) {
		super();
		this.setName("textTrigger");
		this.setIDName("textTrigger");
		this.nroptions = nroptions;
		this.options = options;
		this.thetext = thetext;
		initialiseOptions();
	}

	private void initialiseOptions() {
		for(TextOption opt:options) {
			opt.setContinueCommand(new ContinueCommand(this));
		}
		
	}
	public void addTextOption(TextOption textOption) {
		// TODO Auto-generated method stub
		nroptions++;
		textOption.setContinueCommand(new ContinueCommand(this));
		options.add(textOption);
		triggerAddOptionListeners(textOption);
	}
	private void triggerAddOptionListeners(TextOption textOption) {
		// TODO Auto-generated method stub
		for(AddOptionListener listen:optionlisteners) {
			listen.optionAdded(textOption);
		}
	}
	
	private void triggerAddOptionListenersRemoved(TextOption opt) {
		// TODO Auto-generated method stub
		for(AddOptionListener listen:optionlisteners) {
			listen.optionRemoved(opt);
		}
	
	}
	/*
	@Override
	public SelectKind getKind() {
		return SelectKind.MULTITRIGGER;
		
	}
	*/
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		addTextArea(itemInfoText);
		for(TextOption opt:options) {
			addTextOptionEditor(opt,itemInfoText);
		}
		addNewOptionCreator(itemInfoText);
	}
	private void addNewOptionCreator(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("new");
		TextTrigger trig=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new CreateNewOptionCommand(trig,new TextOption("option" +(nroptions+1))));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("create option");
		
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
	private void addTextOptionEditor(TextOption opt, ItemInfoContainer itemInfoText) {
		opt.addEventSpecifics(itemInfoText);
		addRemoveOptionButton(itemInfoText,opt);
	}
	private void addRemoveOptionButton(ItemInfoContainer itemInfoText, TextOption opt) {
		JButton button=new JButton("remove");
		TextTrigger trig=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new RemoveOptionCommand(trig,opt));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("remove "+opt.getName());
		
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
	private void addTextArea(ItemInfoContainer itemInfoText) {
		JTextArea area=new JTextArea(200,400);
		area.setText(thetext);
		
		
		area.getDocument().addDocumentListener(new DocumentListener() {

		
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			private void warn() {
				thetext= area.getText();
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
		
	}
	public void addAddOptionListener(AddOptionListener multiTextTriggerField) {
		optionlisteners.add(multiTextTriggerField);
		
	}
	public void removeTextOption(TextOption opt) {
		nroptions--;
		options.remove(opt);
		triggerAddOptionListenersRemoved(opt);
		
	}
	@Override
	public Univent copy() {
		TextTrigger trig= new TextTrigger(nroptions,options,thetext);
		trig.addAllTriggers(this);
		return trig;
	}
	
	
}
