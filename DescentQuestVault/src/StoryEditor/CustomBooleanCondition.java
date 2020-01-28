package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.ItemController;
import model.values.Comparison;
import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.CustomValue;
import view.viewItems.ItemBox.ItemInfoContainer;

public class CustomBooleanCondition extends DataCondition {
	
	
	private CustomBoolean theItem;
	private CustomBoolean compvalue;
	private String name;
	private static int nr;
	

	public CustomBooleanCondition(CustomBoolean tocompare,CustomBoolean compvalue) {
		super(tocompare);
		theItem=tocompare;
		//this.comp=comp;
		this.compvalue=compvalue;
		name="Customboolean condition"+nr++;
	}

	@Override
	public boolean isCondition() {
		// TODO Auto-generated method stub
		return theItem.isValue() == compvalue.isValue();
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//addComparisonModifier(itemInfoText);
		addCustomBooleanPicker(itemInfoText);
		addSecondCustomBooleanPicker(itemInfoText);
		
	}
	
	private void addSecondCustomBooleanPicker(ItemInfoContainer itemInfoText) {
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		String[] strings=new String[comboOptions.length+1];
		
		for(int i=0;i<comboOptions.length;i++) {
			strings[i]=comboOptions[i].getName();
		}
		JComboBox<String> button=new JComboBox<String>(strings);
		
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=button.getSelectedIndex();
				
			
				
				CustomBoolean mon=comboOptions[j];
				
				compvalue=mon;
				
		    }
		};
		
		button.addActionListener(listen);
		 JLabel field = new JLabel();
		 field.setText("change customboolean2:");
		
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
	
	private void addCustomBooleanPicker(ItemInfoContainer itemInfoText) {
		ItemController control=ItemController.getItemController();
		CustomBoolean[] comboOptions=control.getCustomBooleans();
		String[] strings=new String[comboOptions.length+1];
		
		for(int i=0;i<comboOptions.length;i++) {
			strings[i]=comboOptions[i].getName();
		}
		JComboBox<String> button=new JComboBox<String>(strings);
		
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=button.getSelectedIndex();
				
			
				
				CustomBoolean mon=comboOptions[j];
				
				theItem=mon;
				
		    }
		};
		
		button.addActionListener(listen);
		 JLabel field = new JLabel();
		 field.setText("change customboolean1:");
		
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
