package view.game.mappanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import StoryEditor.AddItemEvent;
import controller.AddGameHeroListener;
import controller.AddGameMonsterListener;
import controller.UserInputController;
import controller.commands.ContinueCommand;
import controller.commands.Game.GoBackToEditorCommand;
import controller.turns.GameController;
import frame.SubContainer;
import misc.ActivateAble;
import misc.BaseFile;
import misc.SampleFile;
import model.Activation;
import model.ItemController;
import model.Hero.Hero;
import model.event.EventEndListener;
import model.event.MovementString;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import view.Items.Map.MapItem;
import view.Items.Map.OpenMainMenuCommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.game.ActivationFactory;
import view.game.ButtonPanel;
import view.game.GameDoor;
import view.game.GameGrid;
import view.game.GameMonster;
import view.game.HeroPanel;
import view.game.MonsterKind;
import view.game.MonsterPanel;
import view.game.MoveToBackListener;
import view.hero.GameHero;
import view.hero.ViewHero;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;

public class GameMapPanel extends SubContainer implements MoveToBackListener {
	
	protected GameGrid grid;
	protected JLayeredPane mapPanels;
	protected JPanel textPanel=new JPanel();
	protected HeroPanel heroPanel=new HeroPanel();
	protected MonsterPanel monsterPanel=new MonsterPanel();
	protected ButtonPanel temporaryPanel;
	
	private ArrayList<AddGameHeroListener> heroPlaceListeners=new ArrayList<AddGameHeroListener>();
	private ArrayList<AddGameMonsterListener> monsterPlaceListeners=new ArrayList<AddGameMonsterListener>();;
	
	private BaseFile basefile;
	
	public GameMapPanel(Dimension defaultSize) {
		super(defaultSize);
		temporaryPanel=new ButtonPanel(this);
		mapPanels=new JLayeredPane();
		grid=new GameGrid(defaultSize);
		grid.addMoveToBackListener(this);
		this.add(mapPanels);
	
		mapPanels.setSize(defaultSize);
		mapPanels.setPreferredSize(defaultSize);
		mapPanels.add(grid,2,2);
	
		mapPanels.add(textPanel,2,2);
	    mapPanels.moveToBack(textPanel);
	   
		mapPanels.moveToFront(grid);
		 addGoBackButton();
		 addGetRandomItemButton();
		 
		 
		 textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		 
		 mapPanels.add(temporaryPanel,2,2);
	      mapPanels.moveToBack(temporaryPanel);
	      temporaryPanel.setBorder(  BorderFactory.createRaisedBevelBorder());
	       
		 mapPanels.add(heroPanel,2,2);
		    mapPanels.moveToBack(heroPanel);
		    mapPanels.add(monsterPanel,2,2);
		    mapPanels.moveToBack(monsterPanel);
		  
		// addHeroes(basefile.getHeroes());
		 
			Goldpanel goldpanel=new Goldpanel();
			//goldpanel.setBackground(Color.white);
			goldpanel.setSize(120,30);
			mapPanels.add(goldpanel,2,2);
			mapPanels.moveToFront(goldpanel);
			goldpanel.setBackground(new Color(246,221,199));
			goldpanel.setBorder(BorderFactory.createLineBorder(Color.black));
			goldpanel.setLocation(45,mapPanels.getHeight()-140); 
			
			FamePanel famepanel=new FamePanel();
			//goldpanel.setBackground(Color.white);
			famepanel.setSize(120,30);
			mapPanels.add(famepanel,2,2);
			mapPanels.moveToFront(famepanel);
			famepanel.setBackground(new Color(246,221,199));
			famepanel.setBorder(BorderFactory.createLineBorder(Color.black));
			famepanel.setLocation(170,mapPanels.getHeight()-140); 
			
			PerilPanel perilpanel=new PerilPanel();
			//goldpanel.setBackground(Color.white);
			perilpanel.setSize(120,30);
			mapPanels.add(perilpanel,2,2);
			mapPanels.moveToFront(perilpanel);
			perilpanel.setBackground(new Color(246,221,199));
			perilpanel.setBorder(BorderFactory.createLineBorder(Color.black));
			perilpanel.setLocation(295,mapPanels.getHeight()-140);
			
			HopePanel hopepanel=new HopePanel();
			//goldpanel.setBackground(Color.white);
			hopepanel.setSize(120,30);
			mapPanels.add(hopepanel,2,2);
			mapPanels.moveToFront(hopepanel);
			hopepanel.setBackground(new Color(246,221,199));
			hopepanel.setBorder(BorderFactory.createLineBorder(Color.black));
			hopepanel.setLocation(420,mapPanels.getHeight()-140);
	}



	private void addHeroes(ArrayList<Hero> heroes) {
		
		heroPanel.setSize(100,450);
		heroPanel.setLocation(50,130);
		for(Hero hero:heroes) {
			GameHero viewher=new GameHero(hero);
			heroPanel.addHero(viewher);
			this.triggerHeroPlaceListeners(viewher);
		}
		mapPanels.moveToFront(heroPanel);
		// TODO Auto-generated method stub
		
	}
	public GameMonster addGameMonster(ViewMonster toplace) {
		monsterPanel.setSize(100,450);
		monsterPanel.setLocation(this.getWidth()-200,100);
		GameMonster gamemon=new GameMonster(toplace);
	//	topla
		monsterPanel.addMonster(gamemon);
		grid.addGameMonster(gamemon);
		mapPanels.moveToFront(monsterPanel);
		// TODO Auto-generated method stub
		this.triggerMonsterPlaceListeners(gamemon);
		return gamemon;
	}
	
	private void addMonsters(ArrayList<ViewMonster> monsters) {
		
	}
	public void showActivateAbles(Point point, ArrayList<ActivateAble> list) {
		point=new Point(point.x-50-this.getX(),point.y-60-this.getY());
		System.out.println("shownow");
		temporaryPanel.reset();
		temporaryPanel.setLocation(point);
		temporaryPanel.setBackground(new Color(222,184,135,99));
		for(ActivateAble act:list) {
			for(Activation activation:act.getActivations()) {
				temporaryPanel.addButton(ActivationFactory.createActivationButton(activation));
				
			}
		}
		mapPanels.moveToFront(temporaryPanel);
		
	}


	public void addGameTile(ViewTile toplace) {
		// TODO Auto-generated method stub
		grid.addGameTile(toplace);
		
	}

	public void addDoorToSquare(ViewDoor toplace, ViewSquare square) {
		grid.addDoorToSquare(toplace,square);
		
	}

	public void addGameDoor(ViewDoor toplace) {
		// TODO Auto-generated method stub
		this.addDoorToSquare(toplace, toplace.getBaseSquare());
	}


	public void removeGameDoor(GameDoor door) {
		// TODO Auto-generated method stub
		grid.removeDoor(door);
	}

	public void addGameToken(ViewToken toplace) {
		// TODO Auto-generated method stub
		grid.addGameToken(toplace);
	}

	public void removeGameToken(ViewToken token) {
		grid.removeGameToken(token);
		
	}

	public void removeGameTile(ViewTile tile) {
		grid.removeGameTile(tile);
		
	}
	
	public void addToMainMenuCommand() {
		JButton backToMenuButton=new JButton("to Main");
		backToMenuButton.setLocation(50,50);
		backToMenuButton.setSize(90, 30);
		backToMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenMainMenuCommand comm=new OpenMainMenuCommand();
				UserInputController.getController().performCommand(comm);
			}
		});
		mapPanels.add(backToMenuButton,2,2);
		mapPanels.moveToFront(backToMenuButton);
	}

	private void addGetRandomItemButton() {

		JButton butt=new JButton("Get Random Item");
		butt.setLocation(50,90);
		butt.setSize(140, 30);
		butt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				UserInputController control=UserInputController.getController();
				try {
					AddItemEvent comm=new AddItemEvent();
					comm.trigger();
					
				}
				catch(Exception l) {
					System.out.println(l );
					System.out.println( "gone back to main menu");
					AddItemEvent comm=new AddItemEvent();
					comm.trigger();
				}
				
			}
		});
		mapPanels.add(butt,2,2);
		mapPanels.moveToFront(butt);
		
	}


	public void addGoBackButton() {
		
		JButton butt=new JButton("Go Back");
		butt.setLocation(50,50);
		butt.setSize(90, 30);
		butt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				UserInputController control=UserInputController.getController();
				try {
					control.performCommand(new GoBackToEditorCommand());
				}
				catch(Exception l) {
					System.out.println(l );
					System.out.println( "gone back to main menu");
					OpenMainMenuCommand comm=new OpenMainMenuCommand();
					UserInputController.getController().performCommand(comm);
				}
				
			}
		});
		mapPanels.add(butt,2,2);
		mapPanels.moveToFront(butt);
	}


	public void showTextDialog(String text, EventEndListener listen ) {
		mapPanels.moveToBack(temporaryPanel);
		// TODO Auto-generated method stub
		//grid.showTextDialog(text);
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		JTextPane area=new JTextPane();
		area.setText(text);
		area.setFont(new Font("descentquestbuilderfont", Font.PLAIN, 20));

	    SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = area.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
   
		textPanel.add(area);
		area.setBackground(new Color(0,0,0,0));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),400);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(222,184,135));
		area.getCaret().setVisible(false); 
		this.revalidate();
		this.repaint();
		JButton optionbutton=new JButton("continue");
		JPanel buttonpanel=new JPanel();
		textPanel.add(buttonpanel);
		buttonpanel.setSize(Math.max(mapPanels.getWidth()-500,400),50);
		buttonpanel.setLocation(50, 350);
		buttonpanel.setBackground(new Color(0,0,0,0));
		optionbutton.setSize(300,200);
		optionbutton.setBackground(new Color(222,194,145));
		optionbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				closeText();
				
				listen.eventEnded();
			}

		
		});
		buttonpanel.add(optionbutton);
		
	}


	public void showTextDialog(String text, ArrayList<TextOption> newoptions, EventEndListener listen ) {
		System.out.println(text);
		mapPanels.moveToBack(temporaryPanel);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//grid.showTextDialog(text);
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		JTextPane area=new JTextPane();
		area.setText(text);
		 SimpleAttributeSet center = new SimpleAttributeSet();
	        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	        StyledDocument doc = area.getStyledDocument();
	        doc.setParagraphAttributes(0, doc.getLength(), center, false);
	   
	    	area.getCaret().setVisible(false); 
		
		area.setFont(new Font("descentquestbuilderfont", Font.PLAIN, 40));
		textPanel.add(area);
		area.setBackground(new Color(0,0,0,0));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),400);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(222,184,135));
		JPanel buttonpanel=new JPanel();
		textPanel.add(buttonpanel);
		buttonpanel.setSize(Math.max(mapPanels.getWidth()-500,400),50);
		buttonpanel.setLocation(50, 350);
		buttonpanel.setBackground(new Color(0,0,0,0));
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
					listen.eventEnded();
				}

			
				
			});
			buttonpanel.add(optionbutton);
		}
		textPanel.revalidate();
		textPanel.repaint();
		this.revalidate();
		this.repaint();
		textPanel.requestFocus();
	}
	
	public void closeText() {
		// TODO Auto-generated method stub
		mapPanels.moveToBack(textPanel);
	}
	public void moveTemporariesToBack() {
		// TODO Auto-generated method stub
		mapPanels.moveToBack(temporaryPanel);
	}


	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect, MonsterKind kind, EventEndListener listen) {
		// TODO Auto-generated method stub
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		//first show zombie sign on top
		
		textPanel.setLayout(null);
	
		textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel picLabel = new JLabel(new ImageIcon(monster.getImageItem().getPreciseImage(70,70)));
		textPanel.add(picLabel);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),450);
		textPanel.setLocation(200,10);
		picLabel.setSize(70,70);
		picLabel.setLocation(textPanel.getWidth()/2-25, 5);
		JTextArea area=new JTextArea();
		
		area.setAlignmentX(Component.CENTER_ALIGNMENT);
		area.setText(kind.toString()+" "+monster.getName() +"'s movement");
		Font g1=new Font("descentquestbuilderfont", Font.PLAIN, 40);
		Font g2=new Font("descentquestbuilderfont", Font.PLAIN, 26);
		AffineTransform affinetransform = new AffineTransform(); 
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);   
		int textwidth1 = (int)(g2.getStringBounds(area.getText(), frc).getWidth());
		area.setFont(g1);
		textPanel.add(area);
		area.setLocation(textPanel.getWidth()/2-textwidth1/2,80);

	    
		area.setBackground(new Color(0,0,0,0));
		area.setEditable(false);
		area.setSize(mapPanels.getWidth()-80,60);
		if(kind==MonsterKind.MASTER) {
			textPanel.setBackground(new Color( 162, 60, 77));
		}
		else {
			textPanel.setBackground(new Color(222,184,135));
		}
		
		
		JTextArea area2=new JTextArea();
		String str=continousEffect.getThemovement()+"\n";
		int textwidth = (int)(g2.getStringBounds(str, frc).getWidth());
		for(MovementString string:movement) {
			str=str+"- "+string.getThemovement()+"\n";
			textwidth=Math.max((int)(g2.getStringBounds(string.getThemovement(), frc).getWidth()),textwidth );
		}
		str=str;
		area2.setAlignmentX(Component.CENTER_ALIGNMENT);
		area2.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		area2.setText(str);
		
		area2.setFont(g2);
		textPanel.add(area2);
		area2.setLocation(textPanel.getWidth()/2-textwidth/2,130);

	
	    
		area2.setBackground(new Color(0,0,0,0));
		area2.setEditable(false);
		area2.setSize(mapPanels.getWidth()-80,150);
		JPanel buttonpanel=new JPanel();
		textPanel.add(buttonpanel);
		buttonpanel.setSize(Math.max(mapPanels.getWidth()-500,400),50);
		buttonpanel.setLocation(50, 360);
		buttonpanel.setBackground(new Color(0,0,0,0));
		JButton optionbutton=new JButton("continue");
		
		optionbutton.setSize(300,200);
		optionbutton.setBackground(new Color(222,194,145));
		optionbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeText();
				UserInputController control=UserInputController.getController();
				control.performCommand(new ContinueCommand());
				listen.eventEnded();
				
			}

		
			
		});
		buttonpanel.add(optionbutton);
		this.revalidate();
		this.repaint();
		optionbutton.requestFocus();
	}


	public void addMonsterPlaceListener(GameController gamecontrol) {
		// TODO Auto-generated method stub
		monsterPlaceListeners.add(gamecontrol);
	}


	public void addHeroPlaceListener(GameController gamecontrol) {
		// TODO Auto-generated method stub
		heroPlaceListeners.add(gamecontrol);
	}


	public void triggerHeroPlaceListeners(GameHero hero) {
		for(AddGameHeroListener listen:heroPlaceListeners) {
			listen.addHero(hero);
		}
	}


	public void triggerMonsterPlaceListeners(GameMonster monster) {
		for(AddGameMonsterListener listen:monsterPlaceListeners) {
			listen.addMonster(monster);
		}
	}


	public void initialiseGame(BaseFile sampleFile) {
		// TODO Auto-generated method stub
		ItemController itcontrol=ItemController.getItemController();
	//	itcontrol.resetMap();
	//	itcontrol.loadMap(sampleFile.getConstantMap());
		basefile=sampleFile;
		addHeroes(basefile.getHeroes());
		grid.initialise(sampleFile);
	}


	public void removeGameMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		monsterPanel.removeMonster(toremove);
		grid.removeGameMonster(toremove);
	}


	public void removeMapMonster(GameMonster toremove) {
		// TODO Auto-generated method stub
		grid.removeGameMonster(toremove);
	}
	
	






}
