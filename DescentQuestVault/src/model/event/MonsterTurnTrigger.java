package model.event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;

import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import misc.ActivateAble;
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
	private ArrayList<MonsterSpecial> monsterspecials=new ArrayList<MonsterSpecial>();
	
	private String monsterInfo;
	
	
	
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
	
	protected void addMonsterSpecials(ArrayList<MonsterSpecial> monsterSpecials) {
		monsterspecials.addAll(monsterSpecials);
		
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add button with add to triggerfield this,a ne wmonstermovement
		addNewMonsterMovementToTriggerFieldButton(itemInfoText);
		addMonsterSpecialButtons(itemInfoText);
		addInfoTextChanger(itemInfoText);
	}


	private void addInfoTextChanger(ItemInfoContainer itemInfoText) {
		JTextArea area=new JTextArea(200,400);
		area.setText(monsterInfo);
		
		
		area.getDocument().addDocumentListener(new DocumentListener() {

		
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			private void warn() {
				monsterInfo= area.getText();
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
		
	}


	private void addMonsterSpecialButtons(ItemInfoContainer itemInfoText) {
		for(MonsterSpecial special: monsterspecials) {
			addMakePriorityButton(special,itemInfoText);
			changeActivationNameButton(special,itemInfoText);
		}
		addMakeNewMonsterSpecialButton(itemInfoText);
		// TODO Auto-generated method stub
		
	}


	private void addMakeNewMonsterSpecialButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addMonsterSpecial(new MonsterSpecial("Custom Special"));
				itemInfoText.reset();
				addEventSpecifics(itemInfoText);
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new special");
		
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


	private void changeActivationNameButton(MonsterSpecial special, ItemInfoContainer itemInfoText) {
		JLabel lab=new JLabel("special");
		 JTextField field = new JFormattedTextField();
		
		 field.setName(special.getText());
		 field.setColumns(10);
		 field.setText(special.getText());
		 field.getDocument().addDocumentListener(new DocumentListener() {
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
				 // System.out.println("changedname "+field.getText());
				  special.setText(field.getText());
			  }
			});
		int w=itemInfoText.getWidth();
		lab.setSize(new Dimension(w/2-20,25));
		field.setSize(new Dimension(w/2,25));
		lab.setPreferredSize(new Dimension((int)(w/2-20),25));
		field.setPreferredSize(new Dimension(w/2,25));
		lab.setHorizontalAlignment(SwingConstants.RIGHT);
	      
		itemInfoText.addPreText(lab,field);
		
	}


	private void addMakePriorityButton(MonsterSpecial special, ItemInfoContainer itemInfoText) {
		JButton button=new JButton("priority up");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setAsMainPriority(special);
				itemInfoText.reset();
				addEventSpecifics(itemInfoText);
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(special.getText());
		
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


	public void addMonsterSpecial(MonsterSpecial special) {
		// TODO Auto-generated method stub
		monsterspecials.add(special);
	}
	public void setAsMainPriority(MonsterSpecial special) {
		if(monsterspecials.contains(special)) {
			monsterspecials.remove(special);
			monsterspecials.add(0,special);
		}
	}


	public String getMonsterInfo() {
		// TODO Auto-generated method stub
		return monsterInfo;
	}


	public ArrayList<MonsterSpecial> getMonsterSpecials() {
		// TODO Auto-generated method stub
		return monsterspecials;
	}


	
}
