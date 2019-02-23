package model.event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.values.BooleanValue;
import model.values.BooleanValueItem;
import model.values.CustomBoolean;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

public class IfBooleanTrigger extends Trigger implements NameChangeListener {

	
	private boolean newvalue;
	private BooleanValueItem toset;
	private boolean namebased=true;
	
	public IfBooleanTrigger(boolean newvalue, BooleanValueItem booleanValueItem) {
		super();
		this.newvalue = newvalue;
		this.toset = booleanValueItem;
		setIDName("if trigger "+ booleanValueItem.getIDName());
		setName("if trigger "+ booleanValueItem.getName());
		
		booleanValueItem.addNameChangeListener(this);
	}


	public void trigger() {
		CustomBoolean bool=(CustomBoolean) toset.getItem();
		if(bool.isValue()==newvalue) {
			super.trigger();
		}
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add booleanvalue
		BooleanValue[] comboOptions = {BooleanValue.TRUE,BooleanValue.FALSE};
		
		JComboBox<BooleanValue> button=new JComboBox<BooleanValue>(comboOptions);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newvalue=((BooleanValue) button.getSelectedItem()).getValue();
				
			
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
	public Univent copy() {
		IfBooleanTrigger toreturn=new IfBooleanTrigger(newvalue,toset);
		this.addAllTriggers(toreturn);
		return toreturn;
	}
	
	
}
