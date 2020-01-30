package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import ItemEditor.ActionTaker;
import controller.UserInputController;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.select.SelectCommand;
import frame.SubContainer;
import misc.ActivateAble;
import model.event.MonsterTurnTrigger;
import model.values.BooleanValue;
import view.Items.Map.ViewMonster;

public class ItemInfoContainer extends SubContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> lebles=new ArrayList<JLabel>();
	private InfoItemBox box;
	
	public ItemInfoContainer(Dimension defaultSize, InfoItemBox box) {
		super(defaultSize);
		this.box=box;
		// TODO Auto-generated constructor stub
	}
	
	public ItemInfoContainer(int i, int j) {
		super(i,j);
		box=new InfoItemBox(i,j);
		
	}

	public void reset() {
		
		this.setPreferredSize(new Dimension(this.getPreferredSize().width,0));
		this.setSize(this.getPreferredSize());
		System.out.println("resetted "+this.getPreferredSize().height);
		this.removeAll();
	}

	public void addPreText(JLabel lab, JTextField field) {
		int size=0;
		lab.setPreferredSize(new Dimension(lab.getWidth(),25));
		for(JLabel labs:lebles) {
			size+=labs.getHeight();
		}
		
		lab.setLocation(0,size);
		field.setLocation(lab.getWidth(),size);
		lebles.add(lab);
		this.add(lab);
		this.add(field);
		increaseSize(lab.getHeight()+50);
		
	}

	private int sum(int size, int height) {
		// TODO Auto-generated method stub
		return size+height ;
	}

	public void addPreButton(JLabel lab, JButton button) {
		int size=0;
		lab.setPreferredSize(new Dimension(lab.getWidth(),25));
		for(JLabel labs:lebles) {
			size+=labs.getHeight();
		}
		
		lab.setLocation(0,size);
		button.setLocation(lab.getWidth(),size);
		lebles.add(lab);
		this.add(lab);
		this.add(button);
		increaseSize(lab.getHeight()+50);
		
	}

	public void addPreChekBox(JLabel lab, JCheckBox button) {
		int size=0;
		lab.setPreferredSize(new Dimension(lab.getWidth(),25));
		for(JLabel labs:lebles) {
			size+=labs.getHeight();
		}
		
		lab.setLocation(0,size);
		button.setLocation(lab.getWidth(),size);
		lebles.add(lab);
		this.add(lab);
		this.add(button);
		increaseSize(lab.getHeight());
		
	}

	public void addPreComboBox(JLabel lab, JComboBox button) {
		int size=0;
		lab.setPreferredSize(new Dimension(lab.getWidth(),25));
		for(JLabel labs:lebles) {
			size+=labs.getHeight();
		}
		
		lab.setLocation(0,size);
		button.setLocation(lab.getWidth(),size);
		lebles.add(lab);
		this.add(lab);
		this.add(button);
		increaseSize(lab.getHeight()+50);
		
	}

	public void addToPanel(JScrollPane pan) {
		pan.setPreferredSize(new Dimension(this.getWidth()-50,100));
		pan.setSize(new Dimension(this.getWidth()-50,100));
		this.add(pan);
		increaseSize(pan.getHeight()+50);
	}

	private void increaseSize(int height) {
		// TODO Auto-generated method stub
		
		this.setPreferredSize(new Dimension(this.getWidth(),this.getPreferredSize().height+height));
		//setSize(this.getWidth(),this.getHeight());
		System.out.println("itemboxheight "+this.getPreferredSize().height+" addedheight "+height);
		this.revalidate();
		this.repaint();
	}

	public void addTextBox(String string) {
		 JLabel field = new JLabel();
		 field.setText(string);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=this.getWidth();
        field.setSize(new Dimension(w-20,25));
        field.setPreferredSize(new Dimension(w-20,25));
        field.setLocation(10, 10);
		this.add(field);
		increaseSize(field.getHeight()+20);
		
	}

	public void refreshImage() {
		// TODO Auto-generated method stub
		if(box!=null) {
			box.refreshImage();
		}
		else {
			this.revalidate();
			this.repaint();
		}
	}
	public void addNumericEditButton(String text ,ItemInfoContainer itemInfoText,int modvalue ,ActionTaker<Integer> exor) {
		//add booleanvalue
		
		
		 JLabel field = new JLabel();
		 field.setText(text);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
     
		
		//add textfield for the value
		
		JLabel lab=new JLabel(text);
		
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
				exor.perform((int) field2.getValue());  
			  }
			});
		
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
     int w=itemInfoText.getWidth();
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field2);
		
		
		
	}
	
	
	public void addEditButton(String text ,ItemInfoContainer itemInfoText,String modvalue ,ActionTaker<String> exor) {
		//add booleanvalue
		
		
		 JLabel field = new JLabel();
		 field.setText(text);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
     
		
		//add textfield for the value
		
		JLabel lab=new JLabel(text);
		
		
		    // If you want the value to be committed on each keystroke instead of focus lost
		   
		    JFormattedTextField field2 = new JFormattedTextField();

		  
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
				exor.perform((String) field2.getValue());  
			  }
			});
		
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
     int w=itemInfoText.getWidth();
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field2);
		
		
		
	}
	public void addTextArea(ItemInfoContainer itemInfoText,String otText,ActionTaker<String> taker) {

		JTextArea area=new JTextArea(this.getWidth(),400);
		area.setText(otText);
		
		
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
				taker.perform(area.getText());
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
	}
	
	public <P> JComboBox addJComboBox(String pretext,P[] list,ActionTaker<P> taker) {
		JComboBox<P> button=new JComboBox<P>(list);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				P comp=((P) button.getSelectedItem());
				taker.perform(comp);
				
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(pretext);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=this.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        //button.setHorizontalAlignment(SwingConstants.RIGHT);
       this.addPreComboBox(field,button);
		return button;
	}
	public <P> void addButton(String pretext,String buttontext,ActionTaker<ActionEvent> taker) {
	
		JButton button=new JButton(buttontext);
	
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taker.perform(arg0);
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(pretext);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
	   // itemInfoText.add(lab);
	   // itemInfoText.add(field);
	    int w=this.getWidth();
	    field.setSize(new Dimension(w/2-20,25));
	    button.setSize(new Dimension(w/2,25));
	    button.setPreferredSize(new Dimension((int)(w/2-20),25));
	    field.setPreferredSize(new Dimension(w/2,25));
	    button.setHorizontalAlignment(SwingConstants.RIGHT);
		this.addPreButton(field,button);
	}
	public <P> void addCheckBox(String pretext,boolean checked,ActionTaker<Boolean> taker) {
		
		JCheckBox button=new JCheckBox(pretext);
		button.setSelected (checked);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taker.perform(button.isSelected());
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(pretext);
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
	   // itemInfoText.add(lab);
	   // itemInfoText.add(field);
	    int w=this.getWidth();
	    field.setSize(new Dimension(w/2-20,25));
	    button.setSize(new Dimension(w/2,25));
	    button.setPreferredSize(new Dimension((int)(w/2-20),25));
	    field.setPreferredSize(new Dimension(w/2,25));
	    button.setHorizontalAlignment(SwingConstants.RIGHT);
		this.addPreChekBox(field,button);
	}
	

}

