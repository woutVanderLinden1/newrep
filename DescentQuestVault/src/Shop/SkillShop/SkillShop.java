package Shop.SkillShop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import ItemEditor.ActionTaker;

import Shop.ItemShop.SellPanel;
import Shop.ItemShop.ShopPanel;
import SkillEditor.SavedSkill;
import frame.MainFrame;
import frame.SubContainer;
import misc.BaseFile;
import misc.CampaignFile;
import misc.Tools;
import model.Hero.Hero;
import view.game.mappanel.SmallTextPanel;

public class SkillShop extends JFrame{
	
	private ShopPanel<SavedSkill> shoppanel;
	private SellPanel<SavedSkill> sellpanel;
	private Hero hero;
	private MainFrame frame;
	private SmallTextPanel pane;
	

	public SkillShop(Dimension defaultSize,  MainFrame frame, Hero hero) {
		defaultSize.setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2-600, dim.height/2-this.getSize().height/2-400);
		this.setSize(defaultSize);
		this.frame=frame;
		int w=defaultSize.width;
		int h=defaultSize.height/2-40;
		this.hero=hero;
		System.out.println(w);
		shoppanel=this.initialiseShopPanel(hero,600,150);
		sellpanel=this.initialiseSellPanel(hero,w,150);
		
		pane=new SmallTextPanel("Experience", hero.getExp(), hero, Color.black);
		pane.setSize(new Dimension(200,150));
		pane.setMinimumSize(new Dimension(200,150));
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pane,shoppanel);
		splitPane1.setPreferredSize(new Dimension(w,h));
		splitPane1.setMinimumSize(new Dimension(w,h));
		splitPane1.setSize(w,h);
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPane1,sellpanel);
		splitPane0.setPreferredSize(new Dimension(800,800));
		splitPane0.setMinimumSize(new Dimension(800,800));
		splitPane0.setSize(800,800);
		this.add(splitPane0);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		     hero.removeListeners(pane);
		    }
		});
		this.initializePanels();
		
		
	}

	

	private SellPanel<SavedSkill> initialiseSellPanel(Hero file2, int w, int h) {
		SkillShop thisthing=this;
		
		SellPanel<SavedSkill> panel=new SellPanel<SavedSkill>("Learned",new Dimension(w,h),file2.getSkills(),new ActionTaker<SavedSkill>() {
	
			@Override
			public void perform(SavedSkill value) {
					 JOptionPane optionPane = new JOptionPane(
			                "Do you want to unlearn "+value.getName()+ "\n"
			                + "You will receive "+value.getSkillpoints()+ "skillpoints \n"+
			                		"Note this is not possible in the normal games."
			                ,
			                JOptionPane.QUESTION_MESSAGE,
			                JOptionPane.YES_NO_OPTION);
	
					JDialog dialog = new JDialog(thisthing, 
			                             "Unlearn skill",
			                             true);
					
					
			dialog.setContentPane(optionPane);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(
			    JDialog.DO_NOTHING_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	initializePanels();
			    }
			});
			optionPane.addPropertyChangeListener(
			    new PropertyChangeListener() {
			        public void propertyChange(PropertyChangeEvent e) {
			            String prop = e.getPropertyName();
	
			            if (dialog.isVisible() 
			             && (e.getSource() == optionPane)
			             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
			              
			                dialog.setVisible(false);
			            }
			        }
			    });
			dialog.pack();
			dialog.setVisible(true);
	
			int value2 = ((Integer)optionPane.getValue()).intValue();
			if (value2 == JOptionPane.YES_OPTION) {
				hero.removeSkill(value);
				file2.setExp(file2.getExp()+value.getSkillpoints());
				initializePanels();
			} else if (value2 == JOptionPane.NO_OPTION) {
				initializePanels();
			}
					
					
				}
			
		});
		return panel;
	}


	private ShopPanel<SavedSkill> initialiseShopPanel(Hero file2, int w, int h) {
		SkillShop thisthing=this;
		System.out.println("shop "+file2.getAvailableSkills());
		ShopPanel<SavedSkill> panel=new ShopPanel<SavedSkill>("Available",new Dimension(w,h),file2.getAvailableSkills(),new ActionTaker<SavedSkill>() {

			@Override
			public void perform(SavedSkill value) {
					 JOptionPane optionPane = new JOptionPane(
			                "Do you want to get the skill "+value.getName()+ "\n"
			                + "This costs "+value.getSkillpoints()+ " skillpoints \n"+
			                		"this cannot be undone."
			                ,
			                JOptionPane.QUESTION_MESSAGE,
			                JOptionPane.YES_NO_OPTION);
	
					JDialog dialog = new JDialog(thisthing, 
			                             "Get a skill",
			                             true);
			dialog.setContentPane(optionPane);
			dialog.setDefaultCloseOperation(
			    JDialog.DO_NOTHING_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	initializePanels();
			    }
			});
			optionPane.addPropertyChangeListener(
			    new PropertyChangeListener() {
			        public void propertyChange(PropertyChangeEvent e) {
			            String prop = e.getPropertyName();
	
			            if (dialog.isVisible() 
			             && (e.getSource() == optionPane)
			             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
			              
			                dialog.setVisible(false);
			            }
			        }
			    });
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
	
			int value2 = ((Integer)optionPane.getValue()).intValue();
			if (value2 == JOptionPane.YES_OPTION) {
				if(file2.getExp()<value.getSkillpoints()) {
					JOptionPane.showMessageDialog(frame, "You don't have enough exp\n Are you trying to cheat?");
				}
				else {
					file2.addSkill(value);
					file2.setExp(file2.getExp()-value.getSkillpoints());
					
				}
				initializePanels();
				
				
			} else if (value2 == JOptionPane.NO_OPTION) {
				initializePanels();
			}
					
					
				}
			
		});
		
		return panel;
	}


	private void initializePanels() {
		// TODO Auto-generated method stub
		shoppanel.initializeShopPanel();
		sellpanel.initializeShopPanel();
	}
}

