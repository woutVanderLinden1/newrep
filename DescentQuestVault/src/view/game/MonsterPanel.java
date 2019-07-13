package view.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.hero.GameHero;
import view.viewItems.ItemBox.ImageItem;

public class MonsterPanel extends JPanel {

	private ArrayList<GameMonster> theMonsters;
	
	public MonsterPanel() {
		theMonsters=new ArrayList<GameMonster>();
		this.setBackground(new Color(200,0,0,95));
	}
	
	public void addMonster(GameMonster viewher) {
		// TODO Auto-generated method stub
		theMonsters.add(viewher);
		System.out.println("the monsterpanel does exist");
		System.out.println(viewher);
		ImageItem monsterit=viewher.getImageItem();
		System.out.println(viewher.getImageItem().getImage());
		refreshMonsterImage(viewher);
	}
	
	public void refreshMonsterImage(GameMonster monster){
		Image im;
		if(monster.isTurnended()) {
			System.out.println("displaying darkened image");
			im=monster.getPreciseImage(100,100);
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
			im=monster.getPreciseImage(100,100);
		}
		JLabel picLabel = new JLabel(new ImageIcon(im));
		add(picLabel);
		picLabel.addMouseListener(new MonsterMouseListener(monster));
	}

	public void removeMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		theMonsters.remove(toremove);
		refreshMonsterImages();
		
	}

	private void refreshMonsterImages() {
		this.removeAll();
		for(GameMonster mon:theMonsters) {
			this.refreshMonsterImage(mon);
		}
		this.revalidate();
		this.repaint();
	}
}
