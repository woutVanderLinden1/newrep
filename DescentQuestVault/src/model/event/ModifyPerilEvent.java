package model.event;

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

import controller.UserInputController;
import controller.command.ModifyIntegerValueCommand;
import model.ItemController;
import model.values.IntegerValueItem;
import model.values.Modification;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ItemInfoContainer;

public class ModifyPerilEvent extends Event implements NameChangeListener {

	private IntegerValueItem theitem;
	private Modification mod;
	private int modvalue=0;
	private boolean namebased=true;

	public IntegerValueItem getTheitem() {
		return theitem;
	}

	public void setTheitem(IntegerValueItem theitem) {
		this.theitem = theitem;
	}

	public Modification getMod() {
		return mod;
	}

	public void setMod(Modification mod) {
		this.mod = mod;
	}

	public ModifyPerilEvent(Modification set) {
		//theitem=integerValueItem;
		mod=set;
		
		setIDName("changeValue peril");
		setName("change Value peril");
		//integerValueItem.addNameChangeListener(this);
	}
	public ModifyPerilEvent(Modification set,int modvalue) {
		//theitem=integerValueItem;
		mod=set;
		this.modvalue=modvalue;
		setIDName("changeValueperil");
		setName("change Value Peril");
		//integerValueItem.addNameChangeListener(this);
	}

	public int getModvalue() {
		return modvalue;
	}

	public void setModvalue(int modvalue) {
		this.modvalue = modvalue;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		ItemController itemcontrol=ItemController.getItemController();
		control.performCommand(new ModifyIntegerValueCommand(modvalue,mod,itemcontrol.getPeril()));
	}
	
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("Modify "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		//add booleanvalue
		Modification[] comboOptions = {Modification.SET,Modification.PLUS,Modification.MINUS,Modification.MULTIPLY,Modification.DIVIDE};
		
		JComboBox<Modification> button=new JComboBox<Modification>(comboOptions);
		
		
		button.setSelectedItem(mod);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mod=((Modification) button.getSelectedItem());
				
			
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
		  
		
		 field2.setValue(modvalue);
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
				modvalue=(int) field2.getValue();  
			  }
			});
		
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
     
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field2);
		
		
		
	}
	public Univent copy() {
		return new ModifyPerilEvent(mod,modvalue);
	}
}
