package monsterEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.UserInputController;
import controller.commands.LoadMovementCommand;
import controller.commands.SaveMovementCommand;
import controller.commands.select.SelectCommand;
import model.Monster.*;
import monster.Alrick;
import monster.Merick;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.SelectAble;

public class MonsterOptions  extends JPanel {

	private JComboBox<String> box;
	private HashMap<String,Monster> map;
	private Monster[] list= {new Zombie(),new Barghest(),new Elemental(),new Ettin(),new FleshMoulder(), new GoblinArcher(),new Merroid(),new ShadowDragon(),new Spider(),new Baron(), new Tentacle(),new Eliza(),new Belthir(),new Splig(), new Alrick(),new Merick()};
	private JButton saveButton;
	private JButton loadButton;
	
	
	public MonsterOptions(int i, int j,MonsterEditor edit){
		this.setSize(i,j);
		
		this.setPreferredSize(new Dimension(i,j));
		this.setMinimumSize(new Dimension(400,300));
		this.setBackground(Color.BLUE);
		initialiseBox(edit);
		initialiseSaveButton();
		initialiseLoadButton();
		// TODO Auto-generated constructor stub
	}



	private void initialiseLoadButton() {
		loadButton=new JButton("Load");
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new LoadMovementCommand());
				
			}
			
		});
		this.add(loadButton);
		
	}



	private void initialiseSaveButton() {
		saveButton=new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new SaveMovementCommand());
				
			}
			
		});
		this.add(saveButton);
	}



	private void initialiseBox(MonsterEditor edit) {
		String[] strings=new String[list.length];
		map=new HashMap<String,Monster>();
		int t=0;
		for(Monster mon:list) {
			map.put(mon.getName(), mon);
			strings[t]=mon.getName();
			t++;
		}
		box=new JComboBox<String>(strings);	
		this.add(box);
		box.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=(String) box.getSelectedItem();
				Monster mon=map.get(str);
				MonsterEditorController control=MonsterEditorController.getMonsterEditorController();
				MonsterItem it=new MonsterItem(mon);
				control.performCommand(new SelectCommand(it));
				edit.setMonster(mon);
				edit.setCurrentmovement(it.getTrig());
		    }
		});
		
	}



	public void sendEvent(MouseEvent e, Point newLocation, SelectAble selectAble) {
		// TODO Auto-generated method stub
		
	}



	public void defaultSelect() {
		box.setSelectedItem(box.getSelectedItem());
		
	}

}
