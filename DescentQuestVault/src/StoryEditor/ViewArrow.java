package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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

import model.Condition;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class ViewArrow extends Arrow implements SelectAble{
	
	private Condition nextcondition;
	private int nextpriority;
	private String idname;
	private String name;
	private static int arrownr;
	private ArrayList<NameChangeListener> listen=new ArrayList<NameChangeListener>();
	private int priority;
	
	public ViewArrow(DraggAblePanel origin, int ex, int ey,SubDragPanel parent) {
		super(origin, ex, ey,parent);
		arrownr++;
		idname="arrow"+arrownr;
		name=idname;
				// TODO Auto-generated constructor stub
	}

	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return idname;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void addNameChangeListener(NameChangeListener listen) {
		// TODO Auto-generated method stub
		this.listen.add(listen);
	}

	@Override
	public void triggerNameChangeListeners(String newname) {
		// TODO Auto-generated method stub
		for(NameChangeListener listeners:listen) 
		{
			listeners.nameChanged(newname);
		}
	}

	@Override
	public void changeName(String value) {
		// TODO Auto-generated method stub
		this.name=name;
		this.triggerNameChangeListeners(value);
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.ARROW;
	}

	@Override
	public void select() {
		this.setColor(Color.orange);
		selected=true;
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		this.setColor(Color.blue);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeInvisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

	public void addArrowSpecifics(ItemInfoContainer itemInfoText) {
		//give the ability to use one of the constants and their values in addition
		//for each condition add conditionpicker and a custom value to compare it with
		//in addition a value the value can also be another custom value
		
		//add add new condition button 
		//addConditionPicker();
		addPriorityModifier(itemInfoText);
	}


public void addPriorityModifier(ItemInfoContainer itemInfoText) {
		JLabel lab=new JLabel("damage: ");
		 JTextField field = new JFormattedTextField();
		
		 field.setName(Integer.toString(priority));
		 field.setColumns(10);
		 field.setText(Integer.toString(priority));
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
				  priority=Integer.parseInt(field.getText());
			  }
			});
		 field.addPropertyChangeListener("name",new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					
					priority=Integer.parseInt(field.getText());
					
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
		//super.addEventSpecifics(itemInfoText);
	}
	

}
