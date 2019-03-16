package model.event;

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

import model.values.Comparison;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.IntegerValueItem;
import model.values.Modification;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ValueChangeListener;

public class IfIntegerTrigger extends Trigger implements NameChangeListener,ValueChangeListener {
	
	
	private Comparison comp;
	private IntegerValueItem theItem;
	private int compvalue;
	private boolean namebased=true;



	public Comparison getComp() {
		return comp;
	}

	public void setComp(Comparison comp) {
		this.comp = comp;
	}

	public IntegerValueItem getTheItem() {
		return theItem;
	}

	public void setTheItem(IntegerValueItem theItem) {
		this.theItem = theItem;
	}

	public int getCompvalue() {
		return compvalue;
	}

	public void setCompvalue(int compvalue) {
		this.compvalue = compvalue;
	}

	public IfIntegerTrigger(Comparison equals, IntegerValueItem integerValueItem) {
		super();
		comp=equals;
		theItem=integerValueItem;
		integerValueItem.addNameChangeListener(this);
		theItem.addValueChangeListener(this);
		restateName(integerValueItem.getName());
		
	}
	public IfIntegerTrigger(Comparison comp, IntegerValueItem theItem, int compvalue) {
		super();
		this.comp = comp;
		this.theItem = theItem;
		this.compvalue = compvalue;
		theItem.addNameChangeListener(this);
		theItem.addValueChangeListener(this);
		restateName(theItem.getName());
	}
	
	
	public void trigger() {
		CustomInteger bool=(CustomInteger) theItem.getItem();
		if(comp.compare(bool.getTheInteger(),compvalue)) {
			super.trigger();
		}
	}
	
	@Override
	public void nameChanged(String newname) {
		System.out.println("if Integer "+newname);
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("if Integer "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		addComparisonModifier(itemInfoText);
		addIntModifier(itemInfoText);
	
		
		
	}

	private void addIntModifier(ItemInfoContainer itemInfoText) {
	//add booleanvalue
	
		
		//add textfield for the value
		
		JLabel lab=new JLabel("modvalue: ");
		
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
		  
		
		 field2.setValue(compvalue);
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
				compvalue=(int) field2.getValue();  
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

	private void addComparisonModifier(ItemInfoContainer itemInfoText) {
	Comparison[] comboOptions = {Comparison.EQUALS,Comparison.LESS,Comparison.MORE,Comparison.DOESNOTEQUAL};
		
		JComboBox<Comparison> button=new JComboBox<Comparison>(comboOptions);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comp=((Comparison) button.getSelectedItem());
				
			
			}
			
		});
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
		
	}

	
	public Univent copy() {
		IfIntegerTrigger toreturn=new IfIntegerTrigger(comp,theItem,compvalue);
		this.addAllTriggers(toreturn);
		return toreturn;
	}

	@Override
	public void valueChanged(int theInteger) {
		System.out.println("the value changed to "+theInteger);
		trigger();
		
	}
}
