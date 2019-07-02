package view.game.mappanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import model.ItemController;
import view.viewItems.ItemBox.ValueChangeListener;

public class HopePanel  extends JTextPane implements ValueChangeListener {


	public HopePanel() {
		this.setEditable(false);
		this.setCaretColor(Color.white);
		this.setForeground(Color.white);
		 Font font = new Font("Arial", Font.BOLD, 25);
	        this.setFont(font);
		ItemController control=ItemController.getItemController();
		control.addChangeValueListener(this);
		refreshHopeValue(control.getHope().getTheInteger());
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		refreshHopeValue(control.getHope().getTheInteger());
	}

	@Override
	public void valueChanged(int theInteger) {
		ItemController control=ItemController.getItemController();
		refreshHopeValue(control.getHope().getTheInteger());
		
	}

	private void refreshHopeValue(int theInteger) {
		this.setText("Hope: "+theInteger);
		this.setName("Hope: "+theInteger);
		
	}
	
}
