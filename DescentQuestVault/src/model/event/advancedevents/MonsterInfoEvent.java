package model.event.advancedevents;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import controller.UserInputController;
import controller.commands.Game.ShowTextCommand;
import model.event.Event;
import model.event.EventEndListener;
import model.event.EventTriggerStack;
import model.event.MonsterSpecial;
import model.event.MonsterTurnTrigger;
import model.event.Univent;
import model.event.advancedevents.peril.DamageMajorPerilEvent;
import model.event.extraevents.TextStop;
import view.Items.Map.ViewMonster;
import view.events.RemoveMapMonsterEvent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public class MonsterInfoEvent extends Event {

	private ViewMonster monster;
	private String thetext;
	
	

	public MonsterInfoEvent(ViewMonster viewMonster) {
		monster=viewMonster;
	}
	@Override
	

	public void trigger() {
		UserInputController control=UserInputController.getController();
		//control.performCommand(new HoldToContinueCommand(this));
		MonsterTurnTrigger trigger=monster.getMonsterMovementTrigger();
		//control.performCommand(new ShowTextCommand(,"continue"));
		TextStop text=new TextStop(trigger.getMonsterInfo()+"\n \n"+makeMovePriorities(trigger));
		text.addEventEndListener(new EventEndListener() {

			@Override
			public void eventEnded() {
				
				
				//stack.triggerNextStackEvent();
				triggerEventEndListeners();
			}
			
		});
		
		text.trigger();
	}

	private String makeMovePriorities(MonsterTurnTrigger trigger) {
		String toreturn= "Special priority  \n ";
		int t=0;
		for(MonsterSpecial special:trigger.getMonsterSpecials()) {
			t++;
			toreturn=toreturn+""+t+": "+special.getText()+"\n";
		}
		// TODO Auto-generated method stub
		return toreturn;
	}
	public boolean isStopEvent() {
		return true;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new MonsterInfoEvent(monster);
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		this.addTextSpecifics(itemInfoText);
		/*
		JLabel lab=new JLabel("damage: ");
		 JTextField field = new JFormattedTextField();
		
		 field.setName(Integer.toString(perildamage));
		 field.setColumns(10);
		 field.setText(Integer.toString(perildamage));
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
				 // System.out.println("changedname "+field.getText());
				  perildamage=Integer.parseInt(field.getText());
			  }
			});
		 field.addPropertyChangeListener("name",new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					
					perildamage=Integer.parseInt(field.getText());
					
				}
	        	
	        });
		 AbstractDocument ment=(AbstractDocument) field.getDocument();
		  ment.setDocumentFilter(new DocumentFilter() {
	            public void replace(FilterBypass fb, int offs, int length,
	                    String str, AttributeSet a) throws BadLocationException {

	                String text = fb.getDocument().getText(0,
	                        fb.getDocument().getLength());
	                text += str;
	                if ((fb.getDocument().getLength() + str.length() - length) <= 9
	                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
	                    super.replace(fb, offs, length, str, a);
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                }
	            }

	            public void insertString(FilterBypass fb, int offs, String str,
	                    AttributeSet a) throws BadLocationException {

	                String text = fb.getDocument().getText(0,
	                        fb.getDocument().getLength());
	                text += str;
	                if ((fb.getDocument().getLength() + str.length()) <= 9
	                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
	                    super.insertString(fb, offs, str, a);
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                }
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
		super.addEventSpecifics(itemInfoText);
		*/
	}
	
	
	public void addTextSpecifics(ItemInfoContainer itemInfoText) {
		
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
	

}
