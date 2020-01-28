package Shop.ItemShop;

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
import ItemEditor.SavedItem;
import frame.MainFrame;
import frame.SubContainer;
import misc.BaseFile;
import misc.CampaignFile;
import misc.Tools;
import model.ItemController;
import view.game.mappanel.SmallTextPanel;

public class ItemShop extends JFrame{
	
	private ShopPanel<SavedItem> shoppanel;
	private SellPanel<SavedItem> sellpanel;
	private BaseFile file;
	private MainFrame frame;
	private SmallTextPanel pane;
	

	public ItemShop(Dimension defaultSize, MainFrame frame) {
		defaultSize.setSize(1000, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2-600, dim.height/2-this.getSize().height/2-400);
		this.setSize(defaultSize);
		ItemController control=ItemController.getItemController();
		file=control.getCampaignFile().getBaseFile();
		this.frame=frame;
		int w=defaultSize.width;
		int h=defaultSize.height/2-40;
		this.file=file;
		shoppanel=this.initialiseShopPanel(file,600,150);
		sellpanel=this.initialiseSellPanel(file,600,150);
		
		
		pane=new SmallTextPanel("Gold ", file.getMoney(),file.getMoneyValue(), Color.yellow);
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
		
		
	}


	private SellPanel<SavedItem> initialiseSellPanel(BaseFile file2, int w, int h) {
		
		
		SellPanel<SavedItem> panel=new SellPanel<SavedItem>("Inventory",new Dimension(w,h),file2.getEquipment(),new ActionTaker<SavedItem>() {
	
			@Override
			public void perform(SavedItem value) {
					 JOptionPane optionPane = new JOptionPane(
			                "Do you want to sell"+value.getName()+ "\n"
			                + "You will receive "+value.getGoldprice()/2+ "gold \n"+
			                		"this cannot be undone."
			                ,
			                JOptionPane.QUESTION_MESSAGE,
			                JOptionPane.YES_NO_OPTION);
	
					JDialog dialog = new JDialog(frame, 
			                             "Sell a item",
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
			dialog.setVisible(true);
	
			int value2 = ((Integer)optionPane.getValue()).intValue();
			if (value2 == JOptionPane.YES_OPTION) {
				file2.removeItem(value);
				file2.setMoney(file2.getMoney()+value.getGoldprice()/2);
				initializePanels();
			} else if (value2 == JOptionPane.NO_OPTION) {
				initializePanels();
			}
					
					
				}
			
		});
		return panel;
	}


	private ShopPanel<SavedItem> initialiseShopPanel(BaseFile file2, int w, int h) {
		ArrayList<SavedItem> items=generateItems(file2.getFame());
		ShopPanel<SavedItem> panel=new ShopPanel<SavedItem>("Shop",new Dimension(w,h),items,new ActionTaker<SavedItem>() {

			@Override
			public void perform(SavedItem value) {
					 JOptionPane optionPane = new JOptionPane(
			                "Do you want to buy"+value.getName()+ "\n"
			                + "This costs "+value.getGoldprice()+ "gold \n"+
			                		"this cannot be undone."
			                ,
			                JOptionPane.QUESTION_MESSAGE,
			                JOptionPane.YES_NO_OPTION);
	
					JDialog dialog = new JDialog(frame, 
			                             "Buy a item",
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
			dialog.setVisible(true);
	
			int value2 = ((Integer)optionPane.getValue()).intValue();
			if (value2 == JOptionPane.YES_OPTION) {
				if(file2.getMoney()<value.getGoldprice()) {
					JOptionPane.showMessageDialog(frame, "You don't have enough gold\n Are you trying to ruin me?");
				}
				else {
					items.remove(value);
					file2.addItem(value);
					file2.setMoney(file2.getMoney()-value.getGoldprice());
					
				}
				initializePanels();
				
				
			} else if (value2 == JOptionPane.NO_OPTION) {
				initializePanels();
			}
					
					
				}
			
		});
		
		return panel;
	}


	private ArrayList<SavedItem> generateItems(int fame) {
		ArrayList<SavedItem> shownitems=new ArrayList<SavedItem>();
		int added=0;
	String directoryPath= System.getProperty("user.dir")+"/Items";
	File dir = new File(directoryPath);
	  File[] directoryListing = dir.listFiles();
	  if (directoryListing != null) {
	    for (File child : directoryListing) {
	      Optional<String> answer=this.getExtensionByStringHandling(child.getName());
	      System.out.println();
	      if(answer.get().equals("ser")){
	    	  added++;
	    	  SavedItem i=null;
				 FileInputStream fileIn;
				try {
					fileIn = new FileInputStream(child);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					i = (SavedItem) in.readObject();
				    in.close();
				    fileIn.close();
				    System.out.println("the read file is " +i);
				    if(i.isShopavailable() && i.getMinfame()>fame&&(i.getMaxfame()>fame||i.getMaxfame()==0)){
				        if(!file.getEquipment().contains(i)) {
				        	shownitems.add(i);
				        }
				        if(!file.hasFullEquipment(i)) {
				        	
				        		shownitems.add(i);
				        	
				        }
				    	
				    }
				
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	      }
	    }
	  }
	  
		  ArrayList<SavedItem> returnedItems=new ArrayList<SavedItem>();
		  
		  while(returnedItems.size()<5&& returnedItems.size()<shownitems.size()) {
			 int l=Tools.getRandomInt(shownitems.size()); 
			 if(!returnedItems.contains(shownitems.get(l))) {
				 returnedItems.add(shownitems.get(l));
			 }
		  }
		  
	  
		return returnedItems;
	}
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	private void initializePanels() {
		// TODO Auto-generated method stub
		shoppanel.initializeShopPanel();
		sellpanel.initializeShopPanel();
	}
}
