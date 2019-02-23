package view.game;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class ActivationButton extends JButton {

	private ArrayList<ActionPerformListener> listeners=new ArrayList<ActionPerformListener>();
	
	public ActivationButton(String name) {
		super(name);
	}

	public void actionPerformed(ActionEvent arg0) {
		for(ActionPerformListener listen:listeners) {
			listen.actionPerformed(arg0);
			
		}
		
	}

	public void addActionPerformListener(ActionPerformListener listen){
		listeners.add(listen);
	}
}
