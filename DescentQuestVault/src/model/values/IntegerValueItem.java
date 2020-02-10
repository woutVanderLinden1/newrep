package model.values;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.UserInputController;
import controller.command.CreateIntegerValueCommand;
import controller.commands.CreateBooleanValueCommand;
import controller.commands.ICommand;
import model.event.IfBooleanTrigger;
import model.event.IfIntegerTrigger;
import model.event.InitialiseBooleanEvent;
import model.event.ModifyIntegerValueEvent;
import model.event.SetBooleanValueEvent;
import model.event.Univent;
import model.event.modifier.IfIntegerModifier;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ValueChangeListener;

public class IntegerValueItem extends ValueItem {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7482775502385665484L;

	public CustomInteger getVal() {
		return val;
	}

	public void setVal(CustomInteger val) {
		this.val = val;
	}

	public IfIntegerModifier getIfmodifier() {
		return ifmodifier;
	}

	public void setIfmodifier(IfIntegerModifier ifmodifier) {
		this.ifmodifier = ifmodifier;
	}

	public IfIntegerTrigger getIftrigger() {
		return iftrigger;
	}

	private CustomInteger val;
	
	private InitialiseBooleanEvent ev;
	private ArrayList<Univent> eventlist=new ArrayList<Univent>();
	

	private ModifyIntegerValueEvent modifyEvent;
	private IfIntegerTrigger iftrigger;
	private IfIntegerModifier ifmodifier;
	private CompareToOtherIntegerTrigger comparetrigger;

	//private WhileBooleanTrigger whiletrigger;
	
	public IntegerValueItem(CustomInteger val) {
		super(val);
		this.val=val;
		setModifyEvent(new ModifyIntegerValueEvent(Modification.SET,this.getVal()));
		setIftrigger(new IfIntegerTrigger(Comparison.EQUALS,this.getVal()));
		setComparetrigger(new CompareToOtherIntegerTrigger(Comparison.EQUALS,this.getVal()));
		setIfmodifier(new IfIntegerModifier(Comparison.EQUALS,this.getVal()));
		
		eventlist.add(modifyEvent);
		eventlist.add(iftrigger);
		eventlist.add(ifmodifier);
		eventlist.add(comparetrigger);
		// TODO Auto-generated constructor stub
	}

	public CompareToOtherIntegerTrigger getComparetrigger() {
		return comparetrigger;
	}

	public void setComparetrigger(CompareToOtherIntegerTrigger comparetrigger) {
		this.comparetrigger = comparetrigger;
	}

	public ModifyIntegerValueEvent getModifyEvent() {
		return modifyEvent;
	}

	public void setModifyEvent(ModifyIntegerValueEvent modifyEvent) {
		this.modifyEvent = modifyEvent;
	}

	public void setIftrigger(IfIntegerTrigger iftrigger) {
		this.iftrigger = iftrigger;
	}



	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		return eventlist;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new IntegerValueItem(val);
	}

	@Override
	public ICommand getNewCreationCommand() {
		// TODO Auto-generated method stub
		return new CreateIntegerValueCommand();
	}

	@Override
	public ValueKind getValueKind() {
		// TODO Auto-generated method stub
		return ValueKind.INTEGER;
	}

	public void addValueChangeListener(IfIntegerTrigger ifIntegerTrigger) {
		// TODO Auto-generated method stub
		val.addValueChangeListener(ifIntegerTrigger);
	}
	


	
	

}

