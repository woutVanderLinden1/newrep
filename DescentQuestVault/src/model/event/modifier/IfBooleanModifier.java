package model.event.modifier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.ItemController;
import model.event.IfBooleanTrigger;
import model.event.Univent;
import model.event.modifier.Modifier;
import model.values.BooleanValue;
import model.values.BooleanValueItem;
import model.values.CustomBoolean;
import model.values.CustomBoolean;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

public class IfBooleanModifier extends Modifier  implements NameChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1929303446356797129L;
	private boolean newvalue;
	private CustomBoolean newitem;
	private boolean namebased=true;
	
	public IfBooleanModifier(boolean newvalue, CustomBoolean CustomBoolean) {
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
		newitem=(CustomBoolean) toset.getValue();
		super.intialiseForGame(vent);
	}
	
}
