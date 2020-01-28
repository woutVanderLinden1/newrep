package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.UserInputController;
import controller.commands.LoadCommand;
import controller.commands.SaveGameCommand;
import controller.commands.TestGameCommand;
import controller.commands.select.RotateSelectedCommand;
import frame.SubContainer;
import misc.listeners.ItemRemoveListener;
import misc.listeners.MapItemPlaceListener;
import misc.listeners.RemoveTileListener;
import misc.listeners.TilePlaceListener;
import model.SelectedArea;
import view.events.EventBox;
import view.viewItems.DoorItem;
import view.viewItems.GridPanel;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;


public class MapPanel extends SubContainer implements Serializable {

	private MapOptionPanel optionPanel;
	private GridPanel mapGrid;
	
	
	public MapPanel(int width, int height, UserInputController userInput) {
		super(width, height);
		//this.setBackground(Color.GREEN);
	/*
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("Images//texture.jpg"));
			Image img= myPicture.getScaledInstance(width, height, 0);
			 BufferedImage bimage = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

			    // Draw the image on to the buffered image
			    Graphics2D bGr = bimage.createGraphics();
			    bGr.drawImage(img, 0, 0, null);
			    bGr.dispose();
			    myPicture=bimage;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	//	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		//picLabel.setPreferredSize(new Dimension(width,height));
	//	add(picLabel);
		JButton testButton=new JButton("test");
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestGameCommand comm=new TestGameCommand();
				UserInputController.getController().performCommand(comm);
			}
		});
		JButton saveButton=new JButton("save");
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveGameCommand comm=new SaveGameCommand(MapPanel.this);
				UserInputController.getController().performCommand(comm);
			}
		});
		JButton loadButton=new JButton("load");
		MapPanel map=this;
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadCommand comm=new LoadCommand(map);
				UserInputController.getController().performCommand(comm);
			}
		});
		
		
		JButton backToMenuButton=new JButton("to Main");
		
		backToMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenMainMenuCommand comm=new OpenMainMenuCommand();
				UserInputController.getController().performCommand(comm);
			}
		});
		JButton loadCampaignConstantsButton=new JButton("Load Campaign Constants");
		
		loadCampaignConstantsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadCampaignConstantCommand comm=new LoadCampaignConstantCommand(map);
				UserInputController.getController().performCommand(comm);
			}
		});
		JButton saveAsSubQuest=new JButton("make subquest");
		
		saveAsSubQuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveAsSubQuestCommand comm=new SaveAsSubQuestCommand(map);
				UserInputController.getController().performCommand(comm);
			}
		});
		optionPanel=new MapOptionPanel(width,100);
		mapGrid=new GridPanel(width,height-110,userInput);
		this.add(optionPanel);
		optionPanel.add(testButton);
		optionPanel.add(saveButton);
		optionPanel.add(loadButton);
		optionPanel.add(backToMenuButton);
		optionPanel.add(loadCampaignConstantsButton);
		optionPanel.add(saveAsSubQuest);
		this.add(mapGrid);
	//	gamePanel.setPreferredSize(new Dimension(width,height));
		
	//	this.add(gamePanel);
		//gamePanel.drawImage(background, 0, 0, null);
		
	}


	public ViewSquare getTileAt(int x, int y) {
		// TODO Auto-generated method stub
		return mapGrid.getSquareAt(x-mapGrid.getX(),y-mapGrid.getY());
	}


	public void addTile(TileItem tileName, ViewSquare square) {
		mapGrid.addTile(tileName,square);
		
	}


	public void changeTileColor(ViewSquare tile, Color color) {
		mapGrid.changeTileColor(tile,color);
		
	}


	public ArrayList<ViewSquare> changeShapeColor(ShapeItem selected, ViewSquare square, Color color) {
		// TODO Auto-generated method stub
		return mapGrid.changeShapeColor(selected,square,color);
	}


	public boolean isLegalOccupied(ViewSquare square, SelectAble selected) {
		// TODO Auto-generated method stub
		return mapGrid.isLegalOccupied(square,selected);
	}


	public boolean isConnected(ViewSquare square, TileItem selected) {
		// TODO Auto-generated method stub
		return mapGrid.isConnected(square,selected);
	}


	public ArrayList<ViewTileExit> getConnected(ViewSquare square,TileItem selected) {
		// TODO Auto-generated method stub
		return mapGrid.getConnected(square,selected);
	}


	public ArrayList<Connection> getConnections(ViewSquare square,TileItem selected) throws Exception {
		// TODO Auto-generated method stub
		return mapGrid.getConnections(square,selected);
	}


	public void fillExits() {
		// TODO Auto-generated method stub
		mapGrid.fillExits();
	}

	@Override
	public void sendEvent(MouseEvent e,Point p,SelectAble sel) {
		Point point= new Point(p.x-this.getX(),p.y-this.getY());
		
			optionPanel.sendEvent(e,point,sel);
		
	
			mapGrid.sendEvent(e,point,sel);
		
	}


	public void removeTile(ViewTile toselect) {
		mapGrid.removeTile(toselect);
	}


	public boolean containsSelected(SelectAble selected) {
		// TODO Auto-generated method stub
		return mapGrid.containsSelected(selected);
	}


	public boolean isRotatable(ViewTile selected) {
	
		return mapGrid.isTileRotatable(selected);
	}


	public void rotateSelected(SelectAble selected) {
		mapGrid.rotateSelected(selected);
		
	}


	public void addTilePlaceListener(TilePlaceListener eventBox) {
		// TODO Auto-generated method stub
		mapGrid.addTilePlaceListener(eventBox);
	}


	public void deleteSelected(SelectAble selected) {
		// TODO Auto-generated method stub
		mapGrid.deleteSelected(selected);
	}


	public ViewTile addViewTileToSquare(ViewTile tile, ViewSquare square) {
		return mapGrid.addTile(tile,square);
	}


	public void addTileRemoveListener(RemoveTileListener eventBox) {
		// TODO Auto-generated method stub
		mapGrid.addRemoveTileListener(eventBox);
	}


	public void addDoorToSquare(DoorItem door,ViewSquare square) {
		// TODO Auto-generated method stub
		mapGrid.addDoorToSquare(door,square);
	}


	public void addItemPlaceListener(SelectedArea getselectedArea) {
		mapGrid.addItemPlaceListener(getselectedArea);
		
	}


	public void removeItem(MapItem toselect) {
		// TODO Auto-generated method stub
		mapGrid.removeItem(toselect);
	}


	public ViewDoor addViewDoorToSquare(ViewDoor viewdoor, ViewSquare square) {
		// TODO Auto-generated method stub
		return mapGrid.addViewDoorToSquare(viewdoor,square);
	}


	public boolean isRotatable(ViewDoor selecteddoor) {
		// TODO Auto-generated method stub
		return mapGrid.isRotatable(selecteddoor);
	}


	public void addItemPlaceListener(MapItemPlaceListener listen) {
		mapGrid.addItemPlaceListener(listen);
		
	}


	public void addItemRemoveListener(ItemRemoveListener eventBox) {
		mapGrid.addItemRemoveListener(eventBox);
		
	}


	public void startDragItem(MapItem toselect) {
		// TODO Auto-generated method stub
		mapGrid.startDragItem(toselect);
	}


	public void startDragTile(ViewTile toselect) {
		// TODO Auto-generated method stub
		mapGrid.startDragTile(toselect);
	}


	public void addTokenToSquare(TokenItem token, ViewSquare square) {
		// TODO Auto-generated method stub
		mapGrid.addItemToSquare(token,square);
	}


	public void addTokenToSquare(ViewToken viewtoken, ViewSquare square) {
		// TODO Auto-generated method stub
		mapGrid.addViewItemToSquare(viewtoken,square);
	}


	public MapItem addItemToSquare(MonsterItem monster, ViewSquare square) {
		return mapGrid.addItemToSquare(monster,square);
		
	}


	public MapItem addViewItemToSquare(ViewMonster viewmonster, ViewSquare square) {
		return mapGrid.addViewItemToSquare(viewmonster,square);
		
	}


	public ArrayList<ViewSquare> getOccupiedSquares(ViewSquare square, ViewMonster themonster) {
		// TODO Auto-generated method stub
		return mapGrid.getOccupiedSquares(square,themonster);
	}


	public void makeInvisible(MapItem mapit) {
		// TODO Auto-generated method stub
		System.out.println(mapit);
		System.out.println(mapit.getKind());
		switch(mapit.getKind()) {

		case VIEWTILE:
			mapGrid.deleteTile((ViewTile) mapit);
			break;


		default:
			mapGrid.deleteItem(mapit);
			break;
		
		}
	
	}


	public void makeVisible(MapItem mapit) {
	
		
		// TODO Auto-generated method stub
		mapGrid.makeVisible(mapit);
	}




	
	
}
