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
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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
import model.event.MovementString;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import view.Items.Map.MapItem;
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
	
	private GameGrid grid;
	private JLayeredPane mapPanels;
	private JPanel textPanel=new JPanel();
	private HeroPanel heroPanel=new HeroPanel();
	private MonsterPanel monsterPanel=new MonsterPanel();
	private ButtonPanel temporaryPanel;
	
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
			goldpanel.setBackground(new Color(0,0,0,0));
			goldpanel.setLocation(50,mapPanels.getHeight()-140); 
			
			FamePanel famepanel=new FamePanel();
			//goldpanel.setBackground(Color.white);
			famepanel.setSize(120,30);
			mapPanels.add(famepanel,2,2);
			mapPanels.moveToFront(famepanel);
			famepanel.setBackground(new Color(0,0,0,0));
			famepanel.setLocation(170,mapPanels.getHeight()-140); 
			
			PerilPanel perilpanel=new PerilPanel();
			//goldpanel.setBackground(Color.white);
			perilpanel.setSize(120,30);
			mapPanels.add(perilpanel,2,2);
			mapPanels.moveToFront(perilpanel);
			perilpanel.setBackground(new Color(0,0,0,0));
			perilpanel.setLocation(290,mapPanels.getHeight()-140);
			
			HopePanel hopepanel=new HopePanel();
			//goldpanel.setBackground(Color.white);
			hopepanel.setSize(120,30);
			mapPanels.add(hopepanel,2,2);
			mapPanels.moveToFront(hopepanel);
			hopepanel.setBackground(new Color(0,0,0,0));
			hopepanel.setLocation(410,mapPanels.getHeight()-140);
	}


	private void addHeroes(ArrayList<Hero> heroes) {
		
		heroPanel.setSize(100,450);
		heroPanel.setLocation(50,100);
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
		point=new Point(point.x-50,point.y-20);
		System.out.println("shownow");
		temporaryPanel.reset();
		temporaryPanel.setLocation(point);
		temporaryPanel.setBackground(new Color(222,184,135,95));
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

	public void addGoBackButton() {
		
		JButton butt=new JButton("Go Back");
		butt.setLocation(50,50);
		butt.setSize(90, 30);
		butt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new GoBackToEditorCommand());
			}
		});
		mapPanels.add(butt,2,2);
		mapPanels.moveToFront(butt);
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
		area.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
		textPanel.add(area);
		area.setBackground(new Color(0,0,0,0));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),400);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(222,184,135,95));
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
		JTextArea area=new JTextArea();
		area.setText(text);
		
	
		
		area.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
		textPanel.add(area);
		area.setBackground(new Color(0,0,0,0));
		area.setEditable(false);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),400);
		textPanel.setLocation(200,10);
		area.setSize(mapPanels.getWidth()-80,300);
		textPanel.setBackground(new Color(222,184,135,95));
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


	public void showMonsterMovement(MonsterItem monster, ArrayList<MovementString> movement, MovementString continousEffect, MonsterKind kind) {
		// TODO Auto-generated method stub
		textPanel.removeAll();
		// TODO Auto-generated method stub
		mapPanels.moveToFront(textPanel);
		//first show zombie sign on top
		
		textPanel.setLayout(null);
	
		textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel picLabel = new JLabel(new ImageIcon(monster.getImageItem().getPreciseImage(70,70)));
		textPanel.add(picLabel);
		textPanel.setSize(Math.max(mapPanels.getWidth()-400,400),350);
		textPanel.setLocation(200,10);
		picLabel.setSize(70,70);
		picLabel.setLocation(textPanel.getWidth()/2-25, 5);
		JTextArea area=new JTextArea();
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
		area.setSize(mapPanels.getWidth()-80,40);
		
		
		textPanel.setBackground(new Color(222,184,135,95));
		JTextArea area2=new JTextArea();
		String str=continousEffect.getThemovement()+"\n";
		int textwidth = (int)(g2.getStringBounds(str, frc).getWidth());
		for(MovementString string:movement) {
			str=str+"- "+string.getThemovement()+"\n";
			textwidth=Math.max((int)(g2.getStringBounds(string.getThemovement(), frc).getWidth()),textwidth );
		}
		
		area2.setText(str);
		
		area2.setFont(g2);
		textPanel.add(area2);
		area2.setLocation(textPanel.getWidth()/2-textwidth/2,120);

	
	    
		area2.setBackground(new Color(0,0,0,0));
		area2.setEditable(false);
		area2.setSize(mapPanels.getWidth()-80,150);
		JPanel buttonpanel=new JPanel();
		textPanel.add(buttonpanel);
		buttonpanel.setSize(Math.max(mapPanels.getWidth()-500,400),50);
		buttonpanel.setLocation(50, 300);
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
		itcontrol.resetMap();
		itcontrol.loadMap(sampleFile.getConstantMap());
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
