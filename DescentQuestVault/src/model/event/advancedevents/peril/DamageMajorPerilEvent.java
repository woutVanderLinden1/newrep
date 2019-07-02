package model.event.advancedevents.peril;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
import model.event.Univent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public class DamageMajorPerilEvent extends Event {

	private int perildamage;
	
	public DamageMajorPerilEvent(int t) {
		perildamage=t;
	}

	public void trigger() {
		UserInputController control=UserInputController.getController();
		//control.performCommand(new HoldToContinueCommand(this));
		control.performCommand(new ShowTextCommand("The heroes divide "+ perildamage +" damage among them"));
		perildamage=perildamage+2;
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
		return new DamageMajorPerilEvent(perildamage);
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
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
	}
	
	
	
}
