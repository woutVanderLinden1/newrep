package model.event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.ItemController;
import model.values.BooleanValue;
import model.values.BooleanValueItem;
import model.values.CustomBoolean;
import model.values.CustomBoolean;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

public class IfBooleanTrigger extends Trigger implements NameChangeListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225081927779523921L;
	private boolean newvalue;
	private CustomBoolean newitem;
	private boolean namebased=true;
	
	public IfBooleanTrigger(boolean newvalue, CustomBoolean CustomBoolean) {
		super();
		this.newvalue = newvalue;
		this.newitem = CustomBoolean;
		setIDName("if trigger "+ CustomBoolean.getName());
		setName("if trigger "+ CustomBoolean.getName());
		
		CustomBoolean.addNameChangeListener(this);
	}


	public void trigger() {
		CustomBoolean bool=(CustomBoolean) newitem;
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
		button.setSelectedItem(BooleanValue.toValue(newvalue));
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
		IfBooleanTrigger toreturn=new IfBooleanTrigger(newvalue,newitem);
		this.addAllTriggers(toreturn);
		return toreturn;
	}
	
private BooleanValueItem toset;
	
	public void intialiseForGame(ItemController vent) {
		if(toset!=null) {
			newitem=(CustomBoolean) toset.getValue();
		}
		
		super.intialiseForGame(vent);
	}
	
}
