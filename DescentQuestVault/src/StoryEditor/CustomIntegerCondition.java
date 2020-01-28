package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.ItemController;
import model.Monster.Monster;
import model.values.Comparison;
import model.values.CustomInteger;
import view.Items.Map.ViewMonster;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class CustomIntegerCondition extends DataCondition {


	private Comparison comp;
	private CustomInteger theItem;
	private CustomInteger compvalue;
	private String name;
	
	private static int nr;
	
	public CustomIntegerCondition(CustomInteger theItem,Comparison comp,CustomInteger compvalue) {
		super(theItem);
		this.theItem=theItem;
		this.comp=comp;
		this.compvalue=compvalue;
		name= "custominteger"+ nr++;
		
	}
	@Override
	public boolean isCondition() {
		
		return comp.compare(theItem.getTheInteger(), compvalue.getTheInteger());
	}
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		addComparisonModifier(itemInfoText);
		addCustomIntegerPicker(itemInfoText);
		addSecondCustomIntegerPicker(itemInfoText);
		
	}
	
	private void addSecondCustomIntegerPicker(ItemInfoContainer itemInfoText) {
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		String[] strings=new String[comboOptions.length+1];
		
		for(int i=0;i<comboOptions.length;i++) {
			strings[i]=comboOptions[i].getName();
		}
		JComboBox<String> button=new JComboBox<String>(strings);
		
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=button.getSelectedIndex();
				
			
				
				CustomInteger mon=comboOptions[j];
				
				compvalue=mon;
				
		    }
		};
		
		button.addActionListener(listen);
		 JLabel field = new JLabel();
		 field.setText("change custominteger2:");
		
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
	
	private void addCustomIntegerPicker(ItemInfoContainer itemInfoText) {
		ItemController control=ItemController.getItemController();
		CustomInteger[] comboOptions=control.getCustomIntegers();
		String[] strings=new String[comboOptions.length+1];
		
		for(int i=0;i<comboOptions.length;i++) {
			strings[i]=comboOptions[i].getName();
		}
		JComboBox<String> button=new JComboBox<String>(strings);
		
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=button.getSelectedIndex();
				
			
				
				CustomInteger mon=comboOptions[j];
				
				theItem=mon;
				
		    }
		};
		
		button.addActionListener(listen);
		 JLabel field = new JLabel();
		 field.setText("change custominteger1:");
		
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
	private void addComparisonModifier(ItemInfoContainer itemInfoText) {
		Comparison[] comboOptions = {Comparison.EQUALS,Comparison.LESS,Comparison.MORE,Comparison.DOESNOTEQUAL};
			
			JComboBox<Comparison> button=new JComboBox<Comparison>(comboOptions);
			
			
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					comp=((Comparison) button.getSelectedItem());
					
				
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
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
