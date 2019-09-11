package StoryEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import frame.SubContainer;
import misc.Tools;
import view.events.ReleasAble;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public abstract class DraggAblePanel extends SubContainer implements SelectAble, ReleasAble {

	private ArrayList<DragPanelMoveListener> movelisteners=new ArrayList<DragPanelMoveListener>();
	private ArrayList<DeleteListener> dellistener=new ArrayList<DeleteListener>();
	
	private String text;
	private Color currentColor;
	protected JTempTextField textLabel;
	private Image image;
	protected boolean invisilocked=true;
	
	
	public DraggAblePanel(String text) {
		super(100,50);
		this.setPreferredSize(new Dimension(100,50));
		this.setSize(new Dimension(100,50));
		this.text=text;
		textLabel=new JTempTextField();
		textLabel.setText(text);
		textLabel.setName(text);
		this.initialiseImage(Color.yellow);
		DraggAblePanel pan=this;
		/*
		textLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}
			
		});
		textLabel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan);
				pan.dispatchEvent(convertMouseEvent);
				
			}
			
		});
		*/
		//this.setBackground(Color.green);
	}
	
	public void setLocation(Point newlocation) {
		super.setLocation(newlocation);
		for(DragPanelMoveListener listener:movelisteners) {
			listener.panelmoved(this);
		}
		
	}
	

	
	public void addDragPanelMoveListener(DragPanelMoveListener listen) {
		movelisteners.add(listen);
	}
	
	public void initialiseImage(Color color) {
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
		this.initialiseImage(color,Color.blue);
		
	}
	
	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		if(textLabel!=null) {
			textLabel.setBackground(color);
		}
		
	}
	@Override
	public void addMouseListener(MouseListener listen) {
		super.addMouseListener(listen);
		System.out.println("mouselisteners "+this.getMouseListeners().length);
		if(textLabel!=null) {
			textLabel.addMouseListener(listen);
		}
		
		
	}
	@Override
	public void addMouseMotionListener(MouseMotionListener listen) {
		super.addMouseMotionListener(listen);
	
		if(textLabel!=null) {
			textLabel.addMouseMotionListener(listen);
		}
		
	}
	
	public void sendEvent(MouseEvent e, Point p, SelectAble selectable) {
		System.out.println("from sendevent");
		if(SubContainer.isMouseWithinComponent(textLabel)) {
			MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e,textLabel);
			
			textLabel.dispatchEvent(convertMouseEvent);
		}
		else {
			MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e,this);
			
			this.dispatchEvent(convertMouseEvent);
		}
		
		
	}
	
	protected void initialiseImage(Color color,Color color2) {
		if (!invisilocked) {
			return;
		}
		JPanel pan=new JPanel();
		pan.setSize(160,50);
		System.out.println("image made");
		JTextField textLabel=new JTextField(text);
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
		this.setImage(Tools.createImage(pan));
		
	}

	private void setImage(BufferedImage createImage) {
		
		image=createImage;
	}

	public Image getImage() {
		return image;
	}

	public String getText() {
		return text;
		
	}

	public void setText(String text) {
		this.text = text;
		this.initialiseImage(Color.YELLOW);
	}

	@Override
	public String getIDName() {
		
		return text;
	}

	@Override
	public void addNameChangeListener(NameChangeListener listen) {
		
		
	}

	@Override
	public void triggerNameChangeListeners(String newname) {
		
		
	}

	@Override
	public void changeName(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract SelectKind getKind();

	@Override
	public void select() {
		System.out.println("event selected");
		// TODO Auto-generated method stub
		//super.select();
		//selectedItem.select();
		
		setColors(new Color(200,230,100));
		
	}

	private void setColors(Color color) {
		
		currentColor=color;
		this.setBackground(color);
		textLabel.setBackground(color);
	}

	@Override
	public void deselect() {
		
		setColors(new Color(0,230,0));
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeInvisible() {
		// TODO Auto-generated method stub
		
	}

	public void removeListeners() {
		MouseListener[] newlist=this.getMouseListeners().clone();
		for(MouseListener listen:newlist) {
			this.removeMouseListener(listen);
			textLabel.removeMouseListener(listen);
		}
		MouseMotionListener[] newmotlist=this.getMouseMotionListeners().clone();
		for(MouseMotionListener listen:newmotlist) {
			this.removeMouseMotionListener(listen);
			textLabel.removeMouseMotionListener(listen);
		}
		// TODO Auto-generated method stub
		
	}

	public void giveMouseMotionListener(MouseMotionListener mouseMotionListener) {
		// TODO Auto-generated method stub
		this.addMouseMotionListener(mouseMotionListener);
	}

	public void giveMouseListener(MouseListener mouseListener) {
		// TODO Auto-generated method stub
		this.addMouseListener(mouseListener);
	}
	@Override
	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		( (ReleasAble) this.getParent()).released(arg0);
	}

	@Override
	public void dragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		( (ReleasAble) this.getParent()).dragged(arg0);
	}

	public void setInvisible() {
		invisilocked=false;
		Color invisicolor=new Color(0,0,0,0);
		// TODO Auto-generated method stub
		this.setBackground(invisicolor);
		textLabel.setBackground(invisicolor);
		textLabel.setDisabledTextColor(invisicolor);
		this.revalidate();
		this.repaint();
		
	}
	public void setVisible() {
		invisilocked=true;
		this.initialiseImage(new Color(0,230,0,100));
		this.select();
		this.revalidate();
		this.repaint();
		
	}

	public void placed() {
		// TODO Auto-generated method stub
		for(DragPanelMoveListener listener:movelisteners) {
			listener.panelmoved(this);
		}
	}

	public void removeMovePanelListener(Arrow arrow) {
		// TODO Auto-generated method stub
		movelisteners.remove(arrow);
	}
	public void delete() {
		// TODO Auto-generated method stub
		triggerDeleteListeners();
		
	}
	private void triggerDeleteListeners() {
		// TODO Auto-generated method stub
		for(DeleteListener listener:dellistener) {
			listener.panelDeleted();
		}
	}

	public void addDeleteListener(DeleteListener listen) {
		dellistener.add(listen);
	}
}
