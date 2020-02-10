package model.event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.command.AddMasterMovementOptionCommand;
import controller.command.RemoveMasterMovementOptionCommand;
import controller.command.RemoveMinionMovementOptionCommand;
import controller.command.game.EndTurnCommand;
import controller.command.game.MakeInvisibleCommand;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.ICommand;
import controller.commands.makeVisibleCommand;
import controller.commands.Game.ShowMonsterMovementCommand;
import controller.commands.select.AddMinionMovementOptionCommand;
import model.event.extraevents.StopAble;
import monstercreator.SampleMovement;
import monstercreator.SingleMovement;
import view.Items.Map.ViewMonster;
import view.game.GameMonster;
import view.game.MonsterKind;
import view.menu.QuestCreator;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SingleMovementEvent extends Event implements StopAble{

	private MonsterItem mon;
	//private ViewMonster ;
	private SingleMovement ment;
	private boolean stopped;

	public SingleMovementEvent(ViewMonster mon){
		
		this.mon=(MonsterItem) mon.getImageItem();
		ment=new SampleMovement();
		this.setName("simple movement");
		this.setIDName("simpleMovement");
	
	}
	
	public SingleMovementEvent(MonsterItem monsterItem) {
		mon=monsterItem;
		ment=new SampleMovement();
		this.setName("simple movement");
		this.setIDName("simpleMovement");
	}
	public SingleMovementEvent(MonsterItem monsterItem,SingleMovement mov) {
		mon=monsterItem;
		ment=mov;
		this.setName("simple movement");
		this.setIDName("simpleMovement");
	}
	

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void trigger() {
		
		EventTriggerStack stack=EventTriggerStack.getTriggerStack();	
		ArrayList <Univent> toexecute=new ArrayList<Univent>();
		
		if(ment.isMasterfirst()) {
			toexecute.add(getMasterMovement());
			toexecute.add(getMinionMovement());
		}
		else {
			
			toexecute.add(getMinionMovement());
			toexecute.add(getMasterMovement());
		}
		stack.addNewEvents(toexecute);
		//stack.triggerNextStackEvent();
		
		
	
	}
	private Event getMinionMovement() {
		if(ment.getMinionmovement()!=null) {
			EventTriggerStack stack=EventTriggerStack.getTriggerStack();	
			ShowMonsterMovementEvent ev=new ShowMonsterMovementEvent(mon,ment.getMinionmovement(),ment.getContinousMinionEffect(),MonsterKind.MINION);
			return ev;
		}
		return null;
		
		
	}
	private Event getMasterMovement() {
		if(ment.getMinionmovement()!=null) {
				
			ShowMonsterMovementEvent ev=new ShowMonsterMovementEvent(mon,ment.getMastermovement(),ment.getContinousMasterEffect(),MonsterKind.MASTER);
		
			return ev;
		}
		
		return null;
		
	}
	@Override
	public void continueStop() {
		// TODO Auto-generated method stub
		stopped=false;
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		System.out.println("added specifics");
		//add each movement texteditor and new movement adder
		//and option to do minions or masters first
		addMasterFirst(itemInfoText);
		itemInfoText.addTextBox("minion effect");
		addStringEditor(ment.getContinousMinionEffect(),itemInfoText,"minion effect",0);
		addAddMinionMovementButton(itemInfoText);
		addRemoveMinionMovementButton(itemInfoText);
		itemInfoText.addTextBox("minion movement");
		int s=1;
		for(MovementString str:ment.getMinionmovement()) {
			addStringEditor(str,itemInfoText,"minion",s);
			s++;
		}
		itemInfoText.addTextBox("master effect");
		addStringEditor(ment.getContinousMasterEffect(),itemInfoText,"master effect",0);
		addAddMasterMovementButton(itemInfoText);
		addRemoveMasterMovementButton(itemInfoText);
		s=1;
		itemInfoText.addTextBox("master movement");
		for(MovementString str:ment.getMastermovement()) {
			addStringEditor(str,itemInfoText,"master",s);
			s++;
		}
		super.addEventSpecifics(itemInfoText);
	}
	
	private void addRemoveMinionMovementButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("remove");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new RemoveMinionMovementOptionCommand(ment));
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("remove minion move");
		
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
	private void addRemoveMasterMovementButton(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("remove");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new RemoveMasterMovementOptionCommand(ment));
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("remove master move");
		
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
	private void addAddMasterMovementButton(ItemInfoContainer itemInfoText) {

		JButton button=new JButton("Add");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new AddMasterMovementOptionCommand(ment,"new momvement"));
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("new master move");
		
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
	

	private void addStringEditor(MovementString str, ItemInfoContainer itemInfoText,String text,int s) {
		
		JTextArea area=new JTextArea(200,100);
		area.setText(str.getThemovement());
		
		
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
				str.setThemovement(area.getText());
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
   
	}
	private void addAddMinionMovementButton(ItemInfoContainer itemInfoText) {
		
		
			JButton button=new JButton("Add");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserInputController control=UserInputController.getController();
					control.performCommand(new AddMinionMovementOptionCommand(ment,"new momvement"));
					
				}
				
			});
			 JLabel field = new JLabel();
			 field.setText("new minion move");
			
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
	
	private void addMasterFirst(ItemInfoContainer itemInfoText) {
		JCheckBox button=new JCheckBox();
		if(ment.isMasterfirst()) {
			button.setSelected(ment.isMasterfirst());
		}
		button.addActionListener(new ActionListener() {
			boolean ischeked=false;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ischeked=!ischeked;
				if(ischeked) {
				
					ment.setMasterfirst(true);
					
				}
				else {
					
					ment.setMasterfirst(false);
				
				}
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("master first");
		
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
		itemInfoText.addPreChekBox(field,button);
		
	}
	public Univent copy() {
		return new SingleMovementEvent(mon,ment);
	}

	public boolean isStopEvent() {
		return false;
	}
}
