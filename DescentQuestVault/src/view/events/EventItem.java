package view.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import model.event.Event;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class EventItem extends UniventItem {

	private Event ev;
	
	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}

	public EventItem(Event placeTileEvent) {
		super(placeTileEvent);
		ev=placeTileEvent;
		initialiseImage(new Color(0,230,0),new Color(0,0,200));
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.EVENT;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new EventItem(ev);
	}
	
	public String getIDName() {
		return ev.getIDName();
	}
	

	protected void initialiseImage(Color color,Color color2) {
		JPanel pan=new JPanel();
		pan.setSize(160,50);
		System.out.println("image made");
		JTextField textLabel=new JTextField(ev.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		textLabel.setDisabledTextColor(color2);
		//this.setSize(120,50);
		textLabel.setPreferredSize(new Dimension(100,25));
		
		pan.setLayout(null);
	
		
		//textLabel.setOpaque(false);
		
		pan.setBackground(color);
		Font font=new Font("Verdana", Font.BOLD, 15);
		textLabel.setFont(font);
		
		textLabel.setForeground(Color.BLUE);
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
	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return .5;
	}
	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .2;
	}
	
	public void select() {
		super.select();
		System.out.println("imagecolor changed");
		initialiseImage(new Color(200,230,100),new Color(0,0,200));
	}

	public void deselect() {
		super.deselect();
		initialiseImage(new Color(0,230,0),new Color(0,0,200));
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Event;
	}

	@Override
	public void makeVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeInvisible() {
		// TODO Auto-generated method stub
		
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		ev.addEventSpecifics(itemInfoText);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	
	
}
