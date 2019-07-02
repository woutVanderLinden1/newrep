package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import frame.SubContainer;
import model.values.BooleanValue;

public class ItemInfoContainer extends SubContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> lebles=new ArrayList<JLabel>();
	
	public ItemInfoContainer(Dimension defaultSize) {
		super(defaultSize);
		// TODO Auto-generated constructor stub
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

}
