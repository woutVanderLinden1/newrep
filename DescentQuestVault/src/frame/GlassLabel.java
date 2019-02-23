package frame;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GlassLabel extends JLabel {

	private Composite compo;
	private GlassLabel contain;
	
	public GlassLabel(ImageIcon imageIcon){
		super(imageIcon);
		// TODO Auto-generated constructor stub
		compo=makeComposite(.5f);
	}
	protected void paintComponent(Graphics g) {
	//	System.out.println("painted glasscontainer");
		 Graphics2D g2d=(Graphics2D) g;
		  Composite originalComposite = g2d.getComposite();
		  g2d.setComposite(compo);
		super.paintComponent(g);	
		 g2d.setComposite(originalComposite);
	 
	        
	    
	}
	 private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
}
