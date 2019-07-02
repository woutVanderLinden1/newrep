package view.game.mappanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import model.ItemController;
import view.viewItems.ItemBox.ValueChangeListener;

public class Goldpanel extends JTextPane implements ValueChangeListener {

	public Goldpanel() {
		this.setEditable(false);
		this.setCaretColor(Color.yellow);
		this.setForeground(Color.yellow);
		 Font font = new Font("Arial", Font.BOLD, 25);
	        this.setFont(font);
		ItemController control=ItemController.getItemController();
		control.addChangeValueListener(this);
		refreshGoldValue(control.getGold().getTheInteger());
		
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		refreshGoldValue(control.getGold().getTheInteger());
	}

	@Override
	public void valueChanged(int theInteger) {
		ItemController control=ItemController.getItemController();
		refreshGoldValue(control.getGold().getTheInteger());
		
	}

	private void refreshGoldValue(int theInteger) {
		this.setText("Gold: "+theInteger);
		this.setName("Gold: "+theInteger);
		
	}
}
