package model.event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.UserInputController;

import controller.commands.AddEventToTriggerFieldCommand;
import model.Monster.Monster;
import monstercreator.SingleMovement;
import view.Items.Map.ViewMonster;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import monstercreator.*;

public class MonsterTurnTrigger extends Trigger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public Monster getMonster() {
		return monster;
	}


	public void setMonster(Monster monster) {
		this.monster = monster;
	}


	private Monster monster;
	
	public MonsterTurnTrigger(Monster mon){
		monster=mon;
	}
	

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add button with add to triggerfield this,a ne wmonstermovement
		addNewMonsterMovementToTriggerFieldButton(itemInfoText);
	}


	private void addNewMonsterMovementToTriggerFieldButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		Trigger trig=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				trig.addNewEvent(new SingleMovementEvent(new MonsterItem(monster)));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new monstermovement");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}


	public MonsterTurnTrigger copy() {
		MonsterTurnTrigger toreturn=new MonsterTurnTrigger(this.getMonster());
		addAllTriggers(toreturn);
		return toreturn;
	}


	
}
