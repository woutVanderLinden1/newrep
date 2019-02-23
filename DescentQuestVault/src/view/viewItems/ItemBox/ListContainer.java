package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.UserInputController;
import controller.commands.ICommand;
import controller.commands.select.SelectCommand;
import frame.SubContainer;
import model.Item;
import model.ItemController;
import model.Monster.Monster;
import model.Monster.Zombie;
import model.Tile.tilesets.*;
import model.Tile.tilesets.coreset.*;
import model.Tile.tilesets.coresetOutDoor.*;
import model.Tile.tilesets.coreset.Tile10B;
import model.Tile.tilesets.coreset.Tile11B;
import model.Tile.tilesets.coreset.Tile12B;
import model.Tile.tilesets.coreset.Tile13B;
import model.Tile.tilesets.coreset.Tile14B;
import model.Tile.tilesets.coreset.Tile15B;
import model.Tile.tilesets.coreset.Tile16B;
import model.Tile.tilesets.coreset.Tile17B;
import model.Tile.tilesets.coreset.Tile18B;
import model.Tile.tilesets.coreset.Tile19B;
import model.Tile.tilesets.coreset.Tile1B;
import model.Tile.tilesets.coreset.Tile20B;
import model.Tile.tilesets.coreset.Tile21B;
import model.Tile.tilesets.coreset.Tile22B;
import model.Tile.tilesets.coreset.Tile23B;
import model.Tile.tilesets.coreset.Tile24B;
import model.Tile.tilesets.coreset.Tile25B;
import model.Tile.tilesets.coreset.Tile26B;
import model.Tile.tilesets.coreset.Tile27B;
import model.Tile.tilesets.coreset.Tile28B;
import model.Tile.tilesets.coreset.Tile29B;
import model.Tile.tilesets.coreset.Tile2B;
import model.Tile.tilesets.coreset.Tile30B;
import model.Tile.tilesets.coreset.Tile3B;
import model.Tile.tilesets.coreset.Tile4B;
import model.Tile.tilesets.coreset.Tile5B;
import model.Tile.tilesets.coreset.Tile6B;
import model.Tile.tilesets.coreset.Tile7B;
import model.Tile.tilesets.coreset.Tile8B;
import model.Tile.tilesets.coreset.Tile9B;
import model.door.Door;
import model.door.NormalDoor;
import model.generators.Generator;
import model.search.BasicToken;
import model.search.SearchToken;
import model.values.CustomBoolean;
import model.values.CustomValue;
import model.values.ValueItem;
import view.Items.Map.ItemFactory;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;

public class ListContainer extends SubContainer  implements Serializable,AvailabilityChangeListener{

	private ArrayList <ImageItem> itemList=new ArrayList<ImageItem>();
	private int defaultSize= 100;
	private JComboBox box;
	private static ArrayList<Item> availableDoors=new ArrayList<Item>(Arrays.asList(new NormalDoor()));
	private static ArrayList<Item> availableTokens=new ArrayList<Item>(Arrays.asList(new SearchToken()));
	private static ArrayList<Item> availableMonsters=new ArrayList<Item>(Arrays.asList(new Zombie()));
	private ItemController itemcontrol=ItemController.getItemController();
	private ArrayList<ItemButton> currentbuttons=new ArrayList<ItemButton>();
	private HashMap<ItemOptions,ItemButton> buttonmap=new HashMap<ItemOptions,ItemButton>();
	//private JScrollPane scrollPane;
	//private SubContainer pan;
	
	public ListContainer(int width, int height) {
		super(width, height);
		System.out.println(width+" ,"+height);
		// TODO Auto-generated constructor stub
		this.setBackground(Color.black);

//		pan.setPreferredSize(new Dimension(width,height));
//		scrollPane=new JScrollPane(pan,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//	        	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//	        scrollPane.setPreferredSize(new Dimension(width-20,height-5));
//	        scrollPane.setSize(400,50);
//	        //pan.setLayout(new GridLayout(1,4));
//		     pan.setLayout(null);
//	        pan.setBackground(Color.black);
//	        this.setBackground(Color.red);
//	        scrollPane.setLocation(0,0);
//	        this.add(scrollPane); 
//	        this.revalidate();
//	        this.repaint();
//	        */
//	        this.add(pan);
	}

	
	public void getCurrentList() {
		
	}
	public void addItems(JComboBox box) {
	//	this.removeAll();

		for(int i=0;i<this.getComponents().length;i++) {
			//this.remove
		
			this.remove(this.getComponents()[i]);
			i--;
		}
	
		this.revalidate();
		this.repaint();
		
		clearButtons();
		switch((ItemOptions) box.getSelectedItem()) {
		case Door:
			
			addButtons(availableDoors);
			break;
			//addButtons("Images/Door");
		
		case Event:
			//addButtons("Images/Event");
			break;
		case Monster:
			addButtons(availableMonsters);
			//addButtons("Images/Monster");
			break;
		case Square:
			//addButtons("Images/Square");
			break;
		case Text:
			//addButtons("Images/Text");
			break;
			
		case CoreSetOutdoor:
			addButtons(itemcontrol.getCoresetOutDoorTiles());
			break;
		case CoreSetIndoor:
			
			addButtons(itemcontrol.getCoresetTiles());
			break;
		case Essentials:
			
			addButtons(itemcontrol.getEssentialTiles());
			break;
		case Token:
			addButtons(availableTokens);
			//addButtons("Images/Token");
			break;
		case Value:
			addButtons(itemcontrol.getValues());
			//addButtons("Images/Token");
			break;
		case Generator:
			addButtons(itemcontrol.getGenerators());
		default:
			break;
		
		}
		
		
	}

	private void clearButtons() {
		// TODO Auto-generated method stub
		this.removeAll();
		currentbuttons.clear();
	}

	private void addButtons(ArrayList<Item> available) {
		System.out.println("files added");
		itemList.clear();
		//ArrayList<String> nameList=new ArrayList<String>();
		/*
		 try (Stream<Path> paths = Files.walk(Paths.get(availableTiles2))) {
		      paths.forEach(a->{
		    	  System.out.println(a.toString());
			      if((a.toString().endsWith("png"))) {
			    	   itemList.add(new ImageItem(a));
			    	   System.out.println(a);
			      }
		      });
		   
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		 */
			for(Item tile:available) {
				if(tile.isAvailable()) {
					tile.addAvailabilityChangeListener(this);
					ImageItem item=null;
					switch(tile.getItemKind()) {
					case Door:
						item=new DoorItem((Door) tile);
						break;
					case Tile:
						item =new TileItem((OrginalTile) tile);
						break;
					case Token:
						item=new TokenItem((BasicToken) tile);
						break;
					case Monster:
						item=new MonsterItem((Monster) tile);
						break;
					case Value:
						item=ItemFactory.createItem((CustomValue)tile);
						break;
					case Generator:
						item=ItemFactory.createItem((Generator)tile);
						break;
					default:
						break;
					
					}
				
					itemList.add(item);
				}
			}
		//add buttons to the panel
		 this.setLayout(new GridLayout(1,itemList.size()));
		 this.setPreferredSize(new Dimension(defaultSize*itemList.size(),this.getHeight()));
		for(ImageItem item:itemList) {
				this.createButton(item);
				
				
			
			/*
			 scrollPane=new JScrollPane(pan,JScrollPane.VERTICAL_SCROLLBAR_NEVER,
		        	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		        scrollPane.setPreferredSize(new Dimension(getWidth()-20,getHeight()-5));
		       
			scrollPane.revalidate();
			scrollPane.repaint();
			*/
		}
	}

	private void addTiles() {
		System.out.println("files added");
		ArrayList<String> nameList=new ArrayList<String>();
		/*
		 try (Stream<Path> paths = Files.walk(Paths.get("Images/Tiles"))) {
		      paths.forEach(a->{
		    	  System.out.println(a.toString());
			      if((a.toString().endsWith("png"))) {
			    	   itemList.add(new ImageItem(a));
			    	   System.out.println(a);
			      }
		      });
		   
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    */
		/*
		for(OrginalTile tile:availableTiles) {
			TileItem item=new TileItem(tile);
			itemList.add(item);
		}
		*/
		//add buttons to the panel
		 for(ImageItem item:itemList) {
			JButton butt=new JButton(item.getIDName());
			butt.setPreferredSize(new Dimension(defaultSize,this.getHeight()-10));
		    Image img;
		
				//if there is an image set a button with the icon else no button
				System.out.println(item.getImage().toString());
				img = item.getImage();
				butt.setIcon(new ImageIcon(img));
				
				System.out.println("added button");
			   
			
			
		}
		
		
	}

	public void createButton(ImageItem item) {
		if(item.isAvailable()){
			
			ItemButton butt=new ItemButton(item,item.getIDName());
			butt.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					ICommand com=null;
					switch(item.getKind()) {
					case MONSTER:
						 com=new SelectCommand(item.clone());
					case EVENT:
						break;
					case TILEITEM:
						 com=new SelectCommand(item.clone());
						break;
					case VIEWTILE:
						break;
					case DOOR:
						com=new SelectCommand(item.clone());
						break;
					case TOKEN:
						com=new SelectCommand(item.clone());
						break;
					case VALUE:
						com=new SelectCommand(item);
						break;
					case GENERATOR:
						com=new SelectCommand(item);
						break;
					default:
						break;
					
					}
					
					UserInputController.getController().performCommand(com);
				}
			}
			);
			
			butt.setPreferredSize(new Dimension(defaultSize,this.getHeight()-10));
		    Image img;
			
				//if there is an image set a button with the icon else no button
				System.out.println(item.getImage().toString());
				img = item.getImage();
				// Image newimg = img.getScaledInstance( (int)(70*(item.getScaleWidth())),(int)( 70*(item.getScaleHeight())),  java.awt.Image.SCALE_SMOOTH ) ;
				double max=  Math.max(item.getScaleWidth(), item.getScaleHeight());
				System.out.println("maxsearched "+max);
				Image newimg=null;
				if(max!=0) {
					double factor= 70/max;
					newimg = img.getScaledInstance( (int)(factor*(item.getScaleWidth())),(int)( factor*(item.getScaleHeight())),  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				else {
					newimg = img.getScaledInstance(70,70,  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				
				butt.setIcon(new ImageIcon(newimg));
				butt.setVerticalTextPosition(SwingConstants.BOTTOM);
				butt.setHorizontalTextPosition(SwingConstants.CENTER);
				this.add(butt);
				currentbuttons.add(butt);
				//this.add(butt);
				System.out.println("added button");
		}
	}
	
	@Override
	public void depleted(Item item) {
		System.out.println("depeltion happens");
		for(int i=0;i<currentbuttons.size();i++) {
			ItemButton butt=currentbuttons.get(i);
			if(butt.getItem().getItem()==item) {
				System.out.println(" found yas");
				currentbuttons.remove(butt);
				this.remove(butt);
				i--;
			}
			else {
				
			}
		}
		this.revalidate();
		this.repaint();
		
	}

	@Override
	public void added(Item item) {
		//only add if in current list
		for(int i=0;i<itemList.size();i++) {
			ImageItem butt=itemList.get(i);
			if(butt.getItem()==item) {
				this.createButton(butt);
				
			}
		}
		this.revalidate();
		this.repaint();
		// TODO Auto-generated method stub
		
	}


}
