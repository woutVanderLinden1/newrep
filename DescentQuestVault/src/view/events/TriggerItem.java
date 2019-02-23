package view.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import frame.SubContainer;
import model.event.Trigger;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class TriggerItem extends UniventItem {

	private Trigger trigger;
	
	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public TriggerItem(Trigger trig) {
		super(trig);
		setTrigger(trig);
		initialiseImage(new Color(255,230,120),new Color(255,100,40));
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.TRIGGER;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	protected void initialiseImage(Color color, Color color2) {
		// TODO Auto-generated method stub
		int i =100;
	
		JPanel pan=new JPanel();
		pan.setSize(120,50);
		System.out.println("this is added");
		JLabel textLabel=new JLabel(trigger.getIDName());
		System.out.println("this is the name "+trigger.getIDName());
		//textLabel.setOpaque(false);
		
		pan.setBackground(color);
		textLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		textLabel.setForeground(color2);
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		textLabel.setPreferredSize(new Dimension(i,25));
		textLabel.setSize(i,25);
		pan.add(textLabel);
		textLabel.setLocation(10,10);
		SubContainer subEvents=new SubContainer(i-25,80);
		subEvents.setLocation(10,60);
		pan.add(subEvents);
		//raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		subEvents.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		pan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		System.out.println("thisheight "+subEvents.getHeight());
		this.setImage(createImage(pan));
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new TriggerItem(trigger);
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return .7;
	}
	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .2;
	}
	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Event;
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		trigger.addEventSpecifics(itemInfoText);
		
	}
	

}
