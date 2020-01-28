package model.event;

import StoryEditor.CustomIntegerCondition;
import StoryEditor.DataCondition;
import model.values.Comparison;
import model.values.CustomInteger;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ValueChangeListener;

public class IfCustomIntegerTrigger extends Trigger  implements NameChangeListener,ValueChangeListener{

	private CustomIntegerCondition condition;
	private boolean namebased=true;
	
	
	public IfCustomIntegerTrigger(CustomInteger int1,Comparison cond1,CustomInteger int2) {
		condition=new CustomIntegerCondition(int1,cond1,int2);
		
	}
	
	public IfCustomIntegerTrigger(CustomIntegerCondition condition2) {
		condition=condition2;
	}

	@Override
	public Univent copy() {
		IfCustomIntegerTrigger toreturn=new IfCustomIntegerTrigger(condition);
		// TODO Auto-generated method stub
		this.addAllTriggers(toreturn);
		return toreturn;
			
	}
	
	public void trigger() {
	
		if(condition.isCondition()) {
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
	public void valueChanged(int theInteger) {
		System.out.println("the value changed to "+theInteger);
		trigger();
		
	}
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		condition.addEventSpecifics(itemInfoText);
	}

}

