package view.viewItems;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JLabel;

import frame.SubContainer;

public class TitleBox extends SubContainer implements Serializable{
	private JLabel titleLabel;

	public TitleBox(String string, int width, int height) {
		super(width,height);
		this.setPreferredSize(new Dimension(width,height));
		titleLabel=new JLabel(string);
		this.setBackground(Color.GREEN);
		
		this.setSize(width,height);
		titleLabel.setSize(width,height);
		
		titleLabel.setFont(new Font("TimesRoman", Font.BOLD, (this.getHeight())-20));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleLabel);
	}
	

	

}
