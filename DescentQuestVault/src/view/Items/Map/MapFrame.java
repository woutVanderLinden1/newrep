package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ItemEditor.ActionTaker;
import misc.Tools;
import view.game.mappanel.FamePanel;
import view.game.mappanel.Goldpanel;
import view.game.mappanel.HopePanel;
import view.game.mappanel.PerilPanel;

public class MapFrame extends JFrame {
	private BufferedImage backgroundImage=null;
	private JPanel pan;
	private Map<MapLocation,MapButton> buttons=new HashMap<MapLocation,MapButton>();

	public MapFrame(ActionTaker<MapLocation> taker) {
		Dimension size=new Dimension(1021,823);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(size);
		this.setPreferredSize(size);
		try {
			 backgroundImage=(BufferedImage) ImageIO.read(new File("Images/descentmap.png"));
			 System.out.println(backgroundImage.getHeight());
			 System.out.println(backgroundImage.getWidth());
			backgroundImage=Tools.resize(1000,775,(BufferedImage) backgroundImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pan=new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
				 g.drawImage(backgroundImage, 0, 0, this);
				 super.paintComponent(g);
				 
			}
		};
		pan.setBackground(new Color(0,0,0,0));
		pan.setSize(new Dimension(size));
		pan.setPreferredSize(size);
		pan.setLayout(null);
		this.add(pan);
		generateButtons();
		for(MapButton button:buttons.values()) {
			pan.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					taker.perform(button.getMapLocation());
				}
				
			});
		}
	}
	
	public MapFrame(int currentamount,Set<MapLocation> keySet, ActionTaker<MapLocation> taker) {
		Dimension size=new Dimension(1021,823);
		Dimension framesize=new Dimension(1021,873);
		JPanel panel=new JPanel();
		panel.setSize(1021,400);
		JLabel label=new JLabel();
		label.setText("<html><div style='text-align: center;'>" + "Free Time Left:"+ currentamount+ "</div></html>");
		label.setName("Free Time Left:"+ currentamount);
		label.setSize(1021,50);
		label.setBackground(new Color(229,210,191));
		label.setFont(new Font("Arial", Font.BOLD, 40));
		panel.add(label);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(framesize);
		this.setPreferredSize(framesize);
		try {
			 backgroundImage=(BufferedImage) ImageIO.read(new File("Images/descentmap.png"));
			 System.out.println(backgroundImage.getHeight());
			 System.out.println(backgroundImage.getWidth());
			backgroundImage=Tools.resize(1000,775,(BufferedImage) backgroundImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pan=new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
				 g.drawImage(backgroundImage, 0, 0, this);
				 super.paintComponent(g);
				 
			}
		};
	
		pan.setBackground(new Color(0,0,0,0));
		pan.setSize(new Dimension(size));
		pan.setPreferredSize(size);
		pan.setLayout(null);
		this.setLayout(null);
		this.add(panel);
		
		Goldpanel goldpanel=new Goldpanel();
		//goldpanel.setBackground(Color.white);
		goldpanel.setSize(120,30);
		panel.add(goldpanel);
		//mapPanels.moveToFront(goldpanel);
		goldpanel.setBackground(new Color(246,221,199));
		goldpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//goldpanel.setLocation(45,mapPanels.getHeight()-140); 
		
		FamePanel famepanel=new FamePanel();
		//goldpanel.setBackground(Color.white);
		famepanel.setSize(120,30);
		panel.add(famepanel);
		//mapPanels.moveToFront(famepanel);
		famepanel.setBackground(new Color(246,221,199));
		famepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//famepanel.setLocation(170,mapPanels.getHeight()-140); 
		
		PerilPanel perilpanel=new PerilPanel();
		//goldpanel.setBackground(Color.white);
		perilpanel.setSize(120,30);
		panel.add(perilpanel);
		//mapPanels.moveToFront(perilpanel);
		perilpanel.setBackground(new Color(246,221,199));
		perilpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//perilpanel.setLocation(295,mapPanels.getHeight()-140);
		
		HopePanel hopepanel=new HopePanel();
		//goldpanel.setBackground(Color.white);
		hopepanel.setSize(120,30);
		panel.add(hopepanel);
		//mapPanels.moveToFront(hopepanel);
		hopepanel.setBackground(new Color(246,221,199));
		hopepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//hopepanel.setLocation(420,mapPanels.getHeight()-140);
		
		
		this.add(pan);
		panel.setLocation(50, 773);
		
	
		generateButtons();
		for(MapLocation lov:keySet) {
			MapButton button=buttons.get(lov);
			pan.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					taker.perform(button.getMapLocation());
				}
				
			});
		}
		MapLocation lov=MapLocation.CITY;
		MapButton button=buttons.get(lov);
		pan.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				taker.perform(button.getMapLocation());
			}
			
		});
	}

	private void generateButtons() {
		this.addButton(85, 62,  MapLocation.SOUTHWESTFOREST);
		this.addButton(225, 162, MapLocation.DAWNBLADERELIQUARY);
		this.addButton(55, 302, MapLocation.GUARDBARRACKS);
		this.addButton(345, 100,MapLocation.GOBLINSHACK);
		this.addButton(465, 85, MapLocation.COUNTRYCOTTAGE);
		this.addButton(637, 115,MapLocation.ELDERTREE);
		this.addButton(774, 70, MapLocation.WILDLANDS);
		this.addButton(846, 155, MapLocation.DEATHVOLCANO);
		this.addButton(355, 267, MapLocation.CITY);
		this.addButton(675, 262, MapLocation.CARDINALTOWER);
		this.addButton(285, 387, MapLocation.FISHERTOWN);
		this.addButton(595, 444, MapLocation.ABANDONEDMANSION);
		this.addButton(840, 426, MapLocation.RITUALSTONES);
		this.addButton(695, 490, MapLocation.BANDITLAIR);
		this.addButton(215, 524,MapLocation.FROZENSPIRE);
		this.addButton(475, 642, MapLocation.MONSTERDEN);
		this.addButton(632, 559, MapLocation.ELEMENTALISLAND);
		this.addButton(750, 622, MapLocation.TWINPEAK);
		
	}

	public void addButton(int i,int j, MapLocation loc) {
		MapButton button1=new MapButton("<html>" +loc.getString()+ "</html>",loc);
		button1.setLocation(i,j);
		button1.setPreferredSize(new Dimension(100,40));
		button1.setSize(new Dimension(100,40));
		buttons.put(loc, button1);
	}
	
	public static void main(String[] args) {
		HashSet<MapLocation>loc=new HashSet<MapLocation>();
		loc.add(MapLocation.BANDITLAIR);
		MapFrame map=new MapFrame(5,loc,null)  ;
		map.setVisible(true);
	}
}
