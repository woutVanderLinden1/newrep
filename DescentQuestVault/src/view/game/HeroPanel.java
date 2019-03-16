package view.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.hero.DefeatChangeListener;
import view.hero.EndTurnListener;
import view.hero.GameHero;

public class HeroPanel extends JPanel implements DefeatChangeListener,EndTurnListener{

	private ArrayList<GameHero> theheroes;
	
	
	public HeroPanel() {
		theheroes=new ArrayList<GameHero>();
		this.setBackground(new Color(0,0,0,0));
	}
	
	public void addHero(GameHero viewher) {
		
		theheroes.add(viewher);
		viewher.addDefeatChangeListener(this);
		viewher.addTurnEndListener(this);
		System.out.println(viewher);
		System.out.println(viewher.getImage());
		refreshHeroImage(viewher);
		
	}

	private void refreshHeroImage(GameHero viewher) {
		Image imageused=null;
		Image im=null;
		if(viewher.isTurnended()) {
			System.out.println("displaying darkened image");
			im=viewher.getScaleImage(50);
			BufferedImage touse=new BufferedImage(100, 100,BufferedImage.TYPE_INT_ARGB);
			BufferedImage touse2=new BufferedImage(100, 100,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = touse2.createGraphics();
			g2.drawImage(im,0,0,null);
			g2.dispose();
			
			Graphics2D g = touse.createGraphics();
			
			g.drawImage(touse2, new RescaleOp(
			        new float[]{0.9f, 0.9f, 0.9f, .9f}, // scale factors for red, green, blue, alpha
			        new float[]{0, 0, 0, 0}, // offsets for red, green, blue, alpha
			        null), // You can supply RenderingHints here if you want to
			    0, 0);
		    g.dispose();
		    im=touse;
		}
		else {
			im=viewher.getScaleImage(50);
		}
		
		if(viewher.isdefeated()) {
			BufferedImage touse=new BufferedImage(100, 100,BufferedImage.TYPE_INT_ARGB);
			
			Image crossimage=null;
			try {
				crossimage = ImageIO.read(new File("Images/redcross.png"));
				crossimage= crossimage.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH ) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Graphics2D g = touse.createGraphics();
		    g.drawImage(im,0,0,null);
		    g.drawImage(crossimage,0,0,null);
		    g.dispose();
		    imageused=touse;
			
		}
		else {
			
			imageused= im;
		}
		JLabel picLabel = new JLabel(new ImageIcon(imageused));
		add(picLabel);
	
		picLabel.addMouseListener(new HeroMouseListener(viewher));
		
	}

	@Override
	public void defeated(boolean defeated) {
		// TODO Auto-generated method stub
		refreshHeroes();
	}

	private void refreshHeroes() {
		this.removeAll();
		// TODO Auto-generated method stub
		for(GameHero hero:theheroes) {
			refreshHeroImage(hero);
		}
		this.revalidate();
		this.repaint();
	}

	@Override
	public void TurnEnded() {
		// TODO Auto-generated method stub
		refreshHeroes();
	}

}
