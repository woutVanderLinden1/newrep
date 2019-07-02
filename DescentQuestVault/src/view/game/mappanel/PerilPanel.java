package view.game.mappanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import model.ItemController;
import view.viewItems.ItemBox.ValueChangeListener;

public class PerilPanel  extends JTextPane implements ValueChangeListener{


	public PerilPanel() {
		this.setEditable(false);
		this.setCaretColor(Color.red);
		this.setForeground(Color.red);
		 Font font = new Font("Arial", Font.BOLD, 25);
	        this.setFont(font);
		ItemController control=ItemController.getItemController();
		control.addChangeValueListener(this);
		refreshPerilValue(control.getPeril().getTheInteger());
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		refreshPerilValue(control.getPeril().getTheInteger());
	}

	@Override
	public void valueChanged(int theInteger) {
		ItemController control=ItemController.getItemController();
		refreshPerilValue(control.getPeril().getTheInteger());
		
	}

	private void refreshPerilValue(int theInteger) {
		this.setText("Peril: "+theInteger);
		this.setName("Peril: "+theInteger);
		
	}
}
