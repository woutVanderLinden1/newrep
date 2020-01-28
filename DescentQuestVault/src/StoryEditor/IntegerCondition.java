package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import model.ItemController;
import model.values.Comparison;
import model.values.CustomInteger;
import model.values.IntegerValueItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class IntegerCondition extends DataCondition {

	private Comparison comp;
	private CustomInteger theItem;
	private int compvalue;
	private String name;
	private static int nr;
	
	public IntegerCondition(CustomInteger theItem,Comparison comp,int compvalue) {
		super(theItem);
		this.comp=comp;
		this.theItem=theItem;
		this.compvalue=compvalue;
		this.name="integer condition"+nr++;
		
	}
	@Override
	public boolean isCondition() {
		
		return comp.compare(theItem.getTheInteger(), compvalue);
	}
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		addComparisonModifier(itemInfoText);
		addCustomIntegerPicker(itemInfoText);
		addIntModifier(itemInfoText);
		
	}
	
	private void addIntModifier(ItemInfoContainer itemInfoText) {
		//add booleanvalue
		
			
			//add textfield for the value
			
			JLabel lab=new JLabel("modvalue: ");
			
			 NumberFormat format = NumberFormat.getInstance();
			    NumberFormatter formatter = new NumberFormatter(format);
			    formatter.setValueClass(Integer.class);
			    formatter.setMinimum(0);
			    formatter.setMaximum(Integer.MAX_VALUE);
			    formatter.setAllowsInvalid(false);
			    // If you want the value to be committed on each keystroke instead of focus lost
			    formatter.setCommitsOnValidEdit(true);
			    JFormattedTextField field2 = new JFormattedTextField(formatter);

			  
			    // getValue() always returns something valid
			  
			
			 field2.setValue(compvalue);
			 field2.setColumns(10);
			 

		    
			 field2.getDocument().addDocumentListener(new DocumentListener() {
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
					compvalue=(int) field2.getValue();  
				  }
				});
			
			
	     // itemInfoText.add(lab);
	     // itemInfoText.add(field);
			 int w=itemInfoText.getWidth();
	     
	      lab.setSize(new Dimension(w/2-20,25));
	      field2.setSize(new Dimension(w/2,25));
	      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
	      field2.setPreferredSize(new Dimension(w/2,25));
	      lab.setHorizontalAlignment(SwingConstants.RIGHT);
	      
	      itemInfoText.addPreText(lab,field2);
			
			
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
