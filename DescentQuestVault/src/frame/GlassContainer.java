package frame;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import StoryEditor.Arrow;

public class GlassContainer extends SubContainer {

	private ArrayList<Arrow> arrows=new ArrayList<Arrow>();
	
	public void AddArrow(Arrow arrow) {
		arrows.add(arrow);
	}
	
	public GlassContainer(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		compo=makeComposite(.5f);
	}
	public GlassContainer(Dimension defaultSize) {
		super(defaultSize);
		// TODO Auto-generated constructor stub
		compo=makeComposite(0f);
	}
	private Image image;
	private int x;
	private int y;
	private Composite compo;
	
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public void setImage(Image image) {
		this.image=image;
	}
	
	protected void paintComponent(Graphics g) {
	
			
	
		  Graphics2D g2d=(Graphics2D) g;
		  for(Arrow arrow:arrows) {
			  arrow.drawArrow(g2d);
		  }
		  
		  Composite originalComposite = g2d.getComposite();
		  g2d.setComposite(compo);
		 // g2d.dispose();
		  super.paintComponent(g);
		  g.drawImage(image,x,  y,null);
	//  g2d.dispose();
		//  this.removeAll();
		//  this.revalidate();
		  g2d.setComposite(originalComposite);
		 
	  
	  
	        
	    
	}
	 private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	 @Override
	 public void removeAll() {
		 //compo.
		 super.removeAll();
	 }

	public void removeArrows() {
		// TODO Auto-generated method stub
		for(Arrow arr:arrows) {
			if(!arr.isFinsihed()) {
				arr.clearFromListeners();
			}
			
		}
		arrows.clear();
	}

	public void setPoint(Point newLocation) {
		// TODO Auto-generated method stub
		this.setX(newLocation.x);
		this.setY(newLocation.y);
	}
	public void released(MouseEvent e) {
	}
}
