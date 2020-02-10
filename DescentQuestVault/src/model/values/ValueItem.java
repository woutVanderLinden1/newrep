package model.values;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.commands.ICommand;
import view.Items.Map.EventHolder;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public abstract class ValueItem extends ImageItem<CustomValue> implements EventHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2681755507772457661L;
	private CustomValue val;
	
	public CustomValue getValue() {
		return val;
	}
	public Image getScaleImage(int i) {
		Image newimg =null;
	
		 newimg = lastImage.getScaledInstance( (int)(i*this.getScaleWidth()),(int) (i*this.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
		
		// TODO Auto-generated method stub
		return newimg;
	}

	
	public ValueItem(CustomValue val) {
		super(val);
		this.val=val;
		initialiseImage(new Color(210,0,0));
	}
	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VALUE;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Value;
	}


	public void changeName(String newname) {
		this.setName(newname);
		val.setName(newname);
	}

	

	protected void initialiseImage(Color color) {
		JPanel pan=new JPanel();
		pan.setSize(160,50);
		System.out.println("image made");
		JTextField textLabel=new JTextField(val.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		textLabel.setDisabledTextColor(new Color(0,0,200));
		//this.setSize(120,50);
		textLabel.setPreferredSize(new Dimension(100,25));
		
		pan.setLayout(null);
	
		
		//textLabel.setOpaque(false);
		
		pan.setBackground(color);
		Font font=new Font("Verdana", Font.BOLD, 15);
		textLabel.setFont(font);
		
		textLabel.setForeground(Color.WHITE);
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		



		textLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textLabel.setSize(pan.getWidth()-20,pan.getHeight()-20);
		//this.setPreferredSize(new Dimension(120,50));
		pan.add(textLabel);
		textLabel.setLocation(10,10);
	
		pan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.setImage(createImage(pan));
		
	}
	
	public BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    g.dispose();
	    return bi;
	}


	public abstract ICommand getNewCreationCommand() ;
	
	
	public abstract ValueKind getValueKind();
	
	
}
