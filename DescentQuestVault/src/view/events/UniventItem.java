package view.events;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.event.Event;
import model.event.Univent;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectKind;

public abstract class UniventItem extends ImageItem {

	protected Univent uni;
	private String name;
	
	public UniventItem(Univent placeTileEvent) {
		super();
		uni=placeTileEvent;
	//	initialiseImage(new Color(0,230,0));
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
	protected abstract void initialiseImage(Color color,Color color2);


	public Image getScaleImage(int i) {
		Image newimg =null;
	
		 newimg = lastImage.getScaledInstance( (int)(i*this.getScaleWidth()),(int) (i*this.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
		
		// TODO Auto-generated method stub
		return newimg;
	}

	
	public String getName() {
		System.out.println("theevent "+uni);
		return uni.getName();
	}
	
	public void setName(String newname) {
		name=newname;
		triggerNameChangeListeners(newname);
	}
	
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	
	public void addNameChangeListener(NameChangeListener listen) {
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}
	
	public void changeName(String newname) {
		this.setName(newname);
	}
}
