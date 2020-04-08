package view.viewItems;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import controller.UserInputController;
import frame.SubContainer;
import java.awt.Color;
import misc.Tools;
import misc.listeners.ItemRemoveListener;
import misc.listeners.MapItemPlaceListener;
import misc.listeners.RemoveTileListener;
import misc.listeners.TilePlaceListener;
import model.SelectedArea;
import model.Tile.Square;
import model.Tile.Tile;
import model.Tile.TileExit;
import model.Tile.tilesets.EndGapOutDoor;
import model.Tile.tilesets.EndGapTile;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;


public class GridPanel  extends SubContainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int zoom=1;
	public static int squareWidth=64;

	
	private ArrayList<TilePlaceListener> tilePlaceListeners=new ArrayList<TilePlaceListener>();
	private ArrayList<RemoveTileListener> removeListeners=new ArrayList<RemoveTileListener>();
	private ArrayList<MapItemPlaceListener> itemPlaceListeners=new ArrayList<MapItemPlaceListener>();
	private ArrayList<ItemRemoveListener> itemRemoveListeners=new ArrayList<ItemRemoveListener>();
	
	private ArrayList<ViewTileExit> availableExits=new ArrayList<ViewTileExit>();
	private ArrayList<Connection> connections=new ArrayList<Connection>();
	private int mapLength=100;
	private ViewSquare[][] map;
	private Image backgroundImage=null;
	//private JPane mainPanel;
	private JScrollPane scrollPane;
	private JPanel pan;
	
	private ArrayList<ViewTile> tiles;
	private ArrayList<ViewDoor> doors;
	private ArrayList<ViewToken> tokens=new ArrayList<ViewToken>();
	private ArrayList<ViewMonster> monsters=new ArrayList<ViewMonster>();
	
//	private 
	
	public GridPanel(int width, int h, UserInputController userInput) {
		super(width,h);
		tiles=new ArrayList<ViewTile>();
		doors=new ArrayList<ViewDoor>();
		
		this.setBackground(java.awt.Color.RED);
	
		//map=new Square[400][400];
		GridPanel thisthing=this;
	     pan=new SubContainer(squareWidth*mapLength,squareWidth*mapLength){
	    	 public void paintComponent(Graphics g) {
	    		    super.paintComponent(g);

	    		    // Draw the background image.
	    		    g.drawImage(backgroundImage, scrollPane.getViewport().getViewPosition().x, scrollPane.getViewport().getViewPosition().y, this);
	    		    for(ViewTile tile:tiles){
	    		    	
	    		    	tile.draw(g,this);
	    		    	if(tile.isSelected()) {
	    		    		tile.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    }
	    		    for(ViewDoor door:doors){
	    		    	door.draw(g,this);
	    		    	if(door.isSelected()) {
	    		    		door.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    }
	    		    for(ViewToken token:tokens){
	    		    	token.draw(g,this);
	    		    	if(token.isSelected()) {
	    		    		token.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    }
	    		    for(ViewMonster monster:monsters){
	    		    	monster.draw(g,this);
	    		    	if(monster.isSelected()) {
	    		    		monster.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    }
	    		  }
	    	 @Override
	    	 public void addMouseListener() {
	    		 GridMouseListener list=new GridMouseListener(thisthing);
	    		 this.addMouseListener(list);
	    		 this.addMouseMotionListener(list);
	    	 }
	    	 
	     };
	 

        
	     pan.setLayout(null);
	     //pan.setBackground(Color.white);
	     //pan.setPreferredSize(new Dimension(squareWidth*mapLength,squareWidth*mapLength));
	     map=new ViewSquare[mapLength][mapLength];
	    
		try {
			backgroundImage = ImageIO.read(new File("Images/texture1.jpg"));
			backgroundImage=Tools.resize(width,h,(BufferedImage) backgroundImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     JLabel picLabel = new JLabel(new ImageIcon(backgroundImage));
	     picLabel.setPreferredSize(pan.getSize());
	     picLabel.setLocation(0,0);
	    // pan.add(picLabel);
	     for(int i=0; i<map.length;i++){
				for(int j=0;j<map.length;j++){
					ViewSquare tile=new ViewSquare(0,0,64,64,j,i);
					tile.setLocation(j*64,i*64);
					tile.setPreferredSize(new Dimension(64,64));
					tile.setSize(64,64);
					pan.add(tile);
					map[i][j]=tile;
			}
	     }
	     map[0][0].getName();
	       // JFrame frame=new JFrame();
	     //   frame.setSize(400, 400);
	       // frame.add(pan);
		//frame.show();
		
		/*
		mainPanel=new JFXPanel();
		map= new Square[mapLength][mapLength];
		for(int i=0; i<map.length;i++){
			for(int j=0;j<map.length;j++){
				map[i][j]=new Square();
			}
		}
		
		GridPane grid = new GridPane();
		grid.getStyleClass().add("game-grid");

	        for(int i = 0; i < map.length; i++) {
	            ColumnConstraints column = new ColumnConstraints(squareWidth*zoom);
	            grid.getColumnConstraints().add(column);
	        }

	        for(int i = 0; i < map.length; i++) {
	            RowConstraints row = new RowConstraints(squareWidth*zoom);
	            grid.getRowConstraints().add(row);
	        }

	        for (int i = 0; i < map.length; i++) {
	            for (int j = 0; j < map.length; j++) {
	                Pane pane = new Pane();
	                pane.setOnMouseReleased(e -> {
	                    pane.getChildren().add(Anims.getAtoms(1));
	                });
	                pane.getStyleClass().add("game-grid-cell");
	                if (i == 0) {
	                    pane.getStyleClass().add("first-column");
	                }
	                if (j == 0) {
	                    pane.getStyleClass().add("first-row");
	                }
	                grid.add(pane, i, j);
	            }
	        }


	        Scene scene = new Scene(grid, width,h );
	        scene.getStylesheets().add("game.css");
	        mainPanel.setScene(scene);
	        //.show();
	         
	         */
	     //   this.add(mainPanel);
	         //pan.setScene(scene);
	        scrollPane=new JScrollPane(pan,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setPreferredSize(new Dimension(width-20,h-5));
	       // scrollPane.setContent(layout);
	       

	       this.add(scrollPane);
	      
	       
	       /*
	       // added part
	       
	       StackPane layout = new StackPane();
	       layout.getChildren().setAll(
	         new ImageView(backgroundImage)
	         //createKillButton()
	       
	    		   
	    		   );
	       scrollPane=new ScrollPane(pan,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        	    ScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setPreferredSize(new Dimension(width,h-5));
	        scrollPane.setContent(layout);
	        ¨*/
	       //added part end
	       
	     // pan.setScene(scene);
	       /*
	      Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                initFX(pan);
	            }

			
	       });
	       */
	}


    public static void print2D(int mat[][]) 
    { 
        // Loop through all rows 
        for (int[] row : mat) {
  
            // Loop through all columns of current row 
            for (int x : row) {
            	  System.out.print(x + " "); 
            }
            System.out.println();
        }
    } 


	public ViewSquare getSquareAt(int x, int y) {
		
		
		int x2=(int)Math.floor( x/squareWidth);
		int y2=(int) Math.floor(y/squareWidth);
		
		
		//should represent right one with the scroll
		if(x2>=0&&y2>=0) {
			if(x2< map.length&&y2<map.length) {
				return map[y2][x2];
			}
		}
		return null;
	}
	private void addItem(MapItem mp, ViewSquare square) {
		switch(mp.getKind()) {
		case VIEWDOOR:
			this.addViewDoorToSquare((ViewDoor) mp, square);
			break;
		}
		
	}

	public void addDoorToSquare(DoorItem door,ViewSquare square) {
		int [][] shape=door.getShape();
		DoorItem item=(DoorItem) door.getImageItem();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		ViewDoor viewdoor=new ViewDoor(door,baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
	
		//viewdoor.setBaseSquare(baseSquare);
		
	
		
		//availableExits.addAll(tileName.getExits());
		
		markItemTiles(xoff,xstartoff,yoff,ystartoff,shape,viewdoor);
		
		
		doors.add(viewdoor);
		//connect the exits of the tile
		//connectExits(tile);
		
		notifyItemPlaceListeners(viewdoor);
		//add door to doors 
		//marktiles
	}

	public ViewDoor addViewDoorToSquare(ViewDoor viewdoor, ViewSquare square) {
		int [][] shape=viewdoor.getShape();
		DoorItem item=(DoorItem) viewdoor.getImageItem();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		viewdoor.setSquare(baseSquare);
		
		viewdoor.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		
	
		
		//availableExits.addAll(tileName.getExits());
		
		markItemTiles(xoff,xstartoff,yoff,ystartoff,shape,viewdoor);
		
		
		doors.add(viewdoor);
		//connect the exits of the tile
		//connectExits(tile);
		
		//notifyItemPlaceListeners(viewdoor);
		//add door to doors 
		//marktiles
		return viewdoor;
	}



	private void notifyItemPlaceListeners(MapItem door) {
		for(MapItemPlaceListener listen:itemPlaceListeners) {
			listen.notify(door);
		}
		
	}


	public ViewTile addTile(ViewTile tileName, ViewSquare square) {
		int [][] shape=tileName.getShape();
		TileItem item=(TileItem) tileName.getImageItem();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		System.out.println("thebasesquare is "+xoff+" "+yoff);
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		
		ViewTile tile=tileName;
		tile.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		
		addExits(square,tile,xstartoff,ystartoff);
		
		//availableExits.addAll(tileName.getExits());
		
		markTiles(xoff,xstartoff,yoff,ystartoff,shape,tile);
		//tile.reduceAvailability();
		
		tiles.add(tile);
		//connect the exits of the tile
		connectExits(tile);
		
		//notifyTilePlaceListeners(tile);
		return tile;
	}
	public void addTile(TileItem tileName, ViewSquare square) {
		//tileName=tileName.clone();
		
		//System.out.println(tileName);
		int [][] shape=tileName.getShape();
		TileItem item=(TileItem) tileName.getImageItem();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		System.out.println("thebasesquare is "+xoff+" "+yoff);
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		
		ViewTile tile=tileName.getTile(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		
		
		addExits(square,tile,xstartoff,ystartoff);
		
		//availableExits.addAll(tileName.getExits());
		
		markTiles(xoff,xstartoff,yoff,ystartoff,shape,tile);
		
		tile.reduceAvailability();
		tiles.add(tile);
		//connect the exits of the tile
		connectExits(tile);
		
		notifyTilePlaceListeners(tile);
	}

	


	private void addExits(ViewSquare square, ViewTile tile, int xstartoff, int ystartoff) {
		ArrayList<ViewTileExit> exits=new ArrayList<ViewTileExit>();
		for(TileExit exit:tile.getTileItem().getExits()) {
			int location3x=(int)(square.getxID()+exit.getSquare3ShapeLocation().getX())-xstartoff;
			int location3y=(int)(square.getyID()+exit.getSquare3ShapeLocation().getY())-ystartoff;
			int location2x=(int)(square.getxID()+exit.getSquare2ShapeLocation().getX())-xstartoff;
			int location2y=(int)(square.getyID()+exit.getSquare2ShapeLocation().getY())-ystartoff;
			ViewSquare square2=map[location2y][location2x];
			ViewSquare square3=map[location3y][location3x];
			
			ViewTileExit newexit=new ViewTileExit(exit,square2,square3,tile);
			availableExits.add(newexit);
			exits.add(newexit);
		}
		tile.setViewExits(exits);
		
	}


	private void markTiles(int xoff, int xstartoff, int yoff, int ystartoff, int[][] shape, ViewTile tile) {
		ArrayList<ViewSquare> squares=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
		    	int toputy=i+yoff-ystartoff;
		        for(int j=0; j<shape[i].length; j++) {
		        
		      
		    		
		        	int toputx=j+xoff-xstartoff;
		        	System.out.println(xstartoff);
		        	if(shape[i][j]!=0) {
		        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
		        			System.out.println("tile placed at");
		        			System.out.println(toputx);
		        			System.out.println(toputy);
		        			
		        		
		        			ViewSquare toset=map[toputy][toputx];
			        		toset.setTile(tile.getTileItem());
			        		toset.setContainingTile(tile);
			        		toset.setOccupationnr(shape[i][j]);
			        		squares.add(toset);
		        		}
		        		
		        	}
		         
		        }
		    }
		tile.setOccupyingSquares(squares);
	}
	
	private void markItemTiles(int xoff, int xstartoff, int yoff, int ystartoff, int[][] shape, MapItem viewdoor) {
		ArrayList<ViewSquare> squares=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
		    	int toputy=i+yoff-ystartoff;
		        for(int j=0; j<shape[i].length; j++) {
		        
		      
		    		
		        	int toputx=j+xoff-xstartoff;
		        	System.out.println(xstartoff);
		        	if(shape[i][j]!=0) {
		        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
		        			System.out.println("tile placed at");
		        			System.out.println(toputx);
		        			System.out.println(toputy);
		        		
		        			ViewSquare toset=map[toputy][toputx];
			        		
			        		toset.addElement(viewdoor);
			        		squares.add(toset);
		        		}
		        		
		        	}
		         
		        }
		    }
		 viewdoor.setOccupyingSquares(squares);
	}

	private void unMarkTiles(int xoff, int xstartoff, int yoff, int ystartoff, int[][] shape, ViewTile tile) {
		 for(int i=0; i<shape.length; i++) {
		    	int toputy=i+yoff-ystartoff;
		        for(int j=0; j<shape[i].length; j++) {
		        
		      
		    		
		        	int toputx=j+xoff-xstartoff;
		        	System.out.println(xstartoff);
		        	if(shape[i][j]!=0) {
		        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
		        			System.out.println("tile placed at");
		        			System.out.println(toputx);
		        			System.out.println(toputy);
		        			
		        			ViewSquare toset=map[toputy][toputx];
		        			if(toset.getOccupationnr()!=0) {
		        				toset.removeTile();
				        		
		        			}
			        
		        		}
		        		
		        	}
		         
		        }
		    }
		
	}
	private void notifyRemoveTileListeners(ViewTile tile) {
		for(RemoveTileListener listen:removeListeners) {
			listen.TileRemoved(tile);
		}
		
	}



	private void notifyTilePlaceListeners(ViewTile tile) {
		System.out.println("tiilelisteners notified");
		for(TilePlaceListener listen:tilePlaceListeners) {
			listen.tilePlaced(tile);
		}
		
	}





	private void connectExits(ViewTile tile) {
		for(ViewTileExit exit:tile.getViewExits()) {
			//
			ViewTileExit exit2=getConnectedExit(exit);
			if(exit2!=null) {
				Connection connect=new Connection(exit2,exit);
				connect(connect);
			}
		}
		
	}



	private ViewTileExit getConnectedExit(ViewTileExit exit) {
		for(ViewTileExit ex:availableExits) {
			if(exit.isConnectedWith(ex)) {
				return ex;
			}
		}
		
		return null;
	}



	private void connect(Connection connect) {
		availableExits.remove(connect.getExit1());
		availableExits.remove(connect.getExit2());
		connections.add(connect);
		connect.connect();
	}



	public void changeTileColor(ViewSquare tile, Color color) {
		//System.out.println("changing color");
		if(tile==null) {
			return;
		}
		tile.setColor(color);	
	}



	public ArrayList<ViewSquare> changeShapeColor(ShapeItem selected, ViewSquare square, Color color) {
		if(square==null) {
			return new ArrayList<ViewSquare>();
		}
		int[][] shape=selected.getShape();
		ArrayList<ViewSquare> toreturn=new ArrayList<ViewSquare>();
		int xoff=square.getxID();
		int yoff=square.getyID();
	
	
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
	    for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length-1) {
	        			ViewSquare toset=map[toputy][toputx];
		        		toset.setColor(color);
		        		toreturn.add(toset);
	        		}
	        		
	        	}
	         
	        }
	    }
		return toreturn;
	}



	
	public boolean isLegalOccupied(ViewSquare square, ShapeItem selected) {
		switch(selected.getKind()) {
		case DOOR:
			break;
		case EVENT:
			break;
		case TILEITEM:

			return isNotBlocked(square,(TileItem) selected)&&isNotInOther(square,(TileItem)selected);
		case VIEWTILE:
			
			break;
		case VIEWDOOR:
			
			break;
		default:
			break;
		
		}
		return false;
		
	}
	private boolean isNotBlocked(ViewSquare square,TileItem selected) {
		// TODO Auto-generated method stub
	
		if(square==null) {
		
			return false;
		}
		ArrayList<TileExit> exits=selected.getExits();
		
		
		int[][] shape=selected.getShape();
	
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    
    	int x=square.getxID()-xstartoff;
    	int y=square.getyID()-ystartoff;
		
    	for(TileExit exit:exits) {
    	
			if(!isConnected(x,y,square,exit)) {
				int location3x=(int)(x+exit.getSquare3ShapeLocation().getX());
				int location3y=(int)(y+exit.getSquare3ShapeLocation().getY());
				int location2x=(int)(x+exit.getSquare2ShapeLocation().getX());
				int location2y=(int)(y+exit.getSquare2ShapeLocation().getY());
				if(!(location2x>=0&&location2x<map[0].length)) {
					
					return false;
				}
				if(!(location2y>=0&&location2y<map.length)) {
				
					return false;
				}
				if(!(location3x>=0&&location3x<map[0].length)) {
					
					return false;
				}
				if(!(location3y>=0&&location3y<map.length)) {
					
					return false;
				}
				ViewSquare square2=map[location2y][location2x];
				ViewSquare square3=map[location3y][location3x];
				//is connected with another created viewtileexit
				ViewTileExit test=new ViewTileExit(exit, square2, square3,null);
				
				for(Point point:test.getNexts()) {
					
					if(point.getX()>=0&&point.getY()>=0) {
						if(point.getX()<map[0].length&&point.getY()<map.length) {
							if(map[(int)point.getY()][(int)point.getX()].getOccupationnr()!=0) {
								
								return false;
							}
						}
						else {
							
							return false;
						}
					}
					else {
						
						return false;
					}	
				}			
			}
    	}
		
		//other exits shouldn't be blocked by this panel
		for(ViewTileExit avexit:availableExits) {
				boolean connected1=false;
				for(Point point:avexit.getNexts()) {
					if(selected.containsPoint(point,x,y)) {
						if(!connected1) {
							if(selected.getPointValue(point,x,y)==4||selected.getPointValue(point,x,y)==3||selected.getPointValue(point,x,y)==2) {
								connected1=true;
								
							}
							else {
								System.out.println(selected.getPointValue(point, x, y));
								System.out.println(point);
								return false;
							}
						}
						else {
							if(selected.getPointValue(point,x,y)==4||selected.getPointValue(point,x,y)==3||selected.getPointValue(point,x,y)==2) {
								//do nothing they are connected
								connected1=false;
							}
							else {
								
								return false;
							}
						}
						
					}
				}
				if(connected1) {
					System.out.println("4");
					return false;
				}
			}
			
		
    	System.out.println("returned true");
		return true;
	}



	public boolean isNotInOther(ViewSquare square, TileItem selected) {
		int[][] shape=selected.getShape();
		if(square==null) {
			return false;
		}
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean toreturn=true;
		int xstartoff=0;
		int ystartoff=0;
    	while(shape[ystartoff][xstartoff]==0) {
    		xstartoff++;
    		if(xstartoff>=shape[ystartoff].length) {
    			ystartoff++;
    			xstartoff=0;
    		}
    	}
    	
	    for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	if(shape[i][j]!=0) {
	        		if(!(toputx>=0&&toputx<map.length&&toputy<map[0].length)) {
	        			return false;
	        		}
	        		else {
	        			if(map[toputy][toputx].getOccupationnr()!=0) {
	        				return false;	
	        			}
	        		}
	        		
	        	}
	         
	        }
	    }
	    
	    
	    /*
		for(TileExit exit:(ArrayList<TileExit>) selected.getExits()) {
			int xLookup3=(int)(xlocation+exit.getSquare3ShapeLocation().getX());
			int yLookup3=(int)(ylocation+exit.getSquare3ShapeLocation().getY());
			int xLookup2=(int)(xlocation+exit.getSquare2ShapeLocation().getX());
			int yLookup2=(int)(ylocation+exit.getSquare2ShapeLocation().getY());
		}
		*/
		return toreturn;
		
	}



	



	public boolean isConnected(ViewSquare square, TileItem selected) {
		TileItem newItem=(TileItem) selected.getImageItem();
		ArrayList<TileExit> exits=newItem.getExits();
		System.out.println("showing exits"+ exits);
	
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
		int[][] shape=selected.getShape();
    	
    	int xtotal=square.getxID()-xstartoff;
    	int ytotal=square.getyID()-ystartoff;
		
    	for(TileExit exit:exits) {
			
			if(isConnected(xtotal,ytotal,square,exit)) {
				return true;
			}
		}
		return false;
	}



	private boolean isConnected(int x, int y,ViewSquare square,TileExit exit) {
		

		

		int location3x=(int)(x+exit.getSquare3ShapeLocation().getX());
		int location3y=(int)(y+exit.getSquare3ShapeLocation().getY());
		int location2x=(int)(x+exit.getSquare2ShapeLocation().getX());
		int location2y=(int)(y+exit.getSquare2ShapeLocation().getY());

		if(location2x>=0&&location2x<map[0].length) {
			if(location2y>=0&&location2y<map.length) {
				if(location3x>=0&&location3x<map[0].length) {
					if(location3y>=0&&location3y<map.length) {
						ViewSquare square2=map[location2y][location2x];
						ViewSquare square3=map[location3y][location3x];
						ViewTileExit test=new ViewTileExit(exit, square2, square3,null);
						for(ViewTileExit avexit:availableExits) {
								
								//is connected with another created viewtileexit
							if(test.isConnectedWith(avexit)) {
								return true;
							}
							
						}
					}
				}
			}
		}
			
		
		
		
		
		return false;
	}


private ArrayList<ViewTileExit> getConnected(int x, int y,ViewSquare square,TileExit exit) {
		

	ArrayList<ViewTileExit> toreturn=new ArrayList<ViewTileExit>();

		int location3x=(int)(x+exit.getSquare3ShapeLocation().getX());
		int location3y=(int)(y+exit.getSquare3ShapeLocation().getY());
		int location2x=(int)(x+exit.getSquare2ShapeLocation().getX());
		int location2y=(int)(y+exit.getSquare2ShapeLocation().getY());

		if(location2x>=0&&location2x<map[0].length) {
			if(location2y>=0&&location2y<map.length) {
				if(location3x>=0&&location3x<map[0].length) {
					if(location3y>=0&&location3y<map.length) {
						ViewSquare square2=map[location2y][location2x];
						ViewSquare square3=map[location3y][location3x];
						ViewTileExit test=new ViewTileExit(exit, square2, square3,null);
						for(ViewTileExit avexit:availableExits) {
								
								//is connected with another created viewtileexit
							if(test.isConnectedWith(avexit)) {
								toreturn.add(avexit);
							}
							
						}
					}
				}
			}
		}
			
		
		
		
		
		return toreturn;
	}


	public ArrayList<ViewTileExit> getConnected(ViewSquare square, TileItem selected) {
		ArrayList<ViewTileExit> toreturn=new ArrayList<ViewTileExit>();
		TileItem newItem=(TileItem) selected.getImageItem();
		ArrayList<TileExit> exits=newItem.getExits();
		
		System.out.println("showing exits"+ exits);
		
		int[][] shape=selected.getShape();
	
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	int xtotal=square.getxID()-xstartoff;
    	int ytotal=square.getyID()-ystartoff;
		
    	for(TileExit exit:exits) {
			
			toreturn.addAll(getConnected(xtotal,ytotal,square,exit));
		}
		return toreturn;
	}



	public ArrayList<Connection> getConnections(ViewSquare square, TileItem selected) throws Exception {
		ArrayList<Connection> toreturn=new ArrayList<Connection>();
		TileItem newItem=(TileItem) selected.getImageItem();
		ArrayList<TileExit> exits=newItem.getExits();
		
		System.out.println("showing exits"+ exits);
		int xstartoff=0;
		int ystartoff=0;
		int[][] shape=selected.getShape();
    	while(shape[ystartoff][xstartoff]==0) {
    		xstartoff++;
    		if(xstartoff>shape[ystartoff].length) {
    			ystartoff++;
    			xstartoff=0;
    		}
    	}
    	int xtotal=square.getxID()-xstartoff;
    	int ytotal=square.getyID()-ystartoff;
		
    	for(TileExit exit:exits) {
			//tile is already placed but it should return exits of the placed tiles 
    		//so is hould change some things
		//	toreturn.addAll(getConnected(xtotal,ytotal,square,exit));
		}
    	throw(new Exception("hell no"));
		//return toreturn;
	
	}



	public void fillExits() {
		// TODO Auto-generated method stub
		ArrayList<ViewTileExit> copylist=(ArrayList<ViewTileExit>) availableExits.clone();
		for(ViewTileExit avexit:copylist) {
			int xlocation=1000;
			int ylocation=1000;
			

			//but should be connected to avexit
			TileItem toplace=null;
			System.out.println("The theme "+avexit.getTheme());
			switch(avexit.getTheme()) {
			case INDOOR:
				toplace=new TileItem(new EndGapTile());
				break;
			case OUTDOOR:
				toplace=new TileItem(new EndGapOutDoor());
				break;
			default:
				break;
			
			}
	
			int[][] shape=toplace.getShape();
		
			Point poi=toplace.getPointOff();
			int xstartoff=poi.x;
	    	int ystartoff=poi.y;
			switch(avexit.getDirect()) {
			case DOWN:
				break;
			case LEFT:
				toplace.setAngle(90);
				break;
			case RIGHT:
				toplace.setAngle(270);
				break;
			case UP:
				toplace.setAngle(180);
				break;
			default:
				break;
			
			}
			for(Point nextpoint:avexit.getNexts()) {
				
				if(xlocation>nextpoint.x) {
					xlocation=nextpoint.x;
				}
				if(ylocation>nextpoint.y) {
					ylocation=nextpoint.y;
				}
			}
			ylocation=ylocation-ystartoff;
			xlocation=xlocation-xstartoff;
			ViewSquare square=map[ylocation][xlocation];
			addTile(toplace,square);
			System.out.println("exit placed");
		}
	}


	@Override
	public void sendEvent(MouseEvent e,Point p,SelectAble selectable) {
		if(!selectable.isMapItem()) {
			return;
		}
		if(this.isMouseWithinComponent(this)) {
			
		
			//only if holded is a mapitem do the thingie
			//System.out.println("mouseevent got");
			Point point= new Point(p.x-this.getX(),p.y-this.getY());
			
			MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, pan);
			
			pan.dispatchEvent(convertMouseEvent);
			
			/*
			
				switch(e.getID()) {
				case MouseEvent.MOUSE_CLICKED:
					new GridMouseListener(this).mouseClicked(e,point);
					break;
				case MouseEvent.MOUSE_RELEASED:
					new GridMouseListener(this).mouseReleased(e,point);
					break;
				
				}
			
	
				switch(e.getID()) {
				case MouseEvent.MOUSE_MOVED:
					System.out.println("moouse moved");
					new GridMouseListener(this).mouseMoved(e,point);
					break;
				case MouseEvent.MOUSE_DRAGGED:
					System.out.println("moouse dragged");
					new GridMouseListener(this).mouseDragged(e,point);
					break;
				}
				
			*/
		}
		
		
	}


	public void removeTile(ViewTile toselect) {
		
		deleteTile(toselect);
		toselect.increaseAvailability();
		notifyRemoveTileListeners(toselect);
	}

	public void deleteTile (ViewTile toselect) {
		System.out.println("tile  removed "+ toselect);
		System.out.println(tiles.size());
		tiles.remove(toselect);
		System.out.println(tiles.size());
		ViewSquare square=((ViewTile) toselect).getSquare();
		//remove all tiles where this tile was present
		//remove all connections on exits.
		TileItem item=toselect.getTileItem();
		int[][] shape=item.getShape();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	
		int xoff=square.getxID();
		int yoff=square.getyID();
		this.unMarkTiles(xoff, xstartoff, yoff, ystartoff, shape,toselect);
		/*
		 for(int i=0; i<shape.length; i++) {
		    	int toputy=i+yoff-ystartoff;
		        for(int j=0; j<shape[i].length; j++) {
		        
		      
		    		
		        	int toputx=j+xoff-xstartoff;
		        	System.out.println(xstartoff);
		        	if(shape[i][j]!=0) {
		        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
		        			System.out.println("tile placed at");
		        			System.out.println(toputx);
		        			System.out.println(toputy);
		        		
		        			ViewSquare toset=map[toputy][toputx];
			        		toset.removeTile();
			        		//toset.setContainingTile(tile);
			        		if(shape[i][j]!=0) {
			        			toset.setOccupationnr(0);
			        		}
			        	
		        		}
		        		
		        	}
		         
		        }
		    }
		    */
		 //disconnect exits
		removeExits(toselect);
	}

	private void removeExits(ViewTile tile) {
		 for(ViewTileExit exit: tile.getViewExits()) {
			 availableExits.remove(exit);
			 if(exit.isConnected()) {
				 ViewTileExit exit2=exit.getConnectedexit();
				 
				 exit2.disconnect();
				 availableExits.add(exit2);
			 }
		 }
	}


	public boolean containsSelected(SelectAble selected) {
		if(tiles.contains(selected)) {
			return true;
		}
		else {
			if(doors.contains(selected)) {
				return true;
			}
			if(monsters.contains(selected)) {
				return true;
			}
			if(tokens.contains(selected)) {
				return true;
			}
			return false;
		}
	
	}


	public void addTilePlaceListener(TilePlaceListener eventBox) {
		tilePlaceListeners.add(eventBox);
	}


	public boolean isTileRotatable(ViewTile selected) {
		ViewTile tile=(ViewTile) selected;
		boolean toreturn=false;
		//this.removeTile(tile);
		//System.out.println(tileName);
		ViewSquare square=tile.getSquare();
				int [][] shape=tile.getShape();
				TileItem item=tile.getTileItem();
				Point poi=item.getPointOff();
				int xstartoff=poi.x;
		    	int ystartoff=poi.y;
		    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
				//ViewTile tile=tileName.getTile(toput);
		    	print2D(shape);
				int xoff=square.getxID();
				int yoff=square.getyID();
				
				
				//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
				ViewSquare baseSquare=map[yoff][xoff];
				int xval=0;
				int yval=0;
				if(xstartoff!=0) {
					xval=Tools.getNonXEmpties(shape,xstartoff);
				}
				if(ystartoff!=0) {
					yval=Tools.getNonYEmpties(shape,ystartoff);
				}
				
		//ViewTile tile=tileName.getTile(baseSquare,xval*squareWidth,yval*squareWidth);
			
		this.unMarkTiles(xoff, xstartoff, yoff, ystartoff, shape, tile);
		this.removeExits(tile);
		int angle=tile.getAngle();
		tile.rotate();
		
		//ViewSquare square=tile.getSquare();
		if(this.isLegalOccupied(square, item)) {
			
			toreturn=true;
		}
		tile.setAngle(angle);
		this.markTiles(xoff, xstartoff, yoff, ystartoff, shape, tile);
		this.addExits(square, tile, xstartoff, ystartoff);
		//this.addTile(tile.getTileItem(), square);
		// TODO Auto-generated method stub
		//toreturn;
		return toreturn;
		
	}
	public boolean isRotatable(ViewDoor selected) {
		//if tiles are open it's possible
		DoorItem item=(DoorItem) selected.getImageItem();
		ViewSquare square=selected.getBaseSquare();
		int [][] shape=selected.getShape();
		Point poi=item.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean firstcon=true;
		boolean secondcon=false;

		for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	System.out.println(xstartoff);
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
	        			
	        		
	        			ViewSquare toset=map[toputy][toputx];
		        		//so either all are occupied or it's placed on an exit
	        			if(toset.getOccupationnr()==0) {
	        				firstcon=false;
	        			}
	        			
	        		
	        			
	        		}
	        		
	        	}
	         
	        }
	    }
		// TODO Auto-generated method stub
		return firstcon;
	}


	public void rotateSelected(SelectAble selected) {
		switch(selected.getKind()) {
		case VIEWTILE:
			ViewTile tile=(ViewTile) selected;
			// TODO Auto-generated method stub
			this.deleteTile(tile);
			ViewSquare square=tile.getSquare();
			int angle=tile.getAngle();
			tile.rotate();
			this.addTile(tile, square);
			break;
		default:
			MapItem mp=(MapItem) selected;
			ViewSquare thesquare=mp.getBaseSquare();
			this.deleteItem(mp);
			mp.rotate();
			this.addItem(mp, thesquare);
			break;
		}
	
		
	}


	

	public void deleteSelected(SelectAble selected) {
		switch(selected.getKind()) {
		case DOOR:
			break;
		case EVENT:
			break;
		case TILEITEM:
			
			break;
		case VIEWTILE:

			this.removeTile((ViewTile) selected);
			break;
		case VIEWDOOR:
		case VIEWTOKEN:
		case VIEWMONSTER:
			this.removeItem((MapItem) selected);
			break;
		
		
		default:
			break;
		
		}
		
		
	}


	public void addRemoveTileListener(RemoveTileListener eventBox) {
		removeListeners.add(eventBox);
		
	}


	public void moveMap(Point origin, MouseEvent arg0) {
		System.out.println("map moved");
		// TODO Auto-generated method stub
		if (origin != null) {
            JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, pan);
            if (viewPort != null) {
                int deltaX = origin.x - arg0.getX();
                int deltaY = origin.y - arg0.getY();

                Rectangle view = viewPort.getViewRect();
                view.x += deltaX;
                view.y += deltaY;

                pan.scrollRectToVisible(view);
            }
        }
	}


	public boolean isLegalOccupied(ViewSquare square, SelectAble selected) {
		switch(selected.getKind()) {
		case DOOR:
			return this.isLegalOccupied(square,(DoorItem) selected);
			
		case EVENT:
			break;
		case TILEITEM:
			return  this.isLegalOccupied(square,(TileItem) selected);
			
		case VIEWTILE:
			return this.isLegalOccupied(square,((ViewTile) selected).getTileItem());
		case VIEWDOOR:
			
			ViewDoor door=(ViewDoor) selected;
			return isLegalOccupied(square,(DoorItem)door.getImageItem());
		case TOKEN:
			return  this.isLegalOccupied(square,(TokenItem) selected);
		case VIEWTOKEN:
			ViewToken token=(ViewToken) selected;
			return  this.isLegalOccupied(square,(TokenItem)token.getImageItem());
		case MONSTER:
			return  this.isLegalOccupied(square,(MonsterItem) selected);
		case VIEWMONSTER:
			ViewMonster monster=(ViewMonster) selected;
			return  this.isLegalOccupied(square,(MonsterItem)monster.getImageItem());
		default:
			break;
		
		}
		return false;
	}

	public boolean isLegalOccupied(ViewSquare square, DoorItem selected) {

		if(square==null) {
		
			return false;
		}
		//a door can only be placed on 4 tiles of the same tile or on an exit. in the same direction as the door
		int [][] shape=selected.getShape();
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean firstcon=true;
		boolean secondcon=false;
		ArrayList<ViewSquare> squareList=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	System.out.println(xstartoff);
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
	        			
	        		
	        			ViewSquare toset=map[toputy][toputx];
		        		//so either all are occupied or it's placed on an exit
	        			if(toset.getOccupationnr()==0) {
	        				firstcon=false;
	        			}
	        			
	        			squareList.add(toset);
	        			
	        		}
	        		
	        	}
	         
	        }
	    }
		//if a squarelist is the same as another door that door overlaps and can't be placed
		for(ViewDoor door:doors) {
			if(door.containsSameSquares(squareList)) {
				return false;
			}
		}
		//is the tile placed following an exit and on an exit.
		for(ViewTileExit ex:availableExits) {
			if(containsExit(squareList,ex,selected.getDirect())) {
				secondcon=true;
			}
			
		}
		return firstcon||secondcon;
	}
	
	public boolean isLegalOccupied(ViewSquare square, TokenItem selected) {

		if(square==null) {
		
			return false;
		}
		//a door can only be placed on 4 tiles of the same tile or on an exit. in the same direction as the door
		int [][] shape=selected.getShape();
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean firstcon=true;
		boolean secondcon=false;
		ArrayList<ViewSquare> squareList=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	System.out.println(xstartoff);
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
	        			
	        		
	        			ViewSquare toset=map[toputy][toputx];
		        		//so either all are occupied or it's placed on an exit
	        			if(toset.getOccupationnr()==0) {
	        				firstcon=false;
	        			}
	        			
	        			squareList.add(toset);
	        			
	        		}
	        		
	        	}
	         
	        }
	    }
		//if a squarelist is the same as another door that door overlaps and can't be placed
		
		for(ViewToken door:tokens) {
			if(door.containsSameSquares(squareList)) {
				return false;
			}
		}
		
		//is the tile placed following an exit and on an exit.
	
		return firstcon;
	}
	
	public boolean isLegalOccupied(ViewSquare square, MonsterItem selected) {

		if(square==null) {
		
			return false;
		}
		//a door can only be placed on 4 tiles of the same tile or on an exit. in the same direction as the door
		int [][] shape=selected.getShape();
		Point poi=selected.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean firstcon=true;
		boolean secondcon=false;
		ArrayList<ViewSquare> squareList=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	System.out.println(xstartoff);
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
	        			
	        		
	        			ViewSquare toset=map[toputy][toputx];
		        		//so either all are occupied or it's placed on an exit
	        			if(toset.getOccupationnr()==0) {
	        				firstcon=false;
	        			}
	        			
	        			squareList.add(toset);
	        			
	        		}
	        		
	        	}
	         
	        }
	    }
		//if a squarelist is the same as another door that door overlaps and can't be placed
		
		for(ViewMonster monster:monsters) {
			if(monster.containsaSameSquare(squareList)) {
				return false;
			}
		}
		
		//is the tile placed following an exit and on an exit.
	
		return firstcon;
	}
	

	private boolean containsExit(ArrayList<ViewSquare> squareList, ViewTileExit ex, DoorDirection doorDirection) {
		if(squareList.contains(ex.getLocation2())&&squareList.contains(ex.getLocation3())) {
			
			switch(doorDirection) {
			case HORIZON:
				if(ex.getLocation2().getyID()==ex.getLocation3().getyID()) {
					return true;
				}
				break;
			case VERT:
				if(ex.getLocation2().getxID()==ex.getLocation3().getxID()) {
					return true;
				}
				break;
			default:
				break;
			}
		
		}
		
	
		return false;
	}


	public void addItemPlaceListener(MapItemPlaceListener getselectedArea) {
		itemPlaceListeners.add(getselectedArea);
	}

	public void addItemRemoveListener(ItemRemoveListener listen) {
		itemRemoveListeners.add(listen);
	}

	public void removeItem(MapItem toselect) {
		deleteItem(toselect);
		notifyItemRemoveListeners(toselect);
	}


	private void notifyItemRemoveListeners(MapItem toselect) {
		// TODO Auto-generated method stub
		for(ItemRemoveListener listen:itemRemoveListeners) {
			listen.itemRemoved(toselect);
		}
	}

	public void deleteItem(MapItem toselect) {
		doors.remove(toselect);
		monsters.remove(toselect);
		tokens.remove(toselect);
		for(ViewSquare squat:toselect.getOccupyingSquares()) {
			squat.removeItem(toselect);
		}
	}

	public void startDragItem(MapItem toselect) {
		deleteItem(toselect);
		
	}


	public void startDragTile(ViewTile toselect) {
		// TODO Auto-generated method stub
		deleteTile(toselect);
	}


	public MapItem addItemToSquare(ShapeItem token, ViewSquare square) {
		int [][] shape=token.getShape();
		//ImageItem item= token.getImageItem();
		Point poi=token.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		MapItem mapit=null;
		switch(token.getKind()) {
		case DOOR:
			break;
		case MONSTER:
			mapit=new ViewMonster((MonsterItem)token,baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
			
			
			monsters.add((ViewMonster)mapit);
			break;
		case TOKEN:
			mapit=token.createViewItem();
			mapit.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
			
			
			tokens.add((ViewToken)mapit);
			//connect the exits of the tile
			//connectExits(tile);
			
			
			break;
	
		case VIEWDOOR:
			break;
		
		default:
			break;
		
		}

		mapit.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		markItemTiles(xoff,xstartoff,yoff,ystartoff,shape,mapit);
		notifyItemPlaceListeners(mapit);
		return mapit;
	}


	public MapItem addViewItemToSquare(MapItem token, ViewSquare square) {
		int [][] shape=token.getShape();

		Point poi=((ShapeItem)token.getImageItem()).getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		
		
		//ViewSquare baseSquare=map[yoff-ystartoff][xoff-xstartoff];
		ViewSquare baseSquare=map[yoff][xoff];
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		//token.setSquare(baseSquare);
		
		token.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		switch(token.getKind()) {
		case VIEWMONSTER:
			monsters.add((ViewMonster)token);
			break;
		case VIEWDOOR:
			break;
	
		case VIEWTOKEN:
			tokens.add((ViewToken)token);
			break;
		default:
			break;
		}
	
		//	mapit=new ViewToken((TokenItem)token,baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
			
			
			//connect the exits of the tile
			//connectExits(tile);
			
	

		

		token.setBaseSquare(baseSquare,square.getLocation().x-xval*squareWidth,square.getLocation().y-yval*squareWidth);
		markItemTiles(xoff,xstartoff,yoff,ystartoff,shape,token);
		//notifyItemPlaceListeners(token);
		return token;
	}


	public ArrayList<ViewSquare> getOccupiedSquares(ViewSquare square, ViewMonster themonster) {
		// TODO Auto-generated method stub
		if(square==null) {
			return null;
		}
		//a door can only be placed on 4 tiles of the same tile or on an exit. in the same direction as the door
		int [][] shape=themonster.getShape();
		Point poi=themonster.getPointOff();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
    	//ViewSquare toput=map[square.getyID()-ystartoff][square.getyID()];
		//ViewTile tile=tileName.getTile(toput);
    	print2D(shape);
		int xoff=square.getxID();
		int yoff=square.getyID();
		boolean firstcon=true;
		boolean secondcon=false;
		ArrayList<ViewSquare> squareList=new ArrayList<ViewSquare>();
		for(int i=0; i<shape.length; i++) {
	    	int toputy=i+yoff-ystartoff;
	        for(int j=0; j<shape[i].length; j++) {
	        
	      
	    		
	        	int toputx=j+xoff-xstartoff;
	        	System.out.println(xstartoff);
	        	if(shape[i][j]!=0) {
	        		if(toputx>=0&&toputx<map.length&&toputy<map[0].length) {
	        			
	        		
	        			ViewSquare toset=map[toputy][toputx];
		        		//so either all are occupied or it's placed on an exit
	        			if(toset.getOccupationnr()==0) {
	        				firstcon=false;
	        			}
	        			
	        			squareList.add(toset);
	        			
	        		}
	        		
	        	}
	         
	        }
	    }
		return squareList;
	}


	public void makeVisible(MapItem mapit) {
		System.out.println("tile is added back");
		switch(mapit.getKind()) {
			case VIEWTILE:
				System.out.println("tile is added back");
				this.addTile((ViewTile)mapit, mapit.getBaseSquare());
				break;
				
			default:
				this.addItem(mapit, mapit.getBaseSquare());
				break;
		}
		// TODO Auto-generated method stub
		
	}
		
	



		

	
	




	
}
