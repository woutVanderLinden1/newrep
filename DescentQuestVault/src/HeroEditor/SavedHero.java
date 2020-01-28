package HeroEditor;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import ItemEditor.ActionTaker;
import Shop.ItemShop.ImageHolder;
import SkillEditor.HeroType;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SavedHero implements ImageHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String imagestring;
	private String description;
	private HeroType type;
	public SavedHero(String name, String description, HeroType type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagestring() {
		return imagestring;
	}
	public void setImagestring(String imagestring) {
		this.imagestring = imagestring;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HeroType getType() {
		return type;
	}
	public void setType(HeroType type) {
		this.type = type;
	}

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
	public void addItemSpecifics(ItemInfoContainer box) {

		box.addEditButton("name", box, name, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setName(performstring);
			}

			
		});
		HeroType[] list= {HeroType.WARRIOR,HeroType.CLERIC,HeroType.MAGE,HeroType.SCOUT};
		box.addJComboBox("Type", list, new ActionTaker<HeroType>() {

			@Override
			public void perform(HeroType performstring) {
				// TODO Auto-generated method stub
				setType(performstring);
			}

			
		});
		box.addTextArea(box, description,new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setDescription(performstring);
			}

			
		});
		
	}
}
