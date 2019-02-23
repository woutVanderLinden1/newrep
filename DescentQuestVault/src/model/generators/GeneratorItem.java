package model.generators;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import view.viewItems.ItemBox.ImageItem;

public abstract class GeneratorItem extends ImageItem<Generator> implements CommandHolder {

	private Generator gen;
	
	
	public GeneratorItem(Generator tile) {
		super(tile);
		gen=tile;
		initialiseImage(new Color(210,210,210,99));
	}


	protected void initialiseImage(Color color) {
		JPanel pan=new JPanel();
		pan.setSize(160,50);
		System.out.println("image made");
		JTextField textLabel=new JTextField(gen.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		textLabel.setDisabledTextColor(new Color(0,230,0));
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
}
