package model.event;

import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import controller.UserInputController;
import controller.commands.SetBooleanValueCommand;
import model.values.*;

public class SetBooleanValueEvent extends Event implements NameChangeListener{

	private boolean value;
	private BooleanValueItem toset;
	private boolean namebased=true;
	
	
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public BooleanValueItem getToset() {
		return toset;
	}

	public void setToset(BooleanValueItem toset) {
		this.toset = toset;
	}

	public SetBooleanValueEvent(boolean value, BooleanValueItem booleanValueItem) {
		super();
		this.value = value;
		this.toset = booleanValueItem;
		setIDName("changeValue "+ booleanValueItem.getIDName());
		setName("change Value "+ booleanValueItem.getName());
		booleanValueItem.addNameChangeListener(this);
	}

	public void Trigger() {
		UserInputController control=UserInputController.getController();
		control.performCommand(new SetBooleanValueCommand(value,(CustomBoolean) toset.getItem()));
	}
	
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("change Value "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add booleanvalue
		BooleanValue[] comboOptions = {BooleanValue.TRUE,BooleanValue.FALSE};
		
		JComboBox<BooleanValue> button=new JComboBox<BooleanValue>(comboOptions);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				value=((BooleanValue) button.getSelectedItem()).getValue();
				
			
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
		SetBooleanValueEvent toreturn=new SetBooleanValueEvent(value,toset);
		return toreturn;
	}

}
