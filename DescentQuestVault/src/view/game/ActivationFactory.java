package view.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Activation;

public class ActivationFactory {

	public static ActivationButton createActivationButton(Activation activation) {
		// TODO Auto-generated method stub
		ActivationButton butt=new ActivationButton(activation.getName());
		butt.setName(activation.getName());
		butt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				activation.trigger();
				butt.actionPerformed(arg0);
			}
			
		});
		butt.setBackground(new Color(222,194,145));
		return butt;
	}

}
