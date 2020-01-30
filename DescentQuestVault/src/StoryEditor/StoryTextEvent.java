package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.NextTextListeners;
import controller.UserInputController;
import controller.commands.ContinueCommand;
import controller.commands.Game.ShowTextCommand;
import controller.stack.EndGameCommand;
import model.ItemController;
import model.event.EventEndListener;
import model.event.Trigger;
import model.event.Univent;
import model.event.extraevents.TextOption;
import model.values.CustomBoolean;
import view.events.StoryElement;
import view.viewItems.ItemBox.ItemInfoContainer;

public class StoryTextEvent extends StoryEvent implements NextTextListeners {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5697342717274260794L;
	private int nroftext;
	private static int nrstorytexts;
	
	private ArrayList<StoryTextElement> storyments=new ArrayList<StoryTextElement>();
	private int nr;
	private boolean stopped;
	
	@Override
	public void trigger() {
		
	
		//control.performCommand(new HoldToContinueCommand(this));
	
	}
	
	

	private void showText(int nr2) {
		UserInputController control=UserInputController.getController();
		control.performCommand(new ShowTextCommand(storyments.get(nr2).getText(),storyments.get(nr2).getTrigOptions(),new EventEndListener() {

			@Override
			public void eventEnded() {
				triggerEventEndListeners();
			}
			
		}));
		
		
		
	}



	public StoryTextEvent() {
		this.name="StoryTextEvent"+nrstorytexts++;
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		//add textface each time we want text
		// add the opportunity to make choices
		for(StoryTextElement elem:storyments) {
			itemInfoText.addTextBox(elem.getName());
			this.addTextSpecifics(itemInfoText, elem);
			if(elem.getKind()==StoryTextKind.OPTION) {
				this.addTextOptionSpecifics(itemInfoText,(StoryOptionTextElement)elem);
			}
			this.addRemoveTextButton(itemInfoText, elem);
		}
		this.addAddNewTextButton(itemInfoText);
		this.addAddNewOptionButton(itemInfoText);
		
	
		super.addEventSpecifics(itemInfoText);
	}
	private void addAddNewOptionButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryTextEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				storyments.add(new StoryOptionTextElement(hold.nroftext++));
				itemInfoText.refreshImage();
				
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new OptionText");
		
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
	
	private void addAddNewTextButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		StoryTextEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				storyments.add(new StoryTextElement(hold.nroftext++));
				itemInfoText.refreshImage();
				
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new text");
		
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

	private void addTextOptionSpecifics(ItemInfoContainer itemInfoText, StoryOptionTextElement elem) {
		for(Trigger trig:elem.getTrigOptions()) {
			addChangeNameOfConditonField(itemInfoText,trig);
			this.addOptionRemoveButton(itemInfoText,elem,trig);
		}
		this.addAddOptionButton(itemInfoText,elem);
		
	}
	private void addChangeNameOfConditonField(ItemInfoContainer itemInfoText,Trigger trig) {
		//add booleanvalue
		
		
		//add textfield for the value
		
		JLabel lab=new JLabel("modvalue: ");
		
		    JFormattedTextField field2 = new JFormattedTextField();

		  
		    // getValue() always returns something valid
		  
		
		 field2.setValue(trig.getName());
		 
		 

	    
		 field2.getDocument().addDocumentListener(new DocumentListener() {
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
				trig.setName( (String) field2.getValue());  
				itemInfoText.refreshImage();
			  }
			});
		
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
		 int w=itemInfoText.getWidth();
     
      lab.setSize(new Dimension(w/2-20,25));
      field2.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field2.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field2);
		
		
	}
	

	private void addAddOptionButton(ItemInfoContainer itemInfoText, StoryOptionTextElement elem) {
		JButton button=new JButton("add");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.addOptionToElement(elem,new TextOption("newoption",new ContinueCommand()));
				itemInfoText.refreshImage();
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new option fo this text");
		
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
	private void addRemoveTextButton(ItemInfoContainer itemInfoText, StoryTextElement elem) {
		JButton button=new JButton("remove");
		StoryTextEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.removeStoryText(elem);
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(elem.getName());
		
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

	private void addOptionRemoveButton(ItemInfoContainer itemInfoText, StoryOptionTextElement elem, Trigger trig) {
		JButton button=new JButton("remove");
		StoryEvent hold=this;
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hold.removeOptionFromElement(elem,trig);
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(trig.getName());
		
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

	private void addTextSpecifics(ItemInfoContainer itemInfoText,StoryTextElement elem) {

		JTextArea area=new JTextArea(200,400);
		area.setText(elem.getText());
		
		
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
				elem.setText( area.getText());
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
	}



	@Override
	public boolean nextText() {
		 nr++;
		 if(nr>=storyments.size()) {
			 	
				UserInputController control=UserInputController.getController();
				control.performCommand(new EndGameCommand());
				return false;
				//control.endGame();
		 }
		 else
		 {
			 this.showText(nr); 
		 }
		 return true;
		
	}
	public void removeStoryText(StoryTextElement elem) {
		// TODO Auto-generated method stub
		storyments.remove(elem);
	}



	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void triggerStoryEvent(ProgressStatus status) {
		stopped=true;
		
		
		UserInputController control=UserInputController.getController();
		control.beginStoryText();
		control.addNextTextListener(this);
		nr=-1;
		this.nextText();
		
		
		
	}
}
