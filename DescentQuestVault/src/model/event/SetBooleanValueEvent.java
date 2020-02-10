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
import model.ItemController;
import model.values.*;

public class SetBooleanValueEvent extends Event implements NameChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7057383643037117002L;
	private boolean value;
	private CustomBoolean newitem;
	private boolean namebased=true;
	
	
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public CustomBoolean getnewitem() {
		return newitem;
	}

	public void setnewitem(CustomBoolean newitem) {
		this.newitem = newitem;
	}

	public SetBooleanValueEvent(boolean value, CustomBoolean CustomBoolean) {
		super();
		this.value = value;
		this.newitem = CustomBoolean;
		setIDName("changeValue "+ CustomBoolean.getName());
		setName("change Value "+ CustomBoolean.getName());
		CustomBoolean.addNameChangeListener(this);
	}

	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		control.performCommand(new SetBooleanValueCommand(value,(CustomBoolean) newitem));
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
		button.setSelectedItem(BooleanValue.toValue(value));
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
		SetBooleanValueEvent toreturn=new SetBooleanValueEvent(value,newitem);
		return toreturn;
	}

	private BooleanValueItem toset;
	
	public void intialiseForGame(ItemController vent) {
		newitem=(CustomBoolean) toset.getValue();
		super.intialiseForGame(vent);
	}
}
