package view.game;

import java.awt.Color;
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
		JLabel picLabel = new JLabel(new ImageIcon(monsterit.getScaleImage(400)));
		add(picLabel);
		picLabel.addMouseListener(new MonsterMouseListener(viewher));
	}
}
