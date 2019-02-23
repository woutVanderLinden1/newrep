package view.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import MouseListeners.SelectEventListener;
import frame.SubContainer;
import model.event.Event;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class EventField extends BaseField implements SelectAble, NameChangeListener{
	

	private boolean selected;
	private EventItem selectedItem;
	private JLabel picLabel;
	private MouseListener listen;
	private Color currentColor;
	private JTextField textLabel;

	public Event getEvent() {
		return (Event) event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventField(Event placeTileEvent, int w) {

		this.setPreferredSize(new Dimension(w,50));
		this.setSize(new Dimension(w,50));
		selectedItem=new EventItem(placeTileEvent);
		setEvent(placeTileEvent);
		
		/*
		JLabel textLabel=new JLabel(event.getName());
		textLabel.setPreferredSize(new Dimension(100,25));
		
		this.setLayout(null);
	
		
		
		//textLabel.setOpaque(false);
		
		this.setBackground(new Color(0,230,0));
		textLabel.setFont(new Font("TimesRoman", Font.BOLD, 14));
		textLabel.setForeground(new Color(0,0,200));
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		textLabel.setSize(120,25);
		//this.setPreferredSize(new Dimension(120,50));
		this.add(textLabel);
		textLabel.setLocation(10,10);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		*/
		textLabel=new JTextField(selectedItem.getName());
		this.initialiseImage(new Color(0,230,0));
	//	reAddImage();
		listen=new SelectEventListener(this);
		this.addMouseListener(listen);
		textLabel.addMouseListener(listen);
		placeTileEvent.addNameChangeListener(this);
		
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
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.EVENT;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return selectedItem;
	}

	@Override
	public void select() {
		System.out.println("event selected");
		// TODO Auto-generated method stub
		super.select();
		selectedItem.select();
		setColors(new Color(200,230,100));
		
	}

	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		super.deselect();
		selectedItem.deselect();
		setColors(new Color(0,230,0));
	}


	private void setColors(Color color) {
		this.setBackground(color);
		textLabel.setBackground(color);
	}

	protected void initialiseImage(Color color) {
		currentColor=color;
		System.out.println("image made");
		//textLabel=new JTextField(selectedItem.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		//this.setSize(120,50);
		textLabel.setDisabledTextColor(new Color(0,0,200));
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
	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void createTransparent() {
		// TODO Auto-generated method stub
		//initialiseImage(new Color(200,230,100,80));
		this.setBackground(new Color(200,230,100,80));
		textLabel.setBackground(new Color(200,230,100,80));
	}

	@Override
	protected void createBaseImage() {
		// TODO Auto-generated method stub
		this.setBackground(new Color(0,230,0));
		textLabel.setBackground(new Color(0,230,0));
		//this.initialiseImage(new Color(0,230,0));
	}

	@Override
	protected void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
		
		this.dispatchEvent(convertMouseEvent);
		
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		textLabel.setText(newname);
	}

	
	@Override
	public void setSize(Dimension d) {
		this.setSize((int)d.getWidth(),(int)d.getHeight());
		
	
	}


	@Override
	public void setSize(int a,int b) {
		Dimension d=new Dimension(a,b);
		this.setPreferredSize(d);
		super.setSize(a,b);
		if(textLabel!=null) {
			textLabel.setSize(this.getWidth()-40,this.getHeight()-20);
		}
		
		//this.setPreferredSize(new Dimension(120,50));
	
		
	}

	public void activateTemporary() {
		//should deactivate mouselistener
		// TODO Auto-generated method stub
		temporary=true;
		this.removeMouseListener(listen);
		this.removeMouseMotionListener((MouseMotionListener) listen);
	}
	public void deActivateTemporary() {
		// TODO Auto-generated method stub
		temporary=false;
		this.addMouseListener(listen);
		this.addMouseMotionListener((MouseMotionListener) listen);
	}



}
