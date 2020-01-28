package view.hero;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Hero.Hero;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class ViewHero extends ImageItem{

	
	public ViewHero(Hero hero) {
		super(hero);
	}
	public void reinitialise() {
	
		try {
			Hero hero=(Hero)item;
			System.out.println(path);
			path=hero.getImageString();
			
			setImage(ImageIO.read(new File(path)));
			if(image==null) {
				System.out.println("thepath " +path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
