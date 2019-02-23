package view.game;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.hero.GameHero;

public class HeroPanel extends JPanel {

	private ArrayList<GameHero> theheroes;
	
	public HeroPanel() {
		theheroes=new ArrayList<GameHero>();
		this.setBackground(new Color(0,0,0,0));
	}
	
	public void addHero(GameHero viewher) {
		// TODO Auto-generated method stub
		theheroes.add(viewher);
		System.out.println(viewher);
		System.out.println(viewher.getImage());
		JLabel picLabel = new JLabel(new ImageIcon(viewher.getScaleImage(50)));
		add(picLabel);
		picLabel.addMouseListener(new HeroMouseListener(viewher));
	}

}
