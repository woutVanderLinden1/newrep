package SkillEditor;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import ItemEditor.ActionTaker;
import Shop.ItemShop.ImageHolder;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SavedSkill  implements Serializable, ImageHolder{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String skillname;
	private String skilldescript;
	private int skillpoints;
	private String imagestring;
	private SavedClass clas;
	
	public SavedSkill(String skillname, String skilldescript, int skillpoints,SavedClass clas) {
		super();
		this.clas=clas;
		this.skillname = skillname;
		this.skilldescript = skilldescript;
		this.skillpoints = skillpoints;
		
	}

	public String getSkillname() {
		return skillname;
	}
	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}
	public String getSkilldescript() {
		return skilldescript;
	}
	public void setSkilldescript(String skilldescript) {
		this.skilldescript = skilldescript;
	}
	public int getSkillpoints() {
		return skillpoints;
	}
	public void setSkillpoints(int skillpoints) {
		this.skillpoints = skillpoints;
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
	public String getImagestring() {
		// TODO Auto-generated method stub
		return imagestring;
	}
	
	public void addItemSpecifics(ItemInfoContainer box) {
		box.addEditButton("name", box, skillname, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setSkillname(performstring);
			}

			
		});
		box.addNumericEditButton("Experience cost", box, skillpoints, new ActionTaker<Integer>() {


			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setSkillpoints(value);
			}
			
		});
		box.addTextArea(box, skilldescript, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setSkilldescript(skilldescript);
			}

		
		});
		SavedSkill skil=this;
		box.addButton(this.skillname,"delete",new ActionTaker<ActionEvent>(){

			@Override
			public void perform(ActionEvent value) {
				clas.removeSkill(skil);
				
			}
			
		});
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return skillname;
	}
}
