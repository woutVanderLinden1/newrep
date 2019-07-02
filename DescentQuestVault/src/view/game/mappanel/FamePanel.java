package view.game.mappanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import model.ItemController;
import view.viewItems.ItemBox.ValueChangeListener;

public class FamePanel extends JTextPane implements ValueChangeListener {

	public FamePanel() {
		this.setEditable(false);
		this.setCaretColor(Color.blue);
		this.setForeground(Color.blue);
		 Font font = new Font("Arial", Font.BOLD, 25);
	        this.setFont(font);
		ItemController control=ItemController.getItemController();
		control.addChangeValueListener(this);
		refreshGoldValue(control.getFame().getTheInteger());
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		refreshGoldValue(control.getFame().getTheInteger());
	}

	@Override
	public void valueChanged(int theInteger) {
		ItemController control=ItemController.getItemController();
		refreshGoldValue(control.getFame().getTheInteger());
		
	}

	private void refreshGoldValue(int theInteger) {
		this.setText("Fame: "+theInteger);
		this.setName("Fame: "+theInteger);
		
	}
}
