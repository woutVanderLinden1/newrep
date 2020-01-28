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
import view.viewItems.ItemBox.ItemInfoContainer;

public class BooleanCondition extends DataCondition {

	private Comparison comp;
	private CustomBoolean theItem;
	private boolean compvalue;
	private String name;
	private static int nr;
	

	public BooleanCondition(CustomBoolean tocompare,Boolean compvalue) {
		super(tocompare);
		theItem=tocompare;
		this.comp=comp;
		this.compvalue=compvalue;
		name="boolean condition"+nr++;
	}

	@Override
	public boolean isCondition() {
		// TODO Auto-generated method stub
		return theItem.isValue() == compvalue;
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//addComparisonModifier(itemInfoText);
		addCustomBooleanPicker(itemInfoText);
		addSecondCustomBooleanPicker(itemInfoText);
		
	}

	private void addSecondCustomBooleanPicker(ItemInfoContainer itemInfoText) {
		
		Boolean[] comboOptions= {true,false};
		
	
		JComboBox<Boolean> button=new JComboBox<Boolean>(comboOptions);
		
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compvalue=(boolean) button.getSelectedItem();
				
		    }
		};
		
		button.addActionListener(listen);
		 JLabel field = new JLabel();
		 field.setText("change boolean2:");
		
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
