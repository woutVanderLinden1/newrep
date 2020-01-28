package view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import controller.UserInputController;
import controller.commands.GoBackCommand;
import controller.commands.Game.GoBackToEditorCommand;
import frame.SubContainer;
import misc.ActivateAble;
import misc.BaseFile;
import misc.Tools;
import model.Activation;
import model.Monster.MonsterSet;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.viewItems.DoorItem;
import view.viewItems.GridMouseListener;
import view.viewItems.MonsterItem;

public class GameGrid extends SubContainer {
	private JScrollPane scrollPane;
	private JPanel pan;
	public static int squareWidth=64;
	private int mapLength=100;
	private int offset=100;
	private Image backgroundImage=null;
	private ArrayList<GameTile> tiles;
	private ArrayList<GameDoor> doors=new ArrayList<GameDoor>();
	private ArrayList<GameToken> tokens=new ArrayList<GameToken>();
	private ArrayList<GameMonster> monsters=new ArrayList<GameMonster>();
	private GameSquare[][] map;
	private JLayeredPane mainpanel;
	private ArrayList<MoveToBackListener> toBackListeners=new ArrayList<MoveToBackListener>();
	private BaseFile thefile;
	



	public GameGrid(Dimension defaultSize) {
		super(defaultSize);
		//thefile=basefile;
		
		tiles=new ArrayList<GameTile>();
		GameGrid thisthing=this;
		 pan=new SubContainer(offset*2+squareWidth*mapLength,offset*2+squareWidth*mapLength){
			    public void paintComponent(Graphics g) {
	    		    super.paintComponent(g);

	    		    // Draw the background image.
	    		    g.drawImage(backgroundImage, scrollPane.getViewport().getViewPosition().x, scrollPane.getViewport().getViewPosition().y, this);
	    		    for(GameTile tile:tiles){
	    		    	
	    		    	tile.draw(g,this);
	    		    	/*
	    		    	if(tile.isSelected()) {
	    		    		tile.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    	*/
	    		    }
	    		    for(GameDoor door:doors){
	    		    	
	    		    	door.draw(g,this);
	    		    	/*
	    		    	if(tile.isSelected()) {
	    		    		tile.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    	*/
	    		    }
	    		    for(GameToken token:tokens){
	    		    	
	    		    	token.draw(g,this);
	    		    	/*
	    		    	if(tile.isSelected()) {
	    		    		tile.drawShape(g);
	    		    		//g.fil
	    		    		g.setColor(Color.black);
	    		    	}
	    		    	*/
	    		    }
	    		    for(GameMonster monster:monsters) {
	    		    	monster.draw(g, this);
	    		    }
	    		  }
	    	 @Override
	    	 public void addMouseListener() {
	    		 GameGridMouseListener list=new GameGridMouseListener(thisthing);
	    		 this.addMouseListener(list);
	    		 this.addMouseMotionListener(list);
	    	 }
	    	
	     };

	 

         MouseAdapter ma = new MouseAdapter() {

             private Point origin;

             @Override
             public void mousePressed(MouseEvent e) {
                 origin = new Point(e.getPoint());
             }

             @Override
             public void mouseReleased(MouseEvent e) {
             }

             @Override
             public void mouseDragged(MouseEvent e) {
                 if (origin != null) {
                	 JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, pan);   if (viewPort != null) {
                         int deltaX = origin.x - e.getX();
                         int deltaY = origin.y - e.getY();

                         Rectangle view = viewPort.getViewRect();
                         view.x += deltaX;
                         view.y += deltaY;

                         pan.scrollRectToVisible(view);
                     }
                 }
             }

         };
	     pan.setLayout(null);
	     pan.addMouseListener(ma);
	     pan.addMouseMotionListener(ma);
	
	    
	     //pan.setBackground(Color.white);
	     //pan.setPreferredSize(new Dimension(squareWidth*mapLength,squareWidth*mapLength));
	     map=new GameSquare[mapLength][mapLength];
	    
		try {
			backgroundImage = ImageIO.read(new File("Images/texture1.jpg"));
		//	backgroundImage=Tools.resize(squareWidth*mapLength,squareWidth*mapLength,(BufferedImage) backgroundImage);
			backgroundImage=Tools.resize(defaultSize.width,defaultSize.height,(BufferedImage) backgroundImage);
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
					GameSquare tile=new GameSquare(0,0,64,64,j,i);
					tile.setLocation(offset+j*64,offset+i*64);
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
	     mainpanel=new JLayeredPane();
	     
	     mainpanel.setLayout(null);
	      mainpanel.setSize(pan.getSize());
	      mainpanel.setPreferredSize(pan.getSize());
	      mainpanel.add(pan,2,2);
	     
	    //  mainpanel.add(textPanel,2,2);
	    //  mainpanel.moveToBack(textPanel);
	      scrollPane=new JScrollPane(mainpanel,scrollPane.VERTICAL_SCROLLBAR_NEVER,
	        	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setPreferredSize(new Dimension(this.getWidth()-20,this.getHeight()-5));
		 
	       // scrollPane.setContent(layout);

	     
	      this.add(scrollPane);
	      
	      //this.add(mainpanel);
	       
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
	      //addHeroes(basefile);
	     
	}

	private void addHeroes(BaseFile basefile) {
		// TODO Auto-generated method stub
		
	}

	public void addGameTile(ViewTile toplace) {
		ViewSquare ex=toplace.getSquare();
		GameSquare square=map[ex.getyID()][ex.getxID()];
		GameTile tile=new GameTile(square,toplace);
		Point poi=tile.getPointOff();
		int [][] shape=tile.getShape();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
		int xval=0;
		int yval=0;
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		square.addElement(tile);
		tile.setPoint(new Point(square.getX()-(xval*squareWidth),square.getY()-yval*squareWidth));
		tiles.add(tile);
		System.out.println("gametile added");
	}

	public void addDoorToSquare(ViewDoor toplace, ViewSquare square) {
		
		GameSquare gamesquare=map[square.getyID()][square.getxID()];
		GameDoor door=new GameDoor(gamesquare,toplace);
		Point poi=door.getPointOff();
		int [][] shape=door.getShape();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
		int xval=0;
		int yval=0;
		int xoff=square.getxID();
		int yoff=square.getyID();
		//gamesquare.addElement(door);
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		door.setPoint(new Point(gamesquare.getX()-(xval*squareWidth),gamesquare.getY()-yval*squareWidth));
		markTiles(xoff,xstartoff,yoff,ystartoff,shape,door);
		doors.add(door);
		
	}
	private void markTiles(int xoff, int xstartoff, int yoff, int ystartoff, int[][] shape, MapItem item) {
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
		        			
		        		
		        			GameSquare toset=map[toputy][toputx];
			        		toset.addElement(item);
			        	
			        		squares.add(toset);
		        		}
		        		
		        	}
		         
		        }
		    }
		item.setOccupyingSquares(squares);
	}

	public GameSquare getSquareAt(int x, int y) {

		int x2=(int)Math.floor( (x-offset)/squareWidth);
		int y2=(int) Math.floor((y-offset)/squareWidth);
		
		
		//should represent right one with the scroll
		if(x2>=0&&y2>=0) {
			if(x2< map.length&&y2<map.length) {
				return map[y2][x2];
			}
		}
		return null;
		
		// TODO Auto-generated method stub
		
		
	}


/*

	public void removeGameDoor(ViewDoor door) {
	
				
			
				this.removeDoor(door2);
		
	}
	*/
	public void removeDoor(GameDoor door) {
		
		//GameSquare square=(GameSquare) door.getBaseSquare();
		//Point poi=door.getPointOff();
		System.out.println("door to remove "+door);
		for(ViewSquare squar:door.getOccupyingSquares()) {
			
			squar.removeItem(door);
		}
		for(GameDoor thedoor:doors) {
			System.out.println("containsdoor "+ thedoor);
		}
		doors.remove(door);
	
		
	}

	public void addGameToken(ViewToken toplace) {
		ViewSquare square=toplace.getBaseSquare();
		GameSquare gamesquare=map[square.getyID()][square.getxID()];
		GameToken token=new GameToken(gamesquare,toplace);
		Point poi=token.getPointOff();
		int [][] shape=token.getShape();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
		int xval=0;
		int yval=0;
		int xoff=square.getxID();
		int yoff=square.getyID();
		//gamesquare.addElement(token);
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		token.setPoint(new Point(gamesquare.getX()-(xval*squareWidth),gamesquare.getY()-yval*squareWidth));
		markTiles(xoff,xstartoff,yoff,ystartoff,shape,token);
		tokens.add(token);
	}
	
	public void addGameMonster(GameMonster toplace,ViewSquare square) {
		GameMonster token=toplace.copy();
		GameSquare gamesquare=map[square.getyID()][square.getxID()];
		
		Point poi=toplace.getPointOff();
		int [][] shape=toplace.getShape();
		int xstartoff=poi.x;
    	int ystartoff=poi.y;
		int xval=0;
		int yval=0;
		int xoff=square.getxID();
		int yoff=square.getyID();
		//gamesquare.addElement(token);
		if(xstartoff!=0) {
			xval=Tools.getNonXEmpties(shape,xstartoff);
		}
		if(ystartoff!=0) {
			yval=Tools.getNonYEmpties(shape,ystartoff);
		}
		token.setPoint(new Point(gamesquare.getX()-(xval*squareWidth),gamesquare.getY()-yval*squareWidth));
		markTiles(xoff,xstartoff,yoff,ystartoff,shape,token);
		monsters.add(token);
		toplace.addMapMonster(token);
	}

	public void removeGameToken(ViewToken token) {
		GameSquare square=(GameSquare) token.getBaseSquare();
		Point poi=token.getPointOff();
		for(ViewSquare squar:token.getOccupyingSquares()) {
			squar.removeItem(token);
		}
		tokens.remove(token);
	
		
	}
/*
	public void removeGameToken(ViewToken token) {
		this.removeGameToken(token);
	}
	
/*
	public void removeGameTile(ViewTile tile) {
		this.removeGameTile(tile);
	}
	*/

	public void removeGameTile(ViewTile tile) {
	
		for(ViewSquare squar:tile.getOccupyingSquares()) {
			squar.removeTile();
		}
		tiles.remove(tile);
	
		
	}

	public void addGameMonster(GameMonster gamemon) {
		// TODO Auto-generated method stub
		//add to the mosnters to the selected squares
		
		MonsterItem it=gamemon.getMonsterItem();
		Map<Integer, MonsterSet> map=it.getMap();
		if(map==null) {
			return;
		}
		MonsterSet workingset=map.get(thefile.getNrHeroes());
		ArrayList<ViewSquare> squares=new ArrayList<ViewSquare>(gamemon.getPlaceMonsterSquares());
		
		int p = squares.size();
		Random rand = new Random();
		for(int i=0;i<workingset.getMasterMonsters();i++) {
			if(p==0) {
				break;
			}
			int j=rand.nextInt(p);
			
			this.addGameMonster(gamemon,squares.get(j));
			squares.remove(j);
			p--;
			
			//place mastermonsters on terrain
			
		}
		for(int i=0;i<workingset.getMinionMonsters();i++) {
			if(p==0) {
				break;
			}
			//place minionmonsters on terrain
			int j=rand.nextInt(p);
			
			this.addGameMonster(gamemon,squares.get(j));
			squares.remove(j);
			p--;
		}
		
	}

	public void moveTemporariesToBack() {
		// TODO Auto-generated method stub
		for(MoveToBackListener listen:toBackListeners) {
			listen.moveTemporariesToBack();
		}
	
	}

	public void addMoveToBackListener(MoveToBackListener gameMapPanel) {
		// TODO Auto-generated method stub
		toBackListeners.add(gameMapPanel);
	}

	public void initialise(BaseFile sampleFile) {
		// TODO Auto-generated method stub
		thefile=sampleFile;
	}

	public void removeGameMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		System.out.println("removed a monster");
		for(GameMonster mon:toremove.getMapMonsters()) {
			monsters.remove(mon);
		}
		
	}





	/*
	public void showTextDialog(String text) {
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mainpanel.moveToFront(textPanel);
		JTextArea area=new JTextArea();
		area.setText(text);
		textPanel.add(area);
		area.setEditable(false);
		textPanel.setSize(mainpanel.getWidth(),400);
		area.setSize(mainpanel.getWidth()-80,300);
	}
	*/

	


}
