package model.values;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import model.Item;
import model.ItemController;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;

public class CompareToOtherIntegerTrigger extends Trigger implements NameChangeListener {

	private Comparison comp;
	private IntegerValueItem theItem;
	private CustomInteger compvalue;
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

	public CustomInteger getCompvalue() {
		return compvalue;
	}

	public void setCompvalue(CustomInteger compvalue) {
		this.compvalue = compvalue;
	}

	public CompareToOtherIntegerTrigger(Comparison equals, IntegerValueItem integerValueItem) {
		super();
		comp=equals;
		theItem=integerValueItem;
		compvalue=(CustomInteger) integerValueItem.getItem();
		integerValueItem.addNameChangeListener(this);
		
	}
	public CompareToOtherIntegerTrigger(Comparison comp, IntegerValueItem theItem, CustomInteger compvalue) {
		super();
		this.comp = comp;
		this.theItem = theItem;
		this.compvalue = compvalue;
		theItem.addNameChangeListener(this);
	}
	
	
	public void trigger() {
		CustomInteger integer1=(CustomInteger) theItem.getItem();
		CustomInteger integer2=(CustomInteger) theItem.getItem();
		if(comp.compare(integer1.getTheInteger(),integer2.getTheInteger())) {
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
		addIntegerModifier(itemInfoText);
		
		
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

	private void addIntegerModifier(ItemInfoContainer itemInfoText) {
		ItemController control=ItemController.getItemController();
		ArrayList<Item> theitems=control.getValues();
		ArrayList<CustomInteger> vals=new ArrayList<CustomInteger>();
		theitems.forEach(a->{
			if(a.getItemKind()==ItemOptions.Value) {
				if(((CustomValue) a).getValueKind()==ValueKind.INTEGER) {
					vals.add((CustomInteger) a);
				}
			}
		});
		//add booleanvalue
		String[] stockArr = new String[theitems.size()];
		for(int i=0;i<vals.size();i++) {
			stockArr[i]=vals.get(i).getName();
		}
		JComboBox<String> button=new JComboBox<String>(stockArr);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String temp=((String) button.getSelectedItem());
				int position=Arrays.asList(stockArr).indexOf(temp);
				compvalue=vals.get(position);
				//stockArr.indexOf(temp);
			
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

	@Override
	public Univent copy() {
		CompareToOtherIntegerTrigger toreturn=new CompareToOtherIntegerTrigger(comp, theItem, compvalue);
		toreturn.addAllTriggers(this);
		return toreturn;
	}

}
