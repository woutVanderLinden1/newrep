package view.game.mappanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import model.ItemController;
import view.hero.GameHero;
import view.viewItems.ItemBox.ValueChangeListener;

public class SmallTextPanel extends JTextPane implements ValueChangeListener {
	
	private String pretext;

	public SmallTextPanel(String pretext, int value, ValueChanger change,Color color) {
		this.setEditable(false);
		this.pretext=pretext;
		this.setCaretColor(color);
		this.setForeground(color);
		this.setBackground(Color.LIGHT_GRAY);
		 Font font = new Font("Arial", Font.BOLD, 25);
	        this.setFont(font);
	        change.addValueChangeListener(this);
		//ItemController control=ItemController.getItemController();
		//control.addChangeValueListener(this);
		//refreshGoldValue(control.getGold().getTheInteger());
		this.refreshValue(value);
	}


	@Override
	public void valueChanged(int theInteger) {
		ItemController control=ItemController.getItemController();
		refreshValue(control.getGold().getTheInteger());
		
	}

	private void refreshValue(int theInteger) {
		this.setText(pretext+": "+theInteger);
		this.setName(pretext+": "+theInteger);
		this.revalidate();
		this.repaint();
	}


	@Override
	public void trigger() {
		
		
	}
}
