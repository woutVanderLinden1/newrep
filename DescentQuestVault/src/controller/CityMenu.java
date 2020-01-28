package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import ItemEditor.ActionTaker;
import frame.MainFrame;
import model.ItemController;
import view.menu.CommandButton;
import view.menu.Menu;
import view.menu.OpenSkillEditorCommand;
import view.viewItems.TitleBox;

public class CityMenu extends Menu{
	
	public boolean shopdisabled;
	private Map<String, Boolean> disabledactions=new HashMap<String,Boolean>();
	
	public CityMenu(int x, int y) {
		super(x,y);
		shopdisabled=false;
		ItemController control=ItemController.getItemController();
		for(ActionTaker take:control.getCityEvents()) {
			disabledactions.put(((NamedActionTaker) take).getName(), false);
		}
	}

	@Override
	protected void prepareItemBox() {
		itemBox.removeAll();
		
		
		if(!shopdisabled) {
			addCityShopButton();
		}
		
		addRestButton();
		ItemController control=ItemController.getItemController();
		for(ActionTaker take:control.getCityEvents()) {
			if(disabledactions==null||!disabledactions.get(((NamedActionTaker) take).getName())) {
				this.addCityActionButton(((NamedActionTaker) take).getName(),take);
				
			}
			
		}
		this.revalidate();
		this.repaint();
	}

	private void addCityActionButton(String name, ActionTaker take) {
		JButton toadd=new JButton(name);
		toadd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				take.perform(arg0);
				disabledactions.put(((NamedActionTaker) take).getName(), true);
				prepareItemBox();
			}
			
		});
		itemBox.addButton(toadd);
	}

	protected void initialiseTitleBox() {
		this.setBackground(Color.BLUE);
		titleBox=new TitleBox("City",this.getWidth(),80);
		
		this.add(titleBox);
		
	}
	private void addCityShopButton() {
		JButton toadd=new JButton("Shop");
		toadd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserInputController control=UserInputController.getController();
				shopdisabled=true;
				control.startCityShop();
				prepareItemBox();
				
			}
			
		});
		itemBox.addButton(toadd);
		
	}
	private void addRestButton() {
		JButton toadd=new JButton("Rest");
		toadd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserInputController control=UserInputController.getController();
				
				control.startRest();
			}
			
		});
		itemBox.addButton(toadd);
		
	}
}
