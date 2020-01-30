package model.Hero;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import HeroEditor.SavedHero;
import ItemEditor.SavedItem;
import Shop.ItemShop.ImageHolder;
import SkillEditor.SavedClass;
import SkillEditor.SavedSkill;
import model.Item;
import view.game.mappanel.SmallTextPanel;
import view.game.mappanel.ValueChanger;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ValueChangeListener;

public class Hero extends Item implements ImageHolder, ValueChanger{
	
	private ArrayList<SavedSkill> skills=new ArrayList<SavedSkill>();
	private ArrayList<SavedSkill> availableSkills=new ArrayList<SavedSkill>();
	private ArrayList<ValueChangeListener> listeners=new ArrayList<ValueChangeListener>();
	private int exp;
	private String imagestring;
	private SavedClass heroclass;
	private SavedHero heroOrigin;
	public Hero(String name) {
		super(name);
		exp=0;
		// TODO Auto-generated constructor stub
	}


	public Hero(String name, SavedClass heroclass2,String imagestring) {
		super(name);
		heroclass=heroclass2;
		this.imagestring=imagestring;
	}


	public Hero(String string, String string2) {
		super(string);
		name=string;
		imagestring=string2;
	}


	public Hero copy() {
		return new Hero(name,heroclass,imagestring);
	}



	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 2;
	}



	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 2;
	}



	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public ItemOptions getItemKind() {
		// TODO Auto-generated method stub
		return null;
	}





	public ArrayList<SavedSkill> getSkills() {
		// TODO Auto-generated method stub
		return skills;
	}





	public ArrayList<SavedSkill> getAvailableSkills() {
		return availableSkills;
	}





	public void AvailableSkills(ArrayList<SavedSkill> availableSkills) {
		this.availableSkills = availableSkills;
	}





	public int getExp() {
		return exp;
	}





	public void setExp(int exp) {
		this.exp = exp;
		for(ValueChangeListener listen:listeners) {
			listen.valueChanged(exp);
		}
	}





	public void setSkills(ArrayList<SavedSkill> skills) {
		this.skills = skills;
	}





	public void removeSkill(SavedSkill value) {
		// TODO Auto-generated method stub
		skills.remove(value);
		availableSkills.add(value);
	}





	public void addSkill(SavedSkill value) {
		availableSkills.remove(value);
		skills.add(value);
	}


	@Override
	public Icon getImageIcon() {
		BufferedImage img;
		if(imagestring==null||imagestring.equals("")) {
			return null;
		}
		try {
			img = ImageIO.read(new File(imagestring));
			Image newimg = img.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			return new ImageIcon(newimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}


	public void setClass(SavedClass currentclass) {
		skills.clear();
		availableSkills.clear();
		if(currentclass!=null) {
			for(SavedSkill skil: currentclass.getSavedskills()) {
				if(skil.getSkillpoints()==0) {
					skills.add(skil);
				}
				else {
					availableSkills.add(skil);
				}
			}
			this.heroclass=currentclass;
		}
		
		
	}

	public void setHeroOrigin(SavedHero hero) {
		this.heroOrigin=hero;
		if(hero!=null) {
			this.imagestring=hero.getImagestring();
		}
		
	}


	public Collection<? extends SavedItem> getItems() {
		// TODO Auto-generated method stub
		return heroclass.getInitialitems();
	}


	public SavedHero getSavedHero() {
		// TODO Auto-generated method stub
		return heroOrigin;
	}


	public SavedClass getSavedClass() {
		// TODO Auto-generated method stub
		return heroclass;
	}


	public void setSavedClass(SavedClass clas) {
		heroclass=clas;
	}


	public String getImageString() {
		// TODO Auto-generated method stub
		return imagestring;
	}


	public void addExp(int amount) {
		// TODO Auto-generated method stub
		exp=exp+amount;
		for(ValueChangeListener listen:listeners) {
			listen.valueChanged(exp);
		}
	}


	@Override
	public void addValueChangeListener(ValueChangeListener listen) {
		listeners.add(listen);
		
	}


	public void removeListeners(SmallTextPanel pane) {
		listeners.remove(pane);
		
	}


}
