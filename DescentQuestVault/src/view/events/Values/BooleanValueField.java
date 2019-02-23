package view.events.Values;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import MouseListeners.SelectEventListener;
import model.event.Event;
import model.event.Trigger;
import model.values.BooleanValueItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class BooleanValueField extends ValueField {

	

	public BooleanValueField(Event placeTileEvent, int w) {
		super(placeTileEvent, w);
		// TODO Auto-generated constructor stub
	}


	private boolean selected;
	private BooleanValueItem selectedItem;
	private JLabel picLabel;
	private MouseListener listen;
	private Color currentColor;
	private JTextField textLabel;
	
	/*
	public BooleanValueField(BooleanValueItem selectedItem) {
		//super(selectedItem);
		this.selectedItem = selectedItem;
		
		textLabel=new JTextField(selectedItem.getName());
		this.initialiseImage(new Color(230,0,0));
	//	reAddImage();
		listen=new SelectEventListener(this);
		this.addMouseListener(listen);
		textLabel.addMouseListener(listen);
		//placeTileEvent.addNameChangeListener(this);
	}
	*/

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.BOOLEANVALUE;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return selectedItem;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void removeEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createTransparent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createBaseImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		// TODO Auto-generated method stub
		
	}

	
	protected void initialiseImage(Color color) {
		currentColor=color;
		System.out.println("image made");
		//textLabel=new JTextField(selectedItem.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		//this.setSize(120,50);
		textLabel.setDisabledTextColor(new Color(255,255,255));
		textLabel.setPreferredSize(new Dimension(100,25));
		
		this.setLayout(null);
	
		
		//textLabel.setOpaque(false);
		
		this.setBackground(color);
		Font font=new Font("Verdana", Font.BOLD, 15);
		textLabel.setFont(font);
		
		textLabel.setForeground(Color.BLUE);
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		



		textLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textLabel.setSize(this.getWidth()-20,this.getHeight()-20);
		//this.setPreferredSize(new Dimension(120,50));
		this.add(textLabel);
		textLabel.setLocation(10,10);
	
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	
		
	}
}
