package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.event.extraevents.TextOption;
import view.game.mappanel.GameMapPanel;

public class TextMapPanel extends GameMapPanel {

	public TextMapPanel(Dimension defaultSize) {
		super(defaultSize);
		// TODO Auto-generated constructor stub
	}



	public void showTextDialog(String text) {
		mapPanels.moveToBack(temporaryPanel);
		// TODO Auto-generated method stub
		//grid.showTextDialog(text);
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		JTextArea area=new JTextArea();
		area.setText(text);
		area.setFont(new Font("descentquestbuilderfont", Font.PLAIN, 40));
		textPanel.add(area);
		area.setBackground(new Color(246,221,199));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),400);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(246,221,199));
		this.revalidate();
		this.repaint();
	}


	public void showTextDialog(String text, ArrayList<TextOption> newoptions) {
		System.out.println(text);
		mapPanels.moveToBack(temporaryPanel);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//grid.showTextDialog(text);
		textPanel.removeAll();
	
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		JTextPane area=new JTextPane();
	
		area.setBackground(new Color(246,221,199));
		area.setText(text);
		 SimpleAttributeSet center = new SimpleAttributeSet();
	        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	        StyledDocument doc = area.getStyledDocument();
	        doc.setParagraphAttributes(0, doc.getLength(), center, false);
	   
	    	area.getCaret().setVisible(false); 
		
		
	
		
		area.setFont(new Font("descentquestbuilderfont", Font.PLAIN, 20));
		textPanel.add(area);
		area.setBackground(new Color(246,221,199));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),600);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(246,221,199));
		JPanel buttonpanel=new JPanel();
		textPanel.add(buttonpanel);
		buttonpanel.setSize(Math.max(mapPanels.getWidth()-500,400),50);
		buttonpanel.setLocation(50, 350);
		buttonpanel.setBackground(new Color(246,221,199));
	
		for(TextOption option:newoptions) {
			JButton optionbutton=new JButton(option.getName());
			
			optionbutton.setSize(300,200);
			optionbutton.setBackground(new Color(222,194,145));
			optionbutton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					closeText();
					option.trigger();
					option.perform();
					
				}

			
				
			});
			buttonpanel.add(optionbutton);
		}
		textPanel.revalidate();
		textPanel.repaint();
		textPanel.setBackground(Color.gray);
		textPanel.requestFocus();
		this.revalidate();
		this.repaint();
	}
}
