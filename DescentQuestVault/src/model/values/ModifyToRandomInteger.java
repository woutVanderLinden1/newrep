package model.values;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import ItemEditor.ActionTaker;
import controller.UserInputController;
import controller.command.ModifyIntegerValueCommand;
import misc.Tools;
import model.ItemController;
import model.event.Event;
import model.event.ModifyIntegerValueEvent;
import model.event.Univent;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

public class ModifyToRandomInteger extends Event implements NameChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690678728456107713L;
	private CustomInteger newitem;
	private Modification mod;
	private int minvalue=0;
	private int maxvalue=10;
	private boolean namebased=true;

	public CustomInteger getTheitem() {
		return newitem;
	}

	public void setTheitem(CustomInteger theitem) {
		this.newitem = theitem;
	}

	public Modification getMod() {
		return mod;
	}

	public void setMod(Modification mod) {
		this.mod = mod;
	}

	public ModifyToRandomInteger(Modification set, CustomInteger customInteger) {
		newitem=customInteger;
		mod=set;
		
		setIDName("changeValue "+ customInteger.getName());
		setName("change Value "+ customInteger.getName());
		customInteger.addNameChangeListener(this);
	}
	public ModifyToRandomInteger(Modification set, CustomInteger CustomInteger,int minvalue,int maxvalue) {
		newitem=CustomInteger;
		mod=set;
		this.minvalue=minvalue;
		this.maxvalue=maxvalue;
		setIDName("changeValue "+ CustomInteger.getName());
		setName("change Value "+ CustomInteger.getName());
		CustomInteger.addNameChangeListener(this);
	}

	public int getMinvalue() {
		return minvalue;
	}

	public int getMaxvalue() {
		return maxvalue;
	}
	public void setMinvalue(int modvalue) {
		this.minvalue = modvalue;
	}
	public void setMaxvalue(int modvalue) {
		this.maxvalue = modvalue;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		int val=Tools.getRandomInt(minvalue,maxvalue);
		control.performCommand(new ModifyIntegerValueCommand(val,mod,(CustomInteger) newitem));
	}
	
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("Modify "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add booleanvalue
		Modification[] comboOptions = {Modification.SET,Modification.PLUS,Modification.MINUS,Modification.MULTIPLY,Modification.DIVIDE};
		
		JComboBox<Modification> button=new JComboBox<Modification>(comboOptions);
		
		
		button.setSelectedItem(mod);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mod=((Modification) button.getSelectedItem());
				
			
			}
			
		});
		button.setSelectedItem(mod);
		 JLabel field = new JLabel();
		 field.setText("change setvalue");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        //button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreComboBox(field,button);
		
		//add textfield for the value
		
		JLabel lab=new JLabel("minvalue: ");
		
		 NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    // If you want the value to be committed on each keystroke instead of focus lost
		    formatter.setCommitsOnValidEdit(true);
		    JFormattedTextField field2 = new JFormattedTextField(formatter);

		  
		    // getValue() always returns something valid
		  
		
		 field2.setValue(minvalue);
		 field2.setColumns(10);
		 

	    
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
				minvalue=(int) field2.getValue();  
			  }
			});
		
			//field2.setValue(modvalue);
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
     
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field2);
      itemInfoText.addNumericEditButton("maxvalue", itemInfoText, maxvalue, new ActionTaker<Integer>() {

		@Override
		public void perform(Integer value) {
			maxvalue=value;
			
		}
    	  
      });
		
		
	}
	public Univent copy() {
		return new ModifyToRandomInteger(mod,newitem,minvalue,maxvalue);
	}
	private IntegerValueItem theitem;
	
	public void intialiseForGame(ItemController vent) {
		newitem=theitem.getVal();
		super.intialiseForGame(vent);
	}
}
