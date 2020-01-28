package HeroPicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.FilenameUtils;

import HeroEditor.SavedHero;
import ItemEditor.FeatureEditor;
import ItemEditor.ImageChooser;
import ItemEditor.ItemChooser;
import ItemEditor.SavedItem;
import SkillEditor.SavedClass;
import controller.UserInputController;
import frame.MainFrame;
import frame.SubContainer;
import misc.BaseFile;
import model.Hero.Hero;
import ItemEditor.ActionTaker;

public class HeroPicker extends MainFrame {

	private HeroChoosePanel chooser;
	private ClassChoosePanel classchooser;
	private PlayerChoosePanel playerchoose;

	private BaseFile file;
	private ArrayList<SavedHero> availableHeroes;
	private ArrayList<SavedClass> availableclasses;
	private ArrayList<Hero> chosenHeroes=new ArrayList<Hero>();
	private Hero currentHero;
	private SavedClass currentclass;
	private SavedHero currentSavedHero;
	private JPanel continuebuttonpanel;

	public ArrayList<Hero> getChosenHeroes() {
		return chosenHeroes;
	}

	public void setChosenHeroes(ArrayList<Hero> chosenHeroes) {
		this.chosenHeroes = chosenHeroes;
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public void setCurrentHero(Hero currentHero) {
		this.currentHero = currentHero;
	}

	public SavedClass getCurrentclass() {
		return currentclass;
	}

	public void setCurrentclass(SavedClass currentclass) {
		this.currentclass = currentclass;
	}

	public SavedHero getCurrentSavedHero() {
		return currentSavedHero;
	}

	public void setCurrentSavedHero(SavedHero currentSavedHero) {
		this.currentSavedHero = currentSavedHero;
	}

	public HeroPicker(BaseFile file) {
		super();
		this.file=file;
		availableHeroes = this.generateSavedHeroes();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1200, 880);
		this.setMinimumSize(new Dimension(1200, 880));
		this.setPreferredSize(new Dimension(1200, 880));
		this.setVisible(true);
		this.setName("HeroPicker");
		this.setTitle("HeroPicker");

		chooser = new HeroChoosePanel(new Dimension(390, 800), this, availableHeroes, new ActionTaker<SavedHero>() {

			public void perform(SavedHero hero) {
				setActiveSavedHero(hero);
			}

		});
		chooser.setMinimumSize(new Dimension(390, 800));
		classchooser = new ClassChoosePanel(new Dimension(390, 800), this, null, new ActionTaker<SavedClass>() {
			public void perform(SavedClass clas) {
				setActiveClass(clas);
			}

		});
		classchooser.setMinimumSize(new Dimension(0, 800));
		ArrayList<Hero> herolist = new ArrayList<Hero>();
		for (int i = 0; i < file.getNrHeroes(); i++) {
			herolist.add(new Hero("Player" + i));
		}
		playerchoose = new PlayerChoosePanel(new Dimension(200, 600), this, herolist, new ActionTaker<Hero>() {
			public void perform(Hero clas) {
				setActiveHero(clas);
			}
		});
		playerchoose.setMinimumSize(new Dimension(200, 600));
		continuebuttonpanel = new SubContainer(200, 200);
		JButton but = new JButton("continue");
		but.setPreferredSize(new Dimension(150, 150));
		continuebuttonpanel.add(but);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				confirmHeroes();
				
				for(Hero her:chosenHeroes) {
					if(her.getSavedClass()==null) {
						return;
					}
				}
				
				UserInputController control = UserInputController.getUserInputController();
				control.endHeroPicking();
				setVisible(false);
			}

		});

		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, playerchoose, continuebuttonpanel);
		splitPane2.setPreferredSize(new Dimension(200, 800));
		splitPane2.setMinimumSize(new Dimension(200, 800));
		splitPane2.setSize(200, 800);
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chooser, classchooser);
		splitPane0.setPreferredSize(new Dimension(800, 800));
		splitPane0.setMinimumSize(new Dimension(800, 800));
		splitPane0.setSize(800, 800);
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane0,  splitPane2 );
		splitPane1.setPreferredSize(new Dimension(1200, 800));
		splitPane1.setMinimumSize(new Dimension(1200, 800));
		splitPane1.setSize(new Dimension(1200, 800));
		splitPane1.setBackground(Color.black);
		lpane.add(splitPane1, 2, 2);
		lpane.moveToFront(splitPane1);
		setAlwaysOnTop(true);
	}

	public void refreshHeroPicker() {
		chooser.refreshItemButtonPanel();
		classchooser.refreshItemButtonPanel();
		playerchoose.refreshItemButtonPanel();
		this.revalidate();
		this.repaint();

	}

	private void setActiveSavedHero(SavedHero hero) {
		// chooser.deactivateCurrent(currentHero);
		chooser.setCurrent(hero);
		currentSavedHero = hero;
		if (currentHero != null) {
			currentHero.setHeroOrigin(hero);
		}

		classchooser.setButtonList(generateSavedClassesFromHero());
		classchooser.refreshItemButtonPanel();
		chooser.refreshItemButtonPanel();
		playerchoose.refreshItemButtonPanel();
	}

	private ArrayList<SavedClass> generateSavedClassesFromHero() {
		ArrayList<SavedClass> array = new ArrayList<SavedClass>();

		int added = 0;
		String directoryPath = System.getProperty("user.dir") + "/Classes";
		File dir = new File(directoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				Optional<String> answer = this.getExtensionByStringHandling(child.getName());
				System.out.println();
				if (answer.get().equals("ser")) {
					added++;
					SavedClass i = null;
					FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(child);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						i = (SavedClass) in.readObject();
						in.close();
						fileIn.close();
						System.out.println("the read file is " + i);
						if (currentSavedHero != null) {
							if (i.getType() == currentSavedHero.getType()) {
								array.add(i);
							}
						}

					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		return array;
	}

	private ArrayList<SavedHero> generateSavedHeroes() {
		ArrayList<SavedHero> array = new ArrayList<SavedHero>();

		int added = 0;
		String directoryPath = System.getProperty("user.dir") + "/Heroes";
		File dir = new File(directoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				Optional<String> answer = this.getExtensionByStringHandling(child.getName());
				System.out.println();
				if (answer.get().equals("ser")) {
					added++;
					SavedHero i = null;
					FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(child);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						i = (SavedHero) in.readObject();
						in.close();
						fileIn.close();
						System.out.println("the read file is " + i);
						if (currentHero != null) {
							array.add(i);
						}

					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		return array;
	}

	private void setActiveClass(SavedClass clas) {
		currentclass = clas;
		classchooser.setCurrent(clas);
		currentHero.setSavedClass(clas);
		

	}

	private void setActiveHero(Hero hero) {
		confirmSavedHero();
		currentHero=hero;
		playerchoose.setCurrent(hero);
		classchooser.storeCurrent();
		this.setActiveSavedHero(hero.getSavedHero());
		this.setActiveClass(hero.getSavedClass());
		chooser.setButtonList(this.generateSavedHeroes());
		chooser.refreshItemButtonPanel();
		//classchooser.set
		//chooser.set
		
	}

	private void confirmSavedHero() {
		if (currentSavedHero != null) {
			chooser.storeCurrent();

		}
		if (currentHero != null) {
			currentHero.setClass(currentclass);
			currentHero.setHeroOrigin(currentSavedHero);
			if(!chosenHeroes.contains(currentHero)) {
				chosenHeroes.add(currentHero);
			}
			
		}
		

	}

	public void confirmHeroes() {
		// add heroes with items to the basefile
		confirmSavedHero();
		file.initialiseHeroes(this.getChosenHeroes());
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

}
