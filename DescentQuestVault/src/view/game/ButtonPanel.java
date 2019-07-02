package view.game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.game.mappanel.GameMapPanel;

public class ButtonPanel extends JPanel implements ActionPerformListener{

	ArrayList<JButton> butts=new ArrayList<JButton>();
	private GameMapPanel grid;

	public ButtonPanel(GameMapPanel gameMapPanel) {
		this.grid=gameMapPanel;
		this.setSize(new Dimension(200,50));
		this.setPreferredSize(new Dimension(200,50));
	}
	
	public void addButton(ActivationButton createActivationButton) {
		// TODO Auto-generated method stub
		System.out.println("added button");
		int height=this.getHeight();
		this.setSize(new Dimension(200,height+95));
		createActivationButton.setSize(new Dimension(180,50));
		createActivationButton.setPreferredSize(new Dimension(180,50));
		createActivationButton.setLocation(new Point(10,height-50));
		createActivationButton.addActionPerformListener(this);
		this.add(createActivationButton);
	}

	public void reset() {
		this.setSize(new Dimension(200,50));
		this.setPreferredSize(new Dimension(200,50));
		this.removeAll();
		
	}
	
	public void closeThis() {
		grid.moveTemporariesToBack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		closeThis();
	}
	
}
